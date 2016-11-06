package br.com.fatecpg.mygrades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

    public EditText getEditText(int id){
        EditText e = (EditText) findViewById(id);
        return e;
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

    public void gravar(View view){
        double p1, p2;

        try { p1 = Double.parseDouble(getEditText(R.id.txtP1).getText().toString()); } catch (Exception ex){ p1 = 0; }
        try { p2 = Double.parseDouble(getEditText(R.id.txtP2).getText().toString()); } catch (Exception ex){ p2 = 0; }

        Toast.makeText(
                getApplicationContext()
                , "GRAVAR AS NOTAS NO ARQUIVO." + " ||p1->" + p1 + " ||p2->" + p2
                , Toast.LENGTH_SHORT
        ).show();


        /*
        +++++++++++++++++++++++++++++++++++++++++++

        GRAVAR OS DADOS NO ARQUIVO DE DISCIPLINA

        +++++++++++++++++++++++++++++++++++++++++++
        */

    }

}
