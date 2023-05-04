package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 *
 * TODO: Refactor this class by rewritting the runReport method to use the SearchService
 */
@RestController
public class ReportController {

    private final SearchService searchService;

    @Autowired
    public ReportController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/products/report")
    public SearchReportResponse runReport() {
        SearchReportResponse response = new SearchReportResponse();
        Map<String, Integer> hits = new HashMap<>();
        response.setSearchTermHits(hits);

        int count = searchService.getProductCount();
        hits.put("Cool", searchService.getMatchingProductCount("cool"));

        int kidCount = searchService.getMatchingProductCount("kids");
        hits.put("Kids", kidCount);

        int perfectCount = searchService.getMatchingProductCount("perfect");
        hits.put("Perfect", perfectCount);

        hits.put("Amazing", searchService.getMatchingProductCount("amazing"));

        response.setProductCount(count);

        return response;
    }
}

