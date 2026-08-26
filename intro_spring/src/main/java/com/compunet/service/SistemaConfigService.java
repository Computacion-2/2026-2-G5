package com.compunet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SistemaConfigService {

    @Value("${app.institucion.nombre}")
    private String institucion;

    @Value("${app.institucion.departamento}")
    private String departamento;

    @Value("${app.matricula.max-creditos}")
    private int maxCreditos;

    @Value("${app.matricula.costo-credito:500000}")
    private double costoCredito;

    @Value("${app.matricula.descuento.beca:0.10}")
    private double descuentoCredito;

    @Value("${app.matricula.habilitada}")
    private boolean matriculaHabilitada;

    @Value("${app.timeout:3000}")
    private int timeout;

    @Value("${app.matricula.email-contacto}")
    private String correoSoporte;

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getMaxCreditos() {
        return maxCreditos;
    }

    public void setMaxCreditos(int maxCreditos) {
        this.maxCreditos = maxCreditos;
    }

    public double getCostoCredito() {
        return costoCredito;
    }

    public void setCostoCredito(double costoCredito) {
        this.costoCredito = costoCredito;
    }

    public double getDescuentoCredito() {
        return descuentoCredito;
    }

    public void setDescuentoCredito(double descuentoCredito) {
        this.descuentoCredito = descuentoCredito;
    }

    public boolean isMatriculaHabilitada() {
        return matriculaHabilitada;
    }

    public void setMatriculaHabilitada(boolean matriculaHabilitada) {
        this.matriculaHabilitada = matriculaHabilitada;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getCorreoSoporte() {
        return correoSporte;
    }

    public void setCorreoSporte(String correoSporte) {
        this.correoSporte = correoSporte;
    }

    
}
