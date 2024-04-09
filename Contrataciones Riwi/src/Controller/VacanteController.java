package Controller;

import Entity.Vacante;
import Entity.Empresa;
import Model.VacanteModel;
import Model.EmpresaModel;
import Model.VacanteModel;

import javax.swing.*;

public class VacanteController {
    public static void insertVacante(){
        VacanteModel objVacanteModel = new VacanteModel();
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String listEmpresa = EmpresaController.listeEmpresaString();
        try {
            int idEmpresaToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID de la empresa que proporciona la vacante.\n"+listEmpresa));
            Empresa objEmpresa = objEmpresaModel.findByID(idEmpresaToAdd);
            if (objEmpresa == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar el ID de la empresa.");
            }else {
                Vacante objVacante = new Vacante();
                String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante.");
                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la vacante.");
                String duracion =  JOptionPane.showInputDialog("Ingrese la duración de la vacante.");
                String tecnologias = JOptionPane.showInputDialog("Ingrese las tecnologías requeridas para la vacante");
                String estado = JOptionPane.showInputDialog("Ingrese El estado de la vacante. (Activo o Inactivo)");
                objVacante.setTitulo(titulo);
                objVacante.setDescripcion(descripcion);
                objVacante.setDuracion(duracion);
                objVacante.setEstado(estado);
                objVacante.setTecnologia(tecnologias);
                objVacante.setId_empresaFK(objEmpresa);
                objVacante = (Vacante) objVacanteModel.insert(objVacante);
                JOptionPane.showMessageDialog(null, objVacante.toString());
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }
    public static void listVacantes() {
        VacanteModel objVacanteModel = new VacanteModel();
        String text = """
                -------Listado de las Vacantes-------\n
                """;
        for (Object temp : objVacanteModel.readAll()) {
            Vacante objVacante = (Vacante) temp;
            text += objVacante.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listVacantesString() {
        VacanteModel objVacanteModel = new VacanteModel();
        String text = """
                -------Listado de las Vacantes-------\n
                """;
        for (Object temp : objVacanteModel.readAll()) {
            Vacante objVacante = (Vacante) temp;
            text += objVacante.toString() + "\n";
        }
        return text;
    }
    public static void listVacantesByTecno(){
        VacanteModel objVacanteModel = new VacanteModel();
        String tecnologia =JOptionPane.showInputDialog(null, "Ingresa una tecnología de programación.");
        String text = "";
        for (Vacante temp : objVacanteModel.findVacanteByTecno(tecnologia)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay vacantes que requieran esas tecnologías.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Vacantes que requieren esa tecnología----\n"+text);
    }
    public static void listVacantesBytitulo(){
        VacanteModel objVacanteModel = new VacanteModel();
        String titulo =JOptionPane.showInputDialog(null, "Ingresa el título de una vacante.");
        String text = "";
        for (Vacante temp : objVacanteModel.findVacanteBytitulo(titulo)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay vacantes con ese título.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static void updateVacante() {
        VacanteModel objVacanteModel = new VacanteModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la vacante que quiere actualizar.\n" + listVacantesString()));
        Vacante objVacante = objVacanteModel.findById(idToUpdate);
        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "El Id de la vacante no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar título.
                        2. Actualizar descripción.
                        3. Actualizar duración.
                        4. Actualizar tecnologías requeridas
                        5. Actualizar todo.
                        6. Salir.
                        """);
                switch (option) {
                    case "1":
                        String titulo = JOptionPane.showInputDialog(null, "Ingresa el nuevo título de la vacante.");
                        objVacante.setTitulo(titulo);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objVacante);
                        if (confirm == 0) {
                            objVacanteModel.update(objVacante);
                        }
                        break;
                    case "2":
                        String descripcion = JOptionPane.showInputDialog(null, "Ingresa la nueva descripción de la vacante.");
                        objVacante.setDescripcion(descripcion);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objVacante);
                        if (confirm2 == 0) {
                            objVacanteModel.update(objVacante);
                        }
                        break;
                    case "3":
                        String duracion = JOptionPane.showInputDialog(null, "Ingresa la nueva duración de la vacante.");
                        objVacante.setDuracion(duracion);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objVacante);
                        if (confirm3 == 0) {
                            objVacanteModel.update(objVacante);
                        }
                        break;
                    case "4":
                        String tecnologia = JOptionPane.showInputDialog("Ingrese las nuevas tecnologías requeridas de la vacante");
                        objVacante.setTecnologia(tecnologia);
                        int confirm5 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objVacante);
                        if (confirm5 == 0) {
                            objVacanteModel.update(objVacante);
                        }
                        break;
                    case "5":
                        String titulo2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo título de la vacante.");
                        String descripcion2 = JOptionPane.showInputDialog(null, "Ingresa la nueva descripción de la vacante.");
                        String duracion2 = JOptionPane.showInputDialog(null, "Ingresa la nueva duración de la vacante.");
                        String tecnologia2 = JOptionPane.showInputDialog("Ingrese las nuevas tecnologías requeridas de la vacante");
                        objVacante.setTitulo(titulo2);
                        objVacante.setDescripcion(descripcion2);
                        objVacante.setDuracion(duracion2);
                        objVacante.setTecnologia(tecnologia2);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objVacante);
                        if (confirm4 == 0) {
                            objVacanteModel.update(objVacante);
                        }
                        break;
                }
            }while (!option.equals("6"));
        }
    }
    public static void deleteVacante() {
        VacanteModel objVacanteModel = new VacanteModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID la vacante que quiere eliminar.\n" + listVacantesString()));
        Vacante objVacante = objVacanteModel.findById(idToDelete);
        if (objVacante == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra registrado.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar esta Vacante?\n" + objVacante);
            if (confirm == 0) {
                objVacanteModel.delete(objVacante);
            }
        }
    }
}
