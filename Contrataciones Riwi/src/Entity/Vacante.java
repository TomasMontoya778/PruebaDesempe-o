package Entity;

public class Vacante {
    private int id_vacante;
    private Empresa id_empresaFK;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;
    public Vacante(){

    }

    public Vacante(int id_vacante, Empresa id_empresaFK, String titulo, String descripcion, String duracion, String estado, String tecnologia) {
        this.id_vacante = id_vacante;
        this.id_empresaFK = id_empresaFK;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
    }

    public int getId_vacante() {
        return id_vacante;
    }

    public void setId_vacante(int id_vacante) {
        this.id_vacante = id_vacante;
    }

    public Empresa getId_empresaFK() {
        return id_empresaFK;
    }

    public void setId_empresaFK(Empresa id_empresaFK) {
        this.id_empresaFK = id_empresaFK;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    @Override
    public String toString() {
        return "\n----Vacante----" +
                "\nIdentificador de la vacante: " + id_vacante +
                "\nTítulo: " + titulo +
                "\nDescripción: " + descripcion +
                "\nTecnologías requeridas: " + tecnologia +
                "\nDuración: " + duracion +
                "\nEstado: " + estado
                + id_empresaFK;
    }
}
