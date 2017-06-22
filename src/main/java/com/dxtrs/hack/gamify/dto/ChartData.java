package com.dxtrs.hack.gamify.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "chart_data")
public class ChartData implements Serializable {
    @Id
    private Long id;
    @Column()
    private Long value;
    @Column()
    private String label;
}
