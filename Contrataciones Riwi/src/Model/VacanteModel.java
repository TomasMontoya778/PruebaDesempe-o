package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.*;
import Entity.Vacante;
import Entity.Vacante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacanteModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        try {
            String sqlInsert = """
                    INSERT INTO vacante (titulo, descripcion, duracion, estado, tecnologia, id_empresaFK) VALUES (?, ?, ?, ?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objVacante.getTitulo());
            prepared.setString(2, objVacante.getDescripcion());
            prepared.setString(3, objVacante.getDuracion());
            prepared.setString(4, objVacante.getEstado());
            prepared.setString(5, objVacante.getTecnologia());
            prepared.setInt(6, objVacante.getId_empresaFK().getId_empresa());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objVacante.setId_vacante(resultSet.getInt(1));
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
        return objVacante;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listVacante = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, empresa.id_empresa, empresa.nombre_empresa, empresa.sector, empresa.ubicacion, empresa.contacto FROM vacante" +
                    " INNER JOIN empresa ON vacante.id_empresaFK = empresa.id_empresa";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
                objVacante.setId_empresaFK(objEmpresa);
                listVacante.add(objVacante);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }
    public ArrayList<Vacante> findVacanteByTecno(String tecnologias){
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnetion = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, empresa.id_empresa, empresa.nombre_empresa, empresa.sector, empresa.ubicacion, empresa.contacto FROM vacante" +
                    " INNER JOIN empresa ON vacante.id_empresaFK = empresa.id_empresa" +
                    " WHERE tecnologia LIKE ?";
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setString(1, "%"+ tecnologias+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
                objVacante.setId_empresaFK(objEmpresa);
                listVacante.add(objVacante);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }
    public ArrayList<Vacante> findVacanteBytitulo(String titulo){
        ArrayList<Vacante> listVacante = new ArrayList<>();
        Connection objConnetion = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, empresa.id_empresa, empresa.nombre_empresa, empresa.sector, empresa.ubicacion, empresa.contacto FROM vacante" +
                    " INNER JOIN empresa ON vacante.id_empresaFK = empresa.id_empresa" +
                    " WHERE titulo LIKE ?";
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setString(1, "%"+ titulo+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
                objVacante.setId_empresaFK(objEmpresa);
                listVacante.add(objVacante);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listVacante;
    }
    public Vacante findById(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Vacante objVacante = null;
        try {
            String sql = "SELECT vacante.id_vacante, vacante.titulo, vacante.descripcion, vacante.duracion, vacante.estado, vacante.tecnologia, empresa.id_empresa, empresa.nombre_empresa, empresa.sector, empresa.ubicacion, empresa.contacto FROM vacante" +
                    " INNER JOIN empresa ON vacante.id_empresaFK = empresa.id_empresa" +
                    " WHERE id_vacante = ?";
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objVacante = new Vacante();
                objVacante.setId_vacante(resultSet.getInt("id_vacante"));
                objVacante.setTitulo(resultSet.getString("titulo"));
                objVacante.setDescripcion(resultSet.getString("descripcion"));
                objVacante.setDuracion(resultSet.getString("duracion"));
                objVacante.setEstado(resultSet.getString("estado"));
                objVacante.setTecnologia(resultSet.getString("tecnologia"));
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
                objVacante.setId_empresaFK(objEmpresa);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objVacante;
    }
    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        try {
            String sql = """
                    UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ? WHERE id_vacante = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objVacante.getTitulo());
            preparedStatement.setString(2, objVacante.getDescripcion());
            preparedStatement.setString(3, objVacante.getDuracion());
            preparedStatement.setString(4, objVacante.getEstado());
            preparedStatement.setInt(5, objVacante.getId_vacante());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "La vacante fue actualizada correctamente\n" + objVacante);
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
    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Vacante objVacante = (Vacante) obj;
        try {
            String slqDelete = """
                    DELETE FROM vacante WHERE id_vacante = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objVacante.getId_vacante());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "La Vacante fue eliminada correctamente");
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
