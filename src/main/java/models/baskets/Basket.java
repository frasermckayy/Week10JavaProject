package models.baskets;

import models.items.Item;
import models.users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Basket {

    private int id;
    private Set<Item> items;
    private User user;

    public Basket(User user) {
        this.items = new HashSet<>();
        this.user = user;
    }

    public Basket() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "basket")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addItem(Item new_item){
        this.items.add(new_item);
    }

    public void removeitem(Item removedItem){
        this.items.remove(removedItem);
    }

    public void clearBasket(){
        this.items.clear();
    }

    public int numberOfItemsInBasket(){
        return this.items.size();
    }

    public double calculateTotal(){
        double sum = 0;
        for (Item item : this.items){
            sum += (item.getPrice() * item.getQuantity());
        }
        return sum;
    }

    public double buyOneGetOneFree(){
        double sum = 0;
        for (Item item : this.items){
            if (item.getQuantity() > 5) {
                if (item.getQuantity() % 2 == 0) {
                    sum += item.getPrice() * (item.getQuantity() / 2);
                } else {
                    sum += item.getPrice() * ((item.getQuantity() / 2) + 1);
                }
            }
        }
        return sum;
    }

}
