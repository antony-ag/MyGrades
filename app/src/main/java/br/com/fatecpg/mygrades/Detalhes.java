package br.com.fatecpg.mygrades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Detalhes extends AppCompatActivity {
    private ArrayList<String> disciplinas = new  ArrayList<>();
    private int idDisc;
    SharedPreferences pref;
    File[] dirFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //Recupera os dados passados pela main
        Intent main = getIntent();
        idDisc = main.getIntExtra("idDisc", 0); //Este é o índice do array, corresponde a disciplina clicada no main activity
        disciplinas = main.getStringArrayListExtra("disciplinas"); //Dados do ListView


        TextView txtdisc = (TextView)findViewById(R.id.textDisc);
        txtdisc.setText("Disciplina: " + disciplinas.get(idDisc));

        refreshNotas();
    }

    public EditText getEditText(int id){
        EditText e = (EditText) findViewById(id);
        return e;
    }

    /**
     * Exclui o arquivo da disciplina
     * @param view
     */
    public void excluir(View view){
/*
        +++++++++++++++++++++++++++++++++++++++++++

        EXCLUIR O ARQUIVO DE DISCIPLINA

        +++++++++++++++++++++++++++++++++++++++++++
        */
        File dir = getFilesDir();
        File file = new File(dir, disciplinas.get(idDisc));
        boolean deleted = file.delete();

        Toast.makeText(
            getApplicationContext()
            , "Disciplina " + disciplinas.get(idDisc) + " removida!"
            , Toast.LENGTH_SHORT
        ).show();
        disciplinas.remove(idDisc);

        finish();
    }

    /**
     * Grava as notas no arquivo da disciplina
     * @param view
     */
    public void gravar(View view){
        float p1, p2;

        try {
            p1 = Float.parseFloat(getEditText(R.id.edP1).getText().toString());

        } catch (Exception ex){ p1 = 0; }

        try {
            p2 = Float.parseFloat(getEditText(R.id.edP2).getText().toString());
        } catch (Exception ex){ p2 = 0; }


        if(p1 < 0 || p1 > 10 || p2 < 0 || p2 > 10){
            Toast.makeText(
                    getApplicationContext()
                    , "As notas devem ser de 0 a 10!"
                    , Toast.LENGTH_SHORT
            ).show();
        } else {
            Toast.makeText(
                    getApplicationContext()
                    , "Notas salvas com sucesso!"
                    , Toast.LENGTH_SHORT
            ).show();

            SharedPreferences.Editor editor = pref.edit();
            editor.putFloat("p1", p1);
            editor.putFloat("p2", p2);
            editor.commit();
            refreshNotas();
        }
    }

    public void refreshNotas(){
        pref = this.getSharedPreferences(disciplinas.get(idDisc), Context.MODE_PRIVATE);

        try {
            float p1 = pref.getFloat("p1", 0);
            float p2 = pref.getFloat("p2", 0);

            TextView etp1 = (TextView)findViewById(R.id.textP1);
            TextView etp2 = (TextView)findViewById(R.id.textP2);
            etp1.setText("Nota da P1 -> " + p1);
            etp2.setText("Nota da P2 -> " + p2);

            EditText ed1 = (EditText)findViewById(R.id.edP1);
            EditText ed2 = (EditText)findViewById(R.id.edP2);
            ed1.setText(""+p1);
            ed2.setText(""+p2);

        }catch (Exception Ex){}
    }

}
