package com.diluv.confluencia.database.record;

import javax.persistence.Basic;
import javax.persistence.Column;import javax.persistence.Entity;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "roles")
public class RolesEntity {
    private long id;
    private String name;

    @Id
    @Column(name = "id")
    public long getId () {

        return this.id;
    }

    public void setId (long id) {

        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }
}
