package com.xavierluz.servicoonline.pagamento;

public class PagamentoModel {
    private Double valorDoServico;
    private Boolean tipoDescontoDinheiro;
    private Boolean tipoDescontoPorcetagem;
    private Double valorDesconto;
    private Double valorPagamento;
    private Boolean tipoPagamentoDinheiro=true;
    private Boolean tipoPagamentoCheque;
    private Boolean tipoPagamentoCartao;

    public PagamentoModel() {
    }

    public Double getValorDoServico() {
        return valorDoServico;
    }

    public void setValorDoServico(Double valorDoServico) {
        this.valorDoServico = valorDoServico;
    }

    public Boolean getTipoDescontoDinheiro() {
        return tipoDescontoDinheiro;
    }

    public void setTipoDescontoDinheiro(Boolean tipoDescontoDinheiro) {
        this.tipoDescontoDinheiro = tipoDescontoDinheiro;
    }

    public Boolean getTipoDescontoPorcetagem() {
        return tipoDescontoPorcetagem;
    }

    public void setTipoDescontoPorcetagem(Boolean tipoDescontoPorcetagem) {
        this.tipoDescontoPorcetagem = tipoDescontoPorcetagem;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Boolean getTipoPagamentoDinheiro() {
        return tipoPagamentoDinheiro;
    }

    public void setTipoPagamentoDinheiro(Boolean tipoPagamentoDinheiro) {
        this.tipoPagamentoDinheiro = tipoPagamentoDinheiro;
    }

    public Boolean getTipoPagamentoCheque() {
        return tipoPagamentoCheque;
    }

    public void setTipoPagamentoCheque(Boolean tipoPagamentoCheque) {
        this.tipoPagamentoCheque = tipoPagamentoCheque;
    }

    public Boolean getTipoPagamentoCartao() {
        return tipoPagamentoCartao;
    }

    public void setTipoPagamentoCartao(Boolean tipoPagamentoCartao) {
        this.tipoPagamentoCartao = tipoPagamentoCartao;
    }


}
