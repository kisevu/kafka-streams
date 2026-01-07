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
public class Company {
    private String id;
    private String indeed_absolute_link;
    private String indeed_relative_link;
    private String link;
    private String locality;
    private String logo_url;
    private String name;
}
