package is.hi.lucky7;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class notificationHandler {
	
	private Context context;
	private String ns = Context.NOTIFICATION_SERVICE;
	private NotificationManager mNotificationManager;

	
	// Notkun: notificationHandler not = new notificationHandler(getApplicationContext());
	// Notkun: notificationHandler not = new notificationHandler(this);
	public notificationHandler(Context context) {
		this.context = context;
	}
	
	public void postNotification(int id,String title, String msg,long when) {
    	int icon = R.drawable.rysclogo;        // icon from resources
    	CharSequence tickerText = title;              // ticker-text
    	//long when = System.currentTimeMillis();         // notification time
    	mNotificationManager = (NotificationManager) context.getSystemService(ns);

    	//Context context = getApplicationContext();      // application Context, a bara vid thegar alarm er sett upp an klasa forriti
    	CharSequence contentTitle = title;  // message title
    	CharSequence contentText = msg;      // message text
   
    	Intent notificationIntent = new Intent(context, RyscActivity.class); // Vantar i framhaldi leid til ad skipta um intent
    	// t.d. til ad geta notad viewoneevent til ad opna thann event sem verid er ad lata vita af
    	PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

    	// the next two lines initialize the Notification, using the configurations above
    	Notification notification = new Notification(icon, tickerText, when);
    	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

    	mNotificationManager.notify(id, notification);
    }
	
	public void cancelNotification(int id){
		mNotificationManager.cancel(id);
	}
}
