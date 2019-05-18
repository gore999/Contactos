package rodriguezfernandez.carlos.contactos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.RepositorioContactos;

public class ViewModelMainActivity extends AndroidViewModel {
    RepositorioContactos rep;
    ArrayList<Contacto> listaContactos;
    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        rep=new RepositorioContactos(application);
        listaContactos=(ArrayList<Contacto>)rep.getContactos();
    }

    public ArrayList<Contacto> getContactos() {
        return listaContactos;
    }
}
