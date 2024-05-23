package com.ryuge.demo.pedido;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryuge.demo.pedido.controller.PedidoController;
import com.ryuge.demo.pedido.dto.PedidoDTO;
import com.ryuge.demo.pedido.service.PedidoService;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private PedidoDTO pedidoDTO;

    @BeforeEach
    void setUp() {
        pedidoDTO = new PedidoDTO();
        pedidoDTO.setNumeroControle("123456");
        pedidoDTO.setNome("Produto Teste");
        pedidoDTO.setValor(100.0);
        pedidoDTO.setQuantidade(2);
        pedidoDTO.setCodigoCliente(1);
        pedidoDTO.setDataCadastro(LocalDate.now());
    }

    @Test
    public void testBuscarPorNumeroControle() throws Exception {
        when(pedidoService.buscarPedidoPorNumeroControle("123456")).thenReturn(Optional.of(pedidoDTO));

        mockMvc.perform(get("/api/pedidos/numeroControle/123456")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(pedidoDTO))));
    }

    @Test
    public void testBuscarPorDataCadastro() throws Exception {
        when(pedidoService.buscarPedidosPorDataCadastro(LocalDate.now())).thenReturn(Collections.singletonList(pedidoDTO));

        mockMvc.perform(get("/api/pedidos/dataCadastro/" + LocalDate.now())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(pedidoDTO))));
    }

    @Test
    public void testListarPedidos() throws Exception {
        when(pedidoService.listarPedidos()).thenReturn(Collections.singletonList(pedidoDTO));

        mockMvc.perform(get("/api/pedidos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(pedidoDTO))));
    }
}
