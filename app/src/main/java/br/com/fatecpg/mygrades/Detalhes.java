package br.com.fatecpg.mygrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Detalhes extends AppCompatActivity {
    private ArrayList<String> disciplinas = new  ArrayList<>();
    private int idDisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //Recupera os dados passados pela main
        Intent main = getIntent();
        int idDisc = main.getIntExtra("idDisc", 0); //Este é o índice do array, corresponde a disciplina clicada no main activity
        disciplinas = main.getStringArrayListExtra("disciplinas"); //Dados do ListView


        Toast.makeText(
            getApplicationContext()
            , "Você escolheu a opção " + disciplinas.get(idDisc)
            , Toast.LENGTH_SHORT
        ).show();

    }

    public void excluir(View view){

        Toast.makeText(
            getApplicationContext()
            , "Disciplina " + disciplinas.get(idDisc) + " removida!"
            , Toast.LENGTH_SHORT
        ).show();
        disciplinas.remove(idDisc);

        /*
        +++++++++++++++++++++++++++++++++++++++++++

        EXCLUIR O ARQUIVO DE DISCIPLINA

        +++++++++++++++++++++++++++++++++++++++++++
        */

        finish();
    }

    public void gravar(){
        Toast.makeText(
                getApplicationContext()
                , "GRAVAR AS NOTAS NO ARQUIVO."
                , Toast.LENGTH_SHORT
        ).show();

        /*
        +++++++++++++++++++++++++++++++++++++++++++

        GRAVAR OS DADOS NO ARQUIVO DE DISCIPLINA

        +++++++++++++++++++++++++++++++++++++++++++
        */

    }

}
