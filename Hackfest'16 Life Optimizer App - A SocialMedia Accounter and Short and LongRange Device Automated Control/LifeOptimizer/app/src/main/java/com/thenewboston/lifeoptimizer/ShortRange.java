package com.thenewboston.lifeoptimizer;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Shubham on 3/2/2016.
 */
public class ShortRange extends Activity implements View.OnClickListener {
    Button okbutton;
    TextView timerblu, timeraud;
    EditText bluhour, blumin, blusec, audhour, audmin, audsec;
    long secb, seca, hourb, houra, minb, mina, tota, totb;
    int a = 0, uniqueid = 4654;
    CounterClass1 blutimer;
    CounterClass2 audtimer;
    NotificationCompat.Builder notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_range);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        okbutton = (Button) findViewById(R.id.bshortok);
        timeraud = (TextView) findViewById((R.id.tvshortaud));
        timerblu = (TextView) findViewById((R.id.tvshortbtime));

        bluhour = (EditText) findViewById((R.id.etshortbhour));
        blumin = (EditText) findViewById((R.id.etshortbmin));
        blusec = (EditText) findViewById((R.id.etshortbsec));
        audhour = (EditText) findViewById((R.id.etshortaudh));
        audmin = (EditText) findViewById((R.id.etshortaudm));
        audsec = (EditText) findViewById((R.id.etshortauds));

        okbutton.setOnClickListener(this);
        bluhour.setOnClickListener(this);
        blumin.setOnClickListener(this);
        blusec.setOnClickListener(this);
        audhour.setOnClickListener(this);
        audmin.setOnClickListener(this);
        audsec.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etshortaudh:
            case R.id.etshortaudm:
            case R.id.etshortauds:
                okbutton.setVisibility(View.VISIBLE);
                a = 1;
                break;
            case R.id.etshortbhour:
            case R.id.etshortbmin:
            case R.id.etshortbsec:
                okbutton.setVisibility(View.VISIBLE);
                a = 2;
                break;
            case R.id.bshortok:
                okbutton.setVisibility(View.INVISIBLE);
                switch (a) {
                    case 1:
                        seca = Long.valueOf(audsec.getText().toString());
                        mina = Long.valueOf(audmin.getText().toString());
                        houra = Long.valueOf(audhour.getText().toString());
                        tota = (houra * 3600 + mina * 60 + seca);
                        CounterClass1 obj1 = new CounterClass1(tota * 1000 + 1000, 1000);
                        obj1.start();
                        break;
                    case 2:
                        secb = Long.valueOf(blusec.getText().toString());
                        minb = Long.valueOf(blumin.getText().toString());
                        hourb = Long.valueOf(bluhour.getText().toString());
                        totb = (hourb * 3600 + minb * 60 + secb);
                        CounterClass2 obj2 = new CounterClass2(totb * 1000 + 1000, 1000);
                        obj2.start();
                        break;
                }
        }

    }

    private class CounterClass1 extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass1(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (tota > 0) {
                tota = tota - 1;
                timeraud.setText(Long.toString(tota / 3600) + ":" + Long.toString((tota % 3600) / 60) + ":" + Long.toString(tota % 60));
            }
            if (tota == 0) {
               /* notify.setSmallIcon(R.mipmap.oldclock);
                notify.setTicker(" Time Has Been Achieved : " );
                notify.setContentTitle("Audio Timer Over");
                notify.setContentText("Click to set new Alarm");
                notify.setWhen(System.currentTimeMillis());

                Intent comeback = new Intent(ShortRange.this, ShortRange.class);
                PendingIntent pcomeback = PendingIntent.getActivity(ShortRange.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                notify.setContentIntent(pcomeback);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueid, notify.build());*/

                final MediaPlayer ourSong;
                ourSong = MediaPlayer.create(ShortRange.this, R.raw.iphone_5_alarm);
                ourSong.start();
                tota = -1;
            }


        }

        @Override
        public void onFinish() {

        }
    }

    private class CounterClass2 extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass2(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (totb > 0) {
                totb = totb - 1;
                timerblu.setText(Long.toString(totb / 3600) + ":" + Long.toString((totb % 3600) / 60) + ":" + Long.toString(totb % 60));
            }
            if (totb == 0) {
                    /*notify.setSmallIcon(R.mipmap.oldclock);
                    notify.setTicker(" Time Has Been Achieved : " );
                    notify.setContentTitle("Bluetooth Timer Over");
                    notify.setContentText("Click to set new Alarm");
                    notify.setWhen(System.currentTimeMillis());

                    Intent comeback = new Intent(ShortRange.this, ShortRange.class);
                    PendingIntent pcomeback = PendingIntent.getActivity(ShortRange.this, 0, comeback, PendingIntent.FLAG_UPDATE_CURRENT);
                    notify.setContentIntent(pcomeback);

                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueid, notify.build());*/

                final MediaPlayer ourSong;
                ourSong = MediaPlayer.create(ShortRange.this, R.raw.iphone_5_alarm);
                ourSong.start();
                totb = -1;

                Bundle basket = new Bundle();
                basket.putInt("key", 1);
                Intent i = new Intent(ShortRange.this, BTModule.class);
                i.putExtras(basket);
                startActivity(i);
            }
        }

        @Override
        public void onFinish() {

        }
    }
}


