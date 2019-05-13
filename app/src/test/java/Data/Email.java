package Data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
class Email {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int idEmail;
    private int ownerId;
    @NonNull
    private String email;
    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(@NonNull int idEmail) {
        this.idEmail = idEmail;
    }

    public int getOwnewId() {
        return ownerId;
    }

    public void setOwnewId(int ownewId) {
        this.ownerId = ownewId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
