import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private String nomeTarefa;
    private String texto;
    private boolean concluido;
    private LocalDateTime dataAlteracao;

    public Tarefa(String nomeTarefa, String texto) {
        this.nomeTarefa = nomeTarefa;
        this.texto = texto;
        this.concluido = false;
        this.dataAlteracao = LocalDateTime.now();
    }

    public Tarefa(String nomeTarefa, String texto, boolean concluido, LocalDateTime dataAlteracao) {
        this.nomeTarefa = nomeTarefa;
        this.texto = texto;
        this.concluido = concluido;
        this.dataAlteracao = dataAlteracao;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public String getTexto() {
        return texto;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
        this.dataAlteracao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format(
            "Nome da Tarefa: %s | Descrição: %s | Status: [%s] | Data de Alteração: %s",
            nomeTarefa,
            texto,
            concluido ? "X" : " ",
            dataAlteracao.format(formatter)
        );
    }
}
