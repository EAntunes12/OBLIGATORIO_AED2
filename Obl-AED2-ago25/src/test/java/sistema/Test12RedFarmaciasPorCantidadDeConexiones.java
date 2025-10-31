package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12RedFarmaciasPorCantidadDeConexiones {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(5);
        s.registrarFarmacia("F01", "Farmacia 1");
        s.registrarFarmacia("F02", "Farmacia 2");
        s.registrarFarmacia("F03", "Farmacia 3");
        s.registrarFarmacia("F04", "Farmacia 4");
        s.registrarFarmacia("F05", "Farmacia 5");

        s.registrarConexion("F01", "F02", 10, 20, 100);
        s.registrarConexion("F02", "F03", 15, 40, 200);
        s.registrarConexion("F03", "F04", 12, 23, 140);
        s.registrarConexion("F01", "F05", 18, 30, 199);
    }

    @Test
    void RedFarmaciasPorCantidadDeConexionesError01() {
        retorno = s.redFarmaciasPorCantidadDeConexiones("F01", -1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void RedFarmaciasPorCantidadDeConexionesError02() {
        retorno = s.redFarmaciasPorCantidadDeConexiones(null, 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.redFarmaciasPorCantidadDeConexiones("", 2);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void RedFarmaciasPorCantidadDeConexionesError03() {
        retorno = s.redFarmaciasPorCantidadDeConexiones("F99", 2);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void RedFarmaciasPorCantidadDeConexionesOK(){
        retorno = s.redFarmaciasPorCantidadDeConexiones("F01", 1);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(Salidas.FARM02 + "|" + Salidas.FARM05, retorno.getValorString());

        retorno = s.redFarmaciasPorCantidadDeConexiones("F01", 2);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(Salidas.FARM02 + "|" + Salidas.FARM03 + "|" + Salidas.FARM05, retorno.getValorString());

        retorno = s.redFarmaciasPorCantidadDeConexiones("F01", 3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(Salidas.FARM02 + "|" + Salidas.FARM03 + "|" + Salidas.FARM04 + "|" + Salidas.FARM05, retorno.getValorString());
    }
}
