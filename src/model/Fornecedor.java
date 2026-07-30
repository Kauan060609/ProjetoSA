package model;

import exceptions.ValorInvalidoException;

public class Fornecedor {
    private int id_fornecedor;
    private String nome_fornecedor;
    private String cnpj_fornecedor;

    public Fornecedor(int id_fornecedor, String nome_fornecedor, String cnpj_fornecedor) throws ValorInvalidoException {
        if(id_fornecedor <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_fornecedor = id_fornecedor;
        this.nome_fornecedor = nome_fornecedor;
        if(cnpj_fornecedor.length() < 14){
            throw new ValorInvalidoException("O cpnj precisa ter Obrigatóriamente 14 digítos!");
        }
        this.cnpj_fornecedor = cnpj_fornecedor;
    }

    public Fornecedor(String nome_fornecedor, String cnpj_fornecedor) throws ValorInvalidoException{
        this.nome_fornecedor = nome_fornecedor;
        if(cnpj_fornecedor.length() < 14){
            throw new ValorInvalidoException("O cpnj precisa ter Obrigatóriamente 14 digítos!");
        }
        this.cnpj_fornecedor = cnpj_fornecedor;
    }

    public int getId_fornecedor() {
        return id_fornecedor;
    }
    public void setId_fornecedor(int id_fornecedor) throws ValorInvalidoException {
        if(id_fornecedor <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_fornecedor = id_fornecedor;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }
    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getCnpj_fornecedor() {
        return cnpj_fornecedor;
    }
    public void setCnpj_fornecedor(String cnpj_fornecedor) throws ValorInvalidoException{
        if(cnpj_fornecedor.length() < 14){
            throw new ValorInvalidoException("O cpnj precisa ter Obrigatóriamente 14 digítos!");
        }
        this.cnpj_fornecedor = cnpj_fornecedor;
    }
}
