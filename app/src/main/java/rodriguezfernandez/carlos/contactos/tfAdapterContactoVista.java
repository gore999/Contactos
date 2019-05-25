package rodriguezfernandez.carlos.contactos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class tfAdapterContactoVista extends Adapter<tfAdapterContactoVista.TelefonoViewHolder> {
    private List<Telefono> telefs;
    private LayoutInflater inflater;
    tfAdapterContactoVista(Context context, List<Telefono>telefonosListaImportada){
        inflater=LayoutInflater.from(context);
        telefs=telefonosListaImportada;
    }
    @NonNull
    @Override
    public TelefonoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflamos el layout
        View v=inflater.inflate(R.layout.telefono_layoutvistacontacto,viewGroup,false);
        //devolvemos el viewholder
        return new TelefonoViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull TelefonoViewHolder telefonoViewHolder, int i) {
        //Vinculamos los datos
        telefonoViewHolder.telefono.setText(telefs.get(i).getTelefono()+"");
        telefonoViewHolder.imagen.setImageResource(R.drawable.telefono_redondo);
    }

    @Override
    public int getItemCount() {

        return telefs.size();
    }

    public void setdata(List<Telefono> nuevaLista){
        this.telefs=nuevaLista;
        notifyDataSetChanged();
    }
    class TelefonoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView telefono;
        ImageView imagen;
        tfAdapterContactoVista telAdapter;
        public TelefonoViewHolder(@NonNull View itemView,tfAdapterContactoVista adapter) {
            super(itemView);
            telefono=itemView.findViewById(R.id.txtvw_telefono_adapter);
            imagen=itemView.findViewById(R.id.telefonoImagenView);
            telAdapter=adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            Context context=v.getContext();
            Toast.makeText(itemView.getContext(),telefs.get(pos).getTelefono()+"",Toast.LENGTH_SHORT).show();
            Uri llamarA=Uri.parse("tel:"+telefs.get(pos).getTelefono());
            //Usar un ACTION_DIAL no un PHONE CALL.
            Intent intent=new Intent(Intent.ACTION_DIAL,llamarA);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this intent!");
            }

            //itemView.getContext().startActivity(intent);
        }
    }
}
