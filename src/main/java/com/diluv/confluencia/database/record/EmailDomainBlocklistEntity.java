package com.diluv.confluencia.database.record;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_domain_blocklist")
public class EmailDomainBlocklistEntity {

    @Id
    @Column(name = "domain")
    private String domain;

    public String getDomain () {

        return this.domain;
    }

    public void setDomain (String domain) {

        this.domain = domain;
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (!(o instanceof EmailDomainBlocklistEntity)) return false;
        EmailDomainBlocklistEntity that = (EmailDomainBlocklistEntity) o;
        return Objects.equals(getDomain(), that.getDomain());
    }

    @Override
    public int hashCode () {

        return Objects.hash(getDomain());
    }
}
