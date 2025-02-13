class Yemek extends TemelBilgiler implements Urun {
    public Yemek(String ad, double fiyat, String kategori) {
        super(ad, fiyat, kategori);
    }

    @Override
    public void siparisVer() {
        System.out.println(getAd() + " sipariş edildi. Fiyat: " + getFiyat() + " TL");
    }
    public void ozelBilgiGoster() {
        System.out.println("Yemek Özel Bilgileri: " + getAd() + " - Kategori: " + getKategori());
    }
}