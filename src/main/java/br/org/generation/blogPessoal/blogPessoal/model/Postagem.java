package br.org.generation.blogPessoal.blogPessoal.model;

import java.util.Date;// blblioteca criada na linha 41 para Date

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // chamando na notação Size



/* para que esta classe possa ser interpretada pelo banco de dados será 
necessário inserir anotações, que estão ao longo do programa. com essas @anotações 
 o JPA hibernate conseguirá interpretar essa classe como uma entidade. para isso as anotações a serem usadas são
 @Entity que importa de javax.peristence e @Table que importa de java.persistence */

@Entity  // biblioteca javax.persistence
@Table(name = "tb_postagem") // biblioteca javax.peristence

public class Postagem {
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // aqui você determina que ele é a chave primária através da estratégia IDENTITY e também fazendo que ele faça o auto_increment
	private long id;
	
	@NotBlank(message = "O atributo título é obrigatório e não pode conter espaços vazios") //O @NotBlank não aceita espaços vazios, 
	@Size(min = 10, max = 100, message="O Atributo titulo deve conter no minimo 10 caracteres")
	private String titulo;
	

	@NotNull(message = "O Atibuto texto é obrigatório") 
	@Size(min = 10, max = 1000, message="O Atributo  texto deve conter no minimo 10 caracteres")
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	/* quando uma dado passar por este objeto será capturadfo data
	 hora e milisegundos dessa interação.*/
	
	@ManyToOne // Aqui vc não rretonra uma lista pois é de ManytoOne, ou seja, apenas um resultado
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}
