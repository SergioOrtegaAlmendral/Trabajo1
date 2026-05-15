// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
   private static final String URI = "jdbc:mysql://localhost:3306/ejemplo?characterEncoding=UTF-8";
   private static final String USER = "root";
   private static final String PASS = "root";
   private static BDConexion instancia = null;
   private Connection conexion;

   private BDConexion() {
      try {
         this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejemplo?characterEncoding=UTF-8", "root", "root");
      } catch (SQLException sqle) {
         sqle.printStackTrace();
      }

   }

   public static BDConexion conectar() {
      if (instancia == null) {
         instancia = new BDConexion();
      }

      return instancia;
   }

   public Connection bd() {
      return this.conexion;
   }
}