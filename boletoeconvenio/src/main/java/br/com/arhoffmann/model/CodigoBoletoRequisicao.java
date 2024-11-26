package br.com.arhoffmann.model;

public class CodigoBoletoRequisicao {
    private String externalTerminal;
    private Long externalNSU;
    private CodigoBoleto codigoBoleto;

    public CodigoBoletoRequisicao(String externalTerminal, Long externalNSU, CodigoBoleto codigoBoleto) {
        this.externalTerminal = externalTerminal;
        this.externalNSU = externalNSU;
        this.codigoBoleto = codigoBoleto;
    }
}
