package br.com.fatecpg.mygrades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> disciplinas = new  ArrayList<>();
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.optionsListView);
        LerDisciplinas();
        RefreshList();
        ListenerList();

    }

    @Override
    protected void onResume() {
        super.onResume();

        LerDisciplinas();
        RefreshList();

    }

    public EditText getEditText(int id){
        EditText e = (EditText) findViewById(id);
        return e;
    }

    public void inserir(View view){
        String newDisc;

        hideKeyboard(view);

        if (!(getEditText(R.id.editNewDisc).getText().toString().length() == 0)) {

            newDisc = getEditText(R.id.editNewDisc).getText().toString();

            disciplinas.add(newDisc);

            /*
            +++++++++++++++++++++++++++++++++++++++++++

            CRIAR O NOVO ARQUIVO DE DISCIPLINA

            +++++++++++++++++++++++++++++++++++++++++++
            */

            RefreshList();

            Toast.makeText(getApplicationContext(),"Adicionado " + newDisc,Toast.LENGTH_LONG).show();


        }

    }

    /**
     * Adiciona ao array o nome das disciplinas guardadas nos arquivos
     */
    private void LerDisciplinas(){
        disciplinas.clear();

        /*
        +++++++++++++++++++++++++++++++++++++++++++

        LER TODOS OS ARQUIVOS DE DISCIPLINA E ADICIONAR NO ARRAY

        +++++++++++++++++++++++++++++++++++++++++++
        */

        for(int i = 1; i<=3; i++){
            disciplinas.add("Opção " + i);
        }
    }

    /**
     * Atualiza o Listview
     */
    private void RefreshList(){

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this
                , android.R.layout.simple_list_item_1
                , disciplinas
        );

        list.setAdapter(aa);
    }

    /**
     * Monitora o ListView
     */
    private void ListenerList(){

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                CallDetalhes(disciplinas, i);
            }
        });
    }

    /**
     * Chama a activity detalhes.
     * @param array Disciplinas
     * @param index Índice
     */
    private void CallDetalhes(ArrayList<String> array, int index){
        Intent detalhes = new Intent(getApplicationContext(), Detalhes.class);
        detalhes.putExtra("idDisc", index);
        detalhes.putExtra("disciplinas", array);
        startActivity(detalhes);
    }

    /**
     * Oculta o teclado
     * @param view
     */
    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}