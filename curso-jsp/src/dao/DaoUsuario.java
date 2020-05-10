package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

// Nesta classe será feito o processo de interação com o banco da dados(SALVAR,ATUALIZAR,LISTAR,CONSULTAR,DELETAR)
public class DaoUsuario {
	private Connection connection;
	// Pegando a conexão
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(BeanCursoJsp usuario) throws Exception {
		try {
			String sql = "insert into usuario (login,senha,nome,fone, email, cep, rua, bairro, cidade, estado, fotobase64, contenttype, curriculobase64 , contenttypecurriculo, fotobase64miniatura, ativo, sexo, perfil) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.setString(5, usuario.getEmail());
			insert.setString(6, usuario.getCep());
			insert.setString(7, usuario.getRua());
			insert.setString(8, usuario.getBairro());
			insert.setString(9, usuario.getCidade());
			insert.setString(10, usuario.getEstado());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.isAtivo());
			insert.setString(17, usuario.getSexo());
			insert.setString(18, usuario.getPerfil());

			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	
	public List<BeanCursoJsp> listar(String descricaoconsulta) throws SQLException{
		// Selecionar todos os campos da tabela usuario onde login é difrente de admin e lower(Retorna uma expressão de caractere depois de converter para minúsculas os dados de caracteres em maiúsculas.)
		//(nome) como lower(Retorna uma expressão de caractere depois de converter para minúsculas os dados de caracteres em maiúsculas.) **** ordenar por nome 
		String sql = "select*from usuario where login <> 'admin' and lower(nome) like lower('%"+descricaoconsulta+"%') order by nome ";
		return consultarUsuarios(sql);
	}

	public List<BeanCursoJsp> listar() throws Exception {
		String sql = "select*from usuario where login <> 'admin' ";
		return consultarUsuarios(sql);

	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			// beanCursoJsp.setFotoBase64(resultSet.getString("fotoBase64"));
			beanCursoJsp.setContentType(resultSet.getString("contentType"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));

			listar.add(beanCursoJsp);
		}
		return listar;
	}

	public void delete(String id) {
		try {
			// Deleta qualquer usuário que seja diferente do login admin
			String sql = "Delete from usuario where id = '" + id + "' and login <> 'admin' ";
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
				connection.commit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public BeanCursoJsp consultar(String id) throws Exception {
		// Consulta todos os usuários, exceto o admin
		String sql = "Select*from usuario where id='" + id + "' and login <> 'admin' ";
		PreparedStatement consultar = connection.prepareStatement(sql);
		ResultSet resultSet = consultar.executeQuery();
		if (resultSet.next()) {
			BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
			beanCursoJsp.setId(resultSet.getLong("id"));
			beanCursoJsp.setLogin(resultSet.getString("login"));
			beanCursoJsp.setSenha(resultSet.getString("senha"));
			beanCursoJsp.setNome(resultSet.getString("nome"));
			beanCursoJsp.setFone(resultSet.getString("fone"));
			beanCursoJsp.setEmail(resultSet.getString("email"));
			beanCursoJsp.setCep(resultSet.getString("cep"));
			beanCursoJsp.setRua(resultSet.getString("rua"));
			beanCursoJsp.setBairro(resultSet.getString("bairro"));
			beanCursoJsp.setCidade(resultSet.getString("cidade"));
			beanCursoJsp.setEstado(resultSet.getString("estado"));
			beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
			beanCursoJsp.setContentType(resultSet.getString("contenttype"));
			beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
			beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
			beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
			beanCursoJsp.setSexo(resultSet.getString("sexo"));
			beanCursoJsp.setPerfil(resultSet.getString("perfil"));

			return beanCursoJsp;
		}
		return null;
	}

	public void atualizar(BeanCursoJsp usuario) {
		try {
			//  StringBuilder --> Essa classe permite criar e manipular dados de Strings dinamicamente, ou seja, podem criar variáveis de String modificáveis.
			StringBuilder sql = new StringBuilder();
			sql.append(
					"update usuario set login = ?, senha = ?, nome = ?, fone = ?, email = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ativo = ?, sexo = ?, perfil = ? ");

			if (usuario.isAtualizarImagem()) {

				sql.append(" ,fotobase64 = ?, contenttype = ?");
			}

			if (usuario.isAtualizarPDF()) {
				sql.append(" ,curriculobase64 = ?, contenttypecurriculo = ? ");
			}

			if (usuario.isAtualizarImagem()) {
				sql.append(" ,fotobase64miniatura = ? ");
			}
			sql.append(" where id= " + usuario.getId());

			PreparedStatement atualizar = connection.prepareStatement(sql.toString());

			atualizar.setString(1, usuario.getLogin());
			atualizar.setString(2, usuario.getSenha());
			atualizar.setString(3, usuario.getNome());
			atualizar.setString(4, usuario.getFone());
			atualizar.setString(5, usuario.getEmail());
			atualizar.setString(6, usuario.getCep());
			atualizar.setString(7, usuario.getRua());
			atualizar.setString(8, usuario.getBairro());
			atualizar.setString(9, usuario.getCidade());
			atualizar.setString(10, usuario.getEstado());
			atualizar.setBoolean(11, usuario.isAtivo());
			atualizar.setString(12, usuario.getSexo());
			atualizar.setString(13, usuario.getPerfil());

			if (usuario.isAtualizarImagem()) {
				atualizar.setString(14, usuario.getFotoBase64());
				atualizar.setString(15, usuario.getContentType());
			}

			if (usuario.isAtualizarPDF()) {

				if (usuario.isAtualizarPDF() && !usuario.isAtualizarImagem()) {

					atualizar.setString(14, usuario.getCurriculoBase64());
					atualizar.setString(15, usuario.getContentTypeCurriculo());
				} else {
					atualizar.setString(16, usuario.getCurriculoBase64());
					atualizar.setString(17, usuario.getContentTypeCurriculo());
				}
			} else {
				if (usuario.isAtualizarImagem()) {
					atualizar.setString(16, usuario.getFotoBase64Miniatura());
				}
			}

			if (usuario.isAtualizarImagem() && usuario.isAtualizarPDF()) {
				atualizar.setString(18, usuario.getFotoBase64Miniatura());
			}

			atualizar.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "Select count(1) as qtde from usuario where login = '" + login + "'";
		PreparedStatement consultar = connection.prepareStatement(sql);
		ResultSet resultSet = consultar.executeQuery();
		if (resultSet.next()) {

			// Return true
			return resultSet.getInt("qtde") <= 0;
		}
		return false;
	}
	
	public boolean validarLoginUpdate(String login, String id) throws Exception{
		String sql = "select count(1) as qtd from usuario where login = '" + login + "' and id = " + id;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		
		if (resultSet.next()) {
			
			
			return resultSet.getInt("qtd")<=0 ; 
		}
		
		return false;
	}


}
