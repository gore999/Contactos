package rodriguezfernandez.carlos.contactos;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CANAL_ID ="canal recordatorios" ;
    NotificationManager manager;
    int NOTIFICACION_ID;
    String persona;
    @Override
    public void onReceive(Context context, Intent intent) {
        NOTIFICACION_ID=intent.getIntExtra(ContactoVista.ID_CONTACTO,0);//La id del contacto hace de id para la notificacion.
        persona=intent.getStringExtra(ContactoVista.PERSONA);
      creaCanal(context);
      manager.notify(NOTIFICACION_ID,getBuilder(context).build() );

    }
    private void creaCanal(Context context){
        manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel canal=new NotificationChannel(CANAL_ID,context.getString(R.string.alert_channel),NotificationManager.IMPORTANCE_HIGH);
            canal.setDescription(context.getString(R.string.recordatorios));
            canal.enableLights(true);
            canal.enableVibration(true);
            canal.setLightColor(Color.GREEN);
        }
    }
    private NotificationCompat.Builder getBuilder(Context context){
        Intent intent=new Intent(context,ContactoVista.class);
        intent.putExtra(ContactosAdapter.CONTACTO,NOTIFICACION_ID);
        PendingIntent pint=PendingIntent.getActivity(context,NOTIFICACION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CANAL_ID);
        builder.setContentTitle(context.getString(R.string.recordatorio))
                .setContentText(context.getString(R.string.debe_llamar)+persona)
                .setSmallIcon(R.drawable.ic_contacto)
                .setAutoCancel(true)
                .setContentIntent(pint)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        return builder;
    }

}
