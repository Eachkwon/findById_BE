package com.example.week06.model;

public enum DistrictEnum {
    Bukgu("북구"),
    Donggu("동구"),
    Seogu("서구"),
    Junggu("중구"),
    Suseonggu("수성구"),
    Dalseogu("달서구"),
    Namgu("남구"),
    Dalseonggun("달성군");

    private String KrName;

    DistrictEnum(String KrName) {
        this.KrName = KrName;
    }

    public String getKrName() {
        return KrName;
    }

}
