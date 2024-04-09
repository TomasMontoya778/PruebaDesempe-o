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
        String listVacante = VacanteController.listVacantesString();
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
    public static String listVacantesString() {
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
}
