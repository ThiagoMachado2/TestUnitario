package org.example;

public class Produto {
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo");
        }

        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public boolean diminuirEstoque(int quantidade) {
        if (estoque >= quantidade) {
            estoque -= quantidade;
            return true;
        }
        return false;
    }

    public void aumentarEstoque(int quantidade) {
        estoque += quantidade;
    }
}
