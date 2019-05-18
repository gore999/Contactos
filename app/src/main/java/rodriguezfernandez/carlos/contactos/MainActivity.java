package rodriguezfernandez.carlos.contactos;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import rodriguezfernandez.carlos.contactos.Data.Contacto;

public class MainActivity extends AppCompatActivity {
    EditText campoBusqueda;
    RecyclerView listaContactosRecycler;
    ArrayList<Contacto> contactos;
    AndroidViewModel viewModelMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        configurar();
    }

    private void configurar() {
        //Recuperar el viewModel
        viewModelMainActivity=ViewModelProviders.of(this).get(ViewModelMainActivity.class);
        contactos= ((ViewModelMainActivity) viewModelMainActivity).getContactos();
        campoBusqueda=findViewById(R.id.editText_busqueda);
        listaContactosRecycler=findViewById(R.id.ReciclerlistaContactos);

        ContactosAdapter contactosAdapter=new ContactosAdapter(this,contactos);
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
}
