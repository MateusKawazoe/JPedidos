package com.github.jpedidos.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class MD5Test {
  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void encryptTest() {
    MD5 md = new MD5();
    assertEquals("c8751b29c9bd8ef0b7fe9be5def40a39", md.encrypt("Mateus123"));
  }

  @Test
  public void invalidEncryptTest() {
    MD5 md = new MD5();
    md.encrypt(null);
    exception.expect(NullPointerException.class);
  }
}
