package com.example.week06.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckResponse {

    private boolean enable;

    public CheckResponse(boolean exist) { this.enable = exist; }
}
