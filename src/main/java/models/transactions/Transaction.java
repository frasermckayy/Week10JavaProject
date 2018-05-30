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
    private User customer;
    private double amount;
    private String date;
    private Set<Item> items;

    public Transaction(User customer, double amount, String date) {
        this.customer = customer;
        this.amount = amount;
        this.date = date;
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
    @JoinColumn(name="customer", nullable = false)
    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
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

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_items",
    joinColumns = {@JoinColumn(name = "transaction_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)})
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Set<Item> newItems){
        for(Item item : newItems){
            this.items.add(item);
        }
    }

    public void removeItem(Item removedItem){
        this.items.remove(removedItem);
    }

    public void updateAmount(Basket testBasket) {
        testBasket.calculateTotal();
        setAmount(testBasket.getTotal());
    }
}
