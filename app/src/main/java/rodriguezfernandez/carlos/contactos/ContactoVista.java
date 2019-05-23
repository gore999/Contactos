package rodriguezfernandez.carlos.contactos;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.Email;
import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class ContactoVista extends AppCompatActivity {
    public static String ID_CONTACTO="rodriguezfernandez.carlos.contactos.EXTRA.idContacto";
    public static String PERSONA="rodriguezfernandez.carlos.contactos.EXTRA.persona";
    ViewModelVistaContacto viewModelVistaContacto;
    LiveData<Contacto> contacto;
    Contacto contactoCopia;
    LiveData<Contacto> telefonos;
    LiveData<Contacto> emails;
    List<Telefono> telfs;
    List<Email> mails;
    TextView nombre;
    TextView apellidos;
    RecyclerView telefonosRec;
    RecyclerView emailsRec;
    int hora;
    int minuto;
    int identificador;
    String persona;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_vista);
        nombre=findViewById(R.id.contactoVistaNombreTv);
        apellidos=findViewById(R.id.contactoVistaApellidostextView);
        telefonosRec=findViewById(R.id.telefsRecyclerVista);
        emailsRec=findViewById(R.id.emailRecContactoVista);
        telfs=new ArrayList<>();
        mails=new ArrayList<>();
        final tfAdapterContactoVista adaptertf=new tfAdapterContactoVista(getApplicationContext(),  telfs);
        final emAdapterContactoVista adapterml=new emAdapterContactoVista(getApplicationContext(), mails);
        Intent intentDatos=getIntent();
        int id=intentDatos.getIntExtra(ContactosAdapter.CONTACTO,0);
        viewModelVistaContacto=ViewModelProviders.of(this).get(ViewModelVistaContacto.class);
        //Iniciar los datos en el viewmodel.
        viewModelVistaContacto.getContactoId(id);
        viewModelVistaContacto.getTelefonosContacto(id);
        viewModelVistaContacto.getEmailsContacto(id);
        //Configurar los recyclers
        telefonosRec.setAdapter(adaptertf);
        emailsRec.setAdapter(adapterml);
        telefonosRec.setLayoutManager(new LinearLayoutManager(this));
        emailsRec.setLayoutManager(new LinearLayoutManager(this));
        viewModelVistaContacto.contacto.observe(this, new Observer<Contacto>() {
            @Override
            public void onChanged(@Nullable Contacto contacto) {
                if(contacto!=null) {
                    identificador=contacto.getId();
                    persona=contacto.getNombre()+" "+contacto.getApellidos();
                    nombre.setText(contacto.getNombre());
                    apellidos.setText(contacto.getApellidos());
                    contactoCopia = contacto;
                }else{//Si el contacto es nulo, daría error. Así que lo que hacemos es volver a la pantalla de inicio.
                    Intent i=new Intent(ContactoVista.this,MainActivity.class);
                    i.putExtra(MainActivity.EXTRA_MENSAJE,"Eliminado con exito");
                    startActivity(i);
                }
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
                adapterml.setdata(emails);
            }
        });

    }

    public void eliminarContacto(View view) {
        AlertDialog.Builder dialog= new AlertDialog.Builder(ContactoVista.this);
        dialog.setTitle("Atención!");
        dialog.setMessage("Si borra el contacto, no podrá recuperarlo, ¿desea continuar?");
        dialog.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModelVistaContacto.deleteContacto(contactoCopia);
            }
        });
        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Cancelado",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public void setRecordatorio(View view) {

        TimePickerDialog dialogo=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar=Calendar.getInstance();//Instancia calendar

                Toast.makeText(getApplicationContext(),"recordatorio fijado para las "+hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
                long ahora= calendar.getTimeInMillis();
                long lapso=1000*(hourOfDay*3600+minute*60);//Milisegundos
                Intent intent=new Intent(ContactoVista.this,AlarmReceiver.class);
                intent.putExtra(ID_CONTACTO,identificador);
                intent.putExtra(PERSONA,persona);
                PendingIntent pintent=PendingIntent.getBroadcast(getApplicationContext(),identificador,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,ahora+lapso,pintent);
            }
        },hora, minuto,true);
        dialogo.show();
    }
}
