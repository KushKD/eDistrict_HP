package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Abstract.AsyncTaskListener;
import Adapters.SpinAdapter_District;
import Adapters.SpinAdapter_Tehsils;
import Adapters.SpinAdapter_Village_Town;
import Generic.Custom_Dialog;
import Generic.Generic_Async_Get;
import Model.CencusDistrict;
import Model.CencusTehsil;
import Model.CencusVillage_Town_New;
import Model.User;
import Enum.TaskType;
import Utilities.AppStatus;
import Utilities.Econstants;

public class Edit_Profile_Activity extends Activity implements AsyncTaskListener {

    User User_Data = null;
    TextView user_profile_name_tv;
    TextView user_profile_short_bio_tv;
    EditText firstname_tv,middlename_tv,lastname_tv;
    EditText firstname_father_tv, middlename_father_tv, lastname_father_tv;
    EditText gender_tv, dateofbirth_tv , aadhaar_tv, family_id_tv,mobile_number_tv;

    EditText state_tv,village_tv,city_tv,block_tv,panchayat_tv,town_tv,ward_tv,address_tv;
    EditText questionone_tv,questiontwo_tv,answerone_tv,answertwo_tv;
    Button Back_bt, update_bt;
    ImageView edit_Profile_IV;

    Spinner district_sp,tehsil_sp,village_sp;

    LinearLayout village_ui,ward_ui,block_ui,panchayat_ui;


    protected List<CencusDistrict> Districts_Server = null;
    protected List<CencusTehsil> Tehsils_Server = null;
    protected List<CencusVillage_Town_New> Village_Town_New = null;
    protected ArrayList values = null;
    protected Map<String,String> Village_Town_Server = null;
    private SpinAdapter_District adapter;
    private SpinAdapter_Tehsils adapter_tehsils;
    private SpinAdapter_Village_Town adapter_village_town;


