package com.xavierluz.servicoonline.prestados;

import com.google.firebase.database.IgnoreExtraProperties;
import com.xavierluz.servicoonline.item.servico.ItemServico;
import com.xavierluz.servicoonline.item.servico.Servico;
import java.util.List;


@IgnoreExtraProperties
public class ServicoPrestado {
    private String id;
    private String servicoId;
    private String nomeServico;
    private String servicoItemId;
    private String servicoItemNome;
    private Double servicoItemPreco;
    private String descricaoServico;
    private String nomeCliente;
    private Double servicoValor;
    private Double servicoValorCobrado;
    private String Status;
    private Servico servico;
    private List<Servico> servicos;
    private List<ItemServico> servicoItens;
    private String dataServicoCadastrado;

    public ServicoPrestado(){}
    private ServicoPrestado(String servicoId) {
        this.servicoId = servicoId;
    }

    public static ServicoPrestado getInstanceParaSalvarServicoPrestado(String servicoId){
        return new ServicoPrestado(servicoId);
    }

    public String getDataServicoCadastrado() {
        return dataServicoCadastrado;
    }

    public void setDataServicoCadastrado(String dataServicoCadastrado) {
        this.dataServicoCadastrado = dataServicoCadastrado;
    }

    public String getId() {
        return id;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getServicoItemId() {
        return servicoItemId;
    }

    public void setServicoItemId(String servicoItemId) {
        this.servicoItemId = servicoItemId;
    }

    public String getServicoItemNome() {
        return servicoItemNome;
    }

    public void setServicoItemNome(String servicoItemNome) {
        this.servicoItemNome = servicoItemNome;
    }

    public Double getServicoItemPreco() {
        return servicoItemPreco;
    }

    public void setServicoItemPreco(Double servicoItemPreco) {
        this.servicoItemPreco = servicoItemPreco;
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

    public Double getServicoValor() {
        return servicoValor;
    }

    public void setServicoValor(Double servicoValor) {
        this.servicoValor = servicoValor;
    }

    public Double getServicoValorCobrado() {
        return servicoValorCobrado;
    }

    public void setServicoValorCobrado(Double servicoValorCobrado) {
        this.servicoValorCobrado = servicoValorCobrado;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public List<ItemServico> getServicoItens() {
        return servicoItens;
    }

}
