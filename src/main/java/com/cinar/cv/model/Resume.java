package com.cinar.cv.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Özgeçmişin tamamını bir arada tutan sınıf.
 *
 * Kendi içinde başka nesneleri barındırır (kompozisyon / "has-a" ilişkisi):
 * bir Resume'un bir PersonalInfo'su, birden çok Experience ve Education nesnesi vardır.
 */
public class Resume {

    private final PersonalInfo personalInfo;
    private final String summary;
    private final List<Experience> experiences = new ArrayList<>();
    private final List<Education> educations = new ArrayList<>();
    private final List<String> skills = new ArrayList<>();
    private final List<String> languages = new ArrayList<>();

    public Resume(PersonalInfo personalInfo, String summary) {
        this.personalInfo = personalInfo;
        this.summary = summary;
    }

    // Listelere dışarıdan sadece bu metotlarla eleman eklenebilir.

    public void addExperience(Experience experience) {
        experiences.add(experience);
    }

    public void addEducation(Education education) {
        educations.add(education);
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public void addLanguage(String language) {
        languages.add(language);
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public String getSummary() {
        return summary;
    }

    // Getter'lar listenin kendisini değil, salt-okunur bir görünümünü döndürür.
    // Böylece dışarıdan biri getExperiences().clear() diyerek veriyi silemez.

    public List<Experience> getExperiences() {
        return Collections.unmodifiableList(experiences);
    }

    public List<Education> getEducations() {
        return Collections.unmodifiableList(educations);
    }

    public List<String> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public List<String> getLanguages() {
        return Collections.unmodifiableList(languages);
    }
}
