package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.security.auth.callback.ConfirmationCallback;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@MultipartConfig
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listarTodos" ;
			String user = request.getParameter("user");

			if (acao != null && acao.equalsIgnoreCase("delete")) {
				
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao != null && acao.equalsIgnoreCase("editar")) {
				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (acao != null && acao.equalsIgnoreCase("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} else if (acao.equalsIgnoreCase("download")) {
				BeanCursoJsp usuario = daoUsuario.consultar(user);
				if (usuario != null) {
					String contentType = "";
					byte[] fileBytes = null;
					String tipo = request.getParameter("tipo");
					// linha onde recupera o valor do ContentType,
					// aplica o regex e armazena o valor em um vetor na segunda posição
					// Converte a base64 da imagem do banco para byte[]

					if (tipo.equalsIgnoreCase("image")) {
						contentType = usuario.getContentType();
						fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
					} else if (tipo.equalsIgnoreCase("curriculo")) {
						contentType = usuario.getContentTypeCurriculo();
						fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
					}
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);
					// Coloca os bytes em um objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(fileBytes);
					// Inicio da resposta para o navegador
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// doGet(request, response);
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");

			BeanCursoJsp usuario = new BeanCursoJsp();

			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			
			if(request.getParameter("ativo") != null 
					&& request.getParameter("ativo").equalsIgnoreCase("on")) {
				usuario.setAtivo(true);
			}else {
				usuario.setAtivo(false);
			}
			
			boolean podeInserir = true;
			try {

				// Inicio File upload de imagens e pdf
				if (ServletFileUpload.isMultipartContent(request)) {
					Part imagemFoto = request.getPart("foto");
					if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {

						// byte[] bytesImagem = converteStreamParaByte(imagemFoto.getInputStream());

						String fotoBase64 = new Base64()
								.encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));

						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());

						// Inicio da miniautura da imagem

						// Transforma em um buffuredImage
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						// Pega o tipo da imagem
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						// Cria imagem em miniatura
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						// Ecrever imagem novamente
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						String miniaturaBase64 = "data:image/png;base64,"
								+ DatatypeConverter.printBase64Binary(baos.toByteArray());
						System.out.println(miniaturaBase64);

						// Fim miniatura imagem

						usuario.setFotoBase64Miniatura(miniaturaBase64);
						// usuario.setContentType(imagemFoto.getContentType());
					} else {

						usuario.setAtualizarImagem(false);
						usuario.setContentType(request.getParameter("contentTypeTemp"));
					}

					// Processa PDF

					Part pdfCurriculo = request.getPart("curriculo");
					if (pdfCurriculo != null && pdfCurriculo.getInputStream().available() > 0) {
						String curriculoBase64 = new Base64()
								.encodeBase64String(converteStreamParaByte(pdfCurriculo.getInputStream()));
						// converteStreamParaByte(imagemFoto.getInputStream());

						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(pdfCurriculo.getContentType());
					} else {
						usuario.setAtualizarPDF(false);
						usuario.setContentTypeCurriculo(request.getParameter("contentTypeTempPDF"));
					}
				}
				// Fim File upload de imagens e pdf

				//boolean podeInserir = true;

				if (login == null != login.isEmpty()) {
					request.setAttribute("msg", "Preencha o login!");
					podeInserir = false;
				} else if (senha == null != senha.isEmpty()) {
					request.setAttribute("msg", "Preencha a senha");
					podeInserir = false;
				} else if (nome == null || nome.trim().isEmpty()) {
					request.setAttribute("msg", "Preencha o nome");
					podeInserir = false;
				} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg", "Login já cadastrado");
					podeInserir = false;
				} else if (cep == null != cep.trim().isEmpty()) {
					request.setAttribute("msg", "Digite o CEP");
					podeInserir = false;
				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && podeInserir) {
					
					request.setAttribute("msg", "Usuário cadastrado");
					podeInserir = true;
					daoUsuario.salvar(usuario);

				} else if (id != null && !id.isEmpty() && !daoUsuario.validarLoginUpdate(login, id) && podeInserir) {
					request.setAttribute("msg", "Login já cadastrado");

				}
				if (podeInserir == true && login!=null && !login.isEmpty()) {

					request.setAttribute("msg", "Cadastro Atualizado");
					daoUsuario.atualizar(usuario);
				}

				// Se o podeInserir for diferente de true, irá retornar na tela os dados
				// preenchidos.
				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				// request.setAttribute("msg", "Salvo com sucesso");
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	// Converte a entrada de fluxo de dados da imagem para byte[]

	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}

		return baos.toByteArray();
	}
}
