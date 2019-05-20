package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.Email;
import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class ContactoVista extends AppCompatActivity {
    ViewModelVistaContacto viewModelVistaContacto;
    LiveData<Contacto> contacto;
    LiveData<Contacto> telefonos;
    LiveData<Contacto> emails;
    List<Telefono> telfs;
    List<Email> mails;
    TextView nombre;
    TextView apellidos;
    RecyclerView telefonosRec;
    RecyclerView emailsRec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_vista);
        nombre=findViewById(R.id.contactoVistaNombreTv);
        apellidos=findViewById(R.id.contactoVistaApellidostextView);
        telefonosRec=findViewById(R.id.telefsRecyclerVista);
        telfs=new ArrayList<>();
        final tfAdapterContactoVista adaptertf=new tfAdapterContactoVista(getApplicationContext(),  telfs);
        Intent intentDatos=getIntent();
        int id=intentDatos.getIntExtra(ContactosAdapter.CONTACTO,0);
        viewModelVistaContacto=ViewModelProviders.of(this).get(ViewModelVistaContacto.class);
        //Iniciar los datos en el viewmodel.
        viewModelVistaContacto.getContactoId(id);
        viewModelVistaContacto.getTelefonosContacto(id);
        viewModelVistaContacto.getEmailsContacto(id);
        Toast.makeText(getApplicationContext(),id+"",Toast.LENGTH_SHORT).show();
        //Configurar los recyclers
        telefonosRec.setAdapter(adaptertf);
        telefonosRec.setLayoutManager(new LinearLayoutManager(this));
        viewModelVistaContacto.contacto.observe(this, new Observer<Contacto>() {
            @Override
            public void onChanged(@Nullable Contacto contacto) {
                nombre.setText(contacto.getNombre());
                apellidos.setText(contacto.getApellidos());
            }
        });
        viewModelVistaContacto.telefonos.observe(this, new Observer<List<Telefono>>() {
            @Override
            public void onChanged(@Nullable List<Telefono> telefonos) {
                adaptertf.setdata(telefonos);
            }
        });
        viewModelVistaContacto.emails.observe(this, new Observer<List<Email>>() {
            @Override
            public void onChanged(@Nullable List<Email> emails) {

            }
        });

    }
}
