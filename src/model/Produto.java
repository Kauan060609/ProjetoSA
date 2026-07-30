package model;

import exceptions.ValorInvalidoException;

public class Produto {
    private int id_produto;
    private String nome_produto;
    private String marca_produto;
    private double valor_produto;
    private Categoria categoria;
    private Estoque estoque;
    private Fornecedor fornecedor;
    
    public Produto(int id_produto, String nome_produto, String marca_produto, double valor_produto, Categoria categoria, Estoque estoque, Fornecedor fornecedor) throws ValorInvalidoException {
        setId_produto(id_produto);
        this.nome_produto = nome_produto;
        this.marca_produto = marca_produto;
        setValor_produto(valor_produto);
        this.categoria = categoria;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }

    public Produto(String nome_produto, String marca_produto, double valor_produto, Categoria categoria, Estoque estoque, Fornecedor fornecedor) throws ValorInvalidoException {
        this.nome_produto = nome_produto;
        this.marca_produto = marca_produto;
        setValor_produto(valor_produto);
        this.categoria = categoria;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }

    public int getId_produto() {
        return id_produto;
    }
    public void setId_produto(int id_produto) throws ValorInvalidoException{
        if(id_produto <= 0) {
            throw new ValorInvalidoException("O id precisa ser maior que 0.");
        }
        this.id_produto = id_produto;
    }

    public String getNome_produto() {
        return nome_produto;
    }
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getMarca_produto() {
        return marca_produto;
    }
    public void setMarca_produto(String marca_produto) {
        this.marca_produto = marca_produto;
    }

    public double getValor_produto() {
        return valor_produto;
    }
    public void setValor_produto(double valor_produto) throws ValorInvalidoException {
        if(valor_produto < 0) {
            throw new ValorInvalidoException("O valor do produto não pode ser negativo.");
        }
        this.valor_produto = valor_produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
