package com.github.jpedidos.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MD5Test {
    @Test
    public void encryptTest() {
        MD5 md = new MD5();
        assertEquals("c8751b29c9bd8ef0b7fe9be5def40a39", md.encrypt("Mateus123"));
    }
}
