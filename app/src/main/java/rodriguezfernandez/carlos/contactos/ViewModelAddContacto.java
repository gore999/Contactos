package rodriguezfernandez.carlos.contactos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import rodriguezfernandez.carlos.contactos.Data.Contacto;
import rodriguezfernandez.carlos.contactos.Data.Email;
import rodriguezfernandez.carlos.contactos.Data.RepositorioContactos;
import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class ViewModelAddContacto extends AndroidViewModel {
    Contacto contacto;
    RepositorioContactos rep;
    public ViewModelAddContacto(@NonNull Application application) {
        super(application);
        rep=new RepositorioContactos(application);
        contacto=new Contacto();//Se crea un contacto vacio.
        //Le añadimos dos arrayList para almacenar
        contacto.setEmails(new ArrayList<Email>());
        contacto.setTelefonos(new ArrayList<Telefono>());
    }

    public void saveContacto(){
        rep.insertContacto(contacto);
    }
}
