package cl.td.g2.eventos.dto;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class InscripcionDTO {

    private Long id;
    @NotNull(message = "El usuario es requerido")
    private Long usuarioId;
    @NotNull(message = "El evento es requerido")
    private Long eventoId;
    @NotNull(message = "La fecha de inscripción es requerida")
    private LocalDateTime fechaInscripcion;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getEventoId() {
		return eventoId;
	}
	public void setEventoId(Long eventoId) {
		this.eventoId = eventoId;
	}
	public LocalDateTime getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

}