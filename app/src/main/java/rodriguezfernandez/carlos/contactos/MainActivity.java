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
import android.support.v7.widget.SearchView;
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

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String EXTRA_MENSAJE ="rodriguezfernandez.carlos.contactos.EXTRA.MENSAJE" ;
    RecyclerView listaContactosRecycler;
    List<Contacto> contactosActivity;
    ViewModelMainActivity viewModelMainActivity;

    ContactosAdapter contactosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        compruebaIntents();

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

    private void compruebaIntents() {
        Intent i= getIntent();
        if(i!=null) {
            String mensaje = i.getStringExtra(this.EXTRA_MENSAJE);
            if(mensaje!=null)
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
        }
    }


    private void configurar() {
        contactosAdapter=new ContactosAdapter(this,contactosActivity);        //Crear el adaptador para el Recycler.
        viewModelMainActivity=ViewModelProviders.of(this).get(ViewModelMainActivity.class);        //Recuperar el viewModel
        viewModelMainActivity.listaContactos.observe(this, new Observer<List<Contacto>>() { // Observar la lista de contactos livedata del viewmodel.
            @Override
            public void onChanged(@Nullable List<Contacto> contactos) {
                contactosActivity=contactos;
                contactosAdapter.setContactos(contactosActivity);// Cuando cambie, cambiar en el adaptador.
                contactosAdapter.notifyDataSetChanged();
            }
        });
        //contactos= ((ViewModelMainActivity) viewModelMainActivity).getContactos();

        listaContactosRecycler=findViewById(R.id.ReciclerlistaContactos);
        //Añadir el adaptador
        listaContactosRecycler.setAdapter(contactosAdapter);
        listaContactosRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String entrada=s.toLowerCase();
        List<Contacto> newList=new ArrayList<>();
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
        for(Contacto c: contactosActivity){
            if(c.getNombre().toLowerCase().startsWith(entrada) | c.getApellidos().toLowerCase().startsWith(entrada)){

                newList.add(c);
            }
        }
        contactosAdapter.setContactos(newList);
        return false;
    }
}
