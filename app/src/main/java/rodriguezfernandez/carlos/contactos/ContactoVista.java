package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import rodriguezfernandez.carlos.contactos.Data.Contacto;

public class ContactoVista extends AppCompatActivity {
    ViewModelVistaContacto viewModelVistaContacto;
    LiveData<Contacto> contacto;
    TextView nombre;
    TextView apellidos;
    RecyclerView telefonos;
    RecyclerView emails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_vista);
        nombre=findViewById(R.id.contactoVistaNombreTv);
        apellidos=findViewById(R.id.contactoVistaApellidostextView);
        Intent intentDatos=getIntent();
        int id=intentDatos.getIntExtra(ContactosAdapter.CONTACTO,0);
        viewModelVistaContacto=ViewModelProviders.of(this).get(ViewModelVistaContacto.class);
        viewModelVistaContacto.getContactoId(id);
        Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();

        viewModelVistaContacto.contacto.observe(this, new Observer<Contacto>() {
            @Override
            public void onChanged(@Nullable Contacto contacto) {
                nombre.setText(contacto.getNombre());
                apellidos.setText(contacto.getApellidos());

            }
        });

    }
}
