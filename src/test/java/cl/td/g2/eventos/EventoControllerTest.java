package cl.td.g2.eventos;

import cl.td.g2.eventos.controller.EventoController;
import cl.td.g2.eventos.model.Categoria;
import cl.td.g2.eventos.model.Ciudad;
import cl.td.g2.eventos.model.Evento;
import cl.td.g2.eventos.service.EventoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EventoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EventoService eventoService;

    @InjectMocks
    private EventoController eventoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventoController).build();
    }

    @Test
    public void testCreateEvento() throws Exception {
        Evento evento = new Evento("Conferencia de Tecnología", 
                                    LocalDateTime.of(2024, 8, 1, 10, 0), 
                                    LocalDateTime.of(2024, 8, 1, 12, 0), 
                                    new Categoria(1L, "Tecnología", "Conferencia"), 
                                    "Auditorio Principal", 
                                    new Ciudad(1L, "Madrid"), 
                                    BigDecimal.valueOf(50.0));

        when(eventoService.createEvento(any(Evento.class))).thenReturn(evento);

        mockMvc.perform(post("/eventos")
                .contentType("application/json")
                .content("{\"nombre\":\"Conferencia de Tecnología\",\"fechaInicio\":\"2024-08-01T10:00:00\",\"fechaFin\":\"2024-08-01T12:00:00\",\"categoria\":{\"id\":1,\"nombre\":\"Tecnología\",\"descripcion\":\"Conferencia\"},\"ubicacion\":\"Auditorio Principal\",\"ciudad\":{\"id\":1,\"nombre\":\"Madrid\"},\"valor\":50.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Conferencia de Tecnología"));
    }

    @Test
    public void testGetAllEventos() throws Exception {
        Evento evento = new Evento("Conferencia de Tecnología", 
                                    LocalDateTime.of(2024, 8, 1, 10, 0), 
                                    LocalDateTime.of(2024, 8, 1, 12, 0), 
                                    new Categoria(1L, "Tecnología", "Conferencia"), 
                                    "Auditorio Principal", 
                                    new Ciudad(1L, "Madrid"), 
                                    BigDecimal.valueOf(50.0));
        when(eventoService.getAllEventos()).thenReturn(Collections.singletonList(evento));

        mockMvc.perform(get("/eventos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Conferencia de Tecnología"));
    }

    @Test
    public void testGetEventoById() throws Exception {
        Evento evento = new Evento("Conferencia de Tecnología", 
                                    LocalDateTime.of(2024, 8, 1, 10, 0), 
                                    LocalDateTime.of(2024, 8, 1, 12, 0), 
                                    new Categoria(1L, "Tecnología", "Conferencia"), 
                                    "Auditorio Principal", 
                                    new Ciudad(1L, "Madrid"), 
                                    BigDecimal.valueOf(50.0));
        when(eventoService.getEventoById(1L)).thenReturn(evento);

        mockMvc.perform(get("/eventos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Conferencia de Tecnología"));
    }

    @Test
    public void testUpdateEvento() throws Exception {
        Evento evento = new Evento("Conferencia de Tecnología", 
                                    LocalDateTime.of(2024, 8, 1, 10, 0), 
                                    LocalDateTime.of(2024, 8, 1, 12, 0), 
                                    new Categoria(1L, "Tecnología", "Conferencia"), 
                                    "Auditorio Principal", 
                                    new Ciudad(1L, "Madrid"), 
                                    BigDecimal.valueOf(50.0));

        when(eventoService.updateEvento(eq(1L), any(Evento.class))).thenReturn(evento);

        mockMvc.perform(put("/eventos/1")
                .contentType("application/json")
                .content("{\"nombre\":\"Conferencia de Tecnología\",\"fechaInicio\":\"2024-08-01T10:00:00\",\"fechaFin\":\"2024-08-01T12:00:00\",\"categoria\":{\"id\":1,\"nombre\":\"Tecnología\",\"descripcion\":\"Conferencia\"},\"ubicacion\":\"Auditorio Principal\",\"ciudad\":{\"id\":1,\"nombre\":\"Madrid\"},\"valor\":50.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Conferencia de Tecnología"));
    }

    @Test
    public void testDeleteEvento() throws Exception {
        doNothing().when(eventoService).deleteEvento(1L);

        mockMvc.perform(delete("/eventos/1"))
                .andExpect(status().isNoContent());
    }
}

