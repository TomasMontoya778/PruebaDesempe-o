package Controller;

import Entity.Coder;
import Entity.Contratacion;
import Entity.Empresa;
import Entity.Vacante;
import Model.CoderModel;
import Model.ContratacionModel;
import Model.EmpresaModel;
import Model.VacanteModel;

import javax.swing.*;

public class ContratacionCotroller {
    public static void insertContratacion(){
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        CoderModel objCoderModel = new CoderModel();
        String listCoder = CoderController.listCoderString();
        String listVacante = VacanteController.listVacantesStringNEW();
        try {
            int tecnoCoderToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del coder que necesita para la contratación.\n"+listCoder));
            int tecnoVacanteToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la vacante que necesita para la contratación.\n"+listVacante));
            Coder objCoder = objCoderModel.findByID(tecnoCoderToAdd);
            Vacante objVacante = objVacanteModel.findById(tecnoVacanteToAdd);
            if (objVacante == null && objCoder == null) {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún ID de Vacantes y Coders.");
            }else if(objVacante == null){
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún ID de Vacantes.");
            }else if (objCoder == null){
                JOptionPane.showMessageDialog(null, "No se pudo encontrar ningún ID de Coders.");
            }else {
                Contratacion objContratacion = new Contratacion();
                double salario = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el salario del Coder. (Ejm: 3.000000)"));
                String inactivo = "INACTIVO";
                objContratacion.setFecha_aplicacion(new java.sql.Timestamp(System.currentTimeMillis()));
                objContratacion.setEstado(objVacante.getEstado());
                objContratacion.setSalario(salario);
                objContratacion.setId_vacanteFK(objVacante);
                objContratacion.setId_coderFK(objCoder);
                objVacante.setEstado(inactivo);
                if (objCoder.getTecnologias().contains(objVacante.getTecnologia())){
                    objVacanteModel.update(objVacante);
                    objContratacion = (Contratacion) objContratacionModel.insert(objContratacion);
                    JOptionPane.showMessageDialog(null, objContratacion.toString());
                }else{
                    JOptionPane.showMessageDialog(null, "El coder no cumple con las tecnologías requeridas para la vacante.");
                }
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Ingrese un ID válido"+ e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void listContratacion() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String text = """
                -------Listado de las Contrataciones-------\n
                """;
        for (Object temp : objContratacionModel.readAll()) {
            Contratacion objContratacion = (Contratacion) temp;
            text += objContratacion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }
    public static String listContratacionString() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        String text = """
                -------Listado de las Contrataciones-------\n
                """;
        for (Object temp : objContratacionModel.readAll()) {
            Contratacion objContratacion = (Contratacion) temp;
            text += objContratacion.toString() + "\n";
        }
        return text;
    }
    public static void updateContratacion() {
        ContratacionModel objContratacionModel = new ContratacionModel();
        VacanteModel objVacanteModel = new VacanteModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id de la contratación que quiere actualizar.\n" + listContratacionString()));
        Contratacion objContratacion = objContratacionModel.findById(idToUpdate);
        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "El Id de la contratación no se encuentra.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Estado.
                        2. Actualizar salario.
                        3. Actualizar todo.
                        4. Salir.
                        """);
                switch (option) {
                    case "1":
                        String estado = JOptionPane.showInputDialog(null, "Ingresa el nuevo estado de la contratación. (Activo o Inactivo)");
                        objContratacion.setEstado(estado);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta contratación?\n" + objContratacion);
                        if (confirm == 0) {
                            Vacante objVacante = new Vacante();
                            objVacante.setEstado(estado);
                            objContratacionModel.update(objVacante);
                            objContratacionModel.update(objContratacion);
                        }
                        break;
                    case "2":
                        double salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el nuevo salario de la contratación. (3.000000)"));
                        objContratacion.setSalario(salario);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objContratacion);
                        if (confirm2 == 0) {
                            objContratacionModel.update(objContratacion);
                        }
                        break;
                    case "3":
                        String estado2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo estado de la contratación. (Activo o Inactivo)");
                        objContratacion.setEstado(estado2);
                        double salario2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa el nuevo salario de la contratación. (3.000000)"));
                        objContratacion.setSalario(salario2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar esta vacante?\n" + objContratacion);
                        if (confirm3 == 0) {
                            Vacante objVacante = new Vacante();
                            objVacante.setEstado(estado2);
                            objContratacionModel.update(objContratacion);
                            objVacanteModel.update(objVacante);
                        }
                        break;
                }
            }while (!option.equals("4"));
        }
    }
    public static void deleteContratacion() {
        ContratacionModel contratacionModel = new ContratacionModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la contratación que quiere eliminar.\n" + listContratacionString()));
        Contratacion objContratacion = contratacionModel.findById(idToDelete);
        if (objContratacion == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra registrado.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar esta Vacante?\n" + objContratacion);
            if (confirm == 0) {
                contratacionModel.delete(objContratacion);
            }
        }
    }
}
