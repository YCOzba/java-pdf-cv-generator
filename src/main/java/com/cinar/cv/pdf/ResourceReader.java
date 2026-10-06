package com.cinar.cv.pdf;

import java.io.IOException;
import java.io.InputStream;

/**
 * src/main/resources klasöründeki dosyaları (fotoğraf, font) okumaya yarar.
 *
 * Sınıfın başında public yok: bu "default" (package-private) erişimdir.
 * Bu sınıfı sadece aynı paketteki (com.cinar.cv.pdf) sınıflar kullanabilir.
 */
final class ResourceReader {

    private ResourceReader() {
    }

    static byte[] read(String resourcePath) throws IOException {
        // try-with-resources: InputStream nesnesi blok bitince otomatik kapatılır.
        // Dosya okuma hata verse bile kapanma garanti edilir.
        try (InputStream in = ResourceReader.class.getResourceAsStream("/" + resourcePath)) {
            if (in == null) {
                throw new IOException("Kaynak dosya bulunamadı: " + resourcePath);
            }
            return in.readAllBytes();
        }
    }
}
