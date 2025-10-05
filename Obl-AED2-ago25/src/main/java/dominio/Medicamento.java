package dominio;

import interfaz.Categoria;

public class Medicamento {
    private String codigo;
    private String nombre;
    private String fechaVencimiento;
    private Categoria categoria;

    public Medicamento(String codigo, String nombre, String fechaVencimiento, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaVencimiento = fechaVencimiento;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private String formatearCategoria(Categoria c) {
        switch (c) {
            case VENTA_LIBRE: return "Venta libre";
            case RECETA_COMUN: return "Receta comun";
            case RECETA_CONTROLADA: return "Receta controlada";

            default: return c.toString();
        }
    }

    @Override
    public String toString() {
        String categoria = formatearCategoria(this.categoria);

        return this.codigo + ";"
                + this.nombre + ";"
                + this.fechaVencimiento + ";"
                + categoria;
    }
}
