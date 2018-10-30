package com.xavierluz.servicoonline.fechamento;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@IgnoreExtraProperties
public class Fechamento {
    private Integer fechamentoId;
    private Integer fechamentoServicoId;
    private String descricaoFechamento;
    private Date dataFechamento;
    private String dataPorExtenso;
    private Double valorFechamento;
    private List<ServicoPrestoItem> servicoPrestoItems;
    private List<Servico> servicos;

    public Fechamento() {
        this.servicoPrestoItems = new ArrayList<ServicoPrestoItem>();
        this.servicos = new ArrayList<Servico>();
    }

    public Integer getFechamentoId() {
        return fechamentoId;
    }

    public void setFechamentoId(Integer fechamentoId) {
        this.fechamentoId = fechamentoId;
    }

    public Integer getFechamentoServicoId() {
        return fechamentoServicoId;
    }

    public void setFechamentoServicoId(Integer fechamentoServicoId) {
        this.fechamentoServicoId = fechamentoServicoId;
    }

    public String getDescricaoFechamento() {
        return descricaoFechamento;
    }

    public void setDescricaoFechamento(String descricaoFechamento) {
        this.descricaoFechamento = descricaoFechamento;
    }

    public Date getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getDataPorExtenso() {
        return dataPorExtenso;
    }

    public void setDataPorExtenso(String dataPorExtenso) {
        this.dataPorExtenso = dataPorExtenso;
    }

    public Double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(Double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public List<ServicoPrestoItem> getServicoPrestoItems() {
        return servicoPrestoItems;
    }

    public void setServicoPrestoItems(List<ServicoPrestoItem> servicoPrestoItems) {
        this.servicoPrestoItems = servicoPrestoItems;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}
