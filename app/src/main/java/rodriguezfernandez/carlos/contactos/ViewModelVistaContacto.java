package rodriguezfernandez.carlos.contactos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.ContactosDao;
import rodriguezfernandez.carlos.contactos.Data.Email;
import rodriguezfernandez.carlos.contactos.Data.RepositorioContactos;
import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class ViewModelVistaContacto extends AndroidViewModel {
    LiveData<Contacto> contacto;
    LiveData<List<Telefono>> telefonos;
    LiveData<List<Email>> emails;

    RepositorioContactos rep;
    public ViewModelVistaContacto(@NonNull Application application) {
        super(application);
        rep=new RepositorioContactos(application);
    }

    public LiveData <Contacto> getContactoId(int id) {
        contacto=rep.getContactoId(id);
        return contacto;
    }
    public LiveData <List<Email>> getEmailsContacto(int id) {
        emails=rep.getEmailsContacto(id);
        return emails;
    }
    public LiveData <List<Telefono>> getTelefonosContacto(int id) {
        telefonos=rep.getlTelefonosContacto(id);
        return telefonos;
    }
    public void deleteContacto(Contacto c){
        rep.deleteContacto(c);

    }

}
