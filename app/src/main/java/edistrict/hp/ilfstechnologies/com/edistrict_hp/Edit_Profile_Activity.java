package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Model.User;

public class Edit_Profile_Activity extends AppCompatActivity {

    User User_Data = null;
    TextView user_profile_name_tv;
    TextView user_profile_short_bio_tv;
    EditText firstname_tv,middlename_tv,lastname_tv;
    EditText firstname_father_tv, middlename_father_tv, lastname_father_tv;
    EditText gender_tv, dateofbirth_tv , aadhaar_tv, family_id_tv,mobile_number_tv;

    EditText state_tv,district_tv,tehsil_tv,village_tv,city_tv,block_tv,panchayat_tv,town_tv,ward_tv,address_tv;
    EditText questionone_tv,questiontwo_tv,answerone_tv,answertwo_tv;
    Button Back_bt, update_bt;
    ImageView edit_Profile_IV;

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
            setData();


        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
        }
        Back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Profile_Activity.this.finish();
            }
        });
    }

    private void setData() {
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
        district_tv.setText(String.valueOf(User_Data.getDistrict().getName()));
        tehsil_tv.setText(String.valueOf(User_Data.getTehsil().getName()));
        village_tv.setText(String.valueOf(User_Data.getVillage()));
        // city_tv.setText(String.valueOf(User_Data.get()));
        block_tv.setText(String.valueOf(User_Data.getBlock()));
        panchayat_tv.setText(String.valueOf(User_Data.getPanchayat()));
        town_tv.setText(String.valueOf(User_Data.getTown().getName()));
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
        district_tv = (EditText)findViewById(R.id.district);
        tehsil_tv = (EditText)findViewById(R.id.tehsil);
        village_tv = (EditText)findViewById(R.id.village);
       // city_tv = (EditText)findViewById(R.id.city);
        block_tv = (EditText)findViewById(R.id.block);
        panchayat_tv = (EditText)findViewById(R.id.panchayat);
        town_tv = (EditText)findViewById(R.id.town);
        ward_tv = (EditText)findViewById(R.id.ward);
        address_tv = (EditText)findViewById(R.id.address);
        questionone_tv = (EditText)findViewById(R.id.questionone);
        questiontwo_tv = (EditText)findViewById(R.id.questiontwo);
        answerone_tv = (EditText)findViewById(R.id.answerone);
        answertwo_tv = (EditText)findViewById(R.id.answertwo);
        Back_bt = (Button)findViewById(R.id.back);
        edit_Profile_IV = (ImageView)findViewById(R.id.edit_Profile);
    }
}
