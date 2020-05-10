package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJspFone;
import connection.SingleConnection;

public class DaoTelefone {
	private Connection connection;

	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJspFone telefone) {
		try {
			String sql = "insert into telefone (numero,tipo,usuario) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BeanCursoJspFone> listar(Long user) throws Exception {
		List<BeanCursoJspFone> listar = new ArrayList<BeanCursoJspFone>();
		String sql = "select * from telefone where usuario = " + user;
		PreparedStatement show = connection.prepareStatement(sql);
		ResultSet set = show.executeQuery();
		while (set.next()) {
			BeanCursoJspFone telefone = new BeanCursoJspFone();
			telefone.setId(set.getLong("id"));
			telefone.setNumero(set.getString("numero"));
			telefone.setTipo(set.getString("tipo"));
			telefone.setUsuario(set.getLong("usuario"));

			listar.add(telefone);
		}
		return listar;
	}

	public void deletar(String id) {
		try {
			String sql = "delete from telefone where id=" + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
				connection.commit();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void atualizar(BeanCursoJspFone telefone) {
		try {
			String sql = "update telefone set numero = ?, tipo = ?, usuario = ? where id=" + telefone.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, telefone.getNumero());
			update.setString(2, telefone.getTipo());
			update.setLong(3, telefone.getUsuario());
			update.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/*
	 * public boolean validarProduto(String id) throws Exception{ String sql =
	 * "Select count(1) as qtd from produto where id='" +id+"'"; PreparedStatement
	 * consultar = connection.prepareStatement(sql); ResultSet resultSet =
	 * consultar.executeQuery(); if (resultSet.next()) {
	 * 
	 * // Return true return resultSet.getInt("qtd") <= 0; } return false; }
	 */
}
