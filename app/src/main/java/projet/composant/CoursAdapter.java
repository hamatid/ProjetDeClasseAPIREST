package projet.composant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursHolder> {
    private List<CoursModel> coursModelList;

    public CoursAdapter(List<CoursModel> coursModelList) {
        this.coursModelList = coursModelList;
    }

    @NonNull
    @Override
    public CoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cours,parent,false);
        return new CoursHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursAdapter.CoursHolder holder, int position) {

        holder.nomEnseignant_txt.setText(coursModelList.get(position).getNomEnseignant());
        holder.classe_txt.setText(coursModelList.get(position).getNomClasse());
        holder.creneau_txt.setText(coursModelList.get(position).getNomCreneau());
        holder.matiere_txt.setText(coursModelList.get(position).getNomMatiere());
        holder.filiere_txt.setText(coursModelList.get(position).getNomFiliere());
    }

    @Override
    public int getItemCount() {
        return coursModelList.size();
    }

    public class CoursHolder extends RecyclerView.ViewHolder {
        TextView nomEnseignant_txt, filiere_txt,classe_txt,matiere_txt,creneau_txt;
        public CoursHolder(@NonNull View itemView) {
            super(itemView);
            nomEnseignant_txt = (TextView) itemView.findViewById(R.id.nomprofesseur_item_txt);
            filiere_txt = (TextView) itemView.findViewById(R.id.filiere_item_txt);
            classe_txt = (TextView) itemView.findViewById(R.id.classe_item_txt);
            matiere_txt = (TextView) itemView.findViewById(R.id.matiere_item_txt);
            creneau_txt = (TextView) itemView.findViewById(R.id.creneau_item_txt);
        }
    }

}
