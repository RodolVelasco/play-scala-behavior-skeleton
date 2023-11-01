package com.applaudo.services.models;

public enum FileType {
  AlgunArchivo,
  AlgunOtroArchivo,
  AlgunOtroArchivoDiferente;

  public String value() { return name(); }
  public static FileType fromValue(String v) { return valueOf(v); }
}
