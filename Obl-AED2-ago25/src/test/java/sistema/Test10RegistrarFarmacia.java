package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test10RegistrarFarmacia {
    private Retorno retorno;
    private final Sistema s = new ImplementacionSistema();

    @BeforeEach
    public void setUp() {
        s.inicializarSistema(4);
    }

    @Test
    void RegistrarFarmaciaOK(){
        retorno = s.registrarFarmacia("F01", "Farmacia 1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void RegistrarFarmaciaError1(){
        s.registrarFarmacia("F01", "Farmacia 1");
        s.registrarFarmacia("F02", "Farmacia 2");
        s.registrarFarmacia("F03", "Farmacia 3");
        s.registrarFarmacia("F04", "Farmacia 4");
        retorno = s.registrarFarmacia("F05", "Farmacia 5");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void RegistrarFarmaciaError2(){
        retorno = s.registrarFarmacia(null, "Farmacia 2");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarFarmacia("F02", "");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarFarmacia("F02", null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = s.registrarFarmacia("", "Farmacia 2");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void RegistrarFarmaciaError3(){
        s.registrarFarmacia("F01", "Farmacia 1");
        retorno = s.registrarFarmacia("F01", "Farmacia 2");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
