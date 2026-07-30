package model;

import exceptions.ValorInvalidoException;

public class ProdutoCompra {
    private int id_produto_compra;
    private int quantidade_produto;
    private double valor_unitario;
    private Produto produto;
    private Compra compra;
    
    public ProdutoCompra(int id_produto_compra, int quantidade_produto, double valor_unitario, Produto produto, Compra compra) throws ValorInvalidoException {
        setId_produto_compra(id_produto_compra);
        setQuantidade_produto(quantidade_produto);
        setValor_unitario(valor_unitario);
        this.produto = produto;
        this.compra = compra;
    }

    public ProdutoCompra(int quantidade_produto, double valor_unitario, Produto produto, Compra compra) throws ValorInvalidoException{
        setQuantidade_produto(quantidade_produto);
        setValor_unitario(valor_unitario);
        this.produto = produto;
        this.compra = compra;
    }

    public int getId_produto_compra() {
        return id_produto_compra;
    }
    public void setId_produto_compra(int id_produto_compra) throws ValorInvalidoException {
        if(id_produto_compra <= 0) {
            throw new ValorInvalidoException("O ID precisa ser maior que 0.");
        }  
        this.id_produto_compra = id_produto_compra;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }
    public void setQuantidade_produto(int quantidade_produto) throws ValorInvalidoException{
        if(quantidade_produto < 0) {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
        this.quantidade_produto = quantidade_produto;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }
    public void setValor_unitario(double valor_unitario) throws ValorInvalidoException{
        if(valor_unitario < 0) {
            throw new ValorInvalidoException("O valor unitário não pode ser negativo.");
        }
        this.valor_unitario = valor_unitario;
    } 

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
}
