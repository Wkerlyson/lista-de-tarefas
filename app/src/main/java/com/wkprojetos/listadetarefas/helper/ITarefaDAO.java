package com.wkprojetos.listadetarefas.helper;

import com.wkprojetos.listadetarefas.model.Tarefa;

import java.util.List;

public interface ITarefaDAO {
    boolean salvar(Tarefa tarefa);
    boolean atualizar(Tarefa tarefa);
    boolean deletar(Tarefa tarefa);
    List<Tarefa> listar();
}
