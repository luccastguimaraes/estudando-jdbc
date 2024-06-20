package org.trindade.jdbc.dao;

import org.trindade.jdbc.modelo.Categoria;
import org.trindade.jdbc.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getDescricao());
            preparedStatement.execute();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getInt(1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarComCategoria(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, produto.getNome());
                preparedStatement.setString(2, produto.getDescricao());
                preparedStatement.setInt(3, produto.getCategoriaId());

                preparedStatement.execute();

                try (ResultSet rst = preparedStatement.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar() {
        try {
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
            List<Produto> produtos = new ArrayList<Produto>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String nome = resultSet.getString(2);
                        String descricao = resultSet.getString(3);
                        produtos.add(new Produto(id, nome, descricao));
                    }
                }
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscar(Categoria ct) {
        try {
            List<Produto> produtos = new ArrayList<Produto>();

            System.out.println("Executando a query de buscar produto por categoria");

            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ct.getId());
                preparedStatement.execute();

                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        Integer id = resultSet.getInt(1);
                        String nome = resultSet.getString(2);
                        String descricao = resultSet.getString(3);
                        produtos.add(new Produto(id, nome, descricao));
                    }
                }
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, Integer id) {
        try {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
                preparedStatement.setString(1, nome);
                preparedStatement.setString(2, descricao);
                preparedStatement.setInt(3, id);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
