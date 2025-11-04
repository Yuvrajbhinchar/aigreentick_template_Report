package com.aigreentick.services.template.report.controller;


import com.aigreentick.services.template.report.dto.TopOrganisationReportDto;
import com.aigreentick.services.template.report.service.TopOrganisationReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/organisations")
@RequiredArgsConstructor
public class TopOrganisationReportController {

    private final TopOrganisationReportService topOrganisationReportService;

    @GetMapping("/top")
    public List<TopOrganisationReportDto> getTopOrganisations() {
        return topOrganisationReportService.getTopOrganisations();
    }
}

