package edistrict.hp.ilfstechnologies.com.edistrict_hp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import Model.User;
import Presentation.Navigation_Header_TextView;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    User User_Data;

    RelativeLayout scContainer;
    private ArrayList<CardObject> cardList;
    View currentTopView;

    private final int INVALID_POINTER_ID = -1337;
    private int mActivePointerId = INVALID_POINTER_ID;
    private ViewConfiguration viewConfig;
    private float mScreenWidth;
    private float mScreenHeight;
    Integer loadCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

try {
    Intent getRoomDetailsIntent = getIntent();
    User_Data = (User) getRoomDetailsIntent.getSerializableExtra("USER");
}catch(Exception e){
     Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toString() ,Toast.LENGTH_SHORT).show();
}

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header=navigationView.getHeaderView(0);

        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        Navigation_Header_TextView  name_tv = (Navigation_Header_TextView)header.findViewById(R.id.name);
        Navigation_Header_TextView aadhaar_tv = (Navigation_Header_TextView)header.findViewById(R.id.aadhaar_no);

        name_tv.setText(String.valueOf(User_Data.getFirstName())+" "+String.valueOf(User_Data.getMiddleName())+" "+ String.valueOf(User_Data.getLastName()));

        if(String.valueOf(User_Data.getAadhaarNo()).equalsIgnoreCase("null")){
            aadhaar_tv.setText("N/A");
        }else {
            aadhaar_tv.setText(String.valueOf(User_Data.getAadhaarNo()));
        }

        viewConfig = ViewConfiguration.get(this);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;

        DisplayImageOptions displayOptions = new DisplayImageOptions.Builder()
                //.cacheInMemory(true)
                .displayer(new FadeInBitmapDisplayer(400))
                .build();

        ImageLoaderConfiguration imageLoaderConfig = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(displayOptions)
                .build();
        ImageLoader.getInstance().init(imageLoaderConfig);

        scContainer = (RelativeLayout) findViewById(R.id.swipeable_cards_container);

        cardList = new ArrayList<CardObject>();
        cardList.add(createSampleCardObject("Title #1", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #2", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #3", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #4", "http://lorempixel.com/400/300/"));
      /*  cardList.add(createSampleCardObject("Title #5", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #6", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #7", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #8", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #9", "http://lorempixel.com/400/300/"));
        cardList.add(createSampleCardObject("Title #10", "http://lorempixel.com/400/300/"));*/

        addCards(0, cardList.size());
        renderCards();

    }
    private CardObject createSampleCardObject(String title, String imageurl){
        CardObject newCard = new CardObject();

        newCard.title = title;
        newCard.image = imageurl;

        return newCard;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void addCards(int index, int count){
        for(int i = 0; i < count; i++){
            RelativeLayout newCardViewToAdd = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.card_item, scContainer, false);
            scContainer.addView(newCardViewToAdd, index + i);

            newCardViewToAdd.setTranslationY(mScreenHeight + 200);
            newCardViewToAdd.setScaleX(0.5f);
            newCardViewToAdd.setScaleY(0.5f);
            newCardViewToAdd.setAlpha(1f);

            CardViewHolder holder = new CardViewHolder(newCardViewToAdd);
            newCardViewToAdd.setTag(holder);
        }
    }

       private void loadMoreCards(){
        ArrayList<CardObject> newCardsList = new ArrayList<>();
           cardList.add(createSampleCardObject(loadCount+"Title #1", "http://lorempixel.com/400/300/"));
           cardList.add(createSampleCardObject(loadCount+"Title #2", "http://lorempixel.com/400/300/"));
           cardList.add(createSampleCardObject(loadCount+"Title #3", "http://lorempixel.com/400/300/"));
           cardList.add(createSampleCardObject(loadCount+"Title #4", "http://lorempixel.com/400/300/"));

        loadCount++;

        Integer index = cardList.size() - 1;
        cardList.addAll(newCardsList);
        addCards(index, newCardsList.size());
    }
  /*  private void loadMoreCards(){
        ArrayList<CardObject> newCardsList = new ArrayList<>();
        newCardsList.add(createSampleCardObject(loadCount + ". Batch, Card #1", "http://lorempixel.com/400/300/"));
        newCardsList.add(createSampleCardObject(loadCount + ". Batch, Card #2", "http://services.hanselandpetal.com/photos/mona_lavender.jpg"));
        newCardsList.add(createSampleCardObject(loadCount + ". Batch, Card #3", "http://lorempixel.com/400/300/"));
        newCardsList.add(createSampleCardObject(loadCount + ". Batch, Card #4", "http://lorempixel.com/400/300/"));
        newCardsList.add(createSampleCardObject(loadCount + ". Batch, Card #5", "http://lorempixel.com/400/300/"));

        loadCount++;

        Integer index = cardList.size() - 1;
        cardList.addAll(newCardsList);
        addCards(index, newCardsList.size());
    }*/

    private Integer MAX_RENDERED_COUNT = 1;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void renderCards(){
        int renderedCount = -1;
        int i = 0;

        for(int x = i; x <= scContainer.getChildCount(); x++){
            renderedCount++;
            if(renderedCount >= MAX_RENDERED_COUNT){
                return;
            }

            final View view;

            view = scContainer.getChildAt((scContainer.getChildCount() - x - 1));
            CardViewHolder holder = (CardViewHolder) view.getTag();

            if (renderedCount == 0){
                currentTopView = view;
                view.setOnTouchListener(new SwipeViewOnTouchListener());
            }

            if (renderedCount == 0) {
                view.setTranslationY(150f);
                view.setAlpha(1f);
                view.animate().translationY(0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(new OvershootInterpolator());
            }
            else if (renderedCount == 1) {
                view.setTranslationY(1000f);
                view.setAlpha(1f);
                view.animate().translationY(150f).scaleX(0.9f).scaleY(0.9f).setInterpolator(new OvershootInterpolator());
            }


            if(holder.data == null){
                holder.data = cardList.get(x);
                holder.textView.setText(holder.data.title);
                ImageLoader.getInstance().displayImage(holder.data.image, holder.imageView);
            }
        }
    }

    private void removeTopCard(){
        currentTopView.postDelayed(new Runnable() {
            @Override
            public void run() {
                scContainer.removeView(currentTopView);
                cardList.remove(0);

                if(cardList.size() <= MAX_RENDERED_COUNT){
                    loadMoreCards();

                    // TODO: 7/18/2016  
                   // loadPreviousList();
                    
                }

                renderCards();
            }
        }, 100);
    }

    private void topCardClicked() {
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
    }

    private void topCardLeft() {
        currentTopView.animate().translationX(-mScreenWidth).setInterpolator(new OvershootInterpolator());
        removeTopCard();
        //Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();
    }

    private void topCardRight() {
        currentTopView.animate().translationX(mScreenWidth).setInterpolator(new OvershootInterpolator());
        removeTopCard();
        //Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
    }

    static class CardViewHolder {
        public CardObject data;
        public ImageView imageView;
        public TextView textView;

        public CardViewHolder(View cardView){
            imageView = (ImageView) cardView.findViewById(R.id.card_image);
            textView = (TextView) cardView.findViewById(R.id.card_text);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private void snapback(View v){
        if(v != null){
            v.animate().translationX(0f).translationY(0f).rotation(0).setInterpolator(new OvershootInterpolator());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the Profile Click Button action
            Intent intent_Profile = new Intent();
            intent_Profile.putExtra("USER", User_Data);
            intent_Profile.setClass(MainScreen.this, User_Profile_Activity.class);
            startActivity(intent_Profile);
        } else if(id == R.id.nav_settings){
            Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
// CUSTOM GESTURE TRACKING FIELDS - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    private float MAX_ROTATION_AMOUNT = 20f;
    private final float TOUCH_SLOP_SCALE_FACTOR_X = 5.0f;

    class SwipeViewOnTouchListener implements View.OnTouchListener {

        private float mLastTouchX = 0;
        private float mLastTouchY = 0;
        private float dx = 0;
        private float dy = 0;
        private boolean isClick = true;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getActionMasked();

            switch (action) {
                case MotionEvent.ACTION_DOWN: {

                    final float x = event.getRawX();
                    final float y = event.getRawY();

                    mLastTouchX = x;
                    mLastTouchY = y;

                    final int pointerIndex = event.getActionIndex();
                    mActivePointerId = event.getPointerId(pointerIndex);
                    isClick = true;

                    break;
                }
                case MotionEvent.ACTION_MOVE: {

                    final float x = event.getRawX();
                    final float y = event.getRawY();

                    dx += (x - mLastTouchX);
                    dy += (y - mLastTouchY);

                    if (Math.abs(dx) > viewConfig.getScaledTouchSlop() || Math.abs(dy) > viewConfig.getScaledTouchSlop()) {
                        isClick = false;
                    }

                    v.setTranslationX(dx);
                    v.setTranslationY(dy);

                    float percentScreenWidthX = (Math.abs(dx) / mScreenWidth);
                    float rotationDegrees = percentScreenWidthX * MAX_ROTATION_AMOUNT;

                    if (dx < 0) {
                        rotationDegrees *= -1;
                    }

                    v.setRotation(rotationDegrees);

                    mLastTouchX = x;
                    mLastTouchY = y;

                    break;
                }

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {

                    if (Math.abs(dx) <= viewConfig.getScaledTouchSlop() && Math.abs(dy) <= viewConfig.getScaledTouchSlop() && isClick) {
                        topCardClicked();
                    } else if (Math.abs(dx) <= viewConfig.getScaledTouchSlop() * TOUCH_SLOP_SCALE_FACTOR_X) {
                        snapback(v);
                    } else {
                        if (dx < 0) topCardLeft();
                        else topCardRight();
                    }

                    dx = 0;
                    dy = 0;
                    mLastTouchX = 0;
                    mLastTouchY = 0;
                    mActivePointerId = INVALID_POINTER_ID;

                    break;
                }
            }

            return true;
        }

    }
    class CardObject {
        public String title;
        public String image;
    }
}
