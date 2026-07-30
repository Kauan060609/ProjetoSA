package model;

import exceptions.ValorInvalidoException;


public class Compra {
    private int id_compra;
    private String descricao;
    private double valor_total;
    private Cliente cliente;
    
    public Compra(int id_compra, String descricao, double valor_total, Cliente cliente) throws ValorInvalidoException{
        setId_compra(id_compra);
        this.descricao = descricao;
        setValor_total(valor_total);
        this.cliente = cliente;
    }

    public Compra(String descricao, double valor_total, Cliente cliente) throws ValorInvalidoException {
        this.descricao = descricao;
        setValor_total(valor_total);
        this.cliente = cliente;
    }

    public int getId_compra() {
        return id_compra;
    }
    public void setId_compra(int id_compra) throws ValorInvalidoException{
        if(id_compra <= 0) {
            throw new ValorInvalidoException("O id precisa ser maior que 0.");
        }
        this.id_compra = id_compra;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor_total() {
        return valor_total;
    }
    public void setValor_total(double valor_total) throws ValorInvalidoException {
        if(valor_total < 0) {
            throw new ValorInvalidoException("O valor total não pode ser negativo.");
        }
        this.valor_total = valor_total;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
