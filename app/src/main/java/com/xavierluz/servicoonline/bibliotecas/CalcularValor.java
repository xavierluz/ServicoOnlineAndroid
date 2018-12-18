package com.xavierluz.servicoonline.bibliotecas;

import java.util.regex.Pattern;

public class CalcularValor {
    private Integer quantidade;
    private Double valor;
    private Double valorSerCalculado;
    private CalcularValor(){}
    private CalcularValor(Integer quantidade, Double valor){
        this.quantidade = quantidade;
        this.valor = valor;
        quantidadeVezesValor();
    }
    private CalcularValor(Double valorSerCalculado, Double valor){
        this.valorSerCalculado = valorSerCalculado;
        this.valor = valor;
    }
    public static CalcularValor createQuantidadeVezesValor(Integer quantidade, Double valor){
        return new CalcularValor(quantidade,valor);
    }
    public static CalcularValor createPorcetagemDoValor(Double valorAdescontar, Double valor){
        return new CalcularValor(valorAdescontar,valor);
    }
    public static CalcularValor createDescontoDoValor(Double valorAdescontar, Double valor){
        return new CalcularValor(valorAdescontar,valor);
    }
    private Double quantidadeVezesValor(){
        return this.quantidade * this.valor;
    }
    private Double Subtrair(Double valorUm, Double valorDois) {
        if (valorDois > valorUm) {
            return valorDois - valorUm;
        }
        return valorDois;
    }
    public Double getValorCalculadoDaPorcetagem() {
        Double calcularPorcetagem =calcularPorcetagem();
        return Subtrair(calcularPorcetagem,valor);
    }
    public Double getValorCalculado() {
        return Subtrair(this.valorSerCalculado,this.valor);
    }
    private Double calcularPorcetagem(){
        return (this.valor * this.valorSerCalculado) / 100;
    }
    private boolean verificarDecimal( String texto ) {
        if (Pattern.matches ("(\\d)*,\\d{2}+",texto)) {
            return true;
        }
        return false;
    }
}
