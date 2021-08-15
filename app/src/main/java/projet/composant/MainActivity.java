package projet.composant;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    String status_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,new IntentFilter("cour_item"));
        getAll();

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            status_request = intent.getStringExtra("status");
            int id = Integer.parseInt(intent.getStringExtra("id"));


            if (status_request.equals("edit")){
                nomEnseignant_edt.setText(intent.getStringExtra("nom_ens"));
                filiere_edt.setText(intent.getStringExtra("filiere"));
                classe_edt.setText(intent.getStringExtra("classe"));
                creneau_edt.setText(intent.getStringExtra("creneau"));
                matiere_edt.setText(intent.getStringExtra("matiere"));
                init();
                MainActivity.this.programmer.setId(id);

            }else {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle("Attention");
                dialogBuilder.setMessage("Voulez vous reelement effectuer la supression ?");
                dialogBuilder.setPositiveButton("Oui", (dialog, which) -> {
                    deleteProgramme(id);
                    dialog.dismiss();
                });

                dialogBuilder.setNeutralButton("Non", (dialog, which) -> dialog.dismiss());
                dialogBuilder.create().show();
            }
        }
    };


    private void createProgramme(Cours p){
        programmerService = ApiUtils.getProgrammerService();
        Call<Cours> call = programmerService.apiCreate(p);
        call.enqueue(new Callback<Cours>() {
            @Override
            public void onResponse(Call<Cours> call, Response<Cours> response) {
                if(response.isSuccessful()){
                    makeText(MainActivity.this, "Ajouter succes!", Toast.LENGTH_SHORT).show();
                    getAll();
                    nomEnseignant_edt.setText(null);
                    matiere_edt.setText(null);
                    filiere_edt.setText(null);
                    classe_edt.setText(null);
                    creneau_edt.setText(null);
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

    private void updateProgramme(){

        programmerService = ApiUtils.getProgrammerService();
        this.programmer.setEns(nomEnseignant_edt.getText().toString());
        this.programmer.setClasse(classe_edt.getText().toString());
        this.programmer.setFiliere(filiere_edt.getText().toString());
        this.programmer.setMatiere(matiere_edt.getText().toString());
        this.programmer.setVh(creneau_edt.getText().toString());
        Call<Cours> call = programmerService.apiUpdate(programmer.getId(),programmer);
        call.enqueue(new Callback<Cours>() {
            @Override
            public void onResponse(Call<Cours> call, Response<Cours> response) {
                int status = response.code();
                if(status == 200){
                    makeText(MainActivity.this, "Mise a jour avec succes!", Toast.LENGTH_SHORT).show();
                    getAll();
                    nomEnseignant_edt.setText(null);
                    matiere_edt.setText(null);
                    filiere_edt.setText(null);
                    classe_edt.setText(null);
                    creneau_edt.setText(null);
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
                int status = response.code();
                if(response.isSuccessful()){
                    makeText(MainActivity.this, "Supprimer avec succes!", Toast.LENGTH_SHORT).show();
                    getAll();
                }

            }
            @Override
            public void onFailure(Call<Cours> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    private void init(){
        this.programmer=new Cours();
    }

    private  void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @OnClick({R.id.save})
    public void managedAction(View view){
        if(view.getId()==R.id.save){


            if (status_request=="edit"){
                //si tous les champs sont remplis valider la requette
                if(validateEns() && validateFiliere() && validateClasse() && validateMatiere() && validateVh()){
                    updateProgramme();
                    status_request=null;
                    getAll();
                }
            }else {
                //si tous les champs sont remplis valider la requette
                if(validateEns() && validateFiliere() && validateClasse() && validateMatiere() && validateVh()){
                    createProgramme(programmer);
                    status_request=null;
                    getAll();
                }

            }

        }

    }

    public boolean validateEns(){
        String ens = nomEnseignant_edt.getText().toString();

        if (ens.isEmpty()){
            Toast.makeText(this,"Veillez entrer le nom de l'enseignant",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
    public boolean validateFiliere(){
        String filiere = filiere_edt.getText().toString();

        if (filiere.isEmpty()){
            Toast.makeText(this,"Veillez entrer la filiere",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
    public boolean validateMatiere(){
        String matiere = matiere_edt.getText().toString();

        if (matiere.isEmpty()){
            Toast.makeText(this,"Veillez entrer la matiere",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
    public boolean validateClasse(){
        String classe = classe_edt.getText().toString();

        if (classe.isEmpty()){
            Toast.makeText(this,"Veillez entrer la classe",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
    public boolean validateVh(){
        String vh = creneau_edt.getText().toString();

        if (vh.isEmpty()){
            Toast.makeText(this,"Veillez entrer le creneau",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }
}