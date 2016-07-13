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
import Adapters.SpinnerAdapter_Block;
import Adapters.SpinnerAdapter_Municipality;
import Adapters.SpinnerAdapter_Panchayat;
import Adapters.SpinnerAdapter_Ward;
import Generic.Custom_Dialog;
import Generic.Generic_Async_Get;
import Model.CencusBlock;
import Model.CencusDistrict;
import Model.CencusPanchayat;
import Model.CencusTehsil;
import Model.CencusVillage_Town_New;
import Model.HimMuncipality;
import Model.User;
import Enum.TaskType;
import Model.Ward;
import Utilities.AppStatus;
import Utilities.Econstants;

public class Edit_Profile_Activity extends Activity implements AsyncTaskListener {

    User User_Data = null;
    TextView user_profile_name_tv;
    TextView user_profile_short_bio_tv;
    EditText firstname_tv,middlename_tv,lastname_tv;
    EditText firstname_father_tv, middlename_father_tv, lastname_father_tv;
    EditText gender_tv, dateofbirth_tv , aadhaar_tv, family_id_tv,mobile_number_tv;

    EditText state_tv,village_tv,city_tv,town_tv,ward_tv,address_tv;
    EditText questionone_tv,questiontwo_tv,answerone_tv,answertwo_tv;
    Button Back_bt, update_bt;
    ImageView edit_Profile_IV;

    Spinner district_sp,tehsil_sp,village_sp,municipality_sp,ward_sp,block_sp,panchayat_sp;

    LinearLayout village_ui,ward_ui,block_ui,panchayat_ui,municipality_ui;
    String townId = null;
    String villageId = null;


    protected List<CencusDistrict> Districts_Server = null;
    protected List<CencusTehsil> Tehsils_Server = null;
    protected List<CencusVillage_Town_New> Village_Town_New = null;
    protected List<Ward> Ward_Server = null;
    protected List<HimMuncipality> Municipality_Server = null;
    protected List<CencusBlock> Block_server = null;
    protected List<CencusPanchayat> Panchayat_Server = null;
    protected ArrayList values = null;
    protected Map<String,String> Village_Town_Server = null;

