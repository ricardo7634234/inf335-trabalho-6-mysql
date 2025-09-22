package br.unicamp.ic.inf335;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        // --- 1. CRIAR Produtos ---
        System.out.println("--- 1. CRIANDO PRODUTOS ---");
        Produto note = new Produto("NT-001", "Notebook Gamer", "Notebook com placa de vídeo dedicada", new BigDecimal("5500.00"), "Estado de novo");
        Produto mouse = new Produto("MS-002", "Mouse Sem Fio", "Mouse ergonômico com 6 botões", new BigDecimal("150.75"), "Estado de novo");
        produtoDAO.insereProduto(note);
        produtoDAO.insereProduto(mouse);
        System.out.println();

        // --- 2. LISTAR todos os produtos ---
        System.out.println("--- 2. LISTANDO TODOS OS PRODUTOS ---");
        List<Produto> produtos = produtoDAO.listaProdutos();
        produtos.forEach(System.out::println);
        System.out.println();

        // --- 3. ATUALIZAR um produto ---
        System.out.println("--- 3. ATUALIZANDO UM PRODUTO ---");
        if (!produtos.isEmpty()) {
            Produto produtoParaAtualizar = produtos.get(0); // Pegando o notebook
            produtoParaAtualizar.setValor(new BigDecimal("5250.00"));
            produtoParaAtualizar.setEstado("Poucos riscos");
            produtoDAO.atualizarProduto(produtoParaAtualizar);
        }
        System.out.println();

        // --- Listar novamente para ver a alteração ---
        System.out.println("--- LISTANDO PRODUTOS APÓS ATUALIZAÇÃO ---");
        produtoDAO.listaProdutos().forEach(System.out::println);
        System.out.println();

        // --- 4. DELETAR um produto ---
        System.out.println("--- 4. DELETANDO UM PRODUTO ---");
        // Deletando o mouse pelo código
        produtoDAO.apagaProduto("MS-002");
        System.out.println();

        // --- Listar uma última vez para confirmar a exclusão ---
        System.out.println("--- LISTA FINAL DE PRODUTOS ---");
        produtoDAO.listaProdutos().forEach(System.out::println);
    }
}