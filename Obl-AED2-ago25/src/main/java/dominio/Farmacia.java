package dominio;

import interfaz.Sistema;

public class Farmacia implements Comparable<Farmacia>{
    private String nombre;
    private String codigo;

    public Farmacia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }

    @Override
    public int compareTo(Farmacia o) {
        return this.codigo.compareTo(o.codigo);
    }
}
