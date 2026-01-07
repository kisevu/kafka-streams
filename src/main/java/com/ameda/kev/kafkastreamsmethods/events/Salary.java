package com.ameda.kev.kafkastreamsmethods.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: kev.Ameda
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Salary {
    private Integer max;
    private Integer min;
    private String type;
}
