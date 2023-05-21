package com.mycompany.oracleexample;

import java.sql.*;

public class OracleExample {
    
    static String username = "usuario_de_Oracle";
    static String password = "password_de_Oracle";
    static String url = "jdbc:oracle:thin:@//localhost:1521/nombre_de_servicio_Oracle";
    
    public static void main(String[] args) {
        
        Connection con = null;
        
        try {
            // Register JDBC library
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Connect to database
            con = DriverManager.getConnection(url, username, password);
            
            // SELECT
            select(con);
            
            // Insertar una nueva fila
            insert(con);
            
            // SELECT
            select(con);
            
            // Actualizar una fila existente
            update(con);
            
            // SELECT
            select(con);
            
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        
    }
    
    private static void select(Connection con) throws SQLException{
        String selectQuery = "SELECT * FROM tabla";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);
            
            // Iterar sobre los resultados de la consulta SELECT
            while (rs.next()) {
                System.out.println(rs.getString("nombre_columna"));
            }
    }
    
    private static void insert(Connection con) throws SQLException{
        String insertQuery = "INSERT INTO tabla (columna1, columna2) VALUES (?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setString(1, "valor1");
            pstmt.setString(2, "valor2");
            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " fila(s) insertada(s)");
    }
    
    private static void update(Connection con) throws SQLException{
        String updateQuery = "UPDATE tabla SET columna1 = ? WHERE columna2 = ?";
        PreparedStatement pstmt2 = con.prepareStatement(updateQuery);
        pstmt2.setString(1, "nuevo_valor");
        pstmt2.setString(2, "valor_existente");
        int rowsUpdated = pstmt2.executeUpdate();
        System.out.println(rowsUpdated + " fila(s) actualizada(s)");
    }
}

