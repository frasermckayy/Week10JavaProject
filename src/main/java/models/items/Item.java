package models.items;


import models.baskets.Basket;
import models.transactions.Transaction;

import javax.persistence.*;
import javax.xml.crypto.dsig.TransformService;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    private int id;
    private Basket basket;
    private Category category;
    private int quantity;
    private double price;
    private Set<Transaction> transactions;

    public Item(Category category, int quantity, double price) {
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.transactions = new HashSet<>();
    }

    public Item() {
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
    @JoinColumn(name = "basket_id")
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "transactions_items",
    joinColumns = {@JoinColumn(name = "item_id", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "transaction_id", nullable = false, updatable = false)})
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction newTransaction){
        this.transactions.add(newTransaction);
    }

    public void removeTransaction(Transaction removedTransaction){
        this.transactions.remove(removedTransaction);
    }

}


