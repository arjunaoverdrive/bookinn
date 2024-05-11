package org.arjunaoverdrive.bookinn.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.arjunaoverdrive.bookinn.service.csv.CSVService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final CSVService csvService;

    @GetMapping("/users")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public void getStatistics(@RequestParam LocalDate from, @RequestParam LocalDate to, HttpServletResponse response){
        String fileName = "data.csv";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;

        response.setContentType("text/csv");
        response.setHeader(headerKey, headerValue);

        csvService.getStats(from, to, response);
    }

}
