package com.cinar.cv.model;

/**
 * Tek bir eğitim bilgisini (okul, bölüm, yıllar) temsil eder.
 */
public class Education {

    private final String school;
    private final String department;
    private final String startYear;
    private final String endYear;

    public Education(String school, String department, String startYear, String endYear) {
        this.school = school;
        this.department = department;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public String getSchool() {
        return school;
    }

    public String getDepartment() {
        return department;
    }

    public String getYearRange() {
        return startYear + " – " + endYear;
    }
}
