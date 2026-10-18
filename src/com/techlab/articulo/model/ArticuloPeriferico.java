package com.techlab.articulo.model;

public class ArticuloPeriferico extends Articulo {
    private String tipoConexion;

    public ArticuloPeriferico(int codigo, String nombre, double precio, Categoria categoria, String tipoConexion) {
        super(codigo, nombre, precio, categoria);
        this.tipoConexion = tipoConexion;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {
        this.tipoConexion = tipoConexion;
    }

    @Override
    public String getTipoArticulo() {
        return "Articulo Periferico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Tipo de conexión: " + tipoConexion;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo periferico]";
    }
}
