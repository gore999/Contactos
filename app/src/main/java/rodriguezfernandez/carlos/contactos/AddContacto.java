package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        teladap=new telefonoAdapter(this,viewModelContacto.contacto.getTelefonos());
        recyTelefonos.setAdapter(teladap);
        recyTelefonos.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addTelefono(View view) {
        //Crear un objeto telefono a partir del texto en el edittext.
        Telefono nuevoTelefono=new Telefono();
        //Añadimos el telefono al arraylist del contacto, que aun no tiene id asignada.
        nuevoTelefono.setTelefono(Integer.parseInt(String.valueOf(telefonoEdTxt.getText())));
        ArrayList<Telefono> telefonosDelContacto=viewModelContacto.contacto.getTelefonos();
        telefonosDelContacto.add(nuevoTelefono);
        //TEST: mostrar el contenido del array en un toast.
        String telefonos="";
        for(Telefono t: telefonosDelContacto){
            telefonos+=""+t.getTelefono()+"\n";
        }
        Toast.makeText(this,telefonos,Toast.LENGTH_SHORT).show();
        teladap.notifyDataSetChanged();
    }

    public void addEmail(View view) {
    }
}
