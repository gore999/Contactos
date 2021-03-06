package rodriguezfernandez.carlos.contactos.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Contacto.class,Email.class, Telefono.class},version = 1,exportSchema = false)
public abstract class ContactosDB extends RoomDatabase {
    private static ContactosDB INSTANCE;
    public  abstract ContactosDao getDao();

    public static ContactosDB getINSTANCE(Context context) {
        if( INSTANCE==null) {
            synchronized (ContactosDB.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),ContactosDB.class,"contactos")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
