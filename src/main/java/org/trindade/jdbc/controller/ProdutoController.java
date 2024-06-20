package org.trindade.jdbc.controller;

import org.trindade.jdbc.dao.ProdutoDAO;
import org.trindade.jdbc.factory.ConnectionFactory;
import org.trindade.jdbc.modelo.Categoria;
import org.trindade.jdbc.modelo.Produto;

import java.util.List;

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO(new ConnectionFactory().getConexao());
    }

    public void salvar(Produto produto){
        this.produtoDAO.salvar(produto);
    }

    public void salvarComCategoria(Produto produto){
        this.produtoDAO.salvarComCategoria(produto);
    }

    public List<Produto> listar() {
        return this.produtoDAO.listar();
    }

    public List<Produto> buscar(Categoria ct) {
        return this.produtoDAO.buscar(ct);
    }

    public void deletar(Integer id) {
        this.produtoDAO.deletar(id);
    }

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome, descricao, id);
    }
}
