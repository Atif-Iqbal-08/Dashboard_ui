package com.example.emflux;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

public class MainActivity extends Activity {
    SwitchCompat DirFwdRev , SmartSwitch;
    TextView Forward ,Reverse, SmartOn ,SmartOff  , device_name;
    Boolean open = false;
    ArcSeekBar gradientSeekBar;
    CardView cardView;
    //FrameLayout Running_Status_Frame;
    LinearLayout linearLayout,device_heading,Running_status;
    LinearLayout layout;
    TextView status;
    NumberPicker n1,n2,n3;
    String[] time;
    long animation_duration = 600;

    Button Led_pop,Advanced_Menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gradientSeekBar = (ArcSeekBar) findViewById(R.id.gradientSeekBar);
        status =  (TextView)findViewById(R.id.status);

        //for slot view am /pm
        time = getResources().getStringArray(R.array.time);
        //popup for led initialization and class calling
        Led_pop = (Button) findViewById(R.id.led_settings);
        Led_pop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PopUpClass popUpClass = new PopUpClass();
                popUpClass.showPopupWindow(v);
            }
        });
        Advanced_Menu = (Button) findViewById(R.id.Advanced_Menu);
        Advanced_Menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PopUpClass2 popUpClass = new PopUpClass2();
                popUpClass.showPopupWindow(v);
            }
        });


        //Direction fwd reverse
        DirFwdRev = (SwitchCompat) findViewById(R.id.switchOnOff);
        Forward = findViewById(R.id.tvSwitchYes);
        Reverse = findViewById(R.id.tvSwitchNo);

        //dir change function
        DirFwdRev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                    Forward.setTextColor(Color.parseColor("#FF03DAC5"));
                    Reverse.setTextColor(Color.parseColor("#FFFFFFFF"));

                    //do something

                    }

                else {
                     Forward.setTextColor(Color.parseColor("#FFFFFFFF"));
                     Reverse.setTextColor(Color.parseColor("#FF03DAC5"));
                        //do something

                    }
                }
            });
        // Smart Change switch
            SmartSwitch = (SwitchCompat) findViewById(R.id.SmartOnOff);
            SmartOn = findViewById(R.id.tvSmartYes);
            SmartOff = findViewById(R.id.tvSmartNO);
        SmartSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (buttonView.isChecked()){
                    SmartOff.setTextColor(Color.parseColor("#FF03DAC5"));
                    SmartOn.setTextColor(Color.parseColor("#FFFFFFFF"));
                    //do something

                }

                else {
                    SmartOff.setTextColor(Color.parseColor("#FFFFFFFF"));
                    SmartOn.setTextColor(Color.parseColor("#FF03DAC5"));
                    //do something

                }
            }
        });

// running status display
        //Running_Status_Frame = findViewById(R.id.Running_status_frame);
        Running_status = findViewById(R.id.Running_Status);
        device_name =  findViewById(R.id.device_name);
        device_heading = findViewById(R.id.device_heading);

        cardView = findViewById(R.id.card_view);
        linearLayout = findViewById(R.id.full_control);
        layout = findViewById(R.id.layout);


        //Number picker for timer
        n1 = findViewById(R.id.hours);
        n2 = findViewById(R.id.minutes);
        n3 = findViewById(R.id.seconds);
        // number picker range hours
        n1.setMaxValue(12);
        n1.setMinValue(0);
        //minutes
        n2.setMaxValue(59);
        n2.setMinValue(0);
        //Am pm
        n3.setMaxValue(1);
        n3.setMinValue(0);
        n3.setDisplayedValues(time);
        expand();



        //gradient set
        int[] colorArrays = getResources().getIntArray(R.array.gradient);
        gradientSeekBar.setProgressGradient(colorArrays);



        //set event
        gradientSeekBar.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int progress) {
                if (progress != 7 ){
                    Log.d("Value", "" +progress) ;
                    status.setText(" "+progress+" ");}
                else
                {
                 status.setText("Turbo");
                }

            }

        });

        }


    public void expand() {
        cardView.setOnClickListener(view -> {
            TransitionManager.beginDelayedTransition(layout, new AutoTransition());
            if(open)
            {
                linearLayout.setVisibility(View.GONE);
                Running_status.setVisibility(View.VISIBLE);
                device_name.setTextSize(20);

                open = false;
            }
            else
            {
                linearLayout.setVisibility(View.VISIBLE);
                Running_status.setVisibility(View.GONE);
                device_name.setTextSize(28);
                open = true;
            }
        });




    }
}
