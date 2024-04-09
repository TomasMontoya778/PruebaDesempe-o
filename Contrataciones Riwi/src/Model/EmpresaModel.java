package Model;

import Database.ConfigDB;
import Entity.Empresa;
import Entity.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaModel {
    public List<Object> readAll() {
        List<Object> listEmpresas = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sql = """
                    SELECT * FROM empresa;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sql);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
                listEmpresas.add(objEmpresa);
            }
        }catch (SQLException E){
            System.out.println(E.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return listEmpresas;
    }
    public Empresa findByID(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Empresa objEmpresa = null;
        try {
            String sql = """
                    SELECT * FROM empresa WHERE id_empresa = ?;
                    """;
            PreparedStatement prepared = objConnetion.prepareStatement(sql);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objEmpresa = new Empresa();
                objEmpresa.setId_empresa(resultSet.getInt("id_empresa"));
                objEmpresa.setNombre_empresa(resultSet.getString("nombre_empresa"));
                objEmpresa.setSector(resultSet.getString("sector"));
                objEmpresa.setUbicacion(resultSet.getString("ubicacion"));
                objEmpresa.setContacto(resultSet.getString("contacto"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            ConfigDB.closeConnection();
        }
        return objEmpresa;
    }
}
