package com.smurf.pdfdownloaderapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Gamer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String name;
    @NotNull
    public String rank;

    public Gamer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Gamer(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

}
