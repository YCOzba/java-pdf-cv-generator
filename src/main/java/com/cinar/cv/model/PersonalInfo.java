package com.cinar.cv.model;

/**
 * Özgeçmişin en üstündeki kişisel bilgileri tutar.
 *
 * Bütün alanlar private ve final:
 * - private: alanlara sadece bu sınıfın içinden erişilebilir (kapsülleme).
 * - final: değer constructor'da bir kez verilir, sonra değiştirilemez.
 * Dışarıdaki sınıflar bu bilgileri sadece public getter metotlarıyla okuyabilir.
 */
public class PersonalInfo {

    private final String fullName;
    private final String title;
    private final String email;
    private final String phone;
    private final String location;
    private final String github;
    private final String photoFileName;

    public PersonalInfo(String fullName, String title, String email, String phone,
                        String location, String github, String photoFileName) {
        this.fullName = fullName;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.github = github;
        this.photoFileName = photoFileName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getGithub() {
        return github;
    }

    /** src/main/resources klasöründeki fotoğraf dosyasının adı (ör. "photo.jpg"). */
    public String getPhotoFileName() {
        return photoFileName;
    }
}
