package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.app.Activity;
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
                User UP = mapper.readValue(Result_to_Show, User.class);
                Log.e("id", String.valueOf(UP.getId()));

               /* User_Pojo user_pojo = new User_Pojo();
                user_pojo = Registration_Json.Parse_Data_Registration(Result_to_Show);

                //Send the user Object to Save either in database and SharedPrefrences
                Log.e("id",user_pojo.getId());
                Log.e("loginId",user_pojo.getLoginId());
                Log.e("fullName",user_pojo.getFullName());
                Log.e("emailId",user_pojo.getEmailId());
                Log.e("districtId",user_pojo.getDistrictId());
                Log.e("flgPasswordUpdate",user_pojo.getFlgPasswordUpdate());
                Log.e("districtName",user_pojo.getDisplayName());
                Log.e("departmentId",user_pojo.getDepartmentId());
                Log.e("departmentName",user_pojo.getDepartmentName());
                Log.e("desigName",user_pojo.getDesigName());
                Log.e("desigId",user_pojo.getDesigId());
                Log.e("blockId",user_pojo.getBlockId());
                Log.e("patwarId",user_pojo.getPatwarId());
                Log.e("patwarName",user_pojo.getPatwarName());
                Log.e("revenuevillageId",user_pojo.getRevenuevillageId());
                Log.e("revenuevillageName",user_pojo.getRevenuevillageName());
                Log.e("revenuetehsilId",user_pojo.getRevenuetehsilId());
                Log.e("revenuetehsilName",user_pojo.getRevenuetehsilName());
                Log.e("blockName",user_pojo.getBlockName());
                Log.e("panchayatId",user_pojo.getPanchayatId());
                Log.e("panchayatName",user_pojo.getPanchayatName());
                Log.e("villageId",user_pojo.getVillageId());
                Log.e("villageName",user_pojo.getVillageName());
                Log.e("tehsilId",user_pojo.getTehsilId());
                Log.e("tehsilName",user_pojo.getTehsilName());
                Log.e("townId",user_pojo.getTehsilId());
                Log.e("townName",user_pojo.getTownName());
                Log.e("wardId",user_pojo.getWardId());
                Log.e("wardName",user_pojo.getWardName());
                Log.e("stateId",user_pojo.getStateId());
                Log.e("stateName",user_pojo.getStateName());
                Log.e("userGroup",user_pojo.getUserGroup());
                Log.e("userType",user_pojo.getUserType());
                Log.e("userTypeObj",user_pojo.getUserTypeObj());
                Log.e("userGroupName",user_pojo.getUserGroupName());
                Log.e("loginType",user_pojo.getLoginType());
                Log.e("isLoggedIn",user_pojo.getIsLoggedIn());
                Log.e("lastLogin",user_pojo.getLastLogin());
                Log.e("password",user_pojo.getPassword());
                Log.e("firstName",user_pojo.getFirstName());
                Log.e("middleName",user_pojo.getMiddleName());
                Log.e("lastName",user_pojo.getLastName());
                Log.e("fathersFirstName",user_pojo.getFathersFirstName());
                Log.e("fathersMiddleName",user_pojo.getFathersMiddleName());
                Log.e("fathersLastName",user_pojo.getFathersLastName());
                Log.e("dateOfBirth",user_pojo.getDateOfBirth());
                Log.e("mobileNumber",user_pojo.getMobileNumber());
                Log.e("aadhaarNo",user_pojo.getAadhaarNo());
                Log.e("epic",user_pojo.getEpic());
                Log.e("bplNo",user_pojo.getBplNo());
                Log.e("panCard",user_pojo.getPanCard());
                Log.e("familyId",user_pojo.getFamilyId());
                Log.e("emailIsLogin",user_pojo.getEmailIsLogin());
                Log.e("hQueId1",user_pojo.gethQueId1());
                Log.e("hintAns1",user_pojo.getHintAns1());
                Log.e("hQueId2",user_pojo.gethQueId2());
                Log.e("hintAns2",user_pojo.getHintAns2());
                Log.e("expirePasswd",user_pojo.getExpirePasswd());
                Log.e("displayName",user_pojo.getDisplayName());
                Log.e("memberOf",user_pojo.getMemberOf());
                Log.e("filter",user_pojo.getFilter());
                Log.e("doc",user_pojo.getDoc());
                Log.e("mimeType",user_pojo.getMimeType());
                Log.e("fileName",user_pojo.getFileName());
                Log.e("dtCreated",user_pojo.getDtCreated());
                Log.e("linkCreation",user_pojo.getLinkCreation());
                Log.e("forceLogin",user_pojo.getForceLogin());
                Log.e("state",user_pojo.getState());
                Log.e("ruralUrban",user_pojo.getRuralUrban());
                Log.e("district",user_pojo.getDistrict());
                Log.e("tehsil",user_pojo.getTehsil());
                Log.e("city",user_pojo.getCity());
                Log.e("address",user_pojo.getAddress());
                Log.e("block",user_pojo.getBlock());
                Log.e("panchayat",user_pojo.getPanchayat());
                Log.e("village",user_pojo.getVillage());
                Log.e("guestUser",user_pojo.getGuestUser());
                Log.e("gender",user_pojo.getGender());
                Log.e("shortCode",user_pojo.getShortCode());
                Log.e("rowCount",user_pojo.getRowCount());
                Log.e("rowData",user_pojo.getRowData());
                Log.e("rowIndex",user_pojo.getRowIndex());
                Log.e("wrappedData",user_pojo.getWrappedData());
                Log.e("rowAvailable",user_pojo.getRowAvailable());*/







            }
        }else{

            Custom_Dialog.showDialog(Registration.this, "Something went wrong. Please try again later.");
        }
    }

    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

    }
}
