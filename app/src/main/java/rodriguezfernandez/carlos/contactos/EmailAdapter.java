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

import rodriguezfernandez.carlos.contactos.Data.Email;
import rodriguezfernandez.carlos.contactos.Data.Telefono;

public class EmailAdapter extends Adapter<EmailAdapter.EmailViewHolder> {
    private ArrayList<Email> emailList;
    private LayoutInflater inflater;
    EmailAdapter(Context context, ArrayList<Email>emailsListaImportada){
        inflater=LayoutInflater.from(context);
        emailList =emailsListaImportada;
    }
    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflamos el layout
        View v=inflater.inflate(R.layout.email_layout,viewGroup,false);
        //devolvemos el viewholder
        return new EmailViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder EmailViewHolder, int i) {
        //Vinculamos los datos
        EmailViewHolder.email.setText(emailList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return emailList.size();
    }
    class EmailViewHolder extends RecyclerView.ViewHolder{
        TextView email;
        EmailAdapter emailAdapter;
        public EmailViewHolder(@NonNull View itemView,EmailAdapter adapter) {
            super(itemView);
            email=itemView.findViewById(R.id.emailTxtVw);
            emailAdapter=adapter;
        }

    }
}
