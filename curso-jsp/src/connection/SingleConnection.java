package connection;
// Essa classe irá criar uma conexão da linguagem de programação com o banco de dados
import java.sql.*;

public class SingleConnection {
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				// O COMMIT define que os dados serão salvos e/ou persistidos definitivamente no banco de dados após o SQL ser executado na transação
				// O SETAUTOCOMMIT define que a API, biblioteca ou framework não irá salva os dados automaticamente no banco de dados após
				//-- o SQL ser rodado no banco de dados e precisamos fazer isso manualmente invocando o commit
				connection.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com banco de dados");
		}
	}
	public static Connection getConnection() {
		return connection;
	}

}
