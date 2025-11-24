package com.example.tp3partie2;

import java.text.DecimalFormat;

public class Evenement
{
    private String titre;
    private String description;
    private String date;
    private String equipe;
    private String type;

    private double coordoneeX;
    private double coordoneeY;

    public String getFormatedCoordinates()
    {
        DecimalFormat format = new DecimalFormat("#.0000");
        return "x :" + format.format(coordoneeX) + "\ny : " + format.format(coordoneeY);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public double getCoordoneeX() {
        return coordoneeX;
    }

    public void setCoordoneeX(double coordoneeX) {
        this.coordoneeX = coordoneeX;
    }

    public double getCoordoneeY() {
        return coordoneeY;
    }

    public void setCoordoneeY(double coordoneeY) {
        this.coordoneeY = coordoneeY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
