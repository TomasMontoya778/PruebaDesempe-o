import Controller.CoderController;
import Controller.ContratacionCotroller;
import Controller.VacanteController;
import Database.ConfigDB;
import Entity.Coder;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    -------Menú Del Contrataciones Riwi-----
                    1. Menú para Insertar.
                    2. Menú para Listar.
                    3. Menú para Actualizar.
                    4. Menú para Eliminar.
                    5. Salir.
                    """);
            switch (option) {
                case "1":
                    String optionInsert;
                    do {
                        optionInsert = JOptionPane.showInputDialog("""
                                --------Menú Para insertar------
                                1. Insertar Coder.
                                2. Insertar Vacante.
                                3. Insertar Contratación.
                                4. Salir.
                                """);
                        switch (optionInsert) {
                            case "1":
                                CoderController.insertCoder();
                                break;
                            case "2":
                                VacanteController.insertVacante();
                                break;
                            case "3":
                                ContratacionCotroller.insertContratacion();
                                break;
                        }
                    } while (!optionInsert.equals("4"));
                    break;
                case "2":
                    String optionList;
                    do {
                        optionList = JOptionPane.showInputDialog("""
                                --------Menú Para Listar------
                                1. Listar Coder.
                                2. Listar Coders de una cohorte.
                                3. Listar Coders de un clan.
                                4. Listar coders por tecnologías manejadas.
                                5. Listar Vacantes.
                                6. Listar tecnologías de vacantes.
                                7. Listar Vacantes por título
                                8. Salir
                                """);
                        switch (optionList) {
                            case "1":
                                CoderController.listCoder();
                                break;
                            case "2":
                                CoderController.findByCohorte();
                                break;
                            case "3":
                                CoderController.findByClan();
                                break;
                            case "4":
                                CoderController.listByTecno();
                                break;
                            case "5":
                                VacanteController.listVacantes();
                                break;
                            case "6":
                                VacanteController.listVacantesByTecno();
                                break;
                            case "7":
                                VacanteController.listVacantesBytitulo();
                                break;
                        }
                    } while (!optionList.equals("8"));
                    break;
                case "3":
                    String optionUpdate;
                    do {
                        optionUpdate = JOptionPane.showInputDialog(null, """
                                --------Menú Para Actualizar------
                                1. Actualizar Coder.
                                2. Actualizar Vacante.
                                3. Actualizar Contratación.
                                4. Salir.
                                """);
                        switch (optionUpdate) {
                            case "1":
                                CoderController.updateCoder();
                                break;
                            case "2":
                                VacanteController.updateVacante();
                                break;
                            case "3":

                                break;
                        }
                    } while (!optionUpdate.equals("4"));
                    break;
                case "4":
                    String optionDelete;
                    do {
                        optionDelete = JOptionPane.showInputDialog(null, """
                                --------Menú Para Eliminar------
                                1. Eliminar Coder.
                                2. Eliminar Vacante.
                                3. Eliminar Contratación.
                                4. Salir.
                                """);
                        switch (optionDelete){
                            case "1":
                                CoderController.deleteCoder();
                                break;
                            case "2":
                                VacanteController.deleteVacante();
                                break;
                            case "3":

                                break;
                        }
                    }while (!optionDelete.equals("4"));
                    break;
            }

        } while (!option.equals("5"));
    }
}