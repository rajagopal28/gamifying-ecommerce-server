package com.dxtrs.hack.gamify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntry implements Serializable {

    private User user;

    private String levelName;

    private long levelAppliedOveSpanOf;

    private String boardSegmentType;

    private String boardSegmentValue;

    private long levelValue;

}
