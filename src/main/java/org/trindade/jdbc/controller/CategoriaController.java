package org.trindade.jdbc.controller;

import org.trindade.jdbc.dao.CategoriaDAO;
import org.trindade.jdbc.factory.ConnectionFactory;
import org.trindade.jdbc.modelo.Categoria;

import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController()  {
        this.categoriaDAO = new CategoriaDAO(new ConnectionFactory().getConexao());
    }

    public List<Categoria> listar() {
        return this.categoriaDAO.listar();
    }


}
