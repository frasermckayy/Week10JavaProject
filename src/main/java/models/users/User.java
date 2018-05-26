package models.user;

import models.baskets.Basket;
import models.transaction.Transaction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    private int id;
    private Set<Transaction> transaction;
    private models.user.LoyaltyCard loyaltyCard;
    private Basket basket;

    public User() {
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

    @OneToMany(mappedBy = "user")
    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    @OneToOne(mappedBy = "user")
    public models.user.LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(models.user.LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    @OneToOne(mappedBy = "user")
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

}
