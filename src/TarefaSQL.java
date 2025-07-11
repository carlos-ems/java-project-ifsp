import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaSQL {

    public TarefaSQL() {
        criarTabela();
    }

    private void criarTabela() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tarefas (
                nome_tarefa TEXT PRIMARY KEY,
                texto TEXT NOT NULL,
                concluido BOOLEAN NOT NULL,
                data_alteracao TEXT NOT NULL
            );
        """;

        try (Connection connection = ConexaoBD.conectar();
             Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserir(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (nome_tarefa, texto, concluido, data_alteracao) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, tarefa.getNomeTarefa());
            pstmt.setString(2, tarefa.getTexto());
            pstmt.setBoolean(3, tarefa.isConcluido());
            pstmt.setString(4, tarefa.getDataAlteracao().toString());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tarefa> listarTodas() {
        return listarPorFiltro("TODAS");
    }

    public List<Tarefa> listarPorFiltro(String filtro) {
        String sql = "SELECT * FROM tarefas";

        if (filtro.equalsIgnoreCase("PENDENTES")) {
            sql += " WHERE concluido = 0";
        } else if (filtro.equalsIgnoreCase("CONCLUIDAS")) {
            sql += " WHERE concluido = 1";
        }

        List<Tarefa> lista = new ArrayList<>();

        try (Connection connection = ConexaoBD.conectar();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                    rs.getString("nome_tarefa"),
                    rs.getString("texto"),
                    rs.getBoolean("concluido"),
                    LocalDateTime.parse(rs.getString("data_alteracao"))
                );
                lista.add(tarefa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarStatus(String nomeTarefa, boolean concluido) {
        String sql = "UPDATE tarefas SET concluido = ?, data_alteracao = ? WHERE nome_tarefa = ?";

        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setBoolean(1, concluido);
            pstmt.setString(2, LocalDateTime.now().toString());
            pstmt.setString(3, nomeTarefa);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
