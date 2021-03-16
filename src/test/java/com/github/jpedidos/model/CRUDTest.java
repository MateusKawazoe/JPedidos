package com.github.jpedidos.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CRUDTest {
    @Test
    public void inserir() {
        CRUD crud = new CRUD();

        String result = crud.inserirModificarDeletar("INSERT INTO login(login_username, login_password, login_token)"
                                    + " values('Mateuskwz', 'c8751b29c9bd8ef0b7fe9be5def40a39', '2d3855ed596b1a553922e4e5a14abda0')");
        
        assertEquals("false", result);
        result = crud.inserirModificarDeletar("UPDATE login SET login_username = 'Lux' WHERE login_username = 'Mateuskwz'");
        assertEquals("false", result);
        result = crud.buscar("SELECT login_username FROM login WHERE login_username = 'Lux'");
        assertEquals("Lux", result);
        result = crud.inserirModificarDeletar("DELETE FROM login WHERE login_username = 'Lux'");
        assertEquals("false", result);
    }
}
