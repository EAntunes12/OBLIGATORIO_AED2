package dominio.ABB;

import dominio.*;

public interface IABB {

    boolean insertar(Medicamento nuevoMedicamento);

    boolean pertenece(String codigo);

    String imprimirAsc();

    void imprimirDes();

    Medicamento obtener(String codigo);

    Medicamento buscarPorNombre(String nombre);

    RecorridosBusqueda BuscarConContador(String codigo);

}
