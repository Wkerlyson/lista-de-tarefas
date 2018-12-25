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
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.etTarefa);

        //Recupera tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaAtual != null)
            editTarefa.setText(tarefaAtual.getNomeTarefa());

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
                String nomeTarefa = editTarefa.getText().toString();

                if (tarefaAtual != null){ //edição
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        if (dao.atualizar(tarefa)){
                            finish();
                            Toast.makeText(AdicionarTarefaActivity.this, "Tarefa salva", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AdicionarTarefaActivity.this, "Erro ao atualizar a tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{ //salvar

                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if (dao.salvar(tarefa)){
                            finish();
                            Toast.makeText(AdicionarTarefaActivity.this, "Tarefa salva", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AdicionarTarefaActivity.this, "Erro ao salvar a tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AdicionarTarefaActivity.this, "Informe o nome da tarefa", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
