package Presentation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by kuush on 7/6/2016.
 */
public class Navigation_Header_TextView extends TextView {

    public Navigation_Header_TextView(Context context) {
        super(context);
        SetUP_TextView(context);

    }
    public Navigation_Header_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SetUP_TextView(context);

    }

    public Navigation_Header_TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SetUP_TextView(context);

    }


    public void SetUP_TextView(Context context){
        Typeface face= Typeface.createFromAsset(context.getAssets(), "angelina.TTF");
        this.setTypeface(face);
        this.setPadding(13,3,3,3);
        this.setTextColor(Color.parseColor("#FFFFFF"));
        this.setText("Test");

    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // this.setText("I'm Attached");
        //  Log.d("I'm","Attached");

    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }

}
