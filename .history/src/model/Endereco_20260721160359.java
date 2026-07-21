package model;

import exceptions.ValorInvalidoException;

public class Endereco {
    private int id_enderec;
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
    private int numero;
    private String complemento;
    
    public Endereco(int id_enderec, String pais, String estado, String cidade, String rua, int numero, String complemento) throws ValorInvalidoException {
        if(id_enderec <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_enderec = id_enderec;
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(String pais, String estado, String cidade, String rua, int numero, String complemento) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public int getId_enderec() {
        return id_enderec;
    }
    public void setId_enderec(int id_enderec) throws ValorInvalidoException {
        if(id_enderec <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_enderec = id_enderec;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
