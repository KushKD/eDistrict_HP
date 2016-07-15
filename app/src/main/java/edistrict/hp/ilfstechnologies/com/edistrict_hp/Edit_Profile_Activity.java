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
import Adapters.SpinnerAdapter_Question_One;
import Adapters.SpinnerAdapter_Question_Two;
import Adapters.SpinnerAdapter_Ward;
import Generic.Custom_Dialog;
import Generic.Generic_Async_Get;
import Generic.Generic_Async_Post;
import JsonManager.Registration_Json;
import Model.CencusBlock;
import Model.CencusDistrict;
import Model.CencusPanchayat;
import Model.CencusTehsil;
import Model.CencusVillage_Town_New;
import Model.Customer;
import Model.HimMuncipality;
import Model.Question_One;
import Model.Question_Two;
import Model.User;
import Enum.TaskType;
import Model.Ward;
import Utilities.AppStatus;
import Utilities.Econstants;

public class Edit_Profile_Activity extends Activity implements AsyncTaskListener {

    private User User_Data = null;
    private TextView user_profile_short_bio_tv, user_profile_name_tv;
    private EditText state_tv, address_tv, answerone_tv, answertwo_tv, gender_tv, dateofbirth_tv, aadhaar_tv, family_id_tv, mobile_number_tv, firstname_father_tv, middlename_father_tv, lastname_father_tv, firstname_tv, middlename_tv, lastname_tv;
    private Button Back_bt, update_bt;
    private ImageView edit_Profile_IV;
    private Spinner district_sp, tehsil_sp, village_sp, municipality_sp, ward_sp, block_sp, panchayat_sp, questionone_sp, questiontwo_sp;
    private LinearLayout village_ui, ward_ui, block_ui, panchayat_ui, municipality_ui, question_one_ui, question_two_ui;
    private String townId = null, villageId = null, districtID = null, tehsilID = null, blockID = null, panchayatID = null, wardID = null, municipalityID = null, question_one_spinner_value, question_two_spinner_value = null;


    protected List<CencusDistrict> Districts_Server = null;
    protected List<CencusTehsil> Tehsils_Server = null;
    protected List<CencusVillage_Town_New> Village_Town_New = null;
    protected List<Ward> Ward_Server = null;
    protected List<HimMuncipality> Municipality_Server = null;
    protected List<CencusBlock> Block_server = null;
    protected List<CencusPanchayat> Panchayat_Server = null;
    protected List<Question_One> Question_One_List = null;
    protected List<Question_Two> Question_Two_List = null;
    protected Map<String, String> Village_Town_Server = null;
    protected Map<String, String> Question_One_Server = null;
    protected Map<String, String> Question_Two_Server = null;


