package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.BeanCursoJspFone;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String user = request.getParameter("user");

			String acao = request.getParameter("acao");

			if(user!= null) {
				
			if (acao.equalsIgnoreCase("addFone")) {

				BeanCursoJsp usuario = daoUsuario.consultar(user);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);
				// RequestDispatcher irá redirecionar a pagina
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("deleteFone")){
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				String foneId = request.getParameter("foneId");
				daoTelefone.deletar(foneId);
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("userEscolhido", usuario);
				request.setAttribute("telefones", daoTelefone.listar(Long.parseLong(user)));
				request.setAttribute("msg", "Removido com sucesso");
				view.forward(request, response);
			}

			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		try {
		
				
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			String acao = request.getParameter("acao");
			
			if(acao == null || (acao != null && !acao.equalsIgnoreCase("cancelar"))) {
				
			if(numero == null || (numero != null && numero.isEmpty())) {

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(beanCursoJsp.getId()));
				request.setAttribute("msg", "Informe o número do telefone");
				view.forward(request, response);				
			}
			else {
				
			BeanCursoJspFone telefone = new BeanCursoJspFone();
			telefone.setNumero(numero);
			telefone.setTipo(tipo);
			telefone.setUsuario(beanCursoJsp.getId());

			daoTelefone.salvar(telefone);

			// request.getSession().setAttribute("userEscolhido", beanCursoJsp);
			// request.setAttribute("userEscolhido", beanCursoJsp);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daoTelefone.listar(beanCursoJsp.getId()));
			request.setAttribute("msg", "Salvo com sucesso");
			view.forward(request, response);
			}
			
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
