package models.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clothes")
public class Clothe extends Item{

    private String color;
    private char size;

    public Clothe(Category category, int quantity, double price, String color, char size) {
        super(category, quantity, price);
        this.color = color;
        this.size = size;
    }

    public Clothe() {
    }

    @Column(name = "colors")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "sizes")
    public char getSize() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }
}
