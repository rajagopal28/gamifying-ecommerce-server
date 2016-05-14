package com.dxtrs.hack.gamify.model;

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
@Table(name = "board_entry")
public class BoardEntry implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column()
    private String levelName;

    @Column()
    private String boardSegmentType;

    @Column()
    private String boardSegmentValue;

    @Column()
    private long levelValue;

    @Override
    public String toString() {
        return "BoardEntry{" +
                " user=" + user +
                ", levelName='" + levelName + '\'' +
                ", boardSegmentType='" + boardSegmentType + '\'' +
                ", boardSegmentValue='" + boardSegmentValue + '\'' +
                ", levelValue=" + levelValue +
                '}';
    }
}
