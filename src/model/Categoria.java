package model;

import exceptions.ValorInvalidoException;

public class Categoria {
    private int id_categoria;
    private String nome_categoria;
    private String descricao_categoria;
    
    public Categoria(int id_categoria, String nome_categoria, String descricao_categoria) throws ValorInvalidoException{
        if(id_categoria <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_categoria = id_categoria;
        
        this.nome_categoria = nome_categoria;
        this.descricao_categoria = descricao_categoria;
    }

    public Categoria(String nome_categoria, String descricao_categoria) {
        this.nome_categoria = nome_categoria;
        this.descricao_categoria = descricao_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }
    public void setId_categoria(int id_categoria) throws ValorInvalidoException{
        if(id_categoria <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_categoria = id_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }
    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public String getDescricao_categoria() {
        return descricao_categoria;
    }
    public void setDescricao_categoria(String descricao_categoria) {
        this.descricao_categoria = descricao_categoria;
    } 
}
