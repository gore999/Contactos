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

import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class telefonoAdapter extends Adapter<telefonoAdapter.TelefonoViewHolder> {
    private ArrayList<Telefono> telefs;
    private LayoutInflater inflater;
    telefonoAdapter(Context context,ArrayList<Telefono>telefonosListaImportada){
        inflater=LayoutInflater.from(context);
        telefs=telefonosListaImportada;
    }
    @NonNull
    @Override
    public TelefonoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflamos el layout
        View v=inflater.inflate(R.layout.telefono_layout,viewGroup,false);
        //devolvemos el viewholder
        return new TelefonoViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull TelefonoViewHolder telefonoViewHolder, int i) {
        //Vinculamos los datos
        telefonoViewHolder.telefono.setText(telefs.get(i).getTelefono()+"");
    }

    @Override
    public int getItemCount() {

        return telefs.size();
    }
    class TelefonoViewHolder extends RecyclerView.ViewHolder{
        TextView telefono;
        telefonoAdapter telAdapter;
        public TelefonoViewHolder(@NonNull View itemView,telefonoAdapter adapter) {
            super(itemView);
            telefono=itemView.findViewById(R.id.txtvw_telefono_adapter);
            telAdapter=adapter;
        }

    }
}
