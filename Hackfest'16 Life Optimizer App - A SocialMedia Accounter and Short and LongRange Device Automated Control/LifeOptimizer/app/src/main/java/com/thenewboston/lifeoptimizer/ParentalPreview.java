package com.thenewboston.lifeoptimizer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Shubham on 3/5/2016.
 */
public class ParentalPreview extends Activity{
    String names[]={"com.whatsapp","com.facebook.katani","com.instagram.android","com.android.chrome","com.bsb.hike","com.quora.android","com.supercell.clashofclans","com.snapchat.android","com.twitter.android"};
    TextView tv[]=new TextView[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_preview);
        TextView tv[] = new TextView[10];
        initialize();
        CountDownTimer obj=new CounterClass(1800000,1000);
        obj.start();
    }

    private void initialize() {
        tv[0]=(TextView)findViewById(R.id.tvvs1);
        tv[1]=(TextView)findViewById(R.id.tvvs2);
        tv[2]=(TextView)findViewById(R.id.tvvs3);
        tv[3]=(TextView)findViewById(R.id.tvvs4);
        tv[4]=(TextView)findViewById(R.id.tvvs5);
        tv[5]=(TextView)findViewById(R.id.tvvs6);
        tv[6]=(TextView)findViewById(R.id.tvvs7);
        tv[7]=(TextView)findViewById(R.id.tvvs8);
        tv[8]=(TextView)findViewById(R.id.tvvs9);
        tv[9]=(TextView)findViewById(R.id.tvvs10);
    }

    public class CounterClass extends CountDownTimer
    {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(ParentalPreview.this);

            ActivityManager activityManager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            PackageManager pm = getApplicationContext().getPackageManager();
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            ActivityManager.RunningAppProcessInfo appProcess = appProcesses.get(0);

            for(int i=1;i<=9;i++)
            {
                //Long.valueOf(sharepf.getString("fix" + i + "",""))<=Long.valueOf(sharepf.getString("" + i + "","")) &&
                if((appProcess.processName.equals(names[i-1])))
                {
                    final MediaPlayer ourSong;
                    ourSong = MediaPlayer.create(ParentalPreview.this, R.raw.iphone_5_alarm);
                    ourSong.start();
                }
                String temp=sharepf.getString("" + i + "", "0");
                Long val=Long.valueOf(temp);
                Toast.makeText(getApplicationContext(),val.toString(),3000).show();;
                tv[i-1].setText((val%3600)+ ":" + ((val%3600)/60) + ":" + (val%60));
            }
        }

        @Override
        public void onFinish() {

        }
    }
}
