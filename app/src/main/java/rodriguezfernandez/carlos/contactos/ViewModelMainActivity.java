package rodriguezfernandez.carlos.contactos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.RepositorioContactos;

public class ViewModelMainActivity extends AndroidViewModel {
    RepositorioContactos rep;
    LiveData<List<Contacto>> listaContactos;
    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        rep=new RepositorioContactos(application);
        listaContactos=rep.getContactos();

    }

    public LiveData<List<Contacto>> getContactos() {
        return listaContactos;
    }
}
