package com.aigreentick.services.template.report.controller;

import com.aigreentick.services.template.report.dto.UserTemplateSummaryFilterDto;
import com.aigreentick.services.template.report.dto.UserTemplateSummaryReportDto;
import com.aigreentick.services.template.report.service.UserTemplateSummaryReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/templates/user")
@RequiredArgsConstructor
public class UserTemplateSummaryReportController {

    private final UserTemplateSummaryReportService userTemplateSummaryReportService;

    @PostMapping("/summery")
    public List<UserTemplateSummaryReportDto> getSummery(@RequestBody UserTemplateSummaryFilterDto filter){
        return userTemplateSummaryReportService.generateSummary(filter);
    }


}
