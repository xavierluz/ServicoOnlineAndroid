package com.xavierluz.servicoonline.servico;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ServicoItem {
    private String id;
    private String servicoId;
    private String nome;
    private String descricao;
    private Double preco;
    private String status;

    public ServicoItem(String servicoId, String nome, String descricao, Double preco, String status) {
        this.servicoId = servicoId;
        this.descricao = descricao;
        this.preco = preco;
        this.status = status;
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServicoId() {
        return servicoId;
    }

    public void setServicoId(String servicoId) {
        this.servicoId = servicoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
