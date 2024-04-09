package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Contratacion;

import javax.swing.*;
import java.sql.*;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        try {
            String sqlInsert = "INSERT INTO contratacion (fecha_aplicacion, estado, salario, id_vacanteFK, id_coderFK) " +
                    "SELECT ?, ?, ? coder.id_coder " +
                    "FROM coder " +
                    "WHERE coder.id_coder = ? " +
                    "AND EXISTS ( " +
                    "    SELECT 1 " +
                    "    FROM vacante " +
                    "    WHERE vacante.id_vacante = ? " +
                    "    AND vacante.tecnologia = coder.tecnologias " +
                    ")";
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setTimestamp(1, objContratacion.getFecha_aplicacion());
            prepared.setString(2, objContratacion.getEstado());
            prepared.setDouble(3, objContratacion.getSalario());
            prepared.setInt(4, objContratacion.getId_vacanteFK());
            prepared.setInt(5, objContratacion.getId_coderFK());

            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objContratacion.setId_contratacion(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "La vacante fue agregada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Algo salió mal");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objContratacion;
    }

    @Override
    public List<Object> readAll() {
        return null;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
