package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Shubham on 3/2/2016.
 */
public class ParentalControl extends Activity implements View.OnClickListener {
    Button parentalOk;
    EditText parentalUsername,parentalPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_control);
        SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharepf.edit();
        editor.putString("username", "shubham");
        editor.putString("password", "bansal");
        setContentView(R.layout.activity_parental_control);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        intialize();
    }

    private void intialize() {
        parentalOk=(Button)findViewById(R.id.bParentalOK);
        parentalUsername=(EditText)findViewById(R.id.etParentalUsername);
        parentalPassword=(EditText)findViewById(R.id.etParentalPassword);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.bParentalOK:
                SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(this);
                if(sharepf.getString("username","").equals("shubham") && sharepf.getString("password","").equals("bansal"))
                {
                    i =new Intent("android.intent.action.PARENTALLOGIN");
                    startActivity(i);
                }
                break;
            case R.id.etParentalUsername:
                break;
            case R.id.etParentalPassword:
                break;
        }
    }
}
