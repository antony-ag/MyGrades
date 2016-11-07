package br.com.fatecpg.mygrades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Detalhes extends AppCompatActivity {
    private ArrayList<String> disciplinas = new  ArrayList<>();
    private int idDisc;
    SharedPreferences pref;

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

        pref = this.getSharedPreferences(disciplinas.get(idDisc), Context.MODE_PRIVATE);






        try {
            float p1 = pref.getFloat("p1", 0);
            float p2 = pref.getFloat("p2", 0);

            EditText etp1 = (EditText)findViewById(R.id.txtP1);
            EditText etp2 = (EditText)findViewById(R.id.txtP2);
            etp1.setText("Nota da P1 -> " + p1);
            etp2.setText("Nota da P2 -> " + p2);

        }catch (Exception Ex){}




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

    /**
     * Grava as notas no arquivo da disciplina
     * @param view
     */
    public void gravar(View view){
        float p1, p2;


        try {
            p1 = Float.parseFloat(getEditText(R.id.txtP1).getText().toString());

        } catch (Exception ex){ p1 = 0; }

        try {
            p2 = Float.parseFloat(getEditText(R.id.txtP2).getText().toString());
        } catch (Exception ex){ p2 = 0; }

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

        //int qtde = pref.getInt("qtde", 0)+1;
        //float soma = (float) (pref.getFloat("soma", 0)+result);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat("p1", p1);
        editor.putFloat("p2", p2);
        editor.commit();




    }

}
