package Entity;

public class Coder {
    private int id_coder;
    private String nombre_coder;
    private String apellido;
    private String documento;
    private int cohorte;
    private String clan;
    private String cv;
    private  String tecnologias;
    public Coder(){

    }

    public Coder(int id_coder, String nombre_coder, String apellido, String documento, int cohorte, String clan, String cv, String tecnologias) {
        this.id_coder = id_coder;
        this.nombre_coder = nombre_coder;
        this.apellido = apellido;
        this.documento = documento;
        this.cohorte = cohorte;
        this.clan = clan;
        this.cv = cv;
        this.tecnologias = tecnologias;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getNombre_coder() {
        return nombre_coder;
    }

    public void setNombre_coder(String nombre_coder) {
        this.nombre_coder = nombre_coder;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCohorte() {
        return cohorte;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getClan() {
        return clan;
    }

    public String getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(String tecnologias) {
        this.tecnologias = tecnologias;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "\n----Coder----" +
                "\nid_coder: " + id_coder +
                "\nnombre_coder: " + nombre_coder +
                "\napellido: " + apellido +
                "\ndocumento: " + documento +
                "\ncohorte: " + cohorte +
                "\nClan: "+ clan+
                "\nTecnologías: " +tecnologias +
                "\ncv: " + cv;
    }
}
