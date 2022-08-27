package com.example.emflux;

import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;
import com.marcinmoskala.arcseekbar.ProgressListener;

public class PopUpClass2 {
    ArcSeekBar Power_Seeker;
    TextView P_Text;
    public void showPopupWindow(final View view) {
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.advance_pop_up, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Power_Seeker = (ArcSeekBar) popupView.findViewById(R.id.powerconsumptionbar);
        P_Text =  (TextView) popupView.findViewById(R.id.Power_Text);
        int[] colorArrays = view.getContext().getResources().getIntArray(R.array.P_gradient);
        Power_Seeker.setProgressGradient(colorArrays);
        Power_Seeker.setOnProgressChangedListener(new ProgressListener() {
            @Override
            public void invoke(int progress) {
                Log.d("Value", "" +progress) ;
                 P_Text.setText(" "+progress+" ");

            }
//           @Override
//           public void invoke(int progress) {
//
//                 Log.d("Value", "" +progress) ;
//                 P_Text.setText(" "+progress+" ");}
//
//
//
//
        }
        );


        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }}





