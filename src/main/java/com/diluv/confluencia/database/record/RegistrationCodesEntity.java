package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registration_codes")
public class RegistrationCodesEntity {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "valid")
    private boolean valid;

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }

    public boolean isValid () {

        return this.valid;
    }

    public void setValid (boolean valid) {

        this.valid = valid;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof RegistrationCodesEntity)) return false;
        RegistrationCodesEntity that = (RegistrationCodesEntity) o;
        return Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getCode());
    }
}
