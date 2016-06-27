package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Abstract.AsyncTaskListener;
import Generic.Custom_Dialog;
import Utilities.AppStatus;
import Utilities.Econstants;
import Utilities.helper_Functions;
import Enum.TaskType;
import Generic.Generic_Async_Get;

public class Registration extends AppCompatActivity implements AsyncTaskListener {


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
                                sb.append(Email);
                                sb.append("/");
                                sb.append(Phone);
                                sb.append("/");
                                sb.append(Name);
                                url = sb.toString();
                                new Generic_Async_Get(Registration.this, Registration.this, TaskType.USER_LOGIN).execute(url);

                            }else{


                                Custom_Dialog.showDialog(Registration.this ,"Unable to connect to Internet. Please connect to Internet.");
                            }


                        }else{

                            Custom_Dialog.showDialog(Registration.this ,"Please enter a vaild email address.");
                        }

                    } else {

                        Custom_Dialog.showDialog(Registration.this ,"Please enter a valid 10 digit Mobile number.");
                    }

                }else{

                    Custom_Dialog.showDialog(Registration.this ,"Please enter your Name.");
                }

            }
        });


    }

    public void clearData(){
        et_name.setText("");
        et_mobile.setText("");
        et_email.setText("");


    }


    @Override
    public void onTaskCompleted(String result, TaskType taskType) {

        if(taskType == TaskType.USER_LOGIN) {
           // String Result_to_Show = null;
          //  Result_to_Show = Vehicle_In_Out_Json.VehicleIn_Parse(result);

            Custom_Dialog.showDialog(Registration.this, result);
        }else{

            Custom_Dialog.showDialog(Registration.this, "Something went wrong. Please try again later.");
        }
    }

    @Override
    public void onTaskCompleted(Activity activity, String result, TaskType taskType) {

    }
}
