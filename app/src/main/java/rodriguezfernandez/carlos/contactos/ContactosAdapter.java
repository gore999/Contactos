package rodriguezfernandez.carlos.contactos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;

class ContactosAdapter extends Adapter<ContactosAdapter.ContactosViewHolder> {
    List<Contacto> contactosArrayList;
    LayoutInflater inflater;
    ContactosAdapter(Context context, List<Contacto> contactos){
        inflater=LayoutInflater.from(context);
        contactosArrayList=contactos;
    }
    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=inflater.inflate(R.layout.contacto_recycler,viewGroup,false);
        return new ContactosViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder contactosViewHolder, int i) {
        contactosViewHolder.nombre.setText(contactosArrayList.get(i).getNombre());
        contactosViewHolder.apellidos.setText(contactosArrayList.get(i).getApellidos());
    }

    @Override
    public int getItemCount() {
        if(contactosArrayList==null)return 0;
        return contactosArrayList.size();
    }

    public void setContactos(List<Contacto> contactos) {
        contactosArrayList=contactos;
    }

    class ContactosViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView apellidos;
        public ContactosViewHolder(@NonNull View itemView, ContactosAdapter adp) {
            super(itemView);
            nombre=itemView.findViewById(R.id.contacto_nombre);
            apellidos=itemView.findViewById(R.id.contacto_apellidos);
        }
    }
}
