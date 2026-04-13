package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SistemaDAO {
    public void cadastrarFornecedor(Fornecedor fornecedor)throws SQLException{
        String command = """
                INSERT INTO Fornecedor
                (nome, cnpj)
                VALUES
                (?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.executeUpdate();
        }
    }

    public Fornecedor buscarFornecedorID(int busca) throws SQLException{
        String query = """
                SELECT id, nome, cnpj FROM Fornecedor
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");
                Fornecedor fornecedor = new Fornecedor(id, nome, cnpj);
                return fornecedor;
            }else {
                throw new RuntimeException("Id do fornecedor não encontrado!");
            }
        }
    }

    public List<Fornecedor> buscarFornecedores() throws SQLException{
        String query = """
                SELECT id, nome, cnpj FROM Fornecedor
                """;
        List<Fornecedor> fornecedors = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpnj = rs.getString("cnpj");

                fornecedors.add(new Fornecedor(id, nome, cpnj));
            }
        }
        return fornecedors;
    }

    public void atualizarFornecedor(String nome, String cnpj, int id) throws SQLException{
        String command = """
                UPDATE Fornecedor
                SET nome = ?, cnpj = ?
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, nome);
            stmt.setString(2, cnpj);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    public void deletarFornecedor(int id) throws SQLException{
        String command = """
                DELETE FROM Fornecedor WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void cadastrarEquipamento(Equipamento equipamento)throws SQLException{
        String command = """
                INSERT INTO Equipamento
                (nome, numero_serie, fornecedor_id)
                VALUES
                (?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumero_serie());
            stmt.setInt(3, equipamento.getFornecedor_id());
            stmt.executeUpdate();
        }
    }

    public Equipamento buscarEquipamentoID(int busca) throws SQLException{
        String query = """
                SELECT id, nome, numero_serie, fornecedor_id
                FROM Equipamento
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero_serie = rs.getString("numero_serie");
                int fornecedor_id = rs.getInt("fornecedor_id");

                return new Equipamento(id, nome, numero_serie, fornecedor_id);
            }else {
                System.out.println("Id do equipamento não encontrado!");
                throw new RuntimeException("Id do equipamento não encontrado!");
            }
        }
    }

    public List<Equipamento> buscarEquipamentoFornecedor(int busca) throws SQLException{
        String query = """
                SELECT id, nome, numero_serie, fornecedor_id
                FROM Equipamento
                WHERE fornecedor_id = ?;
                """;
        List<Equipamento> equipamentos = new ArrayList<>();
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero_serie = rs.getString("numero_serie");
                int fornecedor_id = rs.getInt("fornecedor_id");

                equipamentos.add(new Equipamento(id, nome, numero_serie, fornecedor_id));
            }
            return equipamentos;
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) throws SQLException{
        String command = """
                UPDATE Equipamento
                SET fornecedor_id = ?
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, equipamento.getFornecedor_id());
            stmt.setInt(2, equipamento.getId());
            stmt.executeUpdate();
        }
    }

    public void deletarEquipamento(Equipamento equipamento) throws SQLException{
        String command = """
                DELETE FROM Equipamento
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, equipamento.getId());
            stmt.executeUpdate();
        }
    }
}
