package com.cinar.cv;

import com.cinar.cv.data.SampleResumeData;
import com.cinar.cv.model.Resume;
import com.cinar.cv.pdf.ResumePdfGenerator;

import java.io.IOException;

/**
 * Programın giriş noktası.
 *
 * main metodu PDF'i kendisi oluşturmaz; sadece gerekli nesneleri hazırlar
 * ve işi ResumePdfGenerator sınıfına devreder.
 */
public class Main {

    public static void main(String[] args) {
        String outputPath = "output/ozgecmis.pdf";

        // 1) Özgeçmiş verisini içeren Resume nesnesi oluşturulur.
        Resume resume = SampleResumeData.create();

        try {
            // 2) PDF'i üretecek nesne oluşturulur.
            ResumePdfGenerator generator = new ResumePdfGenerator();

            // 3) Asıl iş ayrı sınıfta yapılır.
            generator.generate(resume, outputPath);

            System.out.println("Özgeçmiş oluşturuldu: " + outputPath);
        } catch (IOException e) {
            System.err.println("Özgeçmiş oluşturulamadı: " + e.getMessage());
        }
        // main bittiğinde resume ve generator nesnelerine artık hiçbir referans kalmaz;
        // bu nesneler Garbage Collector tarafından temizlenmeye uygun hale gelir.
    }
}
