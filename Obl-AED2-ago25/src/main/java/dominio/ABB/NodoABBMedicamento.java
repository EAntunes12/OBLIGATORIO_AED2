package dominio.ABB;

import dominio.Medicamento;

public class NodoABBMedicamento {
    private NodoABBMedicamento izq;
    private NodoABBMedicamento der;
    private Medicamento dato;

    public NodoABBMedicamento(Medicamento dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }

    public NodoABBMedicamento(NodoABBMedicamento izq, NodoABBMedicamento der, Medicamento dato) {
        this.izq = izq;
        this.der = der;
        this.dato = dato;
    }

    public NodoABBMedicamento getIzq() {
        return izq;
    }

    public void setIzq(NodoABBMedicamento izq) {
        this.izq = izq;
    }

    public NodoABBMedicamento getDer() {
        return der;
    }

    public void setDer(NodoABBMedicamento der) {
        this.der = der;
    }

    public Medicamento getDato() {
        return dato;
    }

    public void setDato(Medicamento dato) {
        this.dato = dato;
    }

    public boolean esHoja() {
        return this.der == null && this.izq == null;
    }

}
