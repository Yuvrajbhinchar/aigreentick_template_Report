package com.aigreentick.services.template.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopOrganisationReportDto {
    private Long organisationId;
    private Long totalTemplates;
    private Double approvedPercentage;
}
