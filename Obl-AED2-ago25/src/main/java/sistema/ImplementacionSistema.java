package sistema;

import dominio.ABB.ABB;
import dominio.ABB.ABBPorCodigo;
import dominio.ABB.ABBPorNombre;
import interfaz.*;
import dominio.*;
import dominio.ABBCodigo.*;
import dominio.ABBNombre.*;

public class    ImplementacionSistema implements Sistema  {
    private int maxFarmacias;
    private ListaSE<Farmacia> listaFarmacias;
//    private ABBMedicamento arbolMedicamentos;
//    private ABBMedicamentoNombre arbolMedicamentosPorNombre;
    private ABB<Medicamento> arbolPorCodigo;
    private ABB<Medicamento> arbolPorNombre;

    //---------METODOS AUXILIARES-------------------------

    public String generarStringSalida(String codigo, String nombre, String fecha, String categoria){
        return codigo + ";" + nombre + ";" + fecha + ";" + categoria;
    }

    private String formatearCategoria(Categoria c) {
        //Metodo auxiliar para buscarMedicamentoPorCodigo

        switch (c) {
            case VENTA_LIBRE: return "Venta libre";
            case RECETA_COMUN: return "Receta comun";
            case RECETA_CONTROLADA: return "Receta controlada";

            default: return c.toString();
        }
    }

    //---------------------------------------------------------

    @Override
    public Retorno inicializarSistema(int maxFarmacias) {
        if(maxFarmacias <= 3){
            return Retorno.error1("El maximo de farmacias debe ser mayor que 3");
        }

        this.maxFarmacias = maxFarmacias;
        this.listaFarmacias = new ListaSE<>();
        //this.arbolMedicamentos = new ABBMedicamento();
        //this.arbolMedicamentosPorNombre = new ABBMedicamentoNombre();
        this.arbolPorCodigo = new ABBPorCodigo();
        this.arbolPorNombre = new ABBPorNombre();

        //TENGO QUE INICIALIZAR EL RESTO DE LAS ESTRUCTURAS ACA;
        return Retorno.ok();
    }

    @Override
    public Retorno registrarMedicamento(String codigo, String nombre, String fechaVencimiento, Categoria categoria) {

        if(codigo == null || nombre == null || fechaVencimiento == null || categoria == null ){
            return Retorno.error1("Alguno de los datos ingresados fue nulo.");
        }

        final String codigoLimpio = codigo.trim();
        final String nombreLimpio = nombre.trim();
        final String fechaVencimientoLimpio = fechaVencimiento.trim();

        if(codigoLimpio.isEmpty() || nombreLimpio.isEmpty() || fechaVencimientoLimpio.isEmpty()){
            return Retorno.error1("Alguno de los datos ingresados fue nulo.");
        }

        String patronFecha = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!fechaVencimientoLimpio.matches(patronFecha)) {
            return Retorno.error2("La fecha debe tener el formato AAAA-MM-DD.");
        }

        if(arbolPorCodigo == null || arbolPorNombre == null){
            this.arbolPorCodigo = new ABBPorCodigo();
            this.arbolPorNombre = new ABBPorNombre();
        }

        if (this.arbolPorCodigo.obtener(codigoLimpio) != null && this.arbolPorNombre.obtener(codigoLimpio) != null) {
            return Retorno.error3("Ya existe un medicamento con ese código.");
        }

        if (this.arbolPorCodigo.buscarPorNombre(nombreLimpio) != null && arbolPorNombre.buscarPorNombre(nombreLimpio) != null) {
            return Retorno.error4("Ya existe un medicamento con ese nombre.");
        }

        Medicamento nuevoMedicamento = new Medicamento(codigoLimpio, nombreLimpio, fechaVencimientoLimpio, categoria);
        this.arbolPorCodigo.insertar(nuevoMedicamento);
        this.arbolPorNombre.insertar(nuevoMedicamento);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarMedicamentoPorCodigo(String codigo) {
        if(codigo == null || codigo.trim().isEmpty()){
            return Retorno.error1("El codigo no puede ser vacio o nulo.");
        }

        RecorridosBusqueda recorridos = this.arbolPorCodigo.buscarConContador(codigo);

        if(recorridos.getMedicamento() == null){
            return Retorno.error2("El medicamento no existe.");
        }

        Medicamento m =  recorridos.getMedicamento();
        String salida = generarStringSalida(m.getCodigo(), m.getNombre(), m.getFechaVencimiento(), formatearCategoria(m.getCategoria()));

        return Retorno.ok(recorridos.getRecorridos(), salida);
    }

    @Override
    public Retorno listarMedicamentosPorCodigoAscendente() {
        String listadoMedicamentosAsc = this.arbolPorCodigo.imprimirAsc();
        return Retorno.ok(listadoMedicamentosAsc);
    }

    @Override
    public Retorno listarMedicamentosPorCodigoDescendente() {
        String listadoMedicamentosDes = this.arbolPorCodigo.imprimirDes();
        return Retorno.ok(listadoMedicamentosDes);
    }

    @Override
    public Retorno buscarMedicamentoPorNombre(String nombre) {
        if(nombre == null || nombre.trim().isEmpty()){
            return Retorno.error1("El nombre no puede ser vacio.");
        }
        RecorridosBusqueda recorridos = this.arbolPorNombre.buscarPorNombreConContador(nombre);

        if(recorridos.getMedicamento() == null){
            return Retorno.error2("El medicamento no existe.");
        }

        Medicamento m =  recorridos.getMedicamento();
        String salida = generarStringSalida(m.getCodigo(), m.getNombre(), m.getFechaVencimiento(), formatearCategoria(m.getCategoria()));

        return Retorno.ok(recorridos.getRecorridos(), salida);
    }

    @Override
    public Retorno listarMedicamentosPorNombreAscendente() {
        String listadoMedicamentosPorNombreAscendente = this.arbolPorNombre.imprimirAsc();
        return Retorno.ok(listadoMedicamentosPorNombreAscendente);
    }

    @Override
    public Retorno listarMedicamentosPorNombreDescendente() {
        String listadoMedicamentosPorNombreDescendente = this.arbolPorNombre.imprimirDes();
        return Retorno.ok(listadoMedicamentosPorNombreDescendente);
    }

    @Override
    public Retorno listarMedicamentosPorCategoría(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarFarmacia(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoOrigen, String codigoDestino, int tiempo, int distancia, int costo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno redFarmaciasPorCantidadDeConexiones(String codigoOrigen, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarFarmacia(String codigoOrigen) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calcularRutaMenorTiempo(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno calcularRutaMenorDistancia(String codigoOrigen, String codigoDestino) {
        return Retorno.noImplementada();
    }




}
