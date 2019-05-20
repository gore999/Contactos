package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;

public class MainActivity extends AppCompatActivity {
    EditText campoBusqueda;
    RecyclerView listaContactosRecycler;
    List<Contacto> contactos;
    ViewModelMainActivity viewModelMainActivity;
    Button botonBuscar;
    ContactosAdapter contactosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewContacto=new Intent(MainActivity.this,AddContacto.class);
                startActivity(intentNewContacto);
            }
        });

    }


    private void configurar() {
        contactosAdapter=new ContactosAdapter(this,contactos);
        //Recuperar el viewModel
        viewModelMainActivity=ViewModelProviders.of(this).get(ViewModelMainActivity.class);
        viewModelMainActivity.listaContactos.observe(this, new Observer<List<Contacto>>() {
            @Override
            public void onChanged(@Nullable List<Contacto> contactos) {
                contactosAdapter.setContactos(contactos);
            }
        });
        //contactos= ((ViewModelMainActivity) viewModelMainActivity).getContactos();
        campoBusqueda=findViewById(R.id.editText_busqueda);
        botonBuscar=findViewById(R.id.buttonBuscar);
        listaContactosRecycler=findViewById(R.id.ReciclerlistaContactos);
        //Añadir el adaptador
        listaContactosRecycler.setAdapter(contactosAdapter);
        listaContactosRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void buscarContactos(View view) {
        String filtro=campoBusqueda.getText().toString();
        Toast.makeText(getApplicationContext(),filtro,Toast.LENGTH_LONG).show();

        viewModelMainActivity.getContactosFiltro(filtro);
        contactosAdapter.notifyDataSetChanged();

    }
}
