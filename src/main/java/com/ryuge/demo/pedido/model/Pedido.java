package com.ryuge.demo.pedido.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String numeroControle;

    private LocalDate dataCadastro;

    private String nome;

    private double valor;

    private int quantidade;

    private int codigoCliente;

    private double valorTotal;

    // Getters e Setters

    @PrePersist
    public void prePersist() {
        if (dataCadastro == null) {
            dataCadastro = LocalDate.now();
        }
        if (quantidade == 0) {
            quantidade = 1;
        }
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        double desconto = 0;
        if (quantidade > 10) {
            desconto = 0.10;
        } else if (quantidade > 5) {
            desconto = 0.05;
        }
        valorTotal = quantidade * valor * (1 - desconto);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroControle() {
        return numeroControle;
    }

    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((numeroControle == null) ? 0 : numeroControle.hashCode());
        result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        long temp;
        temp = Double.doubleToLongBits(valor);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + quantidade;
        result = prime * result + codigoCliente;
        temp = Double.doubleToLongBits(valorTotal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (numeroControle == null) {
            if (other.numeroControle != null)
                return false;
        } else if (!numeroControle.equals(other.numeroControle))
            return false;
        if (dataCadastro == null) {
            if (other.dataCadastro != null)
                return false;
        } else if (!dataCadastro.equals(other.dataCadastro))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
            return false;
        if (quantidade != other.quantidade)
            return false;
        if (codigoCliente != other.codigoCliente)
            return false;
        if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
            return false;
        return true;
    }

    
}
