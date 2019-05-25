package rodriguezfernandez.carlos.contactos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
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

import java.util.List;

import rodriguezfernandez.carlos.contactos.Data.Email;

import static rodriguezfernandez.carlos.contactos.MainActivity.*;

public class emAdapterContactoVista extends Adapter<emAdapterContactoVista.EmailViewHolder> {
    private List<Email> emails;
    private LayoutInflater inflater;
    String usuario;
    String subject;
    emAdapterContactoVista(Context context, List<Email>emailsListaImportada){
        inflater=LayoutInflater.from(context);
        emails =emailsListaImportada;
        SharedPreferences shpref=android.support.v7.preference.PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        usuario=shpref.getString(MainActivity.KEY_NOMBRE,"Usuario");
        subject=shpref.getString(MainActivity.KEY_SUBJECT,"Mensaje enviado desde PMDM app");
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflamos el layout
        View v=inflater.inflate(R.layout.email_layoutvistacontacto,viewGroup,false);
        //devolvemos el viewholder
        return new EmailViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder emailViewHolder, int i) {
        //Vinculamos los datos
        emailViewHolder.email.setText(emails.get(i).getEmail()+"");
        emailViewHolder.imagen.setImageResource(R.drawable.sobre_redondo);
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public void setdata(List<Email> nuevaLista){
        this.emails =nuevaLista;
        notifyDataSetChanged();
    }

    class EmailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView email;
        ImageView imagen;
        emAdapterContactoVista emAdapter;
        public EmailViewHolder(@NonNull View itemView, emAdapterContactoVista adapter) {
            super(itemView);
            email=itemView.findViewById(R.id.emailTxtVw);
            imagen=itemView.findViewById(R.id.imageViewmail);
            emAdapter=adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos=getAdapterPosition();
            Context context=v.getContext();
            Toast.makeText(itemView.getContext(), emails.get(pos).getEmail()+"",Toast.LENGTH_SHORT).show();
            Uri llamarA=Uri.parse("mailto:"+ emails.get(pos).getEmail());

            //Usar un ACTION_DIAL no un PHONE CALL.
            Intent intent=new Intent(Intent.ACTION_SENDTO,llamarA);
            intent.putExtra(Intent.EXTRA_SUBJECT,subject+context.getString(R.string.autor)+usuario);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//Me da un error en mi movil. Que necesita ese Flag el intent. ?¿?¿

            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
                context.startActivity(intent);

            } else {
                Log.d("ImplicitIntents", "Can't handle this intent!");
            }

            //itemView.getContext().startActivity(intent);
        }
    }
}
