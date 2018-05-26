package models.items;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food extends Item {
    private String name;

    public Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
