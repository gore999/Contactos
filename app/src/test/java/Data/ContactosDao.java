package Data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;

@Dao
public interface ContactosDao {
    @Insert
    public void insertContacto(Contacto... contactos);
    @Delete
    public void deleteContacto(Contacto contacto);
    @Update
    public  void updateContacto(Contacto contacto);

    @Query("SELECT * FROM Contacto ORDER BY nombre")
    public ArrayList<Contacto> getContactos();

    @Query("SELECT * FROM Contacto WHERE nombre like :string  ORDER BY nombre")
    public ArrayList<Contacto> getContactosFiltro(String string);

    @Insert
    public void insertEmail(Email... emails);
    @Delete
    public void deleteEmail(Email email);
    @Update
    public void updateEmail(Email email);
    @Query("SELECT * FROM Email WHERE ownerId=:id")
    public LiveData<ArrayList<Email>> getEmailsContacto(int id);

    @Insert
    public void insertTelefono(Telefono... telefonos);
    @Delete
    public void deleteTelefono(Telefono telefono);
    @Update
    public void updateTelefono(Telefono telefono);
    @Query("SELECT * FROM Email WHERE ownerId=:id")
    public LiveData<ArrayList<Telefono>> getTelefonosContacto(int id);

}
