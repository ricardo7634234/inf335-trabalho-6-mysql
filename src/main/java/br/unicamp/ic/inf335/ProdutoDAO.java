package br.unicamp.ic.inf335;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    /**
     * Cria um novo produto no banco de dados.
     * @param produto O objeto Produto a ser salvo.
     */
    public void insereProduto(Produto produto) {
        String sql = "INSERT INTO produtos (codigo, nome, descricao, valor, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getCodigo());
            pstmt.setString(2, produto.getNome());
            pstmt.setString(3, produto.getDescricao());
            pstmt.setBigDecimal(4, produto.getValor());
            pstmt.setString(5, produto.getEstado());
            pstmt.executeUpdate();
            System.out.println("✅ Produto criado com sucesso: " + produto.getNome());

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao criar produto: " + e.getMessage());
        }
    }

    /**
     * Lista todos os produtos cadastrados no banco de dados.
     * @return Uma lista de objetos Produto.
     */
    public List<Produto> listaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getString("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValor(rs.getBigDecimal("valor"));
                produto.setEstado(rs.getString("estado"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    /**
     * Atualiza as informações de um produto existente.
     * @param produto O objeto Produto com os dados atualizados.
     */
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, valor = ?, estado = ? WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setBigDecimal(3, produto.getValor());
            pstmt.setString(4, produto.getEstado());
            pstmt.setString(5, produto.getCodigo());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("🔄 Produto atualizado com sucesso para o código: " + produto.getCodigo());
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com o código: " + produto.getCodigo());
            }

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar produto: " + e.getMessage());
        }
    }

    /**
     * Deleta um produto do banco de dados pelo seu código.
     * @param codigo O código do produto a ser deletado.
     */
    public void apagaProduto(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("❌ Produto com código " + codigo + " foi deletado.");
            } else {
                System.out.println("⚠️ Nenhum produto encontrado com o código: " + codigo);
            }

        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar produto: " + e.getMessage());
        }
    }
}