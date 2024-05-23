package com.ryuge.demo.pedido.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryuge.demo.pedido.dto.PedidoDTO;
import com.ryuge.demo.pedido.model.Pedido;
import com.ryuge.demo.pedido.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping(consumes = {"application/json", "application/xml"})
    public PedidoDTO criarPedido(@RequestBody PedidoDTO pedidoDTO) throws Exception {
        Pedido pedido = pedidoService.salvarPedido(pedidoDTO);
        return pedidoService.convertToDTO(pedido);
    }

    @GetMapping
    public List<PedidoDTO> listarPedidos(@RequestParam Optional<String> numeroControle,
                                         @RequestParam Optional<LocalDate> dataCadastro) {
        if (numeroControle.isPresent()) {
            return pedidoService.buscarPedidoPorNumeroControle(numeroControle.get())
                                 .map(List::of)
                                 .orElse(List.of());
        }
        if (dataCadastro.isPresent()) {
            return pedidoService.buscarPedidosPorDataCadastro(dataCadastro.get());
        }
        return pedidoService.listarPedidos();
    }

    @GetMapping("/numeroControle/{numeroControle}")
    public List<PedidoDTO> buscarPorNumeroControle(@PathVariable String numeroControle) {
        return pedidoService.buscarPedidoPorNumeroControle(numeroControle)
                            .map(List::of)
                            .orElse(List.of());
    }

    @GetMapping("/dataCadastro/{dataCadastro}")
    public List<PedidoDTO> buscarPorDataCadastro(@PathVariable LocalDate dataCadastro) {
        return pedidoService.buscarPedidosPorDataCadastro(dataCadastro);
    }
}