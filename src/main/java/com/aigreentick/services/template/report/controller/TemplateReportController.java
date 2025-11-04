package com.aigreentick.services.template.report.controller;

import com.aigreentick.services.template.report.dto.TemplateReportDto;
import com.aigreentick.services.template.report.dto.TemplateReportFilterDto;
import com.aigreentick.services.template.report.service.TemplateReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/report/templates")
@RequiredArgsConstructor
public class TemplateReportController {

    private final TemplateReportService templateReportService;

    @GetMapping("/get-templates")
    public ResponseEntity<List<TemplateReportDto>> getTemplateReport(@RequestBody TemplateReportFilterDto filter){
        List<TemplateReportDto> report = templateReportService.genrateTemplateReport(filter);
        return ResponseEntity.ok(report);
    }

}
