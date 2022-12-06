package model;

import java.util.Objects;

public class Receta {

    private int id;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    private  String nombre;
    private  String ingredientes;

    public String getNombre() {
        return nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public String getReceta() {
        return receta;
    }
    private  String etiquetas;
    private String receta;


    public Receta(int id, String nombre, String ingredientes, String etiquetas, String receta) {
        this.id = id;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.etiquetas = etiquetas;
        this.receta = receta;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", etiquetas='" + etiquetas + '\'' +
                ", receta='" + receta + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }





}
