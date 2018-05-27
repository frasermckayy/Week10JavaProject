package models.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electronics")
public class Electronic extends Item {

    private String parts;

    public Electronic(Category category, int quantity, double price, String parts) {
        super(category, quantity, price);
        this.parts = parts;
    }

    public Electronic() {
    }

    @Column(name = "parts")
    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }
}
