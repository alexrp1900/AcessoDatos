package Biblioteca;

public class Llibre {

	// Atributos
	private String titulo;
	private String autor;
	private String anyo_nac;
	private String anyo_publi;
	private String editorial;
	private String num_pag;

	// Constructores
	public Llibre() {

	}

	// Constructor con parametros
	public Llibre(String titulo, String autor, String anyo_nac, String anyo_publi, String editorial,String num_pag) {
		
		this.titulo = titulo;
		this.autor = autor;
		this.anyo_nac = anyo_nac;
		this.anyo_publi = anyo_publi;
		this.editorial = editorial;
		this.num_pag = num_pag;
	}

	// Getters and Setters
	public String gettitulo() {
		return titulo;
	}

	public void settitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getanyo_nac() {
		return anyo_nac;
	}

	public void setanyo_nac(String anyo_nac) {
		this.anyo_nac = anyo_nac;
	}

	public String getanyo_publi() {
		return anyo_publi;
	}

	public void setanyo_publi(String anyo_publi) {
		this.anyo_publi = anyo_publi;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getnum_pag() {
		return num_pag;
	}

	public void setnum_pag(String num_pag) {
		this.num_pag = num_pag;
	}
}
