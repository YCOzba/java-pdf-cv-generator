package com.cinar.cv.pdf;

import org.openpdf.text.Font;
import org.openpdf.text.pdf.BaseFont;

import java.awt.Color;
import java.io.IOException;

/**
 * PDF'te kullanılan renkleri ve yazı tiplerini tek bir yerde toplar.
 * Böylece tasarımı değiştirmek için sadece bu dosyaya bakmak yeterli olur.
 *
 * Sınıf ve alanları default (package-private) erişimlidir:
 * sadece com.cinar.cv.pdf paketindeki sınıflar bunları görebilir.
 */
class PdfStyles {

    // static final: nesneye değil sınıfa ait, değişmeyen sabitler.
    static final Color ACCENT = new Color(0x1E, 0x4D, 0x8C);
    static final Color TEXT = new Color(0x1F, 0x23, 0x28);
    static final Color MUTED = new Color(0x6A, 0x73, 0x7D);
    static final Color LINE = new Color(0xD8, 0xDE, 0xE4);
    static final Color TAG_BACKGROUND = new Color(0xEC, 0xF2, 0xF9);
    static final Color PAGE = Color.WHITE;

    final Font name;
    final Font headline;
    final Font contact;
    final Font sectionTitle;
    final Font itemTitle;
    final Font itemSubtitle;
    final Font date;
    final Font body;
    final Font tag;

    PdfStyles() throws IOException {
        // Fontu PDF'in içine gömüyoruz (EMBEDDED) ve IDENTITY_H kodlaması kullanıyoruz.
        // Bu sayede ş, ğ, ı, İ gibi Türkçe karakterler her bilgisayarda doğru görünür.
        BaseFont regular = loadFont("fonts/InstrumentSans-Regular.ttf");
        BaseFont bold = loadFont("fonts/InstrumentSans-Bold.ttf");

        name = new Font(bold, 24, Font.NORMAL, TEXT);
        headline = new Font(regular, 11, Font.NORMAL, ACCENT);
        contact = new Font(regular, 9, Font.NORMAL, MUTED);
        sectionTitle = new Font(bold, 10.5f, Font.NORMAL, ACCENT);
        itemTitle = new Font(bold, 10.5f, Font.NORMAL, TEXT);
        itemSubtitle = new Font(regular, 9.5f, Font.NORMAL, ACCENT);
        date = new Font(regular, 9, Font.NORMAL, MUTED);
        body = new Font(regular, 9.5f, Font.NORMAL, TEXT);
        tag = new Font(regular, 9, Font.NORMAL, ACCENT);
    }

    private static BaseFont loadFont(String resourcePath) throws IOException {
        byte[] fontBytes = ResourceReader.read(resourcePath);
        return BaseFont.createFont(resourcePath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED,
                true, fontBytes, null);
    }
}
