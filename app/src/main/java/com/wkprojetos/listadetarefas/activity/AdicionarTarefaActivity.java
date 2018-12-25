package com.wkprojetos.listadetarefas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.wkprojetos.listadetarefas.R;
import com.wkprojetos.listadetarefas.helper.TarefaDAO;
import com.wkprojetos.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.etTarefa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:
                    TarefaDAO dao = new TarefaDAO(getApplicationContext());
                    Tarefa tarefa = new Tarefa();

                    String nomeTarefa = editTarefa.getText().toString();

                if (!nomeTarefa.isEmpty()) {
                    tarefa.setNomeTarefa(nomeTarefa);
                    dao.salvar(tarefa);
                    finish();
                }else{
                    Toast.makeText(AdicionarTarefaActivity.this, "Informe o nome da tarefa", Toast.LENGTH_SHORT).show();
                }

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
