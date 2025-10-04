package dominio.ABBCodigo;

import dominio.*;

public interface IABB {

    boolean insertar(Medicamento nuevoMedicamento);

    boolean pertenece(String codigo);

    String imprimirAsc();

    String imprimirDes();

    Medicamento obtener(String codigo);

    Medicamento buscarPorNombre(String nombre);

    RecorridosBusqueda BuscarConContador(String codigo);

    RecorridosBusqueda BuscarPorNombreContador(String nombre);

}
