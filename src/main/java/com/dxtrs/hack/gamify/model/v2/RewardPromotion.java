package com.dxtrs.hack.gamify.model.v2;

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
@Table(name = "reward_promotions")
public class RewardPromotion implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String promotionName;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double rewardMultiplier;

    @Column(nullable = false)
    private String category;

    @Column
    private Double upperLimit;

    @Column
    private Double lowerLimit;

    @Column()
    private Date createdTS;

    @Column()
    private Date lastUpdatedTS;
}
