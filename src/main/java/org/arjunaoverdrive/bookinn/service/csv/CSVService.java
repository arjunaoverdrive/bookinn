package org.arjunaoverdrive.bookinn.service.csv;

import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;

public interface CSVService {
    void getStats(LocalDate from, LocalDate to, HttpServletResponse response);
}
