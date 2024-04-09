package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Coder;
import Entity.Contratacion;
import Entity.Empresa;
import Entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContratacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        try {
            String sqlInsert = "INSERT INTO contratacion (fecha_aplicacion, estado, salario, id_coderFK, id_vacanteFK) VALUES (?, ?, ?, ? ,?)";
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setTimestamp(1, objContratacion.getFecha_aplicacion());
            prepared.setString(2, objContratacion.getEstado());
            prepared.setDouble(3, objContratacion.getSalario());
            prepared.setInt(4, objContratacion.getId_coderFK().getId_coder());
            prepared.setInt(5, objContratacion.getId_vacanteFK().getId_vacante());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objContratacion.setId_contratacion(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "La Contratación fue agregada correctamente.");
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
        List<Object> listContratacion = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT contratacion.id_contratacion, contratacion.fecha_aplicacion, contratacion.estado, contratacion.salario, vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, coder.id_coder, coder.nombre_coder, coder.apellidos, coder.documento, coder.cohorte, coder.cv, coder.clan, coder.tecnologias FROM contratacion" +
                    " INNER JOIN coder ON contratacion.id_coderFK = coder.id_coder" +
                    " INNER JOIN vacante ON contratacion.id_vacanteFK = vacante.id_vacante";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Contratacion objContratacion = new Contratacion();
                objContratacion.setId_contratacion(resultSet.getInt("id_contratacion"));
                objContratacion.setFecha_aplicacion(resultSet.getTimestamp("fecha_aplicacion"));
                objContratacion.setEstado(resultSet.getString("estado"));
                objContratacion.setSalario(resultSet.getDouble("salario"));
                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Coder objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
                objContratacion.setId_vacanteFK(objVacante);
                objContratacion.setId_coderFK(objCoder);
                listContratacion.add(objContratacion);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
       return listContratacion;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        try {
            String sql = """
                    UPDATE contratacion SET fecha_aplicacion = ?, estado = ?, salario = ? WHERE id_contratacion = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setTimestamp(1 , objContratacion.getFecha_aplicacion());
            preparedStatement.setString(2, objContratacion.getEstado());
            preparedStatement.setDouble(3, objContratacion.getSalario());
            preparedStatement.setString(4, objContratacion.getEstado());
            preparedStatement.setInt(5, objContratacion.getId_contratacion());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "La contratación fue actualizada correctamente\n" + objContratacion);
            }else {
                JOptionPane.showMessageDialog(null, "Algo ha salido mal...");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }
    public Contratacion findById(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Contratacion objContratacion = null;
        try {
            String sql = "SELECT contratacion.id_contratacion, contratacion.fecha_aplicacion, contratacion.estado, contratacion.salario, vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, coder.id_coder, coder.nombre_coder, coder.apellidos, coder.documento, coder.cohorte, coder.cv, coder.clan, coder.tecnologias FROM contratacion" +
                    " INNER JOIN coder ON contratacion.id_coderFK = coder.id_coder" +
                    " INNER JOIN vacante ON contratacion.id_vacanteFK = vacante.id_vacante" +
                    " WHERE id_contratacion = ?";
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objContratacion = new Contratacion();
                objContratacion.setId_contratacion(resultSet.getInt("id_contratacion"));
                objContratacion.setFecha_aplicacion(resultSet.getTimestamp("fecha_aplicacion"));
                objContratacion.setEstado(resultSet.getString("estado"));
                objContratacion.setSalario(resultSet.getDouble("salario"));
                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Coder objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
                objContratacion.setId_vacanteFK(objVacante);
                objContratacion.setId_coderFK(objCoder);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objContratacion;
    }
    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Contratacion objContratacion = (Contratacion) obj;
        try {
            String slqDelete = """
                    DELETE FROM contratacion WHERE id_contratacion = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objContratacion.getId_contratacion());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "La contratación fue eliminada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Algo ha salido mal...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }
}
