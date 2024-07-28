package cl.td.g2.eventos.dto;


import java.time.LocalDateTime;
import java.math.BigDecimal;

public class EventoDTO {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String ubicacion;
    private Long organizadorId;
    private Long categoriaId;
    private Long ciudadId;
    private BigDecimal valor;
    private String imagenHtml;
    private LocalDateTime fechaCreacion;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Long getOrganizadorId() {
		return organizadorId;
	}
	public void setOrganizadorId(Long organizadorId) {
		this.organizadorId = organizadorId;
	}
	public Long getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	public Long getCiudadId() {
		return ciudadId;
	}
	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getImagenHtml() {
		return imagenHtml;
	}
	public void setImagenHtml(String imagenHtml) {
		this.imagenHtml = imagenHtml;
	}
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

    // Getters and setters
    
    
}