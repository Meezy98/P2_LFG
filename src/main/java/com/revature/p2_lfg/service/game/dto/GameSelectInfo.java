package com.revature.p2_lfg.service.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSelectInfo {

    private int gameId;
    private int platformKey;
    private String name;
    private String imgLink;
    private int sessions;

}
