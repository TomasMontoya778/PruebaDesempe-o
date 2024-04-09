package Entity;

import java.sql.Timestamp;

public class Contratacion {
    private int id_contratacion;
    private Vacante id_vacanteFK;
    private  Coder id_coderFK;
    private Timestamp fecha_aplicacion;
    private String estado;
    private double salario;
    public Contratacion (){

    }

    public Contratacion(int id_contratacion, Vacante id_vacanteFK, Coder id_coderFK, Timestamp fecha_aplicacion, String estado, double salario) {
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

    public Vacante getId_vacanteFK() {
        return id_vacanteFK;
    }

    public void setId_vacanteFK(Vacante id_vacanteFK) {
        this.id_vacanteFK = id_vacanteFK;
    }

    public Coder getId_coderFK() {
        return id_coderFK;
    }

    public void setId_coderFK(Coder id_coderFK) {
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
