package com.cinar.cv.data;

import com.cinar.cv.model.Education;
import com.cinar.cv.model.Experience;
import com.cinar.cv.model.PersonalInfo;
import com.cinar.cv.model.Resume;

import java.util.List;

/**
 * Özgeçmişte kullanılan (hayali) verileri tek bir yerde toplar.
 * Bilgileri değiştirmek istersen sadece bu dosyayı düzenlemen yeterli.
 */
public final class SampleResumeData {

    // private constructor: bu sınıftan nesne oluşturulmasını engeller.
    // Sınıfın tek işi static create() metodunu sağlamak.
    private SampleResumeData() {
    }

    public static Resume create() {
        PersonalInfo info = new PersonalInfo(
                "Yağız Çınar Özbakır",
                "Yazılım Mühendisliği Öğrencisi · Java Geliştirici",
                "1250505057@ogr.klu.edu.tr",
                "+90 555 000 00 00",
                "İstanbul, Türkiye",
                "github.com/YCOzba",
                "photo.jpg"
        );

        Resume resume = new Resume(info,
                "Kırklareli Üniversitesi Yazılım Mühendisliği öğrencisiyim. Nesne yönelimli programlama, "
                        + "Java ile backend geliştirme ve mobil uygulamalar üzerine çalışıyorum. "
                        + "Temiz, okunabilir kod yazmaya ve yeni teknolojileri projeler üzerinden öğrenmeye önem veriyorum.");

        // Aynı Experience sınıfından üç ayrı nesne oluşturuluyor.
        resume.addExperience(new Experience(
                "Java Geliştirici Stajyeri",
                "Mavi Bulut Yazılım",
                "Haz 2026", "Ağu 2026",
                List.of(
                        "Spring Boot ile sipariş takip servisine yeni REST uç noktaları ekledim.",
                        "JUnit ile birim testleri yazarak servisin test kapsamını artırdım.",
                        "Kod incelemelerine katılıp ekip içi kodlama standartlarına uyum sağladım."
                )));

        resume.addExperience(new Experience(
                "Yarı Zamanlı Mobil Uygulama Geliştirici",
                "Kuzey Teknoloji",
                "Eki 2025", "May 2026",
                List.of(
                        "Kotlin ile geliştirilen bir Android uygulamasında arayüz hatalarını giderdim.",
                        "Yayın öncesi test sürecini yürüttüm ve kullanıcı geri bildirimlerini ekibe raporladım."
                )));

        resume.addExperience(new Experience(
                "Web Geliştirme Stajyeri",
                "Ada Dijital Ajans",
                "Tem 2025", "Eyl 2025",
                List.of(
                        "HTML, CSS ve JavaScript ile müşteri tanıtım sayfaları hazırladım.",
                        "Sayfaların mobil uyumluluğunu ve yüklenme hızını iyileştirdim."
                )));

        resume.addEducation(new Education(
                "Kırklareli Üniversitesi",
                "Yazılım Mühendisliği (Lisans)",
                "2025", "Devam ediyor"));

        resume.addEducation(new Education(
                "Kemal Hasoğlu Anadolu Lisesi",
                "Sayısal",
                "2021", "2025"));

        resume.addSkill("Java");
        resume.addSkill("OOP");
        resume.addSkill("Spring Boot");
        resume.addSkill("Git & GitHub");
        resume.addSkill("SQL");
        resume.addSkill("Kotlin / Android");
        resume.addSkill("HTML / CSS / JavaScript");
        resume.addSkill("JUnit");

        resume.addLanguage("Türkçe — Ana dil");
        resume.addLanguage("İngilizce — B2+");

        return resume;
    }
}
