package com.xavierluz.servicoonline.fechamento;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Servico {
    private String id;
    private String nome;
    private String descricao;
    private Double valor;

    public Servico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
