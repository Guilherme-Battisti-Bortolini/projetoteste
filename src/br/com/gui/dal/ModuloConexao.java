package br.com.gui.dal;

import java.sql.*;
 
public class ModuloConexao {
    //método responsavel por estabalecer a conexão com o banco

    public static Connection conector() {
        java.sql.Connection conexao = null;
//a linha abaixo chama o driver
        String driver = "com.mysql.jdbc.Driver";
//armazenando informações refrente ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
//estabelesendo a conexão com o banco
try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            // a linha abaixo serve de apoio para esclarecer o erro
           // System.out.println(e);
            return null;
            
        }
 




    }

}
