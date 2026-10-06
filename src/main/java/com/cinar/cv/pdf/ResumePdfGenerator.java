package com.cinar.cv.pdf;

import com.cinar.cv.model.Education;
import com.cinar.cv.model.Experience;
import com.cinar.cv.model.PersonalInfo;
import com.cinar.cv.model.Resume;
import org.openpdf.text.Document;
import org.openpdf.text.Element;
import org.openpdf.text.Image;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Phrase;
import org.openpdf.text.Rectangle;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Bir Resume nesnesini alıp A4 boyutunda PDF özgeçmişe dönüştürür.
 *
 * PDF ile ilgili bütün işler bu sınıftadır; Main sınıfı sadece generate() metodunu çağırır.
 * PDF oluşturmak için OpenPDF kütüphanesi kullanılır.
 */
public class ResumePdfGenerator {

    private static final float PAGE_MARGIN = 40f;
    private static final float PHOTO_WIDTH = 92f;
    private static final float PHOTO_HEIGHT = 115f;
    private static final int SKILL_COLUMNS = 4;

    private final PdfStyles styles;

    public ResumePdfGenerator() throws IOException {
        this.styles = new PdfStyles();
    }

    /**
     * Özgeçmişi verilen dosya yoluna PDF olarak yazar.
     *
     * @param resume     PDF'e dönüştürülecek özgeçmiş
     * @param outputPath oluşturulacak dosyanın yolu (ör. "output/ozgecmis.pdf")
     */
    public void generate(Resume resume, String outputPath) throws IOException {
        Path path = Path.of(outputPath);
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        Document document = new Document(PageSize.A4, PAGE_MARGIN, PAGE_MARGIN, PAGE_MARGIN, PAGE_MARGIN);

        // try-with-resources: dosyaya yazan OutputStream işi bitince otomatik kapatılır.
        try (OutputStream out = Files.newOutputStream(path)) {
            PdfWriter.getInstance(document, out);

            document.open();
            addHeader(document, resume.getPersonalInfo());
            addSummary(document, resume.getSummary());
            addExperiences(document, resume.getExperiences());
            addEducations(document, resume.getEducations());
            addSkills(document, resume.getSkills());
            addLanguages(document, resume.getLanguages());
            document.close();
        }
    }

    // ---------------------------------------------------------------
    // Bölümler: her private metot özgeçmişin bir bölümünü çizer.
    // private oldukları için bu metotları sadece bu sınıf çağırabilir.
    // ---------------------------------------------------------------

    /** Fotoğraf, ad-soyad, unvan ve iletişim bilgilerini içeren üst kısım. */
    private void addHeader(Document document, PersonalInfo info) throws IOException {
        PdfPTable header = new PdfPTable(new float[]{PHOTO_WIDTH + 18f, 400f});
        header.setWidthPercentage(100);

        // Sol hücre: fotoğraf
        Image photo = Image.getInstance(ResourceReader.read(info.getPhotoFileName()));
        photo.scaleToFit(PHOTO_WIDTH, PHOTO_HEIGHT);
        photo.setBorder(Rectangle.BOX);
        photo.setBorderWidth(1f);
        photo.setBorderColor(PdfStyles.LINE);

        PdfPCell photoCell = new PdfPCell(photo, false);
        photoCell.setBorder(Rectangle.NO_BORDER);
        photoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        header.addCell(photoCell);

        // Sağ hücre: isim ve iletişim bilgileri
        PdfPCell textCell = new PdfPCell();
        textCell.setBorder(Rectangle.NO_BORDER);
        textCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        textCell.addElement(new Paragraph(info.getFullName(), styles.name));

        Paragraph headline = new Paragraph(info.getTitle(), styles.headline);
        headline.setSpacingAfter(8f);
        textCell.addElement(headline);

        textCell.addElement(new Paragraph(info.getEmail() + "   ·   " + info.getPhone(), styles.contact));
        textCell.addElement(new Paragraph(info.getLocation() + "   ·   " + info.getGithub(), styles.contact));
        header.addCell(textCell);

        document.add(header);
    }

    private void addSummary(Document document, String summary) {
        addSectionTitle(document, "HAKKIMDA");
        Paragraph paragraph = new Paragraph(summary, styles.body);
        paragraph.setLeading(14f);
        document.add(paragraph);
    }

