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
public class IndeedJobDetails {
    private String apiUrl;
    private Company company;
    private String creation_date;
    private String description;
    private boolean expired;
    private String indeed_final_url;
    private boolean is_easy_apply;
    private String job_title;
    private String job_type;
    private String location;
    private Salary salary;

}
