package projet.composant;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import projet.composant.model.Programmer;
import projet.composant.network.ApiUtils;
import projet.composant.network.ProgrammerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.filiere_edt) EditText filiere_edt;
    @BindView(R.id.enseignant_edt) EditText nomEnseignant_edt;
    @BindView(R.id.classe_edt) EditText classe_edt;
    @BindView(R.id.matiere_edt) EditText matiere_edt;
    @BindView(R.id.creneau_edt) EditText creneau_edt;

    Button button;




    String nomEnseignant, nomFiliere, nomClasse, nomMatiere,nomCreneau;

    ProgrammerService programmerService;
    List<Programmer> list = new ArrayList<Programmer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button = (Button) findViewById(R.id.ajouter_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmerService = ApiUtils.getClient().create(ProgrammerService.class);
                Call<List<Programmer>> call = programmerService.apiRead();
                call.enqueue(new Callback<List<Programmer>>() {
                    @Override
                    public void onResponse(Call<List<Programmer>> call, Response<List<Programmer>> response) {
                        if(response.isSuccessful()){
                            list = response.body();
                            Toast.makeText(MainActivity.this,""+list,Toast.LENGTH_LONG).show();
                            //listView.setAdapter(new UserAdapter(MainActivity.this, R.layout.list_user, list));
                        }
                    }


                    @Override
                    public void onFailure(Call<List<Programmer>> call, Throwable t) {
                        Log.e("ERROR: ", t.getMessage());
                    }
                });
            }
        });


    }

}