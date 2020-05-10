package dao;

import java.sql.*;

import connection.SingleConnection;

public class DaoLogin {
	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	// Método que irá validar o login
	public boolean validarLogin(String login, String senha) throws Exception {
		String sql = "select*from usuario where login = '"+login+"' and senha = '"+senha+"'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true;
		}else {
			return false;
		}
	}
}
