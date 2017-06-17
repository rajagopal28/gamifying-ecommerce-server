package com.dxtrs.hack.gamify.model.v2;


import com.dxtrs.hack.gamify.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_rewards")
public class CumulativeReward implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double value;

    @Column()
    private String category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

}
