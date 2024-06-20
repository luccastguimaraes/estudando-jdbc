package org.trindade.jdbc.dao;

import org.trindade.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() {
        try {
            List<Categoria> categorias = new ArrayList<>();
            String sql = "SELECT ID, NOME FROM CATEGORIA";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.execute();
                try (ResultSet resultSet = preparedStatement.getResultSet()) {
                    while (resultSet.next()) {
                        categorias.add(new Categoria(resultSet.getInt(1), resultSet.getString(2)));
                    }
                }
            }

            return categorias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
