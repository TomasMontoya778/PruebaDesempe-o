package Entity;

import java.sql.Timestamp;

public class Contratacion {
    private int id_contratacion;
    private int id_vacanteFK;
    private  int id_coderFK;
    private Timestamp fecha_aplicacion;
    private String estado;
    private double salario;
    public Contratacion (){

    }

    public Contratacion(int id_contratacion, int id_vacanteFK, int id_coderFK, Timestamp fecha_aplicacion, String estado, double salario) {
        this.id_contratacion = id_contratacion;
        this.id_vacanteFK = id_vacanteFK;
        this.id_coderFK = id_coderFK;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
    }

    public int getId_contratacion() {
        return id_contratacion;
    }

    public void setId_contratacion(int id_contratacion) {
        this.id_contratacion = id_contratacion;
    }

    public int getId_vacanteFK() {
        return id_vacanteFK;
    }

    public void setId_vacanteFK(int id_vacanteFK) {
        this.id_vacanteFK = id_vacanteFK;
    }

    public int getId_coderFK() {
        return id_coderFK;
    }

    public void setId_coderFK(int id_coderFK) {
        this.id_coderFK = id_coderFK;
    }

    public Timestamp getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(Timestamp fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "\n----Contratacion----" +
                "\nid_contratacion: " + id_contratacion +
                "\nfecha_aplicacion: " + fecha_aplicacion +
                "\nestado: " + estado +
                "\nsalario: " + salario +
                id_vacanteFK+
                id_coderFK;
    }
}
