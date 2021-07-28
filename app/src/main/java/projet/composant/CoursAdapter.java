package projet.composant;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursHolder> {


    @NonNull
    @Override
    public CoursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursAdapter.CoursHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CoursHolder extends RecyclerView.ViewHolder {


        public CoursHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
