package Controller;

import Entity.Coder;
import Model.CoderModel;

import javax.swing.*;

public class CoderController {
    public static void insertCoder() {
        CoderModel objCoderModel = new CoderModel();
        String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del Coder.");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese los apellidos del Coder");
        String documento = JOptionPane.showInputDialog("Ingrese el documento de identidad del Coder");
        if (objCoderModel.checkDuplicateDNI(documento)){
            JOptionPane.showMessageDialog(null, "El DNI está duplicado.\nNo se agregó corrextamente.");
        }else{
            int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cohorte del Coder"));
            if (cohorte != 1 && cohorte != 2) {
                JOptionPane.showMessageDialog(null, "En riwi hasta el momento tenemos dos cohortes, Ingrese una cohorte válida");
            } else {
                String cv = JOptionPane.showInputDialog(null, "Ingrese el cv del Coder.");
                String clan = JOptionPane.showInputDialog(null, "Ingrese el clan del Coder.");
                String tecnologias = JOptionPane.showInputDialog(null, "Ingrese las tecnologías que maneja el Coder.");
                Coder objCoder = new Coder();
                objCoder.setNombre_coder(name);
                objCoder.setApellido(apellido);
                objCoder.setDocumento(documento);
                objCoder.setCohorte(cohorte);
                objCoder.setCv(cv);
                objCoder.setClan(clan);
                objCoder.setTecnologias(tecnologias);
                objCoder = (Coder) objCoderModel.insert(objCoder);
                JOptionPane.showMessageDialog(null, objCoder.toString());
            }
        }
    }

    public static void listCoder() {
        CoderModel objCoderModel = new CoderModel();
        String text = """
                -------Listado de los coders-------\n
                """;
        for (Object temp : objCoderModel.readAll()) {
            Coder objCoder = (Coder) temp;
            text += objCoder.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static String listCoderString() {
        CoderModel objCoderModel = new CoderModel();
        String text = """
                -------Listado de los coders-------\n
                """;
        for (Object temp : objCoderModel.readAll()) {
            Coder objCoder = (Coder) temp;
            text += objCoder.toString() + "\n";
        }
        return text;
    }

    public static void findByCohorte() {
        CoderModel objCoderModel = new CoderModel();
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cohorte de los coders que quiere buscar."));
        Coder objCoder = objCoderModel.findByCohorte(cohorte);
        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "No hay coders registrados en esa cohorte");
        } else {
            JOptionPane.showMessageDialog(null, objCoder.toString());
        }
    }

    public static void listByTecno(){
        CoderModel objCoderModel = new CoderModel();
        String tecnologia =JOptionPane.showInputDialog(null, "Ingresa una tecnología de programación,");
        String text = "";
        for (Coder temp : objCoderModel.findByTecno(tecnologia)){
            if (temp == null) {
                JOptionPane.showMessageDialog(null, "No hay coders que manejen esas tecnologías.");
            }else {
                text += temp.toString();
            }
        }
        JOptionPane.showMessageDialog(null, "----Coders que manejan esa tecnología----\n"+text);
    }

    public static void findByClan() {
        CoderModel objCoderModel = new CoderModel();
        String clan = JOptionPane.showInputDialog(null, "Ingrese El clan de los coders que quiere buscar.");
        Coder objCoder = objCoderModel.findByClan(clan);
        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "No hay coders registrados en ese clan");
        } else {
            JOptionPane.showMessageDialog(null, objCoder.toString());
        }
    }

    public static void updateCoder() {
        CoderModel objCoderModel = new CoderModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el Id del coder que quiere actualizar.\n" + listCoderString()));
        Coder objCoder = objCoderModel.findByID(idToUpdate);
        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra registrado.");
        } else {
            String option;
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Actualizar Nombre.
                        2. Actualizar Apellidos.
                        3. Actualizar Documento.
                        4. Actualizar Cohorte.
                        5. Actualizar CV.
                        6. Actualizar todo.
                        7. Salir.
                        """);
                switch (option) {
                    case "1":
                        String name = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del coder.");
                        objCoder.setNombre_coder(name);
                        int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este coder?\n" + objCoder);
                        if (confirm == 0) {
                            objCoderModel.update(objCoder);
                        }
                        break;
                    case "2":
                        String lastname = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del coder.");
                        objCoder.setApellido(lastname);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este coder?\n" + objCoder);
                        if (confirm2 == 0) {
                            objCoderModel.update(objCoder);
                        }
                        break;
                    case "3":
                        String documento = JOptionPane.showInputDialog(null, "Ingresa el nuevo documento del coder.");
                        objCoder.setDocumento(documento);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este coder?\n" + objCoder);
                        if (confirm3 == 0) {
                            objCoderModel.update(objCoder);
                        }
                        break;
                    case "4":
                        int cohorte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la nueva cohorte del coder."));
                        if (cohorte != 1 && cohorte != 2) {
                            JOptionPane.showMessageDialog(null, "En riwi hasta el momento tenemos dos cohortes, Ingrese una cohorte válida");
                        } else {
                            objCoder.setCohorte(cohorte);
                            int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este coder?\n" + objCoder);
                            if (confirm4 == 0) {
                                objCoderModel.update(objCoder);
                            }
                        }
                        break;
                    case "5":
                        String cvNew = JOptionPane.showInputDialog(null, "Ingresa el nuevo cv del coder.");
                        objCoder.setCv(cvNew);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este coder?\n" + objCoder);
                        if (confirm4 == 0) {
                            objCoderModel.update(objCoder);
                        }
                        break;
                    case "6":
                        String name2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo nombre del coder.");
                        String lastname2 = JOptionPane.showInputDialog(null, "Ingresa los nuevos apellidos del coder.");
                        String documento2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo documento del coder.");
                        int newCohorte = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la nueva cohorte del coder."));
                        if (newCohorte != 1 && newCohorte != 2) {
                            JOptionPane.showMessageDialog(null, "En riwi hasta el momento tenemos dos cohortes, Ingrese una cohorte válida");
                        } else {
                            String cvNew2 = JOptionPane.showInputDialog(null, "Ingresa el nuevo cv del coder.");
                            objCoder.setNombre_coder(name2);
                            objCoder.setApellido(lastname2);
                            objCoder.setDocumento(documento2);
                            objCoder.setCohorte(newCohorte);
                            objCoder.setCv(cvNew2);
                            int confirm5 = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres actualizar este paciente?\n" + objCoder);
                            if (confirm5 == 0) {
                                objCoderModel.update(objCoder);
                            }
                        }
                        break;
                }
            } while (!option.equals("7"));
        }
    }

    public static void deleteCoder() {
        CoderModel objCoderModel = new CoderModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del coder que quiere eliminar.\n" + listCoderString()));
        Coder objCoder = objCoderModel.findByID(idToDelete);
        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "El ID no se encuentra registrado.");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Estás seguro que quieres eliminar este Coder?\n" + objCoder);
            if (confirm == 0) {
                objCoderModel.delete(objCoder);
            }
        }
    }
}
