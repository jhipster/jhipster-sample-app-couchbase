package io.github.jhipster.sample.domain;

import static io.github.jhipster.sample.domain.Authority.TYPE_NAME;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.repository.Collection;

/**
 * An authority (a security role) used by Spring Security.
 */
@Document
@TypeAlias(TYPE_NAME)
@Collection(TYPE_NAME)
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String TYPE_NAME = "authority";

    @NotNull
    @Size(max = 50)
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return Objects.equals(name, ((Authority) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Authority{" +
            "name='" + name + '\'' +
            "}";
    }
}
