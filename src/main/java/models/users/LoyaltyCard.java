package models.users;

import javax.persistence.*;

@Entity
@Table(name="loyaltycards")
public class LoyaltyCard {

    private int id;
    private models.user.User user;


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
    public models.user.User getUser() {
        return user;
    }

    public void setUser(models.user.User user) {
        this.user = user;
    }
}
