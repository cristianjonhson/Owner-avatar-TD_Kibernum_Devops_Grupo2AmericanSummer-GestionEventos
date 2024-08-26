package cl.td.g2.eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import cl.td.g2.eventos.model.Usuario;

public class UserTest {

    @Test
    public void testUserCreation() {
        Usuario user = new Usuario(1L, "admin", "admin@example.com", "password123");

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("admin", user.getNombre());
        assertEquals("admin@example.com", user.getEmail());
        assertEquals("password123", user.getContrasena());

        // Verificar que el email sea válido
        assertTrue(user.getEmail().contains("@"));

        // Verificar que la contraseña no esté vacía
        assertFalse(user.getContrasena().isEmpty());
    }
}
