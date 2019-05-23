package rodriguezfernandez.carlos.contactos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ContactosBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Arranca la aplicacion de contactos cuando nota que se conecta unos cascos.
        Intent in=new Intent(context,MainActivity.class);
        context.startActivity(in);
    }
}
