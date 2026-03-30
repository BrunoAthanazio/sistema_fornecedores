package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();
    }

    public static void menu(){
        System.out.println("""
                \n
                1 Cadastrar Fornecedor
                2 Buscar Fornecedor por id
                3 Buscar Todos os Fornecedores
                4 Atualizar Fornecedor
                5 Deletar Fornecedor
                6 Cadastrar Equipamento
                7 Buscar equipamento por id
                8 Buscar equipamento por id do fornecedor
                """);
    }

    public static void inicio(){
        menu();
        int opcao = SC.nextInt();
        SC.nextLine();
        switch (opcao){
            case 1: {
                cadastrarFornecedor();
                break;
            }
            case 2: {
                buscarForneceorID();
                break;
            }
            case 3: {
                buscarFornecedores();
                break;
            }
            case 4: {
                atualizarFornecedor();
                break;
            }
            case 5: {
                deletarFornecedor();
                break;
            }
            case 6: {
                cadastrarEquipamento();
                break;
            }
            case 7: {
                buscarEquipamentoID();
                break;
            }
            case 8: {
                buscarEquipamentoFornecedor();
                break;
            }
            case 9: {
                atualizarEquipamento();
                break;
            }
        }
    }

    public static void cadastrarFornecedor() {
        System.out.println("Insira o nome do fornecedor: ");
        String nome = SC.nextLine();
        System.out.println("Insira o cnpj do fornecedor: ");
        String cnpj = SC.nextLine();
        var dao = new SistemaDAO();
        try{
            dao.cadastrarFornecedor(new Fornecedor(nome, cnpj));
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        inicio();
    }

    public static void buscarForneceorID() {
        Fornecedor fornecedor = null;
        System.out.println("Insira o ID do fornecedor: ");
        int busca = SC.nextInt();
        SC.nextLine();
        var dao = new SistemaDAO();
        try{
            fornecedor = dao.buscarFornecedorID(busca);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        if(fornecedor != null){
            System.out.println("id | nome | cnpj");
            System.out.println(fornecedor.getId() + " | " + fornecedor.getNome() + " | " + fornecedor.getCnpj());
        }
        inicio();
    }

    public static void buscarFornecedores() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        var dao = new SistemaDAO();
        try{
            fornecedores = dao.buscarFornecedores();
        } catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        System.out.println("id | nome | cnpj");
        for(Fornecedor fornecedor : fornecedores){
            System.out.println(fornecedor.getId() + " | " + fornecedor.getNome() + " | " + fornecedor.getCnpj());
        }
        inicio();
    }

    public static void atualizarFornecedor(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        var dao = new SistemaDAO();
        System.out.println("Insira o id do Fornecedor que deseja atualizar: ");
        int id = SC.nextInt();
        SC.nextLine();
        try{
            fornecedores = dao.buscarFornecedores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean idEncontrado = false;
        for(Fornecedor fornecedor : fornecedores){
            if(fornecedor.getId() == id){
                idEncontrado = true;
            }
        }
        if (idEncontrado){
            System.out.println("Insira o novo nome do fornecedor: ");
            String nome = SC.nextLine();
            System.out.println("Insira o novo cnpj do fornecedor: ");
            String cnpj = SC.nextLine();
            try{
                dao.atualizarFornecedor(nome, cnpj, id);
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados");
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("Id do fornecedor não encontrado");
        }
    }

    public static void deletarFornecedor(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        var dao = new SistemaDAO();
        System.out.println("Insira o id do Fornecedor que deseja deletar: ");
        int id = SC.nextInt();
        SC.nextLine();
        try{
            fornecedores = dao.buscarFornecedores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean idEncontrado = false;
        for(Fornecedor fornecedor : fornecedores){
            if(fornecedor.getId() == id){
                idEncontrado = true;
            }
        }
        if (idEncontrado){
            try{
                dao.deletarFornecedor(id);
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados");
                e.printStackTrace();
            }
        }else {
            throw new RuntimeException("Id do fornecedor não econtrado!");
        }
        inicio();
    }

    public static void cadastrarEquipamento(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        var dao = new SistemaDAO();
        System.out.println("Insira o id do Fornecedor que deseja relacionar com equipamento: ");
        int id = SC.nextInt();
        SC.nextLine();
        try{
            fornecedores = dao.buscarFornecedores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean idEncontrado = false;
        for(Fornecedor fornecedor : fornecedores){
            if(fornecedor.getId() == id){
                idEncontrado = true;
            }
        }
        if (idEncontrado) {
            try{
                System.out.println("Insira o nome do quipamento: ");
                String nome = SC.nextLine();
                System.out.println("Insira o numero_serie: ");
                String numero_serie = SC.nextLine();
                dao.cadastrarEquipamento(new Equipamento(nome, numero_serie, id));
            }catch (SQLException e){
                System.out.println("Erro ao acessar o banco de dados");
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("Fornecedor inválido ou inexistente");
        }
        inicio();
    }

    public static void buscarEquipamentoID(){
        Equipamento equipamento = null;
        var dao = new SistemaDAO();
        System.out.println("Insira o id do equipamento: ");
        int busca = SC.nextInt();
        SC.nextLine();
        try{
            equipamento = dao.buscarEquipamentoID(busca);
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados");
            e.printStackTrace();
        }
        if (equipamento != null){
            System.out.println("id | nome | numero_serie | fornecedor_id");
            System.out.println(equipamento.getId() + " | " + equipamento.getNome() + " | " + equipamento.getNumero_serie() + " | " + equipamento.getFornecedor_id());
        }
        inicio();
    }

    public static void buscarEquipamentoFornecedor(){
        var dao = new SistemaDAO();
        List<Equipamento> equipamentos = new ArrayList<>();
        System.out.println("Insira o id do fornecedor: ");
        int busca = SC.nextInt();
        SC.nextLine();
        try{
            equipamentos = dao.buscarEquipamentoFornecedor(busca);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("id | nome | numero_serie | fornecedor_id");
        for(Equipamento equipamento : equipamentos){
            System.out.println(equipamento.getId() + " | " + equipamento.getNome() + " | " + equipamento.getNumero_serie() + " | " + equipamento.getFornecedor_id());
        }
        inicio();
    }

    public static void atualizarEquipamento(){

    }
}