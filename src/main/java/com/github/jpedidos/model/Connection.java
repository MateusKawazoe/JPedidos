package com.github.jpedidos.model;

import com.github.jpedidos.service.Api;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connection {

  private ResultSet rs = null;
  private PreparedStatement st = null;
  private Api api = null;

  public void close() {
    try {
      this.api.con.close();
      this.st.close();
      this.rs.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void setApi(Api api) {
    this.api = api;
  }

  public void setRs(ResultSet rs) {
    this.rs = rs;
  }

  public void setSt(PreparedStatement st) {
    this.st = st;
  }

  public ResultSet getRs() {
    return rs;
  }

  public PreparedStatement getSt() {
    return st;
  }
}
