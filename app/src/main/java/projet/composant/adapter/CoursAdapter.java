package projet.composant.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projet.composant.MainActivity;
import projet.composant.R;
import projet.composant.model.Cours;
import projet.composant.utils.Global;

import static android.widget.Toast.makeText;

public class CoursAdapter extends ArrayAdapter<Cours> {

    private Context context;
    private List<Cours> cours;

    public CoursAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Cours> objects) {
        super(context, resource, objects);
        this.context = context;
        this.cours = objects;
    }

    @BindView(R.id.nom_ens)
    TextView nom_ens;

    @Override
    public int getPosition(@Nullable Cours item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(final int pos, View view, ViewGroup parent){
        ViewHolder holder;
        if(view !=null){
            holder = (ViewHolder) view.getTag();
        }else{
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_cours_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        if(pos %2 == 0){
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{

            view.setBackgroundColor(Color.parseColor("#cfd8dc"));
        }
        holder.nom_ens.setText(String.format("Nom Enseignant: %s", cours.get(pos).getEns()));
        holder.filiere.setText(String.format("Filiere: %s", cours.get(pos).getFiliere()));
        holder.matiere.setText(String.format("Matiere: %s", cours.get(pos).getMatiere()));
        holder.classe.setText(String.format("Classe: %s", cours.get(pos).getClasse()));
        holder.creneau.setText(String.format("Creneau: %s", cours.get(pos).getVh()));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("cour_item");
                intent.putExtra("id", ""+cours.get(pos).getId());
                intent.putExtra("nom_ens", cours.get(pos).getEns());
                intent.putExtra("filiere", cours.get(pos).getFiliere());
                intent.putExtra("classe", cours.get(pos).getClasse());
                intent.putExtra("matiere", cours.get(pos).getMatiere());
                intent.putExtra("creneau", cours.get(pos).getVh());
                intent.putExtra("status", "edit");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("cour_item");
                intent.putExtra("id", ""+cours.get(pos).getId());
                intent.putExtra("status", "del");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        /*
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return view;
    }

    static final class ViewHolder{
        @BindView(R.id.nom_ens)
        TextView nom_ens;
        @BindView(R.id.filiere)
        TextView filiere;
        @BindView(R.id.classe)
        TextView classe;
        @BindView(R.id.matiere)
        TextView matiere;
        @BindView(R.id.creneau)
        TextView creneau;
        @BindView(R.id.del)
        Button del;
        @BindView(R.id.edit)
        Button edit;
        ViewHolder (View view){
            ButterKnife.bind(this,view);
        }
    }
}
