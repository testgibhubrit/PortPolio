package com.poc.portfolio.enums;

public enum Titles {
  SOFTWARE_DEVELOPMENT_ENGINEER("Software Development Engineer"), DANCER("Dancer"), PUBLIC_SPEAKER("Public speaker");

  private final String value;

  Titles(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
