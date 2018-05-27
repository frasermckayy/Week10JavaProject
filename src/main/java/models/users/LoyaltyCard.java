package models.users;

import javax.persistence.*;

@Entity
@Table(name="loyaltycards")
public class LoyaltyCard {

    private int id;
    private models.users.User user;
    private String signUpDate;

    public LoyaltyCard(User user, String signUpDate) {
        this.user = user;
        this.signUpDate = signUpDate;
    }

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

    @Column(name = "signup_date")
    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }
}