    private SpinAdapter_District adapter;
    private SpinAdapter_Tehsils adapter_tehsils;
    private SpinAdapter_Village_Town adapter_village_town;
    private SpinnerAdapter_Ward adapter_ward;
    private SpinnerAdapter_Municipality adapter_municipality;
    private SpinnerAdapter_Block adapter_block;
    private SpinnerAdapter_Panchayat adapter_panchayat;



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
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusDistrict CD = adapter.getItem(position);
                if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()){
                    GetDaTaAsync_Tehsil(CD.getCencus().trim());
                }else{
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please connect to Internet.");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapter) {  }
        });

        tehsil_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusDistrict CD = adapter.getItem(position);
                CencusTehsil CT = adapter_tehsils.getItem(position);

                if(ward_ui.getVisibility()== View.VISIBLE && municipality_ui.getVisibility() == View.VISIBLE){
                    ward_ui.setVisibility(View.GONE);
                    municipality_ui.setVisibility(View.GONE);
                }

                if(block_ui.getVisibility()== View.VISIBLE && panchayat_ui.getVisibility() == View.VISIBLE){
                    block_ui.setVisibility(View.GONE);
                    panchayat_ui.setVisibility(View.GONE);
                }

                if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()){
                    GetDaTaAsync_Village_Town(CD.getCencus().trim(),CT.getCencus().trim());
                }else{
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please Connect to Internet.");
                }
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


                if(CVTN.getCode().toString().contains("T-")){
                     townId = CVTN.getCode().toString().replace("T-","").trim();
                    if(block_ui.getVisibility()== View.VISIBLE && panchayat_ui.getVisibility() == View.VISIBLE){
                        block_ui.setVisibility(View.GONE);
                        panchayat_ui.setVisibility(View.GONE);
                    }
                    Show_Municipality_Ward(townId);

                }else if(CVTN.getCode().toString().contains("V-")){
                     villageId =  CVTN.getCode().toString().replace("V-","").trim();
                    if(ward_ui.getVisibility()== View.VISIBLE && municipality_ui.getVisibility() == View.VISIBLE){
                        ward_ui.setVisibility(View.GONE);
                        municipality_ui.setVisibility(View.GONE);
                    }
                    Show_Block_Panchayat(villageId);

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

    private void Show_Block_Panchayat(String villageId) {
        if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()){
            GetDataAsync_Block(villageId);
            GetDataAsync_Panchayat(villageId);

        }else{
            Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please Connect to Internet.");
        }
    }

    private void GetDataAsync_Panchayat(String villageId) {
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getPanchayats/");
        SB.append(villageId);

        Log.d("Panchayat",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.PANCHAYAT).execute(SB.toString());
    }

    private void GetDataAsync_Block(String villageId) {

        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getBlocks/");
        SB.append(villageId);

        Log.d("Block",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.BLOCK).execute(SB.toString());
    }

    private void Show_Municipality_Ward(String townId) {

        if(AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()){
            GetDataAsync_Ward(townId);
            GetDataAsync_Municipality(townId);

        }else{
            Custom_Dialog.showDialog(Edit_Profile_Activity.this,"Please Connect to Internet.");
        }


    }

    private void GetDataAsync_Municipality(String townId) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getMunicipalities/");
        SB.append(townId);

        Log.d("Municipality",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.MUNICIPALITY).execute(SB.toString());
    }


    private void GetDataAsync_Ward(String townId) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getWards/");
        SB.append(townId);

        Log.d("Wards",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.WARD).execute(SB.toString());
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

        Log.d("Village/Town",SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.CENCUS_VILLAGE_TOWN).execute(SB.toString());


    }

    private void GetDaTaAsync_Tehsil(String trim) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getTehsils/");
        SB.append(trim);
        Log.d("Tehsils",SB.toString());

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
        municipality_sp = (Spinner)findViewById(R.id.municipality);
        block_sp = (Spinner)findViewById(R.id.block);
        panchayat_sp = (Spinner)findViewById(R.id.panchayat);
        ward_sp = (Spinner)findViewById(R.id.ward);
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
        municipality_ui = (LinearLayout)findViewById(R.id.municipality_ui);
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
                 }else if(taskType == TaskType.WARD) {

                 Log.e("Data",result);
                 ObjectMapper mapper=new ObjectMapper();
                 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                 Ward_Server = mapper.readValue(result, new TypeReference<List<Ward>>(){});
                 Log.e("Length",Integer.toString(Ward_Server.size()));
                 adapter_ward = new SpinnerAdapter_Ward(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Ward_Server);
                 ward_sp.setAdapter(adapter_ward);
                 ward_ui.setVisibility(View.VISIBLE);


             }else if(taskType == TaskType.MUNICIPALITY){
                 Log.e("Data",result);
                  ObjectMapper mapper=new ObjectMapper();
                  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                   Municipality_Server = mapper.readValue(result, new TypeReference<List<HimMuncipality>>(){});
                   Log.e("Length",Integer.toString(Municipality_Server.size()));
                 adapter_municipality = new SpinnerAdapter_Municipality(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Municipality_Server);
                 municipality_sp.setAdapter(adapter_municipality);
                 municipality_ui.setVisibility(View.VISIBLE);

             }else if(taskType == TaskType.BLOCK){
                 Log.e("Data",result);
                   ObjectMapper mapper=new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                   Block_server = mapper.readValue(result, new TypeReference<List<CencusBlock>>(){});
                   Log.e("Length",Integer.toString(Block_server.size()));
                   adapter_block = new SpinnerAdapter_Block(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Block_server);
                   block_sp.setAdapter(adapter_block);
                 block_ui.setVisibility(View.VISIBLE);

             }else if(taskType == TaskType.PANCHAYAT){
                 Log.e("Data",result);
                   ObjectMapper mapper=new ObjectMapper();
                   mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                   Panchayat_Server = mapper.readValue(result, new TypeReference<List<CencusPanchayat>>(){});
                   Log.e("Length",Integer.toString(Panchayat_Server.size()));
                   adapter_panchayat = new SpinnerAdapter_Panchayat(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Panchayat_Server);
                   panchayat_sp.setAdapter(adapter_panchayat);
                 panchayat_ui.setVisibility(View.VISIBLE);

             }



             }



    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

    }
}
