package com.ryuge.demo.pedido.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuge.demo.pedido.dto.PedidoDTO;
import com.ryuge.demo.pedido.model.Pedido;
import com.ryuge.demo.pedido.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(PedidoDTO pedidoDTO) throws Exception {
        Optional<Pedido> pedidoExistente = pedidoRepository.findByNumeroControle(pedidoDTO.getNumeroControle());
        if (pedidoExistente.isPresent()) {
            throw new Exception("Número de controle já cadastrado.");
        }
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(pedidoDTO.getNumeroControle());
        pedido.setNome(pedidoDTO.getNome());
        pedido.setValor(pedidoDTO.getValor());
        pedido.setQuantidade(pedidoDTO.getQuantidade() != null ? pedidoDTO.getQuantidade() : 1);
        pedido.setCodigoCliente(pedidoDTO.getCodigoCliente());
        pedido.setDataCadastro(pedidoDTO.getDataCadastro() != null ? pedidoDTO.getDataCadastro() : LocalDate.now());
        pedido.calcularValorTotal();
        return pedidoRepository.save(pedido);
    }

    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<PedidoDTO> buscarPedidoPorNumeroControle(String numeroControle) {
        return pedidoRepository.findByNumeroControle(numeroControle).map(this::convertToDTO);
    }

    public List<PedidoDTO> buscarPedidosPorDataCadastro(LocalDate dataCadastro) {
        return pedidoRepository.findByDataCadastro(dataCadastro).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setNumeroControle(pedido.getNumeroControle());
        dto.setNome(pedido.getNome());
        dto.setValor(pedido.getValor());
        dto.setQuantidade(pedido.getQuantidade());
        dto.setCodigoCliente(pedido.getCodigoCliente());
        dto.setDataCadastro(pedido.getDataCadastro());
        return dto;
    }
}
