package Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(foreignKeys = @ForeignKey(entity = Contacto.class,parentColumns = "id",childColumns = "ownerId"))
class Telefono {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int idTelefono;
    private int ownerId;
    @NonNull
    private int telefono;

    public int getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(@NonNull int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

}
