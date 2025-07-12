import java.util.List;

public class TarefaService {
    private final TarefaSQL consulta = new TarefaSQL();

    public void adicionarTarefa(String nomeTarefa, String descricao) {
        Tarefa tarefa = new Tarefa(nomeTarefa, descricao);
        consulta.inserir(tarefa);
    }

    public List<Tarefa> listarTodas() {
        return consulta.listarTodas();
    }

    public List<Tarefa> listarPendentes() {
        return consulta.listarPorFiltro("PENDENTES");
    }

    public List<Tarefa> listarConcluidas() {
        return consulta.listarPorFiltro("CONCLUIDAS");
    }

    public void alterarStatus(String nomeTarefa, boolean concluido) {
        consulta.atualizarStatus(nomeTarefa, concluido);
    }
}