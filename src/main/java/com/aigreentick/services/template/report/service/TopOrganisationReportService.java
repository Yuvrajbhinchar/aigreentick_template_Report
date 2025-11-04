package com.aigreentick.services.template.report.service;

import com.aigreentick.services.template.enums.TemplateStatus;
import com.aigreentick.services.template.model.Template;
import com.aigreentick.services.template.report.dto.TopOrganisationReportDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopOrganisationReportService {

    private final MongoTemplate mongoTemplate;

    public List<TopOrganisationReportDto> getTopOrganisations() {

        //Group by organisationId
        GroupOperation groupByOrg = group("organisationId").count().as("totalTemplates")
                .sum(ConditionalOperators.when(
                        Criteria.where("status").is(TemplateStatus.APPROVED.name())).then(1).otherwise(0))

                .as("approvedCount");

        //Project to compute approvedPercentage
        ProjectionOperation project = project()
                .and("_id").as("organisationId")
                .and("totalTemplates").as("totalTemplates")
                .and(
                        ArithmeticOperators.Divide.valueOf("approvedCount").divideBy("totalTemplates")
                ).multiply(100).as("approvedPercentage");

        //Sort by totalTemplates (descending)
        SortOperation sort = sort(Sort.by(Sort.Direction.DESC, "totalTemplates"));

        //Limit top 10

        LimitOperation limit = limit(10);

        Aggregation aggregation = newAggregation(groupByOrg, project, sort, limit);

        AggregationResults<TopOrganisationReportDto> results = mongoTemplate.aggregate(aggregation, Template.class, TopOrganisationReportDto.class);

        return results.getMappedResults();

    }
    }

