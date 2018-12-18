package com.xavierluz.servicoonline.item.servico;

import com.google.firebase.database.IgnoreExtraProperties;
import com.xavierluz.servicoonline.prestados.ServicoPrestado;

import java.util.ArrayList;
import java.util.List;
@IgnoreExtraProperties
public class ItemServico {

    private String id;
    private String servicoId;
    private String servicoPrestadoId;
    private String nomeItemServico;
    private String descricaoItemServico;
    private double precoItemServico =0.0;
    private boolean ativo;
    private String status;
    private Integer quantidadeItemServico = 0;
    private Double valorDoItemServico =0.0;
    private Double valorDoDesconto =null;
    private boolean descontoPorcetagem = false;
    private Double valorTotalDoItem;
    private Double Desconto =0.0;
    public ItemServico(){

    }

   // public double getValorDoServico() {
    //    return valorDoServico;
    //}


    public String getServicoPrestadoId() {
        return servicoPrestadoId;
    }

    public void setServicoPrestadoId(String servicoPrestadoId) {
        this.servicoPrestadoId = servicoPrestadoId;
    }

    public Double getDesconto() {
        return Desconto;
    }

    public void setDesconto(Double desconto) {
        Desconto = desconto;
    }

    public boolean isDescontoPorcetagem() {
        return descontoPorcetagem;
    }

    public void setDescontoPorcetagem(boolean descontoPorcetagem) {
        this.descontoPorcetagem = descontoPorcetagem;
    }

    public Double getValorDoItemServico() {
        return valorDoItemServico;
    }

    public void setValorDoItemServico(Double valorDoItemServico) {
        this.valorDoItemServico = valorDoItemServico;
    }

    public Double getValorDoDesconto() {
        return valorDoDesconto;
    }

    public void setValorDoDesconto(Double valorDoDesconto) {
        this.valorDoDesconto = valorDoDesconto;
    }

    public Double getValorTotalDoItem() {
        return valorTotalDoItem;
    }

    public void setValorTotalDoItem(Double valorTotalDoItem) {
        this.valorTotalDoItem = valorTotalDoItem;
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

    public Double getPrecoItemServico() {
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
