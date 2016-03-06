package com.thenewboston.lifeoptimizer;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by Abhishek on 3/5/2016.
 */
public class WidgetProvider1 extends AppWidgetProvider {
    Button bb1,bb2,bb3,bb4,bb5;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for(int i=0;i<appWidgetIds.length;i++)
        {
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_layout);

            Intent openApp1=new Intent(context,Farmer.class);
            Intent openApp2=new Intent(context,Farmer.class);
            Intent openApp3=new Intent(context,Farmer.class);
            Intent openApp4=new Intent(context,Farmer.class);
            Intent openApp5=new Intent(context,Farmer.class);

            Bundle sendingInt1=new Bundle();
            Bundle sendingInt2=new Bundle();
            Bundle sendingInt3=new Bundle();
            Bundle sendingInt4=new Bundle();
            Bundle sendingInt5=new Bundle();

            sendingInt1.putInt("key",1);
            sendingInt2.putInt("key",2);
            sendingInt3.putInt("key",3);
            sendingInt4.putInt("key",4);
            sendingInt5.putInt("key",5);

            openApp1.putExtras(sendingInt1);
            openApp2.putExtras(sendingInt2);
            openApp3.putExtras(sendingInt3);
            openApp4.putExtras(sendingInt4);
            openApp5.putExtras(sendingInt5);

            openApp1.setData(Uri.parse(openApp1.toUri(Intent.URI_INTENT_SCHEME)));
            openApp1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openApp2.setData(Uri.parse(openApp1.toUri(Intent.URI_INTENT_SCHEME)));
            openApp2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openApp3.setData(Uri.parse(openApp1.toUri(Intent.URI_INTENT_SCHEME)));
            openApp3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openApp4.setData(Uri.parse(openApp1.toUri(Intent.URI_INTENT_SCHEME)));
            openApp4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openApp5.setData(Uri.parse(openApp1.toUri(Intent.URI_INTENT_SCHEME)));
            openApp5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            PendingIntent pIntent1=PendingIntent.getActivity(context, 0, openApp1, 0);
            views.setOnClickPendingIntent(R.id.b1, pIntent1);
            PendingIntent pIntent2=PendingIntent.getActivity(context, 0, openApp2, 0);
            views.setOnClickPendingIntent(R.id.b2,pIntent2);
            PendingIntent pIntent3=PendingIntent.getActivity(context, 0, openApp3, 0);
            views.setOnClickPendingIntent(R.id.b3,pIntent3);
            PendingIntent pIntent4=PendingIntent.getActivity(context, 0, openApp4, 0);
            views.setOnClickPendingIntent(R.id.b4,pIntent4);
            PendingIntent pIntent5=PendingIntent.getActivity(context, 0, openApp5, 0);
            views.setOnClickPendingIntent(R.id.b5,pIntent5);

            SharedPreferences sharepf= PreferenceManager.getDefaultSharedPreferences(context);
            if(sharepf.getString("11", "").equals("0"))
                views.setViewVisibility(R.id.b1, View.GONE);
            if(sharepf.getString("22", "").equals("0"))
                views.setViewVisibility(R.id.b2, View.GONE);
            if(sharepf.getString("33", "").equals("0"))
                views.setViewVisibility(R.id.b3, View.GONE);
            if(sharepf.getString("44", "").equals("0"))
                views.setViewVisibility(R.id.b4, View.GONE);
            if(sharepf.getString("55", "").equals("0"))
                views.setViewVisibility(R.id.b5, View.GONE);

            appWidgetManager.updateAppWidget(appWidgetIds[i],views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }
}
