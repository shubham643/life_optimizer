package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Shubham on 3/2/2016.
 */
public class Self extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {

    }
}
