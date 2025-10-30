package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test11RegistrarConexion {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(4);
        s.registrarFarmacia("F01", "Farmacia 1");
        s.registrarFarmacia("F02", "Farmacia 2");
        s.registrarFarmacia("F03", "Farmacia 3");
    }

    @Test
    void RegistrarConexionError01() {
        retorno = s.registrarConexion(null, "F02", 10, 100, 50);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = s.registrarConexion("F01", "", 10, 100, 50);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void RegistrarConexionError02() {
        retorno = s.registrarConexion("F99", "F02", 10, 100, 50);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void RegistrarConexionError03() {
        retorno = s.registrarConexion("F01", "F99", 10, 100, 50);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void RegistrarConexionError04() {
        retorno = s.registrarConexion("F01", "F02", 0, 100, 50);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarConexion("F01", "F02", 10, -5, 50);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());

        retorno = s.registrarConexion("F01", "F02", 10, 5, -1);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void RegistrarConexionError05() {
        retorno = s.registrarConexion("F01", "F02", 10, 100, 50);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = s.registrarConexion("F01", "F02", 15, 200, 60);
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void RegistrarConexionOK() {
        retorno = s.registrarConexion("F01", "F02", 15, 300, 80);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
