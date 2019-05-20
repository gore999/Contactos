package rodriguezfernandez.carlos.contactos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.ContactosDao;
import rodriguezfernandez.carlos.contactos.Data.RepositorioContactos;

public class ViewModelVistaContacto extends AndroidViewModel {
    LiveData<Contacto> contacto;
    LiveData<Contacto> c;
    RepositorioContactos rep;
    public ViewModelVistaContacto(@NonNull Application application) {
        super(application);
        rep=new RepositorioContactos(application);
    }

    public LiveData <Contacto> getContactoId(int id) {
        contacto=rep.getContactoId(id);
        return contacto;
    }
}
