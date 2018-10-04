package com.xavierluz.servicoonline.fechamento;

import android.util.Log;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServicoFechamento {
    private Integer fechamentoId;
    private Integer fechamentoServicoId;
    private String descricaoFechamento;
    private Date dataFechamento;
    private String dataPorExtenso;
    private Double valorFechamento;

    public Double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(Double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public ServicoFechamento() {
    }

    public Integer getFechamentoId() {
        return fechamentoId;
    }

    public void setFechamentoId(Integer fechamentoId) {
        this.fechamentoId = fechamentoId;
    }

    public Integer getfechamentoServicoId() {
        return fechamentoServicoId;
    }

    public void setfechamentoServicoId(Integer fechamentoServicoId) {
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

    public static List<ServicoFechamento> getServicosFechamentos(){
        ServicoFechamento servicoFechamento =  null;
        List<ServicoFechamento> servicoFechamentos =  new ArrayList<ServicoFechamento>();
        for(Integer i = 1; i < 10; i++) {
            servicoFechamento = new ServicoFechamento();

            servicoFechamento.setFechamentoId(i);
            servicoFechamento.setfechamentoServicoId(i);
            servicoFechamento.setDescricaoFechamento("Fechamento do mês");
            servicoFechamento.setDataFechamento( new Date());
            servicoFechamento.setValorFechamento(50.0 * i + (i * .23));
            servicoFechamento.setDataPorExtenso(new Date().toString());
            servicoFechamentos.add(servicoFechamento);
        }
        return servicoFechamentos;
    }


}
