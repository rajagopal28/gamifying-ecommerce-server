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
@Table(name = "user_rewards")
public class UserReward implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private String category;

    @Column()
    private Date createdTS;

    @Column()
    private Date lastUpdatedTs;
}