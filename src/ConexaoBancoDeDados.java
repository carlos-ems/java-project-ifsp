import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexaoBancoDeDados {
    private static final String urlDeConexao = "jdbc:sqlite:tarefas.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(urlDeConexao);
    }
}
