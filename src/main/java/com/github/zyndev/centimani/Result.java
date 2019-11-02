package com.github.zyndev.centimani;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private boolean match;
    private long controlTime;
    private long candidateTime;

}
