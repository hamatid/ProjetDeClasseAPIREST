package projet.composant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText nomEnseignant_edt, filiere_edt,classe_edt,matiere_edt,creneau_edt;
Button ajouter, supprimer, modifier,afficher;
String nomEnseignant, nomFiliere, nomClasse, nomMatiere,nomCreneau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomEnseignant_edt = findViewById(R.id.enseignant_edt);
        filiere_edt = findViewById(R.id.filiere_edt);
        classe_edt = findViewById(R.id.classe_edt);
        matiere_edt = findViewById(R.id.matiere_edt);
        creneau_edt = findViewById(R.id.creneau_edt);

        ajouter = findViewById(R.id.ajouter_btn);
        supprimer = findViewById(R.id.supprimer_btn);
        modifier = findViewById(R.id.modifier_btn);
        afficher = findViewById(R.id.afficher_btn);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomEnseignant = nomEnseignant_edt.getText().toString();
                nomFiliere = filiere_edt.getText().toString();
                nomClasse = classe_edt.getText().toString();
                nomMatiere = matiere_edt.getText().toString();
                nomCreneau = creneau_edt.getText().toString();

                Toast.makeText(getApplicationContext(),nomEnseignant+" "+nomFiliere+" "+nomClasse+" "+nomMatiere+" "+nomCreneau,Toast.LENGTH_LONG).show();
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}