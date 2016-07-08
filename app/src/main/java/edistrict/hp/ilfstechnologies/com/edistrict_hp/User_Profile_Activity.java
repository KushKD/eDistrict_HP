package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import Model.User;

public class User_Profile_Activity extends AppCompatActivity {

    TextView user_profile_name_tv;
    TextView user_profile_short_bio_tv;

    User User_Data = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_);
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

    }

    private void setData() {
        user_profile_name_tv.setText(String.valueOf(User_Data.getFirstName()));
        user_profile_short_bio_tv.setText(String.valueOf(User_Data.getEmailId()));
    }

    private void InitializeView() {

        user_profile_name_tv= (TextView)findViewById(R.id.user_profile_name);
        user_profile_short_bio_tv = (TextView)findViewById(R.id.user_profile_short_bio);
    }
}
