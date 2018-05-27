package models.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "foods")
public class Food extends Item {

    private String name;

    public Food(Category category, int quantity, double price, String name) {
        super(category, quantity, price);
        this.name = name;
    }

    public Food() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
