package com.soongjamm.football_manager;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransferRequestDto {
    private String playerName;
    private String headingTeamName;

    @Builder
    public TransferRequestDto(String playerName, String headingTeamName) {
        this.playerName = playerName;
        this.headingTeamName = headingTeamName;
    }
}
