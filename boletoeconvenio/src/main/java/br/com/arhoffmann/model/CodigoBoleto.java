package br.com.arhoffmann.model;

public class CodigoBoleto {
    private Integer type;
    private String digitable;

    public CodigoBoleto(Integer type, String digitable) {
        this.type = type;
        this.digitable = digitable;
    }
}
