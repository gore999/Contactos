package rodriguezfernandez.carlos.contactos.Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import java.util.ArrayList;
import java.util.List;

public class RepositorioContactos {
    private ContactosDao dao;
//Constructor
public RepositorioContactos(Application application){
    ///Instanciar el dao.
        dao=ContactosDB.getINSTANCE(application).getDao();
    }
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

// Obtener listas de contactos.
    public LiveData<List<Contacto>> getContactos(){
       return dao.getContactos();
    };
    public LiveData<List<Contacto>> getContactosFiltro(String s){
        return dao.getContactosFiltro(s);
    }

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
    public LiveData<List<Email>> getEmailContacto(int id){
        return dao.getEmailsContacto(id);
    }
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
    public LiveData<List<Telefono>> getEmailTelefono(int id){
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
