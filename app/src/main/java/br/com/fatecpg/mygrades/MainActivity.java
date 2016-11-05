package br.com.fatecpg.mygrades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> disciplinas = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 1; i<=10; i++){
            disciplinas.add("Opção " + i);
        }

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                this
                , android.R.layout.simple_list_item_1
                , disciplinas
        );

        ListView list = (ListView)findViewById(R.id.optionsListView);
        list.setAdapter(aa);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(
                        getApplicationContext()
                        , "Você escolheu a opção " + (i+1)
                        , Toast.LENGTH_SHORT
                ).show();
            }
        });


    }
}
