package cl.td.g2.eventos.dto;


import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "El nombre del Usuario es requerido")
    private String nombre;
    @NotBlank(message = "El apellido del Usuario es requerido")
    private String apellido;
    @NotBlank(message = "El email del Usuario es requerido")
    @Email
    private String email;
    @NotNull(message = "La fecha de registro es requerida")
    private LocalDateTime fechaRegistro;
    @NotBlank(message = "El rol del Usuario es requerido")
    private String rol;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
  
}
