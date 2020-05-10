package dao;

import java.sql.*;
import java.util.*;

import beans.BeanCategoria;
import beans.BeanCursoProduto;
import connection.SingleConnection;

public class DaoProduto {
	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoProduto produto) {
		try {
			String sql = "insert into produto (nome,quantidade,valor, categoria_id) values (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setString(2, produto.getQuantidade());
			insert.setString(3, produto.getValor());
			insert.setLong(4, produto.getCategoria_id());
			insert.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BeanCursoProduto> listar() throws Exception{
		List<BeanCursoProduto> listar = new ArrayList<BeanCursoProduto>();
		String sql = "select * from produto order by id";
		PreparedStatement show = connection.prepareStatement(sql);
		ResultSet set = show.executeQuery();
		while(set.next()) {
			BeanCursoProduto produto = new BeanCursoProduto();
			produto.setId(set.getLong("id"));
			produto.setNome(set.getString("nome"));
			produto.setQuantidade(set.getString("quantidade"));
			produto.setValor(set.getString("valor"));
			produto.setCategoria_id(set.getLong("categoria_id"));
			
			listar.add(produto);
		}
		return listar;
	}
	
	public List<BeanCategoria> listaCategorias() throws Exception{
		List<BeanCategoria> retorno = new ArrayList<BeanCategoria>();
		String sql = "select * from categoria";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			BeanCategoria categoria = new BeanCategoria();
			categoria.setId(resultSet.getLong("id"));
			categoria.setNome(resultSet.getString("nome"));
			
			retorno.add(categoria);
		}
		return retorno;
	}
	
	public BeanCursoProduto consultar(String id) throws Exception{
		String sql = "select * from produto where id='" +id+"'";
		PreparedStatement consultar = connection.prepareStatement(sql);
		ResultSet set = consultar.executeQuery();
		if(set.next()) {
			BeanCursoProduto produto = new BeanCursoProduto();
			produto.setId(set.getLong("id"));
			produto.setNome(set.getString("nome"));
			produto.setQuantidade(set.getString("quantidade"));
			produto.setValor(set.getString("valor"));
			produto.setCategoria_id(set.getLong("categoria_id"));
			
			return produto;
		}
		return null;
	}
	
	public void deletar(String id) {
		try {
			String sql = "delete from produto where id=" + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
				connection.commit();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void atualizar(BeanCursoProduto produto) {
		try {
			String sql = "update produto set nome = ?, quantidade = ?, valor = ?, categoria_id =? where id=" + produto.getId();
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, produto.getNome());
			update.setString(2, produto.getQuantidade());
			update.setString(3,  produto.getValor());
			update.setLong(4, produto.getCategoria_id());
			update.executeUpdate();
			connection.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/*public boolean validarProduto(String id) throws Exception{
		String sql = "Select count(1) as qtd from produto where id='" +id+"'";
		PreparedStatement consultar = connection.prepareStatement(sql);
		ResultSet resultSet = consultar.executeQuery();
		if (resultSet.next()) {

			// Return true
			return resultSet.getInt("qtd") <= 0;
		}
		return false;
	}*/
}
