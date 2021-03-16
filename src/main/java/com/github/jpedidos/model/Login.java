package com.github.jpedidos.model;

import com.github.jpedidos.service.Api;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private Api api = new Api();
    private PreparedStatement st;
    private ResultSet rs;
    private String query;

    public String entrar(String usuario,String senha) {
        
        return "";
    }
}
