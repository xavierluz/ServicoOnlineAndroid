package com.xavierluz.servicoonline.item.servico;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Servico {
    private String Id;
    private String nome;
    private List<ItemServico> itemServicos;

    public Servico(){

        itemServicos = new ArrayList<ItemServico>();
    }

    public List<ItemServico> getItemServicos() {
        return itemServicos;
    }

    public void setItemServicos(List<ItemServico> itemServicos) {
        this.itemServicos = itemServicos;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
