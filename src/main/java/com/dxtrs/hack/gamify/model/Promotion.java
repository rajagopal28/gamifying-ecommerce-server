package com.dxtrs.hack.gamify.model;

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
@Table(name = "promotions")
public class Promotion implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String promotionName;

    @Column(nullable = false)
    private String promotionCode;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private String appliedOnLevel;

    @Column(nullable = false)
    private String boardType;

    @Column()
    private Date createdTS;

    @Column()
    private Date lastUpdatedTS;
}
