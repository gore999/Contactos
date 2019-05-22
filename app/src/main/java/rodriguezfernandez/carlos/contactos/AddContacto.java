package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rodriguezfernandez.carlos.contactos.Data.*;
import java.util.ArrayList;
import java.util.List;

public class AddContacto extends AppCompatActivity {
    EditText nombre;
    EditText apellido;
    EditText telefonoEdTxt;
    EditText emailEdTxt;
    RecyclerView recyTelefonos;
    RecyclerView recyEmails;
    ViewModelAddContacto viewModelContacto;
///Adaptadores
    telefonoAdapter teladap;
    EmailAdapter emailadap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacto);
        config();
    }
/*Configurar la vista: Crear Objetos para los elementos del LayOut, crear elViewwModel,
   instanciar adaptadores....
    */
    private void config() {
        //Asignar elementos del layout
        nombre=findViewById(R.id.editTextNombre);
        apellido=findViewById(R.id.editTextApellidos);
        telefonoEdTxt=findViewById(R.id.editTextTelefono);
        emailEdTxt=findViewById(R.id.editTextEmail);
        recyTelefonos=findViewById(R.id.recyclerViewTelef);
        recyEmails=findViewById(R.id.recyclerViewEmail);
        //Vincular el viewModel
        viewModelContacto=ViewModelProviders.of(this).get(ViewModelAddContacto.class);
    //RecyclerView Telefonos.
        teladap=new telefonoAdapter(this,viewModelContacto.contacto.getTelefonos());
        //helper
        ItemTouchHelper ithTelefonos=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                viewModelContacto.contacto.getTelefonos().remove(viewHolder.getAdapterPosition());
                teladap.notifyDataSetChanged();
            }
        });
        recyTelefonos.setAdapter(teladap);
        recyTelefonos.setLayoutManager(new LinearLayoutManager(this));
        ithTelefonos.attachToRecyclerView(recyTelefonos);
//RecyclerView emails.
        //Crear el adaptador, la lista que se le pasa, es la de los emails.
        emailadap=new EmailAdapter(this,viewModelContacto.contacto.getEmails());
        //helper
        ItemTouchHelper ithEmails=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                viewModelContacto.contacto.getEmails().remove(viewHolder.getAdapterPosition());
                emailadap.notifyDataSetChanged();
            }
        });
        recyEmails.setAdapter(emailadap);
        recyEmails.setLayoutManager(new LinearLayoutManager(this));
        ithEmails.attachToRecyclerView(recyEmails);

    }


    public void addTelefono(View view) {
        String valor=telefonoEdTxt.getText().toString();
        if(!valor.isEmpty()) {
            //Crear un objeto telefono a partir del texto en el edittext.
            Telefono nuevoTelefono = new Telefono();
            //Añadimos el telefono al arraylist del contacto, que aun no tiene id asignada.
            nuevoTelefono.setTelefono(Integer.parseInt(valor));
            ArrayList<Telefono> telefonosDelContacto = viewModelContacto.contacto.getTelefonos();
            telefonosDelContacto.add(nuevoTelefono);
            teladap.notifyDataSetChanged();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_vacio),Toast.LENGTH_LONG);
        }
    }

    public void addEmail(View view) {
        String valor=emailEdTxt.getText().toString();
        if(!valor.isEmpty()) {
            Email nuevoEmail = new Email();
            nuevoEmail.setEmail(emailEdTxt.getText().toString());
            ArrayList<Email> emailsDelContacto = viewModelContacto.contacto.getEmails();
            emailsDelContacto.add(nuevoEmail);
            emailadap.notifyDataSetChanged();
        }else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_vacio),Toast.LENGTH_LONG);
        }
    }


    public void guardarContacto(View view) {
        viewModelContacto.contacto.setNombre(nombre.getText().toString());
        viewModelContacto.contacto.setApellidos(apellido.getText().toString());
        switch (compruebaCampos()){
            case 0:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_nombre), Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_apellido), Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.no_datos), Toast.LENGTH_LONG).show();
                break;
            case -1:// Si todo está correcto.
                viewModelContacto.saveContacto();
                break;
        }
        Intent intent=new Intent(AddContacto.this,MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_MENSAJE, "Guardado con exito");
        startActivity(intent);
    }

    //Devuelve un valor entero que representa un estado del contacto. 
    private int compruebaCampos() {
        int salida=-1;
        Contacto c=viewModelContacto.contacto;
        if(c.getTelefonos().size()==0 & c.getEmails().size()==0)salida=2;//2 es Tiene que guardar algun contacto.
        if(c.getApellidos().toString().isEmpty())salida=1;//1 es No hay apellidos.
        if(c.getNombre().toString().isEmpty())salida=0;//0 es No hay nombre
    return salida;
    }

}
