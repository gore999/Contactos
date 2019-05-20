package rodriguezfernandez.carlos.contactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactoVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_vista);
        Intent intentDatos=getIntent();
        int id=intentDatos.getIntExtra(ContactosAdapter.CONTACTO,0);

    }
}
