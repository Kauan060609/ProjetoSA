import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.*;
import connection.ConnectionSA;
import model.Categoria;
import model.Cliente;
import model.Compra;
import model.Endereco;
import model.Estoque;
import model.Fornecedor;
import model.Produto;
import model.ProdutoCompra;
import exceptions.ValorInvalidoException;
import exceptions.CepInvalidoException;
import exceptions.CpfInvalidoException;
import exceptions.SenhaInvalidaException;
import exceptions.TelefoneInvalidoException;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static CategoriaDAO categoriaDAO = new CategoriaDAO();
    static ClienteDAO clienteDAO = new ClienteDAO();
    static CompraDAO compraDAO = new CompraDAO();
    static EnderecoDAO enderecoDAO = new EnderecoDAO();
    static EstoqueDAO estoqueDAO = new EstoqueDAO();
    static FornecedorDAO fornecedorDAO = new FornecedorDAO();
    static ProdutoCompraDAO produtoCompraDAO = new ProdutoCompraDAO();
    static ProdutoDAO produtoDAO = new ProdutoDAO();
    static ViewsDAO viewsDAO = new ViewsDAO();
    static QuerysDAO querysDAO = new QuerysDAO();
    public static void main(String[] args){
        int chosen = 0;
        int opcao = 0;
        int subOpcao = 0;
        do{
            System.out.print("=== MENU DE TABELAS ===\n1 - Categoria\n2 - Cliente\n3 - Compra\n4 - Endereço\n5 - Estoque\n6 - Fornecedor\n7 - ProdutoCompra\n8 - Produtos\n9 - Views\n10 - Queries\n0 - Sair\n --> ");
            chosen = scan.nextInt();
            if(chosen > 10 || chosen < 0){
                System.out.println("Tabela não encontrada");
            }else if(chosen == 9){
                //todo chamar o viewDAO e fazer menu
                System.out.println("=== VIEWS ===\n1 - Clientes\n2 - Estoque\n3 - Financeiro\n4 - Fornecedores");
                opcao = scan.nextInt();
                if(opcao >= 1 && opcao <= 4) {
                    subOpcao = 0;
                    if(opcao == 1) {
                        System.out.println("=== CLIENTES ===\n1 - Ver perfil do cliente\n2 - Ver perfil público do cliente\n3 - Ver clientes vip's\n4 - Ver concentração de clientes por Estado/Cidade");
                        subOpcao = scan.nextInt();
                    } else if (opcao == 3) {
                        System.out.println("=== FATURAMENTO ===\n1 - Ver faturamento por estado.\n2 - Ver vendas por marca\n3 - Ver detalhes de um pedido\nEscolha:");
                        subOpcao = scan.nextInt();
                    }   

                    viewsDAO.showView(opcao, subOpcao);
                } else {
                    System.out.println("Opção não encontrada.");
                } 
            } else if(chosen == 10) {
                System.out.println("=== QUERYS ===\n1 - Gestão e Perfil de Clientes\n2 - Localização e Geografia das Vendas\n3 - Estoque e Fornecedores\n4 - Faturamento, caixa e auditoria\n5 - Desempenho de Categorias\n6 - Analise de produtos e Carrinhos de Compras\nEscolha:  ");
                opcao = scan.nextInt();
                switch(opcao) {
                    case 1:
                        System.out.println("1 - Ver clientes\n2 - Clientes VIPs (+R$1000 gastos)\n3- Clientes cadastrados que nunca compraram\n4 - Resumo de compras por cliente\nEscolha: ");
                        subOpcao = scan.nextInt();
                        break;
                    case 2:
                        System.out.println("1 - Localização dos Clientes\n2 - Estados com maior valor médio gasto por pedido (> R$500)\n Escolha: ");
                        subOpcao = scan.nextInt();
                        break;
                    case 3:
                        System.out.println("1 - Alerta de Reposição\n2 - Valor totalizado do estoque atual\n3 - Fornecedores de cada produto\n4 - Quantidade entregue por Fornecedor\nEscolha: ");
                        subOpcao = scan.nextInt();
                        break;
                    case 4:
                        System.out.println("1 - Faturamento total\n2 - Valor médio por Compra\n3 - Maior compra feita no Site\n4 - Categoria mais lucrativa\nEscolha: ");
                        subOpcao = scan.nextInt();
                        break;
                    case 5:
                        System.out.println("1 - Categorias que mais faturaram\n2 - Quantidade de produtos exitentes por categoria\n3 - Categoria mais lucrativa do sistema.\nEscolha: ");
                        subOpcao = scan.nextInt();
                        break;
                    case 6:
                        System.out.println("1 - Média de itens por carrinho/pedido\n2 - Produtos mais caros e mais baratos\n3 - Produtos mais vendidos (Top 5)\n4 - Itens comprados em um pedido específico\n5 - Busca de produtos por marca\nEscolha: ");
                        subOpcao = scan.nextInt();
                        break;
                }
                if (subOpcao >= 1 && subOpcao <= 6) {
                    querysDAO.showQuery(opcao, subOpcao);
                } else {
                    System.out.println("Opção inválida.");
                }

                
            } else if(chosen == 0){
                    System.out.println("Saindo...");
                    try {
                        Connection conn = ConnectionSA.connect();
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao fechar conexão: " + e.getMessage());
                    }
                }
            else{
                tableSubMenu(chosen);
            }
        }while(chosen != 0);
    }

    public static void tableSubMenu(int option){
        System.out.println("1 - CREATE\n2 - READ\n3 - UPDATE\n4 - DELETE");
        int choice = scan.nextInt();
        int subChoice;
        scan.nextLine();
        String[] input = new String[7];
        String subInput = new String();
        if(choice > 5 || choice <= 0){
            System.out.println("Opção inválida");
            return;
        }

        try{
            switch (option) {
                case 1:
                    if(choice == 1){
                        System.out.println("Template: nome; descrição");
                        input = scan.nextLine().split("; ");
                        categoriaDAO.create(new Categoria(input[0], input[1]));
                    }else if(choice == 2){
                        categoriaDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Categoria updatable = categoriaDAO.getCategoria(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - Nome\n2 - Descriçao\n");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                System.out.println("Novo nome: ");
                                updatable.setNome_categoria(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Nova descrição: ");
                                updatable.setDescricao_categoria(scan.nextLine());
                                break;
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        categoriaDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        categoriaDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 2:
                    if(choice == 1){
                        System.out.println("Template: nome; cpf; telefone; email; cep; senha; id do endereço");
                        input = scan.nextLine().split("; ");
                        clienteDAO.create(new Cliente(input[0], input[1], input[2], input[3], input[4], input[5], enderecoDAO.getEndereco(Integer.valueOf(input[6]))));
                    }else if (choice == 2){
                        clienteDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Cliente updatable = clienteDAO.getCliente(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - Nome\n2 - Cpf\n3 - Telefone\n4 - Email\n5 - CEP\n6 - Senha\n7 - Endereço");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                System.out.println("Novo nome: ");
                                updatable.setNome_cliente(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Novo CPF: ");
                                updatable.setCpf_cliente(scan.nextLine());
                                break;
                            case 3:
                                System.out.println("Novo telefone: ");
                                updatable.setTelefone_cliente(scan.nextLine());
                                break;
                            case 4:
                                System.out.println("Novo email: ");
                                updatable.setEmail_cleinte(scan.nextLine());
                                break;
                            case 5:
                                System.out.println("Novo CEP: ");
                                updatable.setCep_cliente(scan.nextLine());
                                break;
                            case 6:
                                System.out.println("Nova senha: ");
                                updatable.setSenha(scan.nextLine());
                                break;
                            case 7:
                                System.out.println("Id do endereço:");
                                updatable.setEndereco(enderecoDAO.getEndereco(scan.nextInt()));
                                break;
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        clienteDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        clienteDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 3:
                    if(choice == 1){
                        System.out.println("Template: descrição; valor total; id cliente");
                        input = scan.nextLine().split("; ");
                        compraDAO.create(new Compra(input[0], Double.valueOf(input[1]), clienteDAO.getCliente(Integer.valueOf(input[2]))));
                    }else if (choice == 2){
                        compraDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Compra updatable = compraDAO.getCompra(Integer.valueOf(subInput));
                        //String descricao, double valor_total, Cliente client
                        System.out.println("=== ATRIBUTOS ===\n1 - Descrição\n2 - Valor Total\n3 - Cliente\n");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                System.out.println("Nova descrição: ");
                                updatable.setDescricao(scan.nextLine());
                                break;
                            case 2:
                                System.out.println("Novo valor: ");
                                updatable.setValor_total(scan.nextDouble());
                                break;
                            case 3:
                                System.out.println("Id do cliente: ");
                                updatable.setCliente(clienteDAO.getCliente(scan.nextInt()));
                                break;
                                
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        compraDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        compraDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 4:
                    if(choice == 1){
                        System.out.println("Template: pais; estado; cidade; rua; numero; complemento");
                        input = scan.nextLine().split("; ");
                        enderecoDAO.create(new Endereco(input[0], input[1], input[2], input[3], Integer.valueOf(input[4]), input[5]));
                    }else if(choice == 2){
                        enderecoDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Endereco updatable = enderecoDAO.getEndereco(Integer.valueOf(subInput));
                        
                        
                        System.out.println("=== ATRIBUTOS === \n1 - Pais \n2 - Estado \n3- Cidade \n4 - Rua \n5 - Numero \n6 - Complemento \n7 - Id");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                updatable.setPais(scan.nextLine());
                                break;
                            case 2:
                                updatable.setEstado(scan.nextLine());
                                break;

                            case 3:
                                updatable.setCidade(scan.nextLine());
                                break;

                            case 4:
                                updatable.setRua(scan.nextLine());
                                break;

                            case 5:
                                updatable.setNumero(scan.nextInt());
                                break;
                            
                            case 6:
                                updatable.setComplemento(scan.nextLine());
                                break;
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        enderecoDAO.update(updatable);
                    } else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        enderecoDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 5:
                    if(choice == 1){
                        System.out.println("Template: quantidade mínima; quantidade em estoque");
                        input = scan.nextLine().split("; ");
                        estoqueDAO.create(new Estoque(Integer.valueOf(input[0]), Integer.valueOf(input[1])));
                    }else if(choice == 2){
                        estoqueDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Estoque updatable = estoqueDAO.getEstoque(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - Quantidade Mínima\n2 - Quantidade Estoque\n");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                updatable.setQuantidade_minima(scan.nextInt());
                                break;
                            case 2:
                                updatable.setQuantidade_estoque(scan.nextInt());
                                break;  
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        estoqueDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        estoqueDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 6:
                    if(choice == 1){
                        System.out.println("Template: nome; cnpj");
                        input = scan.nextLine().split("; ");
                        fornecedorDAO.create(new Fornecedor(input[0], input[1]));
                    }else if (choice == 2){
                        fornecedorDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Fornecedor updatable = fornecedorDAO.getFornecedor(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - nome_fornecedor \n2 - cnpj_fornecedor");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                updatable.setNome_fornecedor(scan.nextLine());
                                break;
                            case 2:
                                updatable.setCnpj_fornecedor(scan.nextLine());
                                break;

                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        fornecedorDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        fornecedorDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 7:
                    if(choice == 1){
                        System.out.println("Template: quantidade; valor unitário; id do produto; id da compra;");
                        input = scan.nextLine().split("; ");
                        produtoCompraDAO.create(new ProdutoCompra(Integer.valueOf(input[0]), Double.valueOf(input[1]), produtoDAO.getProduto(Integer.valueOf(input[2])), compraDAO.getCompra(Integer.valueOf(input[3]))));
                    }else if (choice == 2){
                        produtoCompraDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        ProdutoCompra updatable = produtoCompraDAO.getProdutoCompra(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - Quantidade de Produtos\n2 - Valor da Unidade\n3 - Produto\n4 - Compra");
                        subChoice = scan.nextInt();
                        switch (subChoice) {
                            case 1:
                                updatable.setQuantidade_produto(scan.nextInt());
                                break;
                            case 2:
                                updatable.setValor_unitario(scan.nextDouble());
                                break;
                            case 3:
                                System.out.print("Id do produto: ");
                                updatable.setProduto(produtoDAO.getProduto(scan.nextInt()));
                                break;
                            case 4:
                                System.out.println("Id da compra: ");
                                updatable.setCompra(compraDAO.getCompra(scan.nextInt()));
                                break;
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        produtoCompraDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.print("id: ");
                        subInput = scan.next();
                        produtoCompraDAO.delete(Integer.valueOf(subInput));
                    }
                    break;
                case 8:
                    if(choice == 1){
                        System.out.println("Template: nome; marca; valor do produto; id da categoria; id do estoque; id do fornecedor");
                        input = scan.nextLine().split("; ");
                        produtoDAO.create(new Produto(input[0], input[1], Double.valueOf(input[2]), categoriaDAO.getCategoria(Integer.valueOf(input[3])), estoqueDAO.getEstoque(Integer.valueOf(input[4])), fornecedorDAO.getFornecedor(Integer.valueOf(input[5]))));
                    }else if(choice == 2){
                        produtoDAO.read();
                    }else if(choice == 3){
                        System.out.print("id: ");
                        subInput = scan.next();
                        Produto updatable = produtoDAO.getProduto(Integer.valueOf(subInput));
                        System.out.println("=== ATRIBUTOS ===\n1 - Nome\n2 - Marca\n3 - Valor do produto\n4 - Categoria\n5 - Estoque\n6 - Fornecedor");
                        subChoice = scan.nextInt();
                        scan.nextLine();
                        switch (subChoice) {
                            case 1:
                                updatable.setNome_produto(scan.nextLine());
                                break;
                            case 2:
                                updatable.setMarca_produto(scan.nextLine());
                                break;
                            case 3:
                                updatable.setValor_produto(scan.nextDouble());
                                break;
                            case 4:
                                System.out.print("Id da categoria: ");
                                updatable.setCategoria(categoriaDAO.getCategoria(scan.nextInt()));
                                break;
                            case 5:
                                System.out.print("Id do estoque: ");
                                updatable.setEstoque(estoqueDAO.getEstoque(scan.nextInt()));
                                break;
                            case 6:
                                System.out.println("Id do fornecedor: ");
                                updatable.setFornecedor(fornecedorDAO.getFornecedor(scan.nextInt()));
                                break;
                            default:
                                System.out.println("Atributo inválido");
                                break;
                        }
                        produtoDAO.update(updatable);
                    }else if(choice == 4){
                        System.out.println("id: ");
                        subInput = scan.next();
                        produtoDAO.delete(Integer.valueOf(subInput));
                    }   
                  
                    break;
                    
            }
        }catch(SQLException sqlError){
            System.out.println("Erro na database: " + sqlError.getMessage());
        }catch(ValorInvalidoException erro){
            System.out.println("Erro no valor: " + erro.getMessage());
        }catch(CpfInvalidoException erro){
            System.out.println("Erro no cpf: " + erro.getMessage());
        }catch(TelefoneInvalidoException erro){
            System.out.println("Erro no telefone: " + erro.getMessage());
        }catch(CepInvalidoException erro){
            System.out.println("Erro no cep: " + erro.getMessage());
        }catch(SenhaInvalidaException erro){
            System.out.println("Erro na senha: " + erro.getMessage());
        }
    }
}