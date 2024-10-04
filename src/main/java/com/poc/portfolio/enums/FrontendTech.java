package com.poc.portfolio.enums;

public enum FrontendTech {
  HTML("HTML"), CSS("CSS"), REACT("ReactJS"), JS("JavaScript");

  private final String value;

  FrontendTech(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
