package model;
import com.mysql.jdbc.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kotlin.jvm.internal.MagicApiIntrinsics;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class MySQL_Query implements SQL_Query {
    public ObservableList<Receta> getTblRecetas() {
        return tblRecetas;
    }
    //configurando los parametros de la conexion
    private ObservableList<Receta> tblRecetas;
    public List<Receta> getAllRecetas() {
        return allRecetas;
    }
    private List<Receta> allRecetas = new ArrayList<>();
    private List<Receta> auxRecetas = new ArrayList<>();
    private String url_connection = "jdbc:mysql://185.61.126.62:3306/POO_FINAL";
    private String username = "root";
    private String password = "7g9!L4].h*0Y";
    //Creando el objeto conexion, para la DB
    private Connection connection = null;
    public int connect (){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url_connection, username, password);
            System.out.println("Conexion exitosa!");
            return 1;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return 0;
        }
    }
    public int insert(Receta receta) throws Insert_Exception {
        try{
            PreparedStatement ps = connection.prepareStatement("INSERT INTO recetas (nombre, ingredientes, etiquetas, receta) VALUES (?, ?, ?, ?);");
            ps.setString(1, receta.getNombre());
            ps.setString(2, receta.getIngredientes());
            ps.setString(3, receta.getEtiquetas());
            ps.setString(4, receta.getReceta());
            ps.executeUpdate();
            return 1;

        } catch (SQLException e) {
            throw new Insert_Exception(e);

        }
    }
    public int selectAll(){
        try{
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM recetas;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                allRecetas.add(new Receta(rs.getInt("id"), rs.getString("nombre"), rs.getString("ingredientes"), rs.getString("etiquetas"), rs.getString("receta") ));
            }
            return 1;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int select(String busqueda){
        try {
            PreparedStatement ps;

            ResultSet rs;

            String[] keywords = split(busqueda);

            StringBuilder query = new StringBuilder("SELECT * FROM recetas WHERE ingredientes LIKE ? ");

            ps = connection.prepareStatement(queryString(query, keywords));

            for (int i = 0; i <= keywords.length-1; i++) {
                ps.setString(i+1, "%" + keywords[i] + "%");
            }

                rs = ps.executeQuery();
                while (rs.next()) {
                    //tblRecetas
                    auxRecetas.add(new Receta(rs.getInt("id"), rs.getString("nombre").replace(" ", "\n"), rs.getString("ingredientes").replace(" ", "\n"), rs.getString("etiquetas").replace(" ", "\n"), rs.getString("receta")));
              }
                //mejora del algoritmo de busqueda
            //aun se puede mejorar más con un doble filtro
             tblRecetas = FXCollections.observableArrayList(sortedRecetasByIngredients(auxRecetas));
            return 1;

        } catch (SQLException e) {
            System.out.println("Error : "+e.getMessage());
            return 0;
        }
    }
    public int delete(Receta receta) throws Delete_Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM recetas WHERE id = ?;");
            ps.setInt(1, receta.getId());
            ps.executeUpdate();
            return 1;
        }catch (SQLException e) {
            throw new Delete_Exception(e);
        }
    }
    public int update (Receta receta) throws Update_Exception {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE recetas SET nombre =?, ingredientes =?, etiquetas =?, receta =? WHERE id =?;");
            ps.setString(1, receta.getNombre());
            ps.setString(2, receta.getIngredientes());
            ps.setString(3, receta.getEtiquetas());
            ps.setString(4, receta.getReceta());
            ps.setInt(5, receta.getId());
            ps.executeUpdate();
            return 1;
        }catch (SQLException e) {
            throw new Update_Exception(e);
        }
    }
    public String [] split(String busqueda) {
        return busqueda.split(" ");
    }
    public String queryString (StringBuilder queryString, String [] keywords){
        for (int i = 0; i < keywords.length-1; i++) {
            queryString.append(" OR ").append(" ? ");
        }
        return queryString.toString();
    }
    public List<Receta> sortedRecetasByIngredients(List<Receta> auxRecetas){
        Comparator<Receta> compByLength = (aName, bName) -> aName.getIngredientes().length() - bName.getIngredientes().length();
        return auxRecetas.stream().sorted(compByLength).toList();
    }
}


