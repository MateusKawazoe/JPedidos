package com.github.jpedidos.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Api {
	public Connection con = null;
    
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); // Seleciona o driver para acesso ao banco de dados.
            con = DriverManager.getConnection("jdbc:mysql://localhost/redes","root",""); // Carrega o driver MYSQL para conexão com o banco de dados.
        }catch(ClassNotFoundException ex){
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
        }catch(SQLException e){
            System.out.print(e);
            throw new RuntimeException(e);
        }
    }
}
