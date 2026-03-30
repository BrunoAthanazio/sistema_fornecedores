package org.example;

public class Equipamento {
    private int id;
    private String nome, numero_serie;
    private int fornecedor_id;

    public Equipamento(int id, String nome, String numero_serie, int fornecedor_id) {
        this.id = id;
        this.nome = nome;
        this.numero_serie = numero_serie;
        this.fornecedor_id = fornecedor_id;
    }

    public Equipamento(String nome, String numero_serie, int fornecedor_id) {
        this.nome = nome;
        this.numero_serie = numero_serie;
        this.fornecedor_id = fornecedor_id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public int getFornecedor_id() {
        return fornecedor_id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public void setFornecedor_id(int fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }
}
