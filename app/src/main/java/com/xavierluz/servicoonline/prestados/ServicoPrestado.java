package com.xavierluz.servicoonline.prestados;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ServicoPrestado {
    private Integer servicoId;
    private String nomeServico;
    private String descricaoServico;
    private String nomeCliente;
    private double servicoValor;
    private double servicoValorCobrado;
    private String Status;
    public ServicoPrestado() {
    }

    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getServicoValor() {
        return servicoValor;
    }

    public void setServicoValor(double servicoValor) {
        this.servicoValor = servicoValor;
    }

    public double getServicoValorCobrado() {
        return servicoValorCobrado;
    }

    public void setServicoValorCobrado(double servicoValorCobrado) {
        this.servicoValorCobrado = servicoValorCobrado;
    }
    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
    public static List<ServicoPrestado> getServicosPrestados(){
        ServicoPrestado servicoPrestado =  null;
        List<ServicoPrestado> servicosPrestados =  new ArrayList<ServicoPrestado>();
        for(Integer i = 1; i < 10; i++) {
            servicoPrestado = new ServicoPrestado();

            servicoPrestado.setServicoId(i);
            servicoPrestado.setNomeServico("Corte de Cabelo");
            servicoPrestado.setNomeCliente("Celso Xavier");
            servicoPrestado.setDescricaoServico("Corte de cabelo usando máquina");
            servicoPrestado.setServicoValorCobrado(50.0);
            servicoPrestado.setStatus("Receber");
            servicosPrestados.add(servicoPrestado);
        }
        return servicosPrestados;
    }
}
