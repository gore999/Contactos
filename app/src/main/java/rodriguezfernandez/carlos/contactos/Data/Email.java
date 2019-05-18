package rodriguezfernandez.carlos.contactos.Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Contacto.class,parentColumns =
"id",childColumns = "ownerId",onDelete = CASCADE))
public class Email {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int idEmail;
    @NonNull
    private int ownerId;
    @NonNull
    private String email;
    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(@NonNull int idEmail) {
        this.idEmail = idEmail;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
