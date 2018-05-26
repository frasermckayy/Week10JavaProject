package models.users;

import models.baskets.Basket;
import models.transactions.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    private int id;
    private Set<Transaction> transaction;
    private models.users.LoyaltyCard loyaltyCard;
    private boolean signedUpForLoyaltyScheme;
    private String name;
    private String username;
    private String password;
    private Basket basket;

    public User(LoyaltyCard loyaltyCard, boolean signedUpForLoyaltyScheme, String name, String username, String password) {
        this.transaction = new HashSet<>();
        this.loyaltyCard = loyaltyCard;
        this.signedUpForLoyaltyScheme = false;
        this.name = name;
        this.username = username;
        this.password = password;
    }

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    @OneToOne(mappedBy = "user")
    public models.users.LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(models.users.LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    @OneToOne(mappedBy = "user")
    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void assignBasket(Basket basket){
        setBasket(basket);
    }

    @Column(name = "loyalty_scheme")
    public boolean isSignedUpForLoyaltyScheme() {
        return signedUpForLoyaltyScheme;
    }

    public void setSignedUpForLoyaltyScheme(boolean signedUpForLoyaltyScheme) {
        this.signedUpForLoyaltyScheme = signedUpForLoyaltyScheme;
    }

    public void signUpToLoyaltyScheme(String signUpDate){
        loyaltyCard.signUp(signUpDate);
        setSignedUpForLoyaltyScheme(true);
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
