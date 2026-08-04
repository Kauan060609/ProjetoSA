package model;

import exceptions.CepInvalidoException;
import exceptions.CpfInvalidoException;
import exceptions.SenhaInvalidaException;
import exceptions.TelefoneInvalidoException;
import exceptions.ValorInvalidoException;

public class Cliente {
    private int id_cliente;
    private String nome_cliente;
    private String cpf_cliente;
    private String telefone_cliente;
    private String email_cleinte; 
    private String cep_cliente;
    private String senha;
    private Endereco endereco;

    // Primeiro Construtor (com ID)
    public Cliente(int id_cliente, String nome_cliente, String cpf_cliente, String telefone_cliente, String email_cleinte, String cep_cliente, String senha, Endereco endereco) throws CpfInvalidoException, TelefoneInvalidoException, CepInvalidoException, ValorInvalidoException, SenhaInvalidaException {
        if(id_cliente <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_cliente = id_cliente;
        this.nome_cliente = nome_cliente;
        
        if(cpf_cliente.length() != 11){
            throw new CpfInvalidoException("O cpf deve obrigatóriamente ter 11 digítos!");
        }
        this.cpf_cliente = cpf_cliente;
        
        if(telefone_cliente.length() != 13 && telefone_cliente.length() != 11){
            throw new TelefoneInvalidoException("O número de telefone deve obrigatoriamente ter 11 Digítos!");
        }
        this.telefone_cliente = telefone_cliente;
        this.email_cleinte = email_cleinte;
        
        if(cep_cliente.length() != 8){
            throw new CepInvalidoException("O cep deve ter obrigatóriamente 8 digítos!");
        }
        this.cep_cliente = cep_cliente;
        

        if(senha.length() < 8 || senha.length() > 30){
            throw new SenhaInvalidaException("A senha precisa obrigatóriamente ter mais de 8 digítos e um máximo de 30 digítos!");
        }
        this.senha = senha;
        this.endereco = endereco;
    }


    public Cliente(String nome_cliente, String cpf_cliente, String telefone_cliente, String email_cleinte, String cep_cliente, String senha, Endereco endereco) throws CpfInvalidoException, TelefoneInvalidoException, CepInvalidoException, ValorInvalidoException, SenhaInvalidaException {
        this.nome_cliente = nome_cliente;
        
        if(cpf_cliente.length() != 11){
            throw new CpfInvalidoException("O cpf deve obrigatóriamente ter 11 digítos!");
        }
        this.cpf_cliente = cpf_cliente;
        
        if(telefone_cliente.length() != 13 && telefone_cliente.length() != 11){
            throw new TelefoneInvalidoException("O número de telefone deve obrigatoriamente ter 11 Digítos!");
        }
        this.telefone_cliente = telefone_cliente;
        this.email_cleinte = email_cleinte;
        
        if(cep_cliente.length() != 9){
            throw new CepInvalidoException("O cep deve ter obrigatóriamente 10 digítos!");
        }
        this.cep_cliente = cep_cliente;
        

        if(senha.length() < 8 || senha.length() > 30){
            throw new SenhaInvalidaException("A senha precisa obrigatóriamente ter mais de 8 digítos e um máximo de 30 digítos!");
        }
        this.senha = senha;
        this.endereco = endereco;
    }


    public int getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(int id_cliente) throws ValorInvalidoException{
        if(id_cliente <= 0){
            throw new ValorInvalidoException("O id precisa obrigatóriamente ser maior que 0!");
        }
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }
    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getCpf_cliente() {
        return cpf_cliente;
    }
    public void setCpf_cliente(String cpf_cliente) throws CpfInvalidoException{
        if(cpf_cliente.length() != 11) {
            throw new CpfInvalidoException("O cpf deve obrigatóriamente ter 11 digítos!");
        }      
        this.cpf_cliente = cpf_cliente;
    }

    public String getTelefone_cliente() {
        return telefone_cliente;
    }
    public void setTelefone_cliente(String telefone_cliente) throws TelefoneInvalidoException{
        if(telefone_cliente.length() != 13 && telefone_cliente.length() != 11){
            throw new TelefoneInvalidoException("O número de telefone deve obrigatoriamente ter 11 Digítos!");
        }
        this.telefone_cliente = telefone_cliente;
    }

    public String getEmail_cleinte() {
        return email_cleinte;
    }
    public void setEmail_cleinte(String email_cleinte) {
        this.email_cleinte = email_cleinte;
    }

    public String getCep_cliente() {
        return cep_cliente;
    }
    public void setCep_cliente(String cep_cliente) throws CepInvalidoException {
        if(cep_cliente.length() != 8){
            throw new CepInvalidoException("O cep deve ter obrigatóriamente 10 digítos!");
        }
        this.cep_cliente = cep_cliente;
    }

    public String getSenha() {
        return senha;
    }
    

    public void setSenha(String senha) throws SenhaInvalidaException{
        if(senha.length() < 8 || senha.length() > 30){
            throw new SenhaInvalidaException("A senha precisa obrigatóriamente ter mais de 8 digítos e um máximo de 30 digítos!");
        } 
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}