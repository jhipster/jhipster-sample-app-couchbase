package io.github.jhipster.sample.domain;

import static io.github.jhipster.sample.config.Constants.ID_DELIMITER;
import static io.github.jhipster.sample.domain.Authority.TYPE_NAME;
import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.repository.Collection;

/**
 * A Authority.
 */
@Document
@TypeAlias(TYPE_NAME)
@Collection(TYPE_NAME)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TYPE_NAME = "authority";

    @NotNull
    @Size(max = 50)
    @Id
    @GeneratedValue(strategy = UNIQUE, delimiter = ID_DELIMITER)
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getName() {
        return this.name;
    }

    public Authority name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return getName() != null && getName().equals(((Authority) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
            "name=" + getName() +
            "}";
    }
}
