package cl.td.g2.eventos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cl.td.g2.eventos.model.Evento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest
class G2GestionEventosApplicationTests {

    @Test
    void testEventCreation() {
        Evento event = new Evento();
        event.setId(1L);
        event.setTitulo("Evento de Prueba");
        event.setFechaInicio(LocalDateTime.now());

        assertEquals(1L, event.getId());
        assertEquals("Evento de Prueba", event.getTitulo());
        assertNotNull(event.getFechaInicio());
    }

    @Test
    void testEqualsAndHashCode() {
    	Evento event1 = new Evento(1L, "Evento 1", LocalDateTime.now());
    	Evento event2 = new Evento(1L, "Evento 1", LocalDateTime.now());

        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }
}
