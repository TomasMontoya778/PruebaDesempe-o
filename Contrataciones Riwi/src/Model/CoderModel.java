package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Coder;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        try {
            String sql = """
                    INSERT INTO coder (nombre_coder, apellidos, documento, cohorte, cv, clan, tecnologias) VALUES (?, ?, ?, ?, ?, ?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objCoder.getNombre_coder());
            objPrepared.setString(2, objCoder.getApellido());
            objPrepared.setString(3, objCoder.getDocumento());
            objPrepared.setInt(4, objCoder.getCohorte());
            objPrepared.setString(5, objCoder.getCv());
            objPrepared.setString(6, objCoder.getClan());
            objPrepared.setString(7, objCoder.getTecnologias());
            objPrepared.execute();
            ResultSet resultSet = objPrepared.getGeneratedKeys();
            while (resultSet.next()){
                objCoder.setId_coder(resultSet.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "El coder fue agregado correctamente.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objCoder;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM coder;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                Coder objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
                listCoder.add(objCoder);
            }
        }catch (SQLException E){
            System.out.println(E.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }
    public Coder findByCohorte(int cohorte){
        Connection objConnetion = ConfigDB.openConnection();
        Coder objCoder = null;
        try {
            String sql = """
                    SELECT * FROM coder WHERE cohorte = ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, cohorte);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objCoder;
    }
    public Coder findByClan(String clan){
        Connection objConnetion = ConfigDB.openConnection();
        Coder objCoder = null;
        try {
            String sql = """
                    SELECT * FROM coder WHERE clan = ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setString(1, clan);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objCoder;
    }
    public ArrayList<Coder> findByTecno(String tecnologias){
        ArrayList<Coder> listCoder = new ArrayList<>();
        Connection objConnetion = ConfigDB.openConnection();
        Coder objCoder = null;
        try {
            String sql = """
                    SELECT * FROM coder WHERE tecnologias like ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setString(1, "%"+ tecnologias+ "%");
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
                listCoder.add(objCoder);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return listCoder;
    }
    public Coder findByID(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Coder objCoder = null;
        try {
            String sql = """
                    SELECT * FROM coder WHERE id_coder = ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objCoder = new Coder();
                objCoder.setId_coder(resultSet.getInt("id_coder"));
                objCoder.setNombre_coder(resultSet.getString("nombre_coder"));
                objCoder.setApellido(resultSet.getString("apellidos"));
                objCoder.setDocumento(resultSet.getString("documento"));
                objCoder.setCohorte(resultSet.getInt("cohorte"));
                objCoder.setClan(resultSet.getString("clan"));
                objCoder.setCv(resultSet.getString("cv"));
                objCoder.setTecnologias(resultSet.getString("tecnologias"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objCoder;
    }
    @Override
    public boolean update(Object obj) {
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        try {
            String sql = """
                    UPDATE coder SET nombre_coder = ?, apellidos = ?, documento = ?, cohorte = ?, cv = ?, clan = ? WHERE id_coder = ?;
                    """;
            PreparedStatement preparedStatement = objConnection.prepareStatement(sql);
            preparedStatement.setString(1 , objCoder.getNombre_coder());
            preparedStatement.setString(2, objCoder.getApellido());
            preparedStatement.setString(3, objCoder.getDocumento());
            preparedStatement.setInt(4, objCoder.getCohorte());
            preparedStatement.setString(5, objCoder.getCv());
            preparedStatement.setString(6, objCoder.getClan());
            preparedStatement.setInt(7, objCoder.getId_coder());
            int totalAffectedRows = preparedStatement.executeUpdate();
            if (totalAffectedRows > 0){
                flag = false;
                JOptionPane.showMessageDialog(null, "El coder fue actualizado correctamente\n" + objCoder);
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
        Coder objCoder = (Coder) obj;
        try {
            String slqDelete = """
                    DELETE FROM coder WHERE id_coder = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objCoder.getId_coder());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "El Coder fue eliminado correctamente");
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
    public boolean checkDuplicateDNI(String dni){
        boolean flag = true;
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlQuery = "SELECT COUNT(*) FROM coder WHERE documento = ?";
            PreparedStatement preparedStatement = objConnection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                flag = count > 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }
}
