package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;

import java.util.*;

import dao.DaoUsuario;

/**
 * Servlet implementation class serveltPesquisa
 */
@WebServlet("/serveltPesquisa")
public class serveltPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();

	public serveltPesquisa() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricaoPesquisa = request.getParameter("descricaoconsulta");
		if(descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
			try {
			
				List<BeanCursoJsp> listaPesquisa = daoUsuario.listar(descricaoPesquisa);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			// Se o campo de pequisa nçao for preenchido irá retornar a página de cadastroUsuario.jsp
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			try {
				request.setAttribute("usuarios", daoUsuario.listar());
			} catch (Exception e) {
				e.printStackTrace();
			}
			view.forward(request, response);
		}

	}

}