    private void addExperiences(Document document, List<Experience> experiences) {
        addSectionTitle(document, "İŞ DENEYİMİ");
        for (Experience experience : experiences) {
            addTitleWithDate(document, experience.getPosition(), experience.getDateRange());
            addSubtitle(document, experience.getCompany());
            for (String highlight : experience.getHighlights()) {
                addBullet(document, highlight);
            }
        }
    }

    private void addEducations(Document document, List<Education> educations) {
        addSectionTitle(document, "EĞİTİM");
        for (Education education : educations) {
            addTitleWithDate(document, education.getSchool(), education.getYearRange());
            addSubtitle(document, education.getDepartment());
        }
    }

    /** Yetenekleri, arka planı renkli küçük kutucuklar (etiketler) halinde gösterir. */
    private void addSkills(Document document, List<String> skills) {
        addSectionTitle(document, "YETENEKLER");

        PdfPTable table = new PdfPTable(SKILL_COLUMNS);
        table.setWidthPercentage(100);
        table.setSpacingBefore(4f);

        for (String skill : skills) {
            PdfPCell cell = new PdfPCell(new Phrase(skill, styles.tag));
            cell.setBackgroundColor(PdfStyles.TAG_BACKGROUND);
            // Sayfa renginde kalın kenarlık, kutucukların arasında boşluk varmış gibi görünmesini sağlar.
            cell.setBorderColor(PdfStyles.PAGE);
            cell.setBorderWidth(3f);
            cell.setPadding(6f);
            cell.setMinimumHeight(26f);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }
        // Son satır eksik kalırsa boş hücrelerle tamamlanır, yoksa tablo çizilmez.
        table.completeRow();

        document.add(table);
    }

    private void addLanguages(Document document, List<String> languages) {
        addSectionTitle(document, "DİLLER");
        for (String language : languages) {
            addBullet(document, language);
        }
    }

    // ---------------------------------------------------------------
    // Yardımcı metotlar
    // ---------------------------------------------------------------

    /**
     * Bölüm başlığını ve altındaki ince çizgiyi ekler.
     *
     * protected: bu sınıftan türetilecek bir alt sınıf (ör. farklı tasarımlı bir CV)
     * başlık stilini kendine göre değiştirebilsin diye protected bırakıldı.
     */
    protected void addSectionTitle(Document document, String title) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setSpacingBefore(14f);
        table.setSpacingAfter(4f);

        PdfPCell cell = new PdfPCell(new Phrase(title, styles.sectionTitle));
        cell.setBorder(Rectangle.BOTTOM);
        cell.setBorderColor(PdfStyles.LINE);
        cell.setBorderWidth(1f);
        cell.setPaddingLeft(0f);
        cell.setPaddingBottom(5f);
        table.addCell(cell);

        document.add(table);
    }

    /** Solda kalın başlık, sağda gri tarih olacak şekilde tek satır ekler. */
    private void addTitleWithDate(Document document, String title, String date) {
        PdfPTable row = new PdfPTable(new float[]{3f, 1.3f});
        row.setWidthPercentage(100);
        row.setSpacingBefore(9f);

        PdfPCell titleCell = new PdfPCell(new Phrase(title, styles.itemTitle));
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setPadding(0f);
        row.addCell(titleCell);

        PdfPCell dateCell = new PdfPCell(new Phrase(date, styles.date));
        dateCell.setBorder(Rectangle.NO_BORDER);
        dateCell.setPadding(0f);
        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        row.addCell(dateCell);

        document.add(row);
    }

    private void addSubtitle(Document document, String text) {
        Paragraph subtitle = new Paragraph(text, styles.itemSubtitle);
        subtitle.setSpacingAfter(2f);
        document.add(subtitle);
    }

    /** Başında madde işareti olan, alt satırları içeriden devam eden paragraf ekler. */
    private void addBullet(Document document, String text) {
        Paragraph bullet = new Paragraph("•   " + text, styles.body);
        bullet.setIndentationLeft(12f);
        bullet.setFirstLineIndent(-9f);
        bullet.setLeading(13.5f);
        document.add(bullet);
    }
}
