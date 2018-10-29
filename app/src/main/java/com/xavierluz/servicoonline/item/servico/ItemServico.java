package com.xavierluz.servicoonline.item.servico;

import com.google.firebase.database.IgnoreExtraProperties;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;

import java.util.ArrayList;
import java.util.List;
@IgnoreExtraProperties
public class ItemServico {

    private String id;
    private String servicoId;
    private String nomeItemServico;
    private String descricaoItemServico;
    private double precoItemServico =0.0;
    private boolean ativo;
    private String status;
    private Integer quantidadeItemServico = 0;
    private double valorCobradoDoItemServico =0.0;
    private double valorDoDescontoDoItemServico = 0.0;
    private boolean descontoPorcetagem = false;
    //private double valorDoServico;

    public ItemServico(){

    }

   // public double getValorDoServico() {
    //    return valorDoServico;
    //}


    public boolean isDescontoPorcetagem() {
        return descontoPorcetagem;
    }

    public void setDescontoPorcetagem(boolean descontoPorcetagem) {
        this.descontoPorcetagem = descontoPorcetagem;
    }

    public double getValorCobradoDoItemServico() {
        return valorCobradoDoItemServico;
    }

    public void setValorCobradoDoItemServico(double valorCobradoDoItemServico) {
        this.valorCobradoDoItemServico = valorCobradoDoItemServico;
    }

    public double getValorDoDescontoDoItemServico() {
        return valorDoDescontoDoItemServico;
    }

    public void setValorDoDescontoDoItemServico(double valorDoDescontoDoItemServico) {
        this.valorDoDescontoDoItemServico = valorDoDescontoDoItemServico;
    }

    public Integer getQuantidadeItemServico() {
        return quantidadeItemServico;
    }

    public void setQuantidadeItemServico(Integer quantidadeItemServico) {
        this.quantidadeItemServico = quantidadeItemServico;
    }

    public ItemServico(String servicoId) {
        this.servicoId = servicoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String itemServicoId) {
        this.id = itemServicoId;
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


    public static List<ItemServico> getItensServicos(String servicoId){
        ItemServico itemServico =  null;
        List<ItemServico>  itemServicos =  new ArrayList<ItemServico>();
        for(Integer i = 1; i < 10; i++) {
            itemServico = new ItemServico(servicoId);
            itemServico.id = i.toString();
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
