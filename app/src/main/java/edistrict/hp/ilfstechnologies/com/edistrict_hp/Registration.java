package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import Abstract.AsyncTaskListener;
import Generic.Custom_Dialog;
import Model.User;
import Utilities.AppStatus;
import Utilities.Econstants;
import Utilities.helper_Functions;
import Enum.TaskType;
import Generic.Generic_Async_Get;
import JsonManager.Registration_Json;

public class Registration extends Activity implements AsyncTaskListener {


    EditText  et_name , et_mobile , et_email;
    Button bt_reset, bt_register;
    User UP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        et_name = (EditText)findViewById(R.id.etname);
        et_mobile = (EditText)findViewById(R.id.etmobile);
        et_email = (EditText)findViewById(R.id.etemail);
        bt_reset = (Button)findViewById(R.id.back);
        bt_register = (Button)findViewById(R.id.register);



        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
                Registration.this.finish();
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = et_name.getText().toString();
                String Phone = et_mobile.getText().toString();
                String Email = et_email.getText().toString();

                if(Name.length()!= 0 && Name!= null){
                    if (Phone.length() == 10 && Integer.parseInt(Phone.substring(0,1)) > 6) {

                        if(Email.length()!=0 && Email!=null && helper_Functions.emailValidator(Email)){
                            //Send Data To Server
                            if(AppStatus.getInstance(Registration.this).isOnline()){
                                String url = null;
                                StringBuilder sb = new StringBuilder();
                                sb.append(Econstants.URL_MAIN);
                                sb.append("citizenLogin");
                                sb.append("/");
                               // sb.append(Email);
                               // sb.append("/");
                                sb.append(Phone);
                               // sb.append("/");
                               // sb.append(Name);
                                url = sb.toString();
                                new Generic_Async_Get(Registration.this, Registration.this, TaskType.USER_LOGIN).execute(url);
                            }else Custom_Dialog.showDialog(Registration.this ,"Unable to connect to Internet. Please connect to Internet.");
                        }else Custom_Dialog.showDialog(Registration.this ,"Please enter a vaild email address.");
                    } else Custom_Dialog.showDialog(Registration.this ,"Please enter a valid 10 digit Mobile number.");
                }else Custom_Dialog.showDialog(Registration.this ,"Please enter your Name.");

            }
        });


    }

    public void clearData(){
        et_name.setText("");
        et_mobile.setText("");
        et_email.setText("");


    }


    @Override
    public void onTaskCompleted(String result, TaskType taskType) throws IOException {

        if(taskType == TaskType.USER_LOGIN) {
            String Result_to_Show = null;
            Result_to_Show = Registration_Json.RegisterME_Error(result);
            if(Result_to_Show.equalsIgnoreCase("Invalid User")) {
                Custom_Dialog.showDialog(Registration.this, Result_to_Show);
            }else{




                Result_to_Show = result;
                Log.e("Result",Result_to_Show);
                ObjectMapper mapper=new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                UP = mapper.readValue(Result_to_Show, User.class);


                Log.e("id", String.valueOf(UP.getId()));
                Log.e("loginId",String.valueOf(UP.getLoginId()));
                Log.e("fullName",String.valueOf(UP.getFullName()));
                Log.e("emailId",String.valueOf(UP.getEmailId()));
                Log.e("districtId",String.valueOf(UP.getDistrictId()));
                Log.e("flgPasswordUpdate",String.valueOf(UP.getFlgPasswordUpdate()));
                Log.e("departmentId",String.valueOf(UP.getDepartmentId()));
                Log.e("desigId",String.valueOf(UP.getDesigId()));
                Log.e("blockId",String.valueOf(UP.getBlockId()));
                Log.e("patwarId",String.valueOf(UP.getPatwarId()));
                Log.e("revenuevillageId",String.valueOf(UP.getRevenuevillageId()));
                Log.e("revenuetehsilId",String.valueOf(UP.getRevenuetehsilId()));
                Log.e("panchayatId",String.valueOf(UP.getPanchayatId()));
                Log.e("villageId",String.valueOf(UP.getVillageId()));
                Log.e("tehsilId",String.valueOf(UP.getTehsilId()));
                Log.e("townId",String.valueOf(UP.getTehsilId()));
                Log.e("wardId",String.valueOf(UP.getWardId()));
                Log.e("stateId",String.valueOf(UP.getStateId()));
                Log.e("dateOfBirth",String.valueOf(UP.getDateOfBirth()));
                Log.e("mobileNumber",String.valueOf(UP.getMobileNumber()));
                Log.e("familyId",String.valueOf(UP.getFamilyId()));
                Log.e("aadhaarNo",String.valueOf(UP.getAadhaarNo()));
                Log.e("panCard",String.valueOf(UP.getPanCard()));
                Log.e("epic",String.valueOf(UP.getEpic()));
                Log.e("bplNo",String.valueOf(UP.getBplNo()));
                Log.e("revenuetehsilName",String.valueOf(UP.getRevenuetehsilName()));
                Log.e("revenuevillageName",String.valueOf(UP.getRevenuevillageName()));
                Log.e("departmentName",String.valueOf(UP.getDepartmentName()));
                Log.e("patwarName",String.valueOf(UP.getPatwarName()));
                Log.e("desigName",String.valueOf(UP.getDesigName()));
                Log.e("districtName",String.valueOf(UP.getDisplayName()));
                Log.e("blockName",String.valueOf(UP.getBlockName()));
                Log.e("panchayatName",String.valueOf(UP.getPanchayatName()));
                Log.e("villageName",String.valueOf(UP.getVillageName()));
                Log.e("tehsilName",String.valueOf(UP.getTehsilName()));
                Log.e("townName",String.valueOf(UP.getTownName()));
                Log.e("wardName",String.valueOf(UP.getWardName()));
                Log.e("stateName",String.valueOf(UP.getStateName()));
                Log.e("userGroup",String.valueOf(UP.getUserGroup()));
                Log.e("userType",String.valueOf(UP.getUserType()));
                Log.e("userTypeObj",String.valueOf(UP.getUserTypeObj()));
                Log.e("userGroupName",String.valueOf(UP.getUserGroupName()));
                Log.e("loginType",String.valueOf(UP.getLoginType()));
                Log.e("isLoggedIn",String.valueOf(UP.getIsLoggedIn()));
                Log.e("lastLogin",String.valueOf(UP.getLastLogin()));
                Log.e("password",String.valueOf(UP.getPassword()));
                Log.e("firstName",String.valueOf(UP.getFirstName()));
                Log.e("middleName",String.valueOf(UP.getMiddleName()));
                Log.e("lastName",String.valueOf(UP.getLastName()));
                Log.e("fathersFirstName",String.valueOf(UP.getFathersFirstName()));
                Log.e("fathersMiddleName",String.valueOf(UP.getFathersMiddleName()));
                Log.e("fathersLastName",String.valueOf(UP.getFathersLastName()));
                Log.e("emailIsLogin",String.valueOf(UP.getEmailIsLogin()));
                Log.e("hQueId1",String.valueOf(UP.gethQueId1()));
                Log.e("hintAns1",String.valueOf(UP.getHintAns1()));
                Log.e("hQueId2",String.valueOf(UP.gethQueId2()));
                Log.e("hintAns2",String.valueOf(UP.getHintAns2()));
                Log.e("displayName",String.valueOf(UP.getDisplayName()));
                Log.e("memberOf",String.valueOf(UP.getMemberOf()));
                Log.e("filter",String.valueOf(UP.getFilter()));
                Log.e("mimeType",String.valueOf(UP.getMimeType()));
                Log.e("fileName",String.valueOf(UP.getFileName()));
                Log.e("address",String.valueOf(UP.getAddress()));
                Log.e("gender",String.valueOf(UP.getGender()));
                Log.e("shortCode",String.valueOf(UP.getShortCode()));
                Log.e("dtCreated",String.valueOf(UP.getDtCreated()));
                Log.e("doc",String.valueOf(UP.getDoc()));
                Log.e("block",String.valueOf(UP.getBlock()));
                Log.e("panchayat",String.valueOf(UP.getPanchayat()));
                Log.e("village",String.valueOf(UP.getVillage()));
                Log.e("linkCreation",String.valueOf(UP.getLinkCreation()));
                Log.e("forceLogin",String.valueOf(UP.getForceLogin()));
                Log.e("state",String.valueOf(UP.getState()));
                Log.e("district",String.valueOf(UP.getDistrict()));
                Log.e("tehsil",String.valueOf(UP.getTehsil()));

/*
                Log.e("ruralUrban",String.valueOf(UP.getRuralUrban()));
                Log.e("city",UP.getCity());
                Log.e("rowCount",UP.getRowCount());
                Log.e("rowData",UP.getRowData());
                Log.e("rowIndex",UP.getRowIndex());
                Log.e("wrappedData",UP.getWrappedData());
                Log.e("rowAvailable",UP.getRowAvailable());
                Log.e("guestUser",UP.getGuestUser());
                Log.e("expirePasswd",UP.getExpirePasswd());*/


                Intent userSearch = new Intent();
                userSearch.putExtra("USER", UP);
                userSearch.setClass(Registration.this, MainScreen.class);
                startActivity(userSearch);
                Registration.this.finish();






            }
        }else{

            Custom_Dialog.showDialog(Registration.this, "Something went wrong. Please try again later.");
        }
    }

    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

    }
}
