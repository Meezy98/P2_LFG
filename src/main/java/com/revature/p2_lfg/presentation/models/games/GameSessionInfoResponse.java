package com.revature.p2_lfg.presentation.models.games;

import com.revature.p2_lfg.service.game.dto.GameSelectInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionInfoResponse {
    private List<GameSelectInfo> gameSessionList;
}
