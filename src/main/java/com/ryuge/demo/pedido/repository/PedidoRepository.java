package com.ryuge.demo.pedido.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryuge.demo.pedido.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    Optional<Pedido> findByNumeroControle(String numeroControle);
    List<Pedido> findByDataCadastro(LocalDate dataCadastro);
}