   // CencusDistrict C_District = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
        try {
            Intent getRoomDetailsIntent = getIntent();
            User_Data = (User) getRoomDetailsIntent.getSerializableExtra("USER");
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString() ,Toast.LENGTH_SHORT).show();
        }

        try{
            InitializeView();
            setData_TextView();
            if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) GetDaTaAsync(); else
                Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please connect to Internet.");



        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
        }
        Back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Profile_Activity.this.finish();
            }
        });

        district_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                CencusDistrict CD = adapter.getItem(position);
                // Here you can do the action you want to...
               // Toast.makeText(Edit_Profile_Activity.this, "Cencus: " + CD.getCencus().trim() + "\nName: " + CD.getName(), Toast.LENGTH_SHORT).show();

                if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) GetDaTaAsync_Tehsil(CD.getCencus().trim());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        tehsil_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // Here you get the current item (a User object) that is selected by its position
                CencusDistrict CD = adapter.getItem(position);
                CencusTehsil CT = adapter_tehsils.getItem(position);
                // Here you can do the action you want to...
               // Toast.makeText(Edit_Profile_Activity.this, "Cencus: " + CD.getCencus().trim() + "\nName: " + CD.getName(), Toast.LENGTH_SHORT).show();

                if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) GetDaTaAsync_Village_Town(CD.getCencus().trim(),CT.getCencus().trim());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        village_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                CencusDistrict CD = adapter.getItem(position);
                CencusTehsil CT = adapter_tehsils.getItem(position);
                CencusVillage_Town_New CVTN = adapter_village_town.getItem(position);
                Toast.makeText(Edit_Profile_Activity.this,  CVTN.getCode().trim() + "\n Name: " + CVTN.getName().trim(), Toast.LENGTH_SHORT).show();

                if(CVTN.getCode().toString().contains("T-")){
                    String townId = CVTN.getCode().toString().replace("T-","").trim();
                    //Show municipality and ward
                    //Econstants.URL_MAIN/getWards/townId
                    //Econstants.URL_MAIN/getMunicipalities/townId

                }else if(CVTN.getCode().toString().contains("V-")){
                    String villageId =  CVTN.getCode().toString().replace("V-","").trim();
                    //Show Block and Panchayat

                    //Econstants.URL_MAIN/getPanchayats/villageId
                    //Econstants.URL_MAIN/getBlocks/villageId

                }else if(CVTN.getCode().toString().equalsIgnoreCase("")){


                }else{
                    Toast.makeText(Edit_Profile_Activity.this, "Something went wrong. Please retry again.", Toast.LENGTH_SHORT).show();

                }

                if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
                  //  GetDaTaAsync_Village_Town(CD.getCencus().trim(), CT.getCencus().trim());
                }else{
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please connect to Internet.");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });
    }

    private void GetDaTaAsync_Village_Town(String Cencus_District ,String Cencus_Tehsil) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getTownVillage/");
        SB.append(Cencus_Tehsil);
        SB.append("/");
        SB.append(Cencus_District);

        Log.d("STRING BUIFDET",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.CENCUS_VILLAGE_TOWN).execute(SB.toString());


    }

    private void GetDaTaAsync_Tehsil(String trim) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getTehsils/");
        SB.append(trim);
        Log.d("STRING BUIFDET",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.CENCUS_TEHSIL).execute(SB.toString());

    }

    private void GetDaTaAsync() {

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.CENCUS_DISTRICT).execute(Econstants.URL_CENCES_DISTRICTS);

    }

    private void setData_TextView() {
        user_profile_name_tv.setText(String.valueOf(User_Data.getFirstName()));
        user_profile_short_bio_tv.setText(String.valueOf(User_Data.getEmailId()));
        firstname_tv.setText(String.valueOf(User_Data.getFirstName()));
        middlename_tv.setText(String.valueOf(User_Data.getMiddleName()));
        lastname_tv.setText(String.valueOf(User_Data.getLastName()));
        firstname_father_tv.setText(String.valueOf(User_Data.getFathersFirstName()));
        middlename_father_tv.setText(String.valueOf(User_Data.getFathersMiddleName()));
        lastname_father_tv.setText(String.valueOf(User_Data.getFathersLastName()));
        gender_tv.setText(String.valueOf(User_Data.getGender()));
        dateofbirth_tv.setText(String.valueOf(User_Data.getDateOfBirth()));
        aadhaar_tv.setText(String.valueOf(User_Data.getAadhaarNo()));
        family_id_tv.setText(String.valueOf(User_Data.getFamilyId()));
        mobile_number_tv.setText(String.valueOf(User_Data.getMobileNumber()));
        state_tv.setText("Himachal Pradesh");
        block_tv.setText(String.valueOf(User_Data.getBlock()));
        panchayat_tv.setText(String.valueOf(User_Data.getPanchayat()));
        ward_tv.setText(String.valueOf(User_Data.getWard().getName()));
        address_tv.setText(String.valueOf(User_Data.getAddress()));
        questionone_tv.setText(String.valueOf(User_Data.gethQueId1()));
        questiontwo_tv.setText(String.valueOf(User_Data.gethQueId2()));
        answerone_tv.setText(String.valueOf(User_Data.getHintAns1()));
        answertwo_tv.setText(String.valueOf(User_Data.getHintAns2()));
    }

    private void InitializeView() {

        user_profile_name_tv= (TextView)findViewById(R.id.user_profile_name);
        user_profile_short_bio_tv = (TextView)findViewById(R.id.user_profile_short_bio);
        firstname_tv= (EditText)findViewById(R.id.firstname);
        middlename_tv = (EditText)findViewById(R.id.middlename);
        lastname_tv= (EditText)findViewById(R.id.lastname);
        firstname_father_tv= (EditText)findViewById(R.id.firstname_father);
        middlename_father_tv= (EditText)findViewById(R.id.middlename_father);
        lastname_father_tv= (EditText)findViewById(R.id.lastname_father);
        gender_tv= (EditText)findViewById(R.id.gender);
        dateofbirth_tv= (EditText)findViewById(R.id.dateofbirth);
        aadhaar_tv= (EditText)findViewById(R.id.aadhaarno);
        family_id_tv = (EditText)findViewById(R.id.familyid);
        mobile_number_tv = (EditText)findViewById(R.id.mobilenumber);
        state_tv = (EditText)findViewById(R.id.state);
        district_sp = (Spinner)findViewById(R.id.district);
        tehsil_sp = (Spinner)findViewById(R.id.tehsil);
        village_sp = (Spinner)findViewById(R.id.village);
        block_tv = (EditText)findViewById(R.id.block);
        panchayat_tv = (EditText)findViewById(R.id.panchayat);
        ward_tv = (EditText)findViewById(R.id.ward);
        address_tv = (EditText)findViewById(R.id.address);
        questionone_tv = (EditText)findViewById(R.id.questionone);
        questiontwo_tv = (EditText)findViewById(R.id.questiontwo);
        answerone_tv = (EditText)findViewById(R.id.answerone);
        answertwo_tv = (EditText)findViewById(R.id.answertwo);
        Back_bt = (Button)findViewById(R.id.back);
        edit_Profile_IV = (ImageView)findViewById(R.id.edit_Profile);
        village_ui = (LinearLayout)findViewById(R.id.village_ui);
        block_ui = (LinearLayout)findViewById(R.id.block_ui);
        ward_ui = (LinearLayout)findViewById(R.id.ward_ui);
        panchayat_ui = (LinearLayout)findViewById(R.id.panchayat_ui);
    }


    @Override
    public void onTaskCompleted(String result, TaskType taskType) throws IOException {

             if(taskType == TaskType.CENCUS_DISTRICT){

                 Log.e("Data",result);
                 ObjectMapper mapper=new ObjectMapper();
                 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                 Districts_Server = mapper.readValue(result, new TypeReference<List<CencusDistrict>>(){});
                 Log.e("Length",Integer.toString(Districts_Server.size()));
                 adapter = new SpinAdapter_District(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Districts_Server);
                 district_sp.setAdapter(adapter);

             }else if(taskType == TaskType.CENCUS_TEHSIL){
                 Log.e("Data",result);
                 ObjectMapper mapper=new ObjectMapper();
                 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                 Tehsils_Server = mapper.readValue(result, new TypeReference<List<CencusTehsil>>(){});
                 Log.e("Length",Integer.toString(Tehsils_Server.size()));
                 adapter_tehsils = new SpinAdapter_Tehsils(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Tehsils_Server);
                 tehsil_sp.setAdapter(adapter_tehsils);
             }else if(taskType == TaskType.CENCUS_VILLAGE_TOWN){
                 Log.e("Data",result);


                     ObjectMapper mapper = new ObjectMapper();
                     mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                     Village_Town_Server = new HashMap<String, String>();
                     Village_Town_Server = mapper.readValue(result, new TypeReference<Map<String, String>>() {});

                       if(Village_Town_Server.size()==0){
                            village_ui.setVisibility(View.GONE);
                       }else{
                           Village_Town_New = new ArrayList<>();
                           CencusVillage_Town_New CVTN = null;
                           for (Map.Entry<String, String> entry : Village_Town_Server.entrySet()) {
                               CVTN = new CencusVillage_Town_New();
                               CVTN.setCode(entry.getKey());
                               CVTN.setName(entry.getValue());
                               System.out.println(entry.getKey() + "/" + entry.getValue());
                               Village_Town_New.add(CVTN);
                           }
                           adapter_village_town = new SpinAdapter_Village_Town(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Village_Town_New);
                           village_sp.setAdapter(adapter_village_town);
                           village_ui.setVisibility(View.VISIBLE);
                       }
                 }



             }



    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

    }
}
