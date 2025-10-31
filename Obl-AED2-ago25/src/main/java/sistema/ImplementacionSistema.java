package sistema;

import dominio.ABB.ABB;
import dominio.ABB.ABBPorCategoria;
import dominio.ABB.ABBPorCodigo;
import dominio.ABB.ABBPorNombre;
import dominio.GRAFO.Conexion;
import dominio.GRAFO.GrafoFarmacia;
import interfaz.*;
import dominio.*;

import java.util.ArrayList;

public class    ImplementacionSistema implements Sistema  {
    private int maxFarmacias;
    private ABB<Medicamento> arbolPorCodigo;
    private ABB<Medicamento> arbolPorNombre;
    private ABB<Medicamento> arbolPorCategoria;
    private GrafoFarmacia grafoFarmacias;

    //---------METODOS AUXILIARES-------------------------

    public String generarStringSalida(String codigo, String nombre, String fecha, String categoria){
        return codigo + ";" + nombre + ";" + fecha + ";" + categoria;
    }

    private String formatearCategoria(Categoria c) {
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
        this.grafoFarmacias = new GrafoFarmacia(maxFarmacias);
        this.arbolPorCodigo = new ABBPorCodigo();
        this.arbolPorNombre = new ABBPorNombre();
        this.arbolPorCategoria = new ABBPorCategoria();

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

        if(arbolPorCodigo == null || arbolPorNombre == null || arbolPorCategoria == null){
            this.arbolPorCodigo = new ABBPorCodigo();
            this.arbolPorNombre = new ABBPorNombre();
            this.arbolPorCategoria = new ABBPorCategoria();
        }

        if (this.arbolPorCodigo.obtener(codigoLimpio) != null) {
            return Retorno.error3("Ya existe un medicamento con ese código.");
        }

        if (this.arbolPorNombre.buscarPorNombre(nombreLimpio) != null) {
            return Retorno.error4("Ya existe un medicamento con ese nombre.");
        }

        Medicamento nuevoMedicamento = new Medicamento(codigoLimpio, nombreLimpio, fechaVencimientoLimpio, categoria);
        this.arbolPorCodigo.insertar(nuevoMedicamento);
        this.arbolPorNombre.insertar(nuevoMedicamento);
        this.arbolPorCategoria.insertar(nuevoMedicamento);

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
        if(unaCategoria == null){
            return Retorno.error1("La categoria no puede ser nula.");
        }
        String listadoMedicamentosPorCategoria = this.arbolPorCategoria.imprimirPorCategoria(unaCategoria);
        return Retorno.ok(listadoMedicamentosPorCategoria);
    }

    @Override
    public Retorno registrarFarmacia(String codigo, String nombre) {
        if(codigo == null || nombre == null || nombre.trim().isEmpty() || codigo.trim().isEmpty()){
            return Retorno.error2("El codigo o el nombre no pueden ser vacios.");
        }

        if(grafoFarmacias.existeFarmacia(codigo)){
            return Retorno.error3("Ya existe una farmacia con ese codigo.");
        }

        if(grafoFarmacias.getCantidad() >= grafoFarmacias.getMaxFarmacias()){
            return Retorno.error1("Ya hay " + maxFarmacias + " farmacias registradas.");
        }

        Farmacia f = new Farmacia(nombre, codigo);
        grafoFarmacias.agregarFarmacia(f);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoOrigen, String codigoDestino, int tiempo, int distancia, int costo) {
        if(codigoOrigen == null || codigoDestino == null || codigoDestino.trim().isEmpty()){
            return Retorno.error1("Los codigos no pueden ser vacios.");
        }
        if(tiempo <= 0 || costo <= 0 || distancia <= 0){
            return Retorno.error4("Los datos deben ser mayores a 0");
        }
        int indiceOrigen = grafoFarmacias.getIndiceFarmacia(codigoOrigen);
        int indiceDestino = grafoFarmacias.getIndiceFarmacia(codigoDestino);

        if(indiceOrigen == -1){
            return Retorno.error2("No existe la farmacia de origen.");
        }
        if(indiceDestino == -1){
            return Retorno.error3("No existe la farmacia de destino.");
        }

        if(grafoFarmacias.existeConexion(indiceOrigen, indiceDestino)){
            return Retorno.error5("Ya existe esa conexion.");
        }

        Conexion conexion = new Conexion(tiempo, distancia, costo);
        grafoFarmacias.crearConexion(indiceOrigen, indiceDestino, conexion);

        return Retorno.ok();
    }

    @Override
    public Retorno redFarmaciasPorCantidadDeConexiones(String codigoOrigen, int cantidad) {
        if(cantidad < 0){
            return Retorno.error1("La cantidad no puede ser menor a 0");
        }
        if(codigoOrigen == null || codigoOrigen.trim().isEmpty()){
            return Retorno.error2("El codigo de origen no puede ser vacio");
        }
        if(!grafoFarmacias.existeFarmacia(codigoOrigen)){
            return Retorno.error3("La farmacia no existe en el sistema");
        }

        ListaSE<Farmacia> listaFarmacias = grafoFarmacias.obtenerRedFarmacias(codigoOrigen, cantidad);
        listaFarmacias.ordenar();

        StringBuilder sb = new StringBuilder();
        NodoListaSE<Farmacia> actual = listaFarmacias.getInicio();

        while (actual != null) {
            Farmacia f = actual.getDato();
            sb.append(f.getCodigo()).append(";").append(f.getNombre());

            if (actual.getSig() != null) {
                sb.append("|");
            }

            actual = actual.getSig();
        }

        return Retorno.ok(sb.toString());
    }

    @Override
    public Retorno analizarFarmacia(String codigoOrigen) {
        if(codigoOrigen == null || codigoOrigen.trim().isEmpty()){
            return Retorno.error1("El codigo no puede ser vacio");
        }
        if(!grafoFarmacias.existeFarmacia(codigoOrigen)){
            return Retorno.error2("No existe esa farmacia en el sistema");
        }

        boolean esCritica = grafoFarmacias.esFarmaciaCritica(codigoOrigen);
        String mensaje = esCritica ? "SI" : "NO";
        return Retorno.ok(mensaje);
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
