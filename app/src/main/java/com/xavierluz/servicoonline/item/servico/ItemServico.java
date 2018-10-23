package com.xavierluz.servicoonline.item.servico;

import com.xavierluz.servicoonline.prestados.ServicoPrestado;

import java.util.ArrayList;
import java.util.List;

public class ItemServico {

    private String itemServicoId;
    private String servicoId;
    private String nomeItemServico;
    private String descricaoItemServico;
    private double precoItemServico;
    private boolean ativo;
    private String status;
    private double valorDoServico;


    public double getValorDoServico() {
        return valorDoServico;
    }


    public ItemServico(String servicoId) {
        this.servicoId = servicoId;
    }

    public String getItemServicoId() {
        return itemServicoId;
    }

    public void setItemServicoId(String itemServicoId) {
        this.itemServicoId = itemServicoId;
    }

    public String getServicoId() {
        return servicoId;
    }

    public void setServicoId(String servicoId) {
        this.servicoId = servicoId;
    }

    public String getNomeItemServico() {
        return nomeItemServico;
    }

    public void setNomeItemServico(String nomeItemServico) {
        this.nomeItemServico = nomeItemServico;
    }

    public String getDescricaoItemServico() {
        return descricaoItemServico;
    }

    public void setDescricaoItemServico(String descricaoItemServico) {
        this.descricaoItemServico = descricaoItemServico;
    }

    public double getPrecoItemServico() {
        return precoItemServico;
    }

    public void setPrecoItemServico(double precoItemServico) {
        this.precoItemServico = precoItemServico;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void somarPreco(double preco){
        this.valorDoServico =this.valorDoServico +  preco;
    }
    public void subtrairPreco(double preco) {
        this.valorDoServico =- preco;
        if (this.valorDoServico < 0)
            this.valorDoServico = 0;
    }
    public static List<ItemServico> getItensServicos(String servicoId){
        ItemServico itemServico =  null;
        List<ItemServico>  itemServicos =  new ArrayList<ItemServico>();
        for(Integer i = 1; i < 10; i++) {
            itemServico = new ItemServico(servicoId);
            itemServico.itemServicoId = i.toString();
            itemServico.ativo = true;
            itemServico.descricaoItemServico = "Corte de cabelo usando tesoura de 7 pontos.";
            itemServico.nomeItemServico = "Corte Tipo 7";
            itemServico.status = "AT";
            itemServico.precoItemServico = (5 + i) * i  + 0.75;
            itemServicos.add(itemServico);
            itemServico = null;
        }
        return itemServicos;
    }
}
