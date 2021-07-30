package projet.composant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projet.composant.adapter.CoursAdapter;
import projet.composant.model.Cours;
import projet.composant.network.ApiUtils;
import projet.composant.service.ProgrammerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.filiere_edt) EditText filiere_edt;
    @BindView(R.id.enseignant_edt) EditText nomEnseignant_edt;
    @BindView(R.id.classe_edt) EditText classe_edt;
    @BindView(R.id.matiere_edt) EditText matiere_edt;
    @BindView(R.id.creneau_edt) EditText creneau_edt;
    @BindView(R.id.listView)
    ListView listView;


    List<Cours> list = new ArrayList<Cours>();
    ProgrammerService programmerService;
    Cours programmer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("cour_item"));

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            makeText(context,intent.getStringExtra("nom_ens"),Toast.LENGTH_LONG).show();
        }
    };


    private void createProgramme(Cours p){
        programmerService = ApiUtils.getProgrammerService();
        Call<Cours> call = programmerService.apiCreate(p);
        call.enqueue(new Callback<Cours>() {
            @Override
            public void onResponse(Call<Cours> call, Response<Cours> response) {
                if(response.isSuccessful()){
                    makeText(MainActivity.this, "Programme created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cours> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void getAll(){
        programmerService = ApiUtils.getProgrammerService();
        Call<List<Cours>> call = programmerService.apiRead();
        call.enqueue(new Callback<List<Cours>>() {
            @Override
            public void onResponse(Call<List<Cours>> call, Response<List<Cours>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new CoursAdapter(MainActivity.this, R.layout.fragment_cours_item, list));
                }
            }

            @Override
            public void onFailure(Call<List<Cours>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateProgramme(int id, Cours p){
        programmerService = ApiUtils.getProgrammerService();
        Call<Cours> call = programmerService.apiUpdate(id, p);
        call.enqueue(new Callback<Cours>() {
            @Override
            public void onResponse(Call<Cours> call, Response<Cours> response) {
                if(response.isSuccessful()){
                    makeText(MainActivity.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cours> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void deleteProgramme(int id){
        programmerService = ApiUtils.getProgrammerService();
        Call<Cours> call = programmerService.apiDelete(id);
        call.enqueue(new Callback<Cours>() {
            @Override
            public void onResponse(Call<Cours> call, Response<Cours> response) {
                if(response.isSuccessful()){
                    makeText(MainActivity.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cours> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void init(){
        programmer=new Cours();
        programmer.setNom_enseignant(nomEnseignant_edt.getText().toString());
        programmer.setClasse(classe_edt.getText().toString());
        programmer.setFiliere(filiere_edt.getText().toString());
        programmer.setMatiere(matiere_edt.getText().toString());
        programmer.setVh(creneau_edt.getText().toString());
    }

    @OnClick({R.id.ajouter_btn,R.id.modifier_btn,R.id.supprimer_btn,R.id.rechercher_btn})
    public void managedAction(View view){
        if(view.getId()==R.id.ajouter_btn){
            init();
            createProgramme(programmer);
            getAll();
        }

        if(view.getId()==R.id.modifier_btn){
            init();
            updateProgramme(programmer.getId(),programmer);
        }

        if(view.getId()==R.id.supprimer_btn){
            deleteProgramme(programmer.getId());
        }

        if(view.getId()==R.id.rechercher_btn){
            getAll();
        }


    }
}