package rodriguezfernandez.carlos.contactos;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Contacto;

class ContactosAdapter extends Adapter<ContactosAdapter.ContactosViewHolder> {

    public static final String CONTACTO = "rodriguezfernandez.carlos.contactos.Data.Contacto.CONTACTO_SELECCIONADO";
    List<Contacto> contactosList;
    LayoutInflater inflater;
    ContactosAdapter(Context context, List<Contacto> contactos){
        inflater=LayoutInflater.from(context);
        contactosList =contactos;
    }
    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=inflater.inflate(R.layout.contacto_recycler,viewGroup,false);
        return new ContactosViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder contactosViewHolder, int i) {
        contactosViewHolder.nombre.setText(contactosList.get(i).getNombre());
        contactosViewHolder.apellidos.setText(contactosList.get(i).getApellidos());
    }

    @Override
    public int getItemCount() {
        if(contactosList ==null)return 0;
        return contactosList.size();
    }

    public void setContactos(List<Contacto> contactos) {
        contactosList =contactos;
    }

    class ContactosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;
        TextView apellidos;
        public ContactosViewHolder(@NonNull final View itemView, ContactosAdapter adp) {
            super(itemView);
            nombre=itemView.findViewById(R.id.contacto_nombre);
            apellidos=itemView.findViewById(R.id.contacto_apellidos);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int posicion=getLayoutPosition();
            int id= contactosList.get(posicion).getId();
            String nombre= contactosList.get(posicion).getNombre();
            Context context = itemView.getContext();

            Intent intent=new Intent(context, ContactoVista.class);
            intent.putExtra(CONTACTO,id);
            Toast.makeText(itemView.getContext(),nombre,Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        }
    }
}
