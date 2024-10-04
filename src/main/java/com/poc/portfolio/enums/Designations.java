package com.poc.portfolio.enums;

public enum Designations {
  SDE_Intern("SDE-Intern");

  private final String value;

  Designations(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
