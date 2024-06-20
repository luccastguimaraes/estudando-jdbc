package org.trindade.jdbc.modelo;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer categoriaId;

    public Produto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCategoriaId() {
        return this.categoriaId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoriaId(Integer id) {
        this.categoriaId = id;
    }

    @Override
    public String toString() {
        return String.format("Id: %d - Nome: %s - Descrição: %s"
                , this.id, this.nome, this.descricao);
    }


}
