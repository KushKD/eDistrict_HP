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
