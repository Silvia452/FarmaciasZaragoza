package com.example.prueba_programacion_android_23;

public class Farmacia {

    private long id;
    private String nombre;
    private String telefono;
    private double latitud;
    private double longitud;
 // inicializamos los contructores y los getters y los setters
    public Farmacia(long id, String nombre, String telefono, double latitud, double longitud) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Farmacia() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
