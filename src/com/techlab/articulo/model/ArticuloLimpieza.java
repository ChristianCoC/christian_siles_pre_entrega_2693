package com.techlab.articulo.model;

public class ArticuloLimpieza extends Articulo {
    private String tipoProducto;

    public ArticuloLimpieza(int codigo, String nombre, double precio, Categoria categoria, String tipoProducto) {
        super(codigo, nombre, precio, categoria);
        this.tipoProducto = tipoProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String getTipoArticulo() {
        return "Articulo de Limpieza";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Tipo de producto: " + tipoProducto;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo limpieza]";
    }
}
