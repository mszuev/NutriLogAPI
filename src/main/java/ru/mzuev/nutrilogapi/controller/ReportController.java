package ru.mzuev.nutrilogapi.controller;

import ru.mzuev.nutrilogapi.dto.ReportResponse;
import ru.mzuev.nutrilogapi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

/**
 * Контроллер для генерации отчетов о питании.
 */
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    /**
     * Генерирует ежедневный отчет по питанию пользователя.
     *
     * @param userId ID пользователя
     * @param date Дата отчета в формате ISO (YYYY-MM-DD)
     * @return DTO с отчетом о питании
     */
    @GetMapping("/daily/{userId}")
    public ReportResponse generateDailyReport(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.generateDailyReport(userId, date);
    }
}