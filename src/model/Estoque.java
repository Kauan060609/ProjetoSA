package model;

import exceptions.ValorInvalidoException;

public class Estoque {
    private int id_estoque;
    private int quantidade_minima;
    private int quantidade_estoque;
    
    public Estoque(int id_estoque, int quantidade_minima, int quantidade_estoque) throws ValorInvalidoException {
        if(id_estoque <= 0) {
            throw new ValorInvalidoException("O id não pode ser inferior a 1.");
        }
        this.id_estoque = id_estoque;
        if(quantidade_minima >= 0) { 
            this.quantidade_minima = quantidade_minima;
        } else {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
        if(quantidade_estoque >= 0) {
            this.quantidade_estoque = quantidade_estoque;
        } else {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
    }

    public Estoque(int quantidade_minima, int quantidade_estoque) throws ValorInvalidoException {
        if(quantidade_minima <= 0) {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
        this.quantidade_minima = quantidade_minima;
        if(quantidade_estoque <= 0) {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
        this.quantidade_estoque = quantidade_estoque;
    }

    public int getId_estoque() {
        return id_estoque;
    }
    public void setId_estoque(int id_estoque) throws ValorInvalidoException {
        if(id_estoque <= 0) {
            throw new ValorInvalidoException("O id não pode ser inferior a 1.");
        }
        this.id_estoque = id_estoque;
    }

    public int getQuantidade_minima() {
        return quantidade_minima;
    }
    public void setQuantidade_minima(int quantidade_minima) throws ValorInvalidoException {
        if(quantidade_minima <= 0) {
             throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        } 
        this.quantidade_minima = quantidade_minima;
       
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }
    public void setQuantidade_estoque(int quantidade_estoque) throws ValorInvalidoException {
        if(quantidade_estoque <= 0) {
            throw new ValorInvalidoException("A quantidade não pode ser negativa.");
        }
        this.quantidade_estoque = quantidade_estoque;
    }
}
