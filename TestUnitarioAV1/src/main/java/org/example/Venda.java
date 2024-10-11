package org.example;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double totalVenda;

    public Venda(Produto produto, int quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public boolean realizarVenda() {
        if (this.quantidadeVendida < 0) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser negativa");
        }

        if (!this.produto.diminuirEstoque(this.quantidadeVendida)) {
            throw new IllegalStateException("Estoque insuficiente para realizar a venda");
        }

        this.totalVenda = this.produto.getPreco() * (double)this.quantidadeVendida;
        return true;
    }


    public double getTotalVenda() {
        return totalVenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
