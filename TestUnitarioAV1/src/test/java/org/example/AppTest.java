package org.example;


import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private Produto produto;
    @BeforeEach
    public void inicio(){
        produto = new Produto("Notebook", 2500.00, 10);
        System.out.println("Sempre faz no INICIO de cada teste");
    }

    @AfterEach
    public void fim(){
       System.out.println("Sempre faz no FINAL de cada teste");
    }

    @Test
    void CriarProdutoValoresValidos() {
        assertEquals("Notebook", produto.getNome());
        assertEquals(2500.00, produto.getPreco());
        assertEquals(10, produto.getEstoque());
    }

    @Test
    void CriacaoProdutoComPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Notebook", -2500.00, 10);
        });
    }

    @Test
    void CriacaoProdutoComEstoqueNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Notebook", 2500.00, -5);
        });
    }

    @Test
    void AlteracaoNomeDeProduto() {
        produto.setNome("Acer Notebook");
        assertEquals("Acer Notebook", produto.getNome());
    }

    @Test
    void AlteracaoPrecoProduto() {
        produto.setPreco(3000.00);
        assertEquals(3000.00, produto.getPreco());
    }

    @Test
    void AlteracaoEstoqueProdutoPositivo() {
        produto.aumentarEstoque(5);
        assertEquals(15, produto.getEstoque());
    }

    @Test
    void AlteracaoPrecoProdutoNegativo() {
        produto.setPreco(-100.00);
        assertNotEquals( produto.getPreco() < 0, produto.getPreco());
    }

    @Test
    void VendaQuantidadeMenorQueEstoque() {
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, venda.getQuantidadeVendida());
    }

    @Test
    void VendaQuantidadeIgualAoEstoque() {
        Venda venda = new Venda(produto, 10);
        assertTrue(venda.realizarVenda());
        assertEquals(10, venda.getQuantidadeVendida());
    }

    @Test
    void VendaQuantidadeMaiorQueEstoque() {
        Venda venda = new Venda(produto, 15);
        assertThrows(IllegalStateException.class, () -> {venda.realizarVenda();});
    }

    @Test
    void CalculoTotalVendaCorretamente() {
        Venda venda = new Venda(produto, 2);
        venda.realizarVenda();
        assertEquals(5000.00, venda.getTotalVenda());
    }

    @Test
    void AumentoEstoqueAposVenda() {
        Venda venda = new Venda(produto, 5);
        venda.realizarVenda();
        produto.aumentarEstoque(5);
        assertEquals(10, produto.getEstoque());
    }

    @Test
    void DiminucaoEstoqueAposVenda() {
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }

    @Test
    void CriacaoEVendaProdutoNaoExistente() {
        Produto produto = null;
        assertThrows(NullPointerException.class, () -> {
            Venda venda = new Venda(produto, 5);
            venda.realizarVenda();
        });
    }


    @Test
    void CriacaoEVendaComQuantidadeNegativa() {
        Produto produto = new Produto("Notebook", 2500.00, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            Venda venda = new Venda(produto, -5);
            venda.realizarVenda();
        });
    }

    @Test
    void AlteracaoEstoqueAposVendaComEstoqueInsuficiente() {
        Produto produto = new Produto("Notebook", 2500.00, 0);
        Venda venda = new Venda(produto, 5);
        assertThrows(IllegalStateException.class, () -> {venda.realizarVenda();});
        assertEquals(0, produto.getEstoque());
    }


    @Test
    void CriacaoVariosProdutosERealizarVendasComEstoqueCompartilhado() {
        Produto produto1 = new Produto("Notebook", 2500.00, 10);
        Produto produto2 = new Produto("Celular", 1500.00, 5);
        Venda venda1 = new Venda(produto1, 3);
        Venda venda2 = new Venda(produto2, 2);

        assertTrue(venda1.realizarVenda());
        assertTrue(venda2.realizarVenda());
        assertEquals(7, produto1.getEstoque());
        assertEquals(3, produto2.getEstoque());
    }

    @Test
    void CalcularTotalVendaQuandoPrecoProdutoAlteradoAntesDaVenda() {
        Produto produto = new Produto("Notebook", 2500.00, 10);
        produto.setPreco(3000.00);
        Venda venda = new Venda(produto, 2);
        venda.realizarVenda();
        assertEquals(6000.00, venda.getTotalVenda());
    }

    @Test
    void ComportamentoVendaQuandoEstoqueInicialEhZero() {
        Produto produto = new Produto("Notebook", 2500.00, 0);
        Venda venda = new Venda(produto, 1);
        assertThrows(IllegalStateException.class, () -> {venda.realizarVenda();});
    }


    @Test
    void AumentoEstoqueAposReposicaoEVendaBemSucedida() {
        Produto produto = new Produto("Notebook", 2500.00, 0);
        produto.aumentarEstoque(10);
        Venda venda = new Venda(produto, 5);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }
}
