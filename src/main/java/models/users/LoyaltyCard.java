package models.users;

import javax.persistence.*;

@Entity
@Table(name="loyaltycards")
public class LoyaltyCard {

    private int id;
    private models.users.User user;


    public LoyaltyCard() {
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

    @OneToOne()
    public models.users.User getUser() {
        return user;
    }

    public void setUser(models.users.User user) {
        this.user = user;
    }
}
