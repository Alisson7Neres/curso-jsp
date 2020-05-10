package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.BeanCursoProduto;
import dao.DaoProduto;

@MultipartConfig
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
		
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");

			if (acao.equalsIgnoreCase("listar")) {
				//daoProduto.listar();
				request.setAttribute("produtos", daoProduto.listar());
			}

			else if (acao.equalsIgnoreCase("delete")) {
				daoProduto.deletar(produto);
				request.setAttribute("produtos", daoProduto.listar());
			}

			else if (acao.equalsIgnoreCase("editar")) {
				BeanCursoProduto beanProduto = daoProduto.consultar(produto);

				request.setAttribute("produto", beanProduto);

			}
			request.setAttribute("categorias", daoProduto.listaCategorias());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// doGet(request, response);
		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listaCategorias());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoria_id");

			BeanCursoProduto produto = new BeanCursoProduto();
			produto.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : 0);
			produto.setNome(nome);
			produto.setQuantidade(quantidade);
			produto.setValor(valor);
			produto.setCategoria_id(Long.parseLong(categoria));
			// Sempre iniciar com true 
			boolean podeInserir = true;

			try {// Verificação dos campos	
				if (nome.equals("") || nome.trim().isEmpty()) {
					request.setAttribute("msg", "Preencha o nome do produto");
					podeInserir = false;
				} else if (quantidade.equals("") || quantidade.trim().isEmpty()) {
					request.setAttribute("msg", "Preencha a quantidade");
					podeInserir = false;
				} else if (valor.equals("") || valor.trim().isEmpty()) {
					request.setAttribute("msg", "Preencha o valor do produto");
					podeInserir = false;
				}
				if (id == null || id.isEmpty() && podeInserir) {
					daoProduto.salvar(produto);
					podeInserir = true;
				} else if (id != null && !id.isEmpty()) {
					daoProduto.atualizar(produto);
				}
				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listaCategorias());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
