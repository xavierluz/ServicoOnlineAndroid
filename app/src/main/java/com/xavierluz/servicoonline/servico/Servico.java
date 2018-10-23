package com.xavierluz.servicoonline.servico;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Servico {
    private String id;
    private String nome;
    private String descricao;
    private String status;

    public Servico() {
    }

    public Servico( String nome, String descricao, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
