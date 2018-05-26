package models.transactions;

import models.users.User;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {

    private int id;
    private User user;

    public Transaction() {
    }

    public Transaction(User user) {
        this.user = user;
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

}
