package com.ogmatech.pradeep.model;

import javax.persistence.*;

@Entity
@Table(name = "dbtwo_note")
public class DbtwoNote {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "note")
    private String note;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
