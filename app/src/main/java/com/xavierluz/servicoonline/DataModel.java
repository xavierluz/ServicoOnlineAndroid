package com.xavierluz.servicoonline;

public class DataModel {
    String nomeServico;
    String sloganServico;
    int id_;
    int image;

    public DataModel(String nomeServico, String sloganServico, int id_, int image) {
        this.nomeServico = nomeServico;
        this.sloganServico = sloganServico;
        this.id_ = id_;
        this.image=image;
    }

    public String getNomeServico() {
        return this.nomeServico;
    }

    public String getSloganServico() {
        return this.sloganServico;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
