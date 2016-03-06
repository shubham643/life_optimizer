package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by Shubham on 3/2/2016.
 */
public class LongRange extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    Button btnSendSMS;
    EditText txtPhoneNo;
    EditText txtMessage;
    RadioButton on1,off1;
    CheckBox c1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_range);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        btnSendSMS = (Button) findViewById(R.id.blongSEND);
        txtPhoneNo = (EditText) findViewById(R.id.blongphone);
        txtMessage = (EditText) findViewById(R.id.blongmsg);
       c1 = (CheckBox) findViewById(R.id.rbON);
        c1.setOnCheckedChangeListener(this);



        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage.getText().toString();
                if (phoneNo.length() > 0 && message.length() > 0)
                    sendSMS(phoneNo, message);
                else
                    Toast.makeText(getBaseContext(),
                            "Please enter both phone number and message.",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendSMS(String phoneNo, String message) {
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, LongRange.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNo, null, message, pi, null);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(c1.isChecked()) {
            txtMessage.setText("AT+CMGF=1 \n" +
                    "AT+CNMI=2,2,0,0,0 \n start tubewell" );
        }
        else{
            txtMessage.setText("AT+CMGF=1 \n" +
                    "AT+CNMI=2,2,0,0,0 \n stop tubewell" );
        }
    }
}
