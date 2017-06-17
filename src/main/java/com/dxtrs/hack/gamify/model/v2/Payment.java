package com.dxtrs.hack.gamify.model.v2;

import com.dxtrs.hack.gamify.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private String description;

    @Column()
    private String category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "promotion_id", nullable = true)
    private RewardPromotion promotion;

    @Column()
    private Double amount;

    @Column()
    private Date createdTS;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", promotion=" + promotion +
                ", createdTS=" + createdTS +
                '}';
    }
}