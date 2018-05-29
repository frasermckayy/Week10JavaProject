package models.baskets;

import db.DBHelper;
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
    private double total;

    public Basket(User user) {
        this.items = new HashSet<>();
        this.user = user;
        this.total = 0;
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

    @OneToMany(mappedBy = "basket", fetch = FetchType.EAGER)
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

    @Column(name = "total")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void addItem(Item new_item){
        this.items.add(new_item);
        calculateTotal();
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

    public void calculateTotal(){
        for (Item item : this.items){
            this.total += (item.getPrice() * item.getQuantity());
        }
    }

    public void buyOneGetOneFree(){
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
        this.total -= sum;
    }

    public void tenPercentOffPurchasesOver100(){
        if (this.total >= 100) {
            this.total *= 0.9;
        }
    }

    public void loyaltyDiscount(User customer){
        if (customer.isSignedUpForLoyaltyScheme()){
            this.total *= 0.9;
        }
    }

}
