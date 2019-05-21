package rodriguezfernandez.carlos.contactos.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RepositorioContactos {
    private ContactosDao dao;
    public LiveData<List<Contacto>> listaContactos;
    LiveData<Contacto> c;
    LiveData<List<Telefono>> listaTelefonos;
    LiveData<List<Email>> listaEmails;

    //Constructor
public RepositorioContactos(Application application){
    ///INSTANCIAR EL DAO----------------------------------------------------------------
        dao=ContactosDB.getINSTANCE(application).getDao();
    }
    ///---------------------------------------------------------------------------------

    //Operaciones basicas con contactos.
    public void insertContacto(Contacto... contactos){
    //Insertamos el contacto en un hilo diferente
        new MyAsyncInsertContacto(dao).execute(contactos[0]);
    };
    public void deleteContacto(Contacto contacto){
        dao.deleteContacto(contacto);
    };
    public  void updateContacto(Contacto contacto){
        dao.updateContacto(contacto);
    };

// OBTENER CONTACTOS--------------------------------------------------------------------.
    //Por id---------------------------------------------
    public LiveData<Contacto> getContactoId(int id){
        c=dao.getContactoId(id);
        return c;
    }
    // Todos los contactos-------------------------------
    public LiveData<List<Contacto>> getContactos(){

        listaContactos=dao.getContactos();
        return listaContactos;
    };
    //Filtrados------------------------------------------
    public LiveData<List<Contacto>> getContactosFiltro(String s){
        listaContactos=dao.getContactosFiltro(s);
        return dao.getContactosFiltro(s);
    }
//EMAILS----------------------------------------------------------------------------------------
//Operaciones basicas con emails.
    public void insertEmail(Email... emails){
        dao.insertEmail(emails);
    }
    public void deleteEmail(Email email){
        dao.deleteEmail(email);
    }
    public void updateEmail(Email email){
        dao.updateEmail(email);
    }
    //Lista de emails de un contacto concreto
    public LiveData<List<Email>> getEmailsContacto(int id){
        listaEmails=dao.getEmailsContacto(id);
        return dao.getEmailsContacto(id);
    }
///TELEFONOS-------------------------------------------------------------------------------------
//Operaciones basicas con telefonos
    public void insertTelefono(Telefono... telefonos){
        dao.insertTelefono(telefonos);
    }
    public void deleteTelefono(Telefono telefono){
        dao.deleteTelefono(telefono);
    }
    public void updateTelefono(Telefono telefono){
        dao.updateTelefono(telefono);
    }
    public LiveData<List<Telefono>> getlTelefonosContacto(int id){
       listaTelefonos=dao.getTelefonosContacto(id);
        return dao.getTelefonosContacto(id);
    }


    private class MyAsyncInsertContacto extends AsyncTask<Contacto,Void,Void> {
        ContactosDao innerDao;
        public MyAsyncInsertContacto(ContactosDao dao) {
            innerDao=dao;
        }

        @Override
        protected Void doInBackground(Contacto... contactos) {
            long[] idResultante=dao.insertContacto(contactos[0]);// Inserta Los datos basicos del contacto.
            //Recupero los emails y telefonos del contacto.
            ArrayList<Telefono> tel=contactos[0].getTelefonos();
            ArrayList<Email> email=contactos[0].getEmails();
            //Los introduzco uno a uno, añadiendo la id de su dueño.
            for(Telefono t: tel){
                t.setOwnerId((int) idResultante[0]);
                dao.insertTelefono(t);
            }
            for(Email e: email){
                e.setOwnerId((int) idResultante[0]);
                dao.insertEmail(e);
            }
            return null;
        }
    }
}
