package cl.td.g2.eventos.dto;

public class InscripcionListDTO extends InscripcionDTO {
	private String organizador;
    private String tituloEvento;
    
    public InscripcionListDTO(InscripcionDTO inscripcionDTO, String organizador, String tituloEvento) {
    	super(inscripcionDTO);
    	this.organizador = organizador;
    	this.tituloEvento = tituloEvento;
    }
    
	public String getOrganizador() {
		return organizador;
	}
	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	public String getTituloEvento() {
		return tituloEvento;
	}
	public void setTituloEvento(String tituloEvento) {
		this.tituloEvento = tituloEvento;
	}
}