    private SpinAdapter_District adapter;
    private SpinAdapter_Tehsils adapter_tehsils;
    private SpinAdapter_Village_Town adapter_village_town;
    private SpinnerAdapter_Ward adapter_ward;
    private SpinnerAdapter_Municipality adapter_municipality;
    private SpinnerAdapter_Block adapter_block;
    private SpinnerAdapter_Panchayat adapter_panchayat;
    private SpinnerAdapter_Question_One adapter_question_one;
    private SpinnerAdapter_Question_Two adapter_question_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);
        try {
            Intent getRoomDetailsIntent = getIntent();
            User_Data = (User) getRoomDetailsIntent.getSerializableExtra("USER");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            InitializeView();
            setData_TextView();
            if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
                GetDaTaAsync();
                Get_Questions_One_Two();
            } else {
                Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please connect to Internet.");
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
        }
        Back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Profile_Activity.this.finish();
            }
        });

        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get Data and Update the Project
                UPDATE_OBJECT();
            }
        });

        questionone_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Question_One Mun = adapter_question_one.getItem(position);
                question_one_spinner_value = Mun.getQuestion_Value().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        questiontwo_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Question_Two Mun = adapter_question_two.getItem(position);
                question_two_spinner_value = Mun.getQuestion_Value().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        ward_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Ward ward = adapter_ward.getItem(position);
                wardID = ward.getId().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        municipality_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                HimMuncipality Mun = adapter_municipality.getItem(position);
                municipalityID = Mun.getId().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        panchayat_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusPanchayat CP = adapter_panchayat.getItem(position);
                panchayatID = CP.getId().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        block_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusBlock CB = adapter_block.getItem(position);
                blockID = CB.getId().toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });


        district_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusDistrict CD = adapter.getItem(position);
                if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
                    districtID = CD.getCencus().trim();
                    GetDaTaAsync_Tehsil(CD.getCencus().trim());
                } else {
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please connect to Internet.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        tehsil_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                CencusDistrict CD = adapter.getItem(position);
                CencusTehsil CT = adapter_tehsils.getItem(position);
                tehsilID = CT.getCencus().trim();
                if (ward_ui.getVisibility() == View.VISIBLE && municipality_ui.getVisibility() == View.VISIBLE) {
                    ward_ui.setVisibility(View.GONE);
                    municipality_ui.setVisibility(View.GONE);
                    wardID = null;
                    municipalityID = null;
                    townId = null;
                    villageId = null;
                }

                if (block_ui.getVisibility() == View.VISIBLE && panchayat_ui.getVisibility() == View.VISIBLE) {
                    block_ui.setVisibility(View.GONE);
                    panchayat_ui.setVisibility(View.GONE);
                    blockID = null;
                    panchayatID = null;
                    townId = null;
                    villageId = null;
                }

                if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
                    GetDaTaAsync_Village_Town(CD.getCencus().trim(), CT.getCencus().trim());
                } else {
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please Connect to Internet.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });

        village_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                // CencusDistrict CD = adapter.getItem(position);
                // CencusTehsil CT = adapter_tehsils.getItem(position);
                CencusVillage_Town_New CVTN = adapter_village_town.getItem(position);


                if (CVTN.getCode().toString().contains("T-")) {
                    townId = CVTN.getCode().toString().replace("T-", "").trim();
                    villageId = null;
                    if (block_ui.getVisibility() == View.VISIBLE && panchayat_ui.getVisibility() == View.VISIBLE) {
                        block_ui.setVisibility(View.GONE);
                        panchayat_ui.setVisibility(View.GONE);
                        blockID = null;
                        panchayatID = null;
                    }
                    Show_Municipality_Ward(townId);

                } else if (CVTN.getCode().toString().contains("V-")) {
                    villageId = CVTN.getCode().toString().replace("V-", "").trim();
                    townId = null;
                    if (ward_ui.getVisibility() == View.VISIBLE && municipality_ui.getVisibility() == View.VISIBLE) {
                        ward_ui.setVisibility(View.GONE);
                        municipality_ui.setVisibility(View.GONE);
                        wardID = null;
                        municipalityID = null;
                    }
                    Show_Block_Panchayat(villageId);

                } else if (CVTN.getCode().toString().equalsIgnoreCase("")) {


                } else {
                    Toast.makeText(Edit_Profile_Activity.this, "Something went wrong. Please retry again.", Toast.LENGTH_SHORT).show();

                }

                if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
                    //  GetDaTaAsync_Village_Town(CD.getCencus().trim(), CT.getCencus().trim());
                } else {
                    Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please connect to Internet.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
            }
        });
    }

    private void UPDATE_OBJECT() {

        //name customer
        Customer customer_post = new Customer();
        customer_post.setFirstName(firstname_tv.getText().toString().trim());
        //  Log.e("first name",firstname_tv.getText().toString().trim());

        if (middlename_tv.getText().toString() != null && !middlename_tv.getText().toString().isEmpty()) {
            customer_post.setMiddleName(middlename_tv.getText().toString().trim());
            //    Log.e("Middle Name name", middlename_tv.getText().toString().trim());
        } else {
            customer_post.setMiddleName("");
            //  Log.e("Middle Name name", customer_post.getMiddleName());
        }

        customer_post.setLastName(lastname_tv.getText().toString().trim());
        //Log.e("last name",lastname_tv.getText().toString().trim());

        //Fathers name
        customer_post.setFathersFirstName(firstname_father_tv.getText().toString().trim());
        //Log.e("first name father",firstname_father_tv.getText().toString().trim());
        customer_post.setFathersMiddleName(middlename_father_tv.getText().toString().trim());
        // Log.e("middle name father",middlename_father_tv.getText().toString().trim());
        customer_post.setFathersLastName(lastname_father_tv.getText().toString().trim());
        // Log.e("last name father",lastname_father_tv.getText().toString().trim());

        //Date Of Birth
        customer_post.setDateOfBirth(dateofbirth_tv.getText().toString().trim());
        //Log.e("DOB",dateofbirth_tv.getText().toString().trim());
        customer_post.setAadhaarNo(aadhaar_tv.getText().toString().trim());
        //Log.e("Aadhaar",aadhaar_tv.getText().toString().trim());

        customer_post.setGender(gender_tv.getText().toString().trim());
        //Log.e("Gender",gender_tv.getText().toString().trim());
        customer_post.setMobileNumber(mobile_number_tv.getText().toString().trim());
        //Log.e("Mobile number",mobile_number_tv.getText().toString().trim());

        //Address Details
        customer_post.setState("2");
        //Log.e("state",customer_post.getState());
        customer_post.setDistrict(districtID.trim());
        //Log.e("Distrct",districtID.toString().trim());
        customer_post.setTehsil(tehsilID.trim());
        //Log.e("Tehsil",tehsilID.toString().trim());
        if (villageId != null && !villageId.isEmpty()) {
            customer_post.setVillage(villageId);
            //  Log.e("village", villageId.toString().trim());
        } else {
            customer_post.setVillage("");
            //Log.e("village", customer_post.getVillage());
        }
        if (panchayatID != null && !panchayatID.isEmpty()) {
            customer_post.setPanchayat(panchayatID);
            //Log.e("Panchayat",panchayatID);
        } else {
            customer_post.setPanchayat("");
            //   Log.e("Panchayat",customer_post.getPanchayat());
        }

        if (blockID != null && !blockID.isEmpty()) {
            customer_post.setBlock(blockID.trim());
            //Log.e("Block",blockID.toString().trim());
        } else {
            customer_post.setBlock("");
            //   Log.e("Block",customer_post.getBlock());
        }
        if (townId != null && !townId.isEmpty()) {
            customer_post.setTown(townId.trim());
            // Log.e("Town", townId);
        } else {
            customer_post.setTown("");
            // Log.e("Town", customer_post.getTown());
        }
        if (municipalityID != null && !municipalityID.isEmpty()) {
            customer_post.setMuncipality(municipalityID);
            // Log.e("Municipality", municipalityID);
        } else {
            customer_post.setMuncipality("");
            // Log.e("Municipality",  customer_post.getMuncipality());

        }

        if (wardID != null && !wardID.isEmpty()) {
            customer_post.setWard(wardID);
            // Log.e("Ward", customer_post.getWard());
        } else {
            customer_post.setWard("");
            // Log.e("Ward", customer_post.getWard());
        }

        if (family_id_tv.getText().toString() != null && !family_id_tv.getText().toString().isEmpty()) {
            customer_post.setFamilyId(family_id_tv.getText().toString().trim());
            // Log.e("family id ",family_id_tv.getText().toString().trim());
        } else {
            customer_post.setFamilyId("");
            //   Log.e("family id ",customer_post.getFamilyId());
        }

        if (wardID != null && !wardID.isEmpty()) {
            customer_post.setWard(wardID);
            // Log.e("Ward", customer_post.getWard());
        } else {
            customer_post.setWard("");
            // Log.e("Ward", customer_post.getWard());
        }
        customer_post.setAddress("");
        //Log.e("address",customer_post.getAddress().toString().trim());

        //Security Details
        customer_post.sethQueId1(question_one_spinner_value);
        //Log.e("Question One",question_one_spinner_value);
        customer_post.sethQueId2(question_two_spinner_value);
        //Log.e("Question Two",question_two_spinner_value);
        customer_post.setHintAns1(answerone_tv.getText().toString().trim());
        //Log.e("Answer One",customer_post.getHintAns1().toString().trim());
        customer_post.setHintAns2(answertwo_tv.getText().toString().trim());
        //Log.e("Answer Two",customer_post.getHintAns2().toString().trim());

        //Others Left Out
        customer_post.setLoginId(User_Data.getLoginId().toString().trim());
        //Log.e("Login ID",customer_post.getLoginId());

        customer_post.setEmailId(user_profile_short_bio_tv.getText().toString().trim());
        //Log.e("Email ID",customer_post.getEmailId());

        customer_post.setPhotoProofData("");
        //Log.e("Photo Proff Data",customer_post.getPhotoProofData());
        customer_post.setPhotoProofFileName("");
        //Log.e("Photo File Name",customer_post.getPhotoProofFileName());
        customer_post.setPhotoProofMimeType("");
        //Log.e("Photo Proff MIME data",customer_post.getPhotoProofMimeType());

        if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {

            new Generic_Async_Post(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.POST_CUSTOMER).execute(customer_post);

        }
    }

    private void Get_Questions_One_Two() {

        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getQuestionSetFirst/");

        Log.d("Question One", SB.toString());
        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.QUESTION_ONE).execute(SB.toString());

        StringBuilder SB_Two = null;
        SB_Two = new StringBuilder();
        SB_Two.append(Econstants.URL_MAIN);
        SB_Two.append("getQuestionSetSecond/");

        Log.d("Question Second", SB_Two.toString());
        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.QUESTION_TWO).execute(SB_Two.toString());

    }

    private void Show_Block_Panchayat(String villageId) {
        if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
            GetDataAsync_Block(villageId);
            GetDataAsync_Panchayat(villageId);

        } else {
            Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please Connect to Internet.");
        }
    }

    private void GetDataAsync_Panchayat(String villageId) {
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getPanchayats/");
        SB.append(villageId);

        Log.d("Panchayat", SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.PANCHAYAT).execute(SB.toString());
    }

    private void GetDataAsync_Block(String villageId) {

        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getBlocks/");
        SB.append(villageId);

        Log.d("Block", SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.BLOCK).execute(SB.toString());
    }

    private void Show_Municipality_Ward(String townId) {

        if (AppStatus.getInstance(Edit_Profile_Activity.this).isOnline()) {
            GetDataAsync_Ward(townId);
            GetDataAsync_Municipality(townId);

        } else {
            Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please Connect to Internet.");
        }


    }

    private void GetDataAsync_Municipality(String townId) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getMunicipalities/");
        SB.append(townId);

        Log.d("Municipality", SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.MUNICIPALITY).execute(SB.toString());
    }


    private void GetDataAsync_Ward(String townId) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getWards/");
        SB.append(townId);

        Log.d("Wards", SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.WARD).execute(SB.toString());
    }

    private void GetDaTaAsync_Village_Town(String Cencus_District, String Cencus_Tehsil) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getTownVillage/");
        SB.append(Cencus_Tehsil);
        SB.append("/");
        SB.append(Cencus_District);

        Log.d("Village/Town", SB.toString());

        new Generic_Async_Get(Edit_Profile_Activity.this, Edit_Profile_Activity.this, TaskType.CENCUS_VILLAGE_TOWN).execute(SB.toString());


    }

    private void GetDaTaAsync_Tehsil(String trim) {
        //CENCUS_TEHSIL
        StringBuilder SB = null;
        SB = new StringBuilder();
        SB.append(Econstants.URL_MAIN);
        SB.append("getTehsils/");
        SB.append(trim);
        Log.d("Tehsils", SB.toString());

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
        answerone_tv.setText(String.valueOf(User_Data.getHintAns1()));
        answertwo_tv.setText(String.valueOf(User_Data.getHintAns2()));
    }

    private void InitializeView() {

        user_profile_name_tv = (TextView) findViewById(R.id.user_profile_name);
        user_profile_short_bio_tv = (TextView) findViewById(R.id.user_profile_short_bio);
        firstname_tv = (EditText) findViewById(R.id.firstname);
        middlename_tv = (EditText) findViewById(R.id.middlename);
        lastname_tv = (EditText) findViewById(R.id.lastname);
        firstname_father_tv = (EditText) findViewById(R.id.firstname_father);
        middlename_father_tv = (EditText) findViewById(R.id.middlename_father);
        lastname_father_tv = (EditText) findViewById(R.id.lastname_father);
        gender_tv = (EditText) findViewById(R.id.gender);
        dateofbirth_tv = (EditText) findViewById(R.id.dateofbirth);
        aadhaar_tv = (EditText) findViewById(R.id.aadhaarno);
        family_id_tv = (EditText) findViewById(R.id.familyid);
        mobile_number_tv = (EditText) findViewById(R.id.mobilenumber);
        state_tv = (EditText) findViewById(R.id.state);
        district_sp = (Spinner) findViewById(R.id.district);
        tehsil_sp = (Spinner) findViewById(R.id.tehsil);
        village_sp = (Spinner) findViewById(R.id.village);
        municipality_sp = (Spinner) findViewById(R.id.municipality);
        block_sp = (Spinner) findViewById(R.id.block);
        panchayat_sp = (Spinner) findViewById(R.id.panchayat);
        ward_sp = (Spinner) findViewById(R.id.ward);
        address_tv = (EditText) findViewById(R.id.address);
        questionone_sp = (Spinner) findViewById(R.id.questionone);
        questiontwo_sp = (Spinner) findViewById(R.id.questiontwo);
        answerone_tv = (EditText) findViewById(R.id.answerone);
        answertwo_tv = (EditText) findViewById(R.id.answertwo);
        Back_bt = (Button) findViewById(R.id.back);
        update_bt = (Button) findViewById(R.id.update);
        edit_Profile_IV = (ImageView) findViewById(R.id.edit_Profile);
        village_ui = (LinearLayout) findViewById(R.id.village_ui);
        block_ui = (LinearLayout) findViewById(R.id.block_ui);
        ward_ui = (LinearLayout) findViewById(R.id.ward_ui);
        panchayat_ui = (LinearLayout) findViewById(R.id.panchayat_ui);
        municipality_ui = (LinearLayout) findViewById(R.id.municipality_ui);
        question_one_ui = (LinearLayout) findViewById(R.id.question_one_ui);
        question_two_ui = (LinearLayout) findViewById(R.id.question_two_ui);
    }


    @Override
    public void onTaskCompleted(String result, TaskType taskType) throws IOException {

        if (taskType == TaskType.CENCUS_DISTRICT) {

            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Districts_Server = mapper.readValue(result, new TypeReference<List<CencusDistrict>>() {
            });
            Log.e("Length", Integer.toString(Districts_Server.size()));
            adapter = new SpinAdapter_District(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Districts_Server);
            district_sp.setAdapter(adapter);

        } else if (taskType == TaskType.CENCUS_TEHSIL) {
            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Tehsils_Server = mapper.readValue(result, new TypeReference<List<CencusTehsil>>() {
            });
            Log.e("Length", Integer.toString(Tehsils_Server.size()));
            adapter_tehsils = new SpinAdapter_Tehsils(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Tehsils_Server);
            tehsil_sp.setAdapter(adapter_tehsils);
        } else if (taskType == TaskType.CENCUS_VILLAGE_TOWN) {
            Log.e("Data", result);


            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Village_Town_Server = new HashMap<String, String>();
            Village_Town_Server = mapper.readValue(result, new TypeReference<Map<String, String>>() {
            });

            if (Village_Town_Server.size() == 0) {
                village_ui.setVisibility(View.GONE);
            } else {
                Village_Town_New = new ArrayList<>();
                CencusVillage_Town_New CVTN = null;
                for (Map.Entry<String, String> entry : Village_Town_Server.entrySet()) {
                    CVTN = new CencusVillage_Town_New();
                    CVTN.setCode(entry.getKey());
                    CVTN.setName(entry.getValue());
                    // System.out.println(entry.getKey() + "/" + entry.getValue());
                    Village_Town_New.add(CVTN);
                }
                adapter_village_town = new SpinAdapter_Village_Town(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Village_Town_New);
                village_sp.setAdapter(adapter_village_town);
                village_ui.setVisibility(View.VISIBLE);
            }
        } else if (taskType == TaskType.WARD) {

            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Ward_Server = mapper.readValue(result, new TypeReference<List<Ward>>() {
            });
            Log.e("Length", Integer.toString(Ward_Server.size()));
            adapter_ward = new SpinnerAdapter_Ward(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Ward_Server);
            ward_sp.setAdapter(adapter_ward);
            ward_ui.setVisibility(View.VISIBLE);


        } else if (taskType == TaskType.MUNICIPALITY) {
            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Municipality_Server = mapper.readValue(result, new TypeReference<List<HimMuncipality>>() {
            });
            Log.e("Length", Integer.toString(Municipality_Server.size()));
            adapter_municipality = new SpinnerAdapter_Municipality(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Municipality_Server);
            municipality_sp.setAdapter(adapter_municipality);
            municipality_ui.setVisibility(View.VISIBLE);

        } else if (taskType == TaskType.BLOCK) {
            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Block_server = mapper.readValue(result, new TypeReference<List<CencusBlock>>() {
            });
            Log.e("Length", Integer.toString(Block_server.size()));
            adapter_block = new SpinnerAdapter_Block(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Block_server);
            block_sp.setAdapter(adapter_block);
            block_ui.setVisibility(View.VISIBLE);

        } else if (taskType == TaskType.PANCHAYAT) {
            Log.e("Data", result);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Panchayat_Server = mapper.readValue(result, new TypeReference<List<CencusPanchayat>>() {
            });
            Log.e("Length", Integer.toString(Panchayat_Server.size()));
            adapter_panchayat = new SpinnerAdapter_Panchayat(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Panchayat_Server);
            panchayat_sp.setAdapter(adapter_panchayat);
            panchayat_ui.setVisibility(View.VISIBLE);

        } else if (taskType == TaskType.QUESTION_ONE) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Question_One_Server = new HashMap<String, String>();
            Question_One_Server = mapper.readValue(result, new TypeReference<Map<String, String>>() {
            });

            if (Question_One_Server.size() == 0) {
                question_one_ui.setVisibility(View.GONE);
            } else {
                Question_One_List = new ArrayList<>();
                Question_One Q_one = null;
                for (Map.Entry<String, String> entry : Question_One_Server.entrySet()) {
                    Q_one = new Question_One();
                    Q_one.setQuestion_Key(entry.getKey());
                    Q_one.setQuestion_Value(entry.getValue());
                    // System.out.println(entry.getKey() + "/" + entry.getValue());
                    Question_One_List.add(Q_one);
                }
                adapter_question_one = new SpinnerAdapter_Question_One(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Question_One_List);
                questionone_sp.setAdapter(adapter_question_one);
            }
        } else if (taskType == TaskType.QUESTION_TWO) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Question_Two_Server = new HashMap<String, String>();
            Question_Two_Server = mapper.readValue(result, new TypeReference<Map<String, String>>() {
            });

            if (Question_Two_Server.size() == 0) {
                question_two_ui.setVisibility(View.GONE);
            } else {
                Question_Two_List = new ArrayList<>();
                Question_Two Q_Two = null;
                for (Map.Entry<String, String> entry : Question_Two_Server.entrySet()) {
                    Q_Two = new Question_Two();
                    Q_Two.setQuestion_Key(entry.getKey());
                    Q_Two.setQuestion_Value(entry.getValue());
                    // System.out.println(entry.getKey() + "/" + entry.getValue());
                    Question_Two_List.add(Q_Two);
                }
                adapter_question_two = new SpinnerAdapter_Question_Two(Edit_Profile_Activity.this, android.R.layout.simple_spinner_item, Question_Two_List);
                questiontwo_sp.setAdapter(adapter_question_two);
            }

        } else {
            Custom_Dialog.showDialog(Edit_Profile_Activity.this, "Please Restart the application.");
        }


    }


    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

        if (taskType == TaskType.POST_CUSTOMER) {
            Log.e("Data", result);

            String Result_to_Show = Registration_Json.Update_User_Message(result);
                Custom_Dialog.showDialog(Edit_Profile_Activity.this, Result_to_Show);


        }
    }
}