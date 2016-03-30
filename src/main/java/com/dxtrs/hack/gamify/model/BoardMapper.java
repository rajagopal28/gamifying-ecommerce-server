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
@Table(name = "board_mapper")
public class BoardMapper implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String levelName;

    @Column(nullable = false)
    private String boardType;

    @Column(nullable = false)
    private long lowerLimit;

    @Column(nullable = false)
    private long upperLimit;

    @Column()
    private Date createdTS;

    @Column()
    private Date lastUpdatedTS;
}
