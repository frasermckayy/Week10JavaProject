package models.transactions;

import models.baskets.Basket;
import models.items.Item;
import models.users.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="transactions")
public class Transaction {

    private int id;
    private User user;
    private double amount;
    private String date;
    private Set<Item> items;

    public Transaction(User user, double amount, String date) {
        this.user = user;
        this.amount = amount;
        this.date = date;
        this.items = items;
        this.items = new HashSet<>();
    }

    public Transaction() {
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

    @ManyToOne
    @JoinColumn(name="user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Column(name = "date_of_transaction")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "transactions_items",
    joinColumns = {@JoinColumn(name = "transaction_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)})
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Item newItem){
        this.items.add(newItem);
    }

    public void removeItem(Item removedItem){
        this.items.remove(removedItem);
    }

    public void updateAmount(Basket testBasket) {
        setAmount(testBasket.calculateTotal());
    }
}
