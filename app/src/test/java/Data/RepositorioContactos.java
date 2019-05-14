package Data;

import android.app.Application;
import android.arch.lifecycle.LiveData;


import java.util.ArrayList;

public class RepositorioContactos {
    private ContactosDao dao;
//Constructor
    RepositorioContactos(Application application){
        dao=ContactosDB.getINSTANCE(application).getDao();
    }
//Operaciones basicas con contactos.
    public void insertContacto(Contacto... contactos){
        dao.insertContacto(contactos);
    };

    public void deleteContacto(Contacto contacto){
        dao.deleteContacto(contacto);
    };

    public  void updateContacto(Contacto contacto){
        dao.updateContacto(contacto);
    };

// Obtener listas de contactos.
    public ArrayList<Contacto> getContactos(){
       return dao.getContactos();
    };
    public ArrayList<Contacto> getContactosFiltro(String s){
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
    public LiveData<ArrayList<Email>> getEmailContacto(int id){
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
    public LiveData<ArrayList<Telefono>> getEmailTelefono(int id){
        return dao.getTelefonosContacto(id);
    }


}
