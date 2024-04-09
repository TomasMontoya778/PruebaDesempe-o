package Controller;

import Entity.Empresa;
import Model.EmpresaModel;

import javax.swing.*;

public class EmpresaController {
    public static void listEmpresa() {
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String text = """
                -------Listado de las Empresas-------\n
                """;
        for (Object temp : objEmpresaModel.readAll()) {
            Empresa objEmpresa = (Empresa) temp;
            text += objEmpresa.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listeEmpresaString() {
        EmpresaModel objEmpresaModel = new EmpresaModel();
        String text = """
                -------Listado de las Empresas-------\n
                """;
        for (Object temp : objEmpresaModel.readAll()) {
            Empresa objEmpresa = (Empresa) temp;
            text += objEmpresa.toString() + "\n";
        }
        return text;
    }
}
