package rodriguezfernandez.carlos.contactos.Data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactosDao {
    //CONTACTOS--------------------------------------------------------------------
    @Insert
     long[] insertContacto(Contacto... contactos);
    @Delete
     void deleteContacto(Contacto contacto);
    @Update
      void updateContacto(Contacto contacto);
    @Query("SELECT * FROM Contacto WHERE id=:id")
    LiveData<Contacto> getContactoId(int id);

    @Query("SELECT * FROM Contacto ORDER BY nombre")
    LiveData<List<Contacto>> getContactos();

    @Query("SELECT * FROM Contacto WHERE nombre like :string  ORDER BY nombre")
    LiveData<List<Contacto>> getContactosFiltro(String string);
    //EMAILS-----------------------------------------------------------------------

    @Insert
     void insertEmail(Email... emails);
    @Delete
     void deleteEmail(Email email);
    @Update
     void updateEmail(Email email);
    @Query("SELECT * FROM Email WHERE ownerId=:id")
    LiveData<List<Email>> getEmailsContacto(int id);

    //TELEFONOS--------------------------------------------------------------------
    @Insert
     void insertTelefono(Telefono... telefonos);
    @Delete
     void deleteTelefono(Telefono telefono);
    @Update
     void updateTelefono(Telefono telefono);

    @Query("SELECT * FROM Telefono WHERE ownerId=:id")
    LiveData<List<Telefono>> getTelefonosContacto(int id);






}
