package com.github.jpedidos.auth;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

  public String encrypt(String password) {
    MessageDigest md = null;
    BigInteger hash = null;

    try {
      md = MessageDigest.getInstance("MD5");
      hash = new BigInteger(1, md.digest(password.getBytes()));
      return hash.toString(16);
    } catch (Exception e) {
      System.out.println(e);
      return "" + hash;
    }
  }
}
