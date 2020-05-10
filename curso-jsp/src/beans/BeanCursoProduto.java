package beans;

public class BeanCursoProduto {
	private long id;
	private String nome;
	private String quantidade;
	private String valor;
	private Long categoria_id;
	
	
	
	public Long getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
