package com.cinar.cv.model;

import java.util.List;

/**
 * Tek bir iş deneyimini temsil eder.
 *
 * Bu sınıf bir "kalıp"tır: özgeçmişteki 3 iş deneyiminin her biri
 * bu kalıptan new ile üretilmiş ayrı bir nesnedir.
 */
public class Experience {

    private final String position;
    private final String company;
    private final String startDate;
    private final String endDate;
    private final List<String> highlights;

    public Experience(String position, String company, String startDate, String endDate,
                      List<String> highlights) {
        this.position = position;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        // List.copyOf: dışarıdan verilen listenin değiştirilemez bir kopyasını saklar.
        // Böylece nesne oluşturulduktan sonra kimse listeyi dışarıdan bozamaz.
        this.highlights = List.copyOf(highlights);
    }

    public String getPosition() {
        return position;
    }

    public String getCompany() {
        return company;
    }

    /** Başlangıç ve bitiş tarihini tek bir metin olarak döndürür (ör. "Haz 2026 – Ağu 2026"). */
    public String getDateRange() {
        return startDate + " – " + endDate;
    }

    public List<String> getHighlights() {
        return highlights;
    }
}
