package beans;

// Os atributos da classe Bean tem que ser identicos ao do banco de dados para fazer a comunicação entre banco de dados e a linguagem de programação
public class BeanCursoJsp {
	private long id;
	private String login;
	private String nome;
	private String senha;
	private String fone;
	private String email;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String fotoBase64;
	private String fotoBase64Miniatura;
	private String contentType;
	private String temFotoUser;
	private String curriculoBase64;
	private String contentTypeCurriculo;
	private String temCurriculo;
	private boolean ativo;
	private String sexo;
	private String perfil;
	
	private boolean atualizarImagem = true;
	private boolean atualizarPDF = true;
	
	
	
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtualizarImagem() {
		return atualizarImagem;
	}

	public void setAtualizarImagem(boolean atualizarImagem) {
		this.atualizarImagem = atualizarImagem;
	}

	public boolean isAtualizarPDF() {
		return atualizarPDF;
	}

	public void setAtualizarPDF(boolean atualizarPDF) {
		this.atualizarPDF = atualizarPDF;
	}

	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}
	
	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}
	
	public String getTemFotoUser() {

		temFotoUser = "data:" + contentType + ";base64," + fotoBase64;

		return temFotoUser;
	}

	public String getTemCurriculo() {

		temCurriculo = "data:" + contentTypeCurriculo + ";base64," + curriculoBase64;

		return temCurriculo;
	}

	public String getCurriculoBase64() {
		return curriculoBase64;
	}

	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}

	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}

	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
