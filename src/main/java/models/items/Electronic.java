package models.items;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electronics")
public class Electronic extends Item {

    private String parts;

    public Electronic() {
    }

    public Electronic(String parts) {
        this.parts = parts;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }
}
