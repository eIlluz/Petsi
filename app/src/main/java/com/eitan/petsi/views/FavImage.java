package com.eitan.petsi.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.eitan.petsi.R;

/**
 * Created by eitan onState 18/10/2014.
 */
public class FavImage extends ImageButton {


    private Boolean onState = false;

    public FavImage(Context context) {
        super(context);
        turnOnOff(false);
    }

    public FavImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        turnOnOff(false);
    }

    public FavImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        turnOnOff(false);
    }

    public void turnOnOff(Boolean on){
        if (on) {
            this.setImageResource(R.drawable.ic_heart_on);
            onState = true;
        }
        else {
            this.setImageResource(R.drawable.ic_heart_off);
            onState = false;
        }
    }

    public void switchState() {
        if (!onState) {
            this.setImageResource(R.drawable.ic_heart_on);
            onState = true;
        }
        else {
            this.setImageResource(R.drawable.ic_heart_off);
            onState = false;
        }
    }

    public Boolean getOnState() {
        return onState;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN)
            switchState();

        return super.onTouchEvent(event);
    }

}
