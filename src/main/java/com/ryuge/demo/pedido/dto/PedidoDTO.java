package com.ryuge.demo.pedido.dto;

import java.time.LocalDate;

public class PedidoDTO {
    private String numeroControle;
    private String nome;
    private double valor;
    private Integer quantidade;
    private Integer codigoCliente;
    private LocalDate dataCadastro;
   
    public String getNumeroControle() {
        return numeroControle;
    }
    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public Integer getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
   
}
