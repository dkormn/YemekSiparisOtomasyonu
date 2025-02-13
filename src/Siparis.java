import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Siparis extends TemelBilgiler {
    private List<Urun> sepet = new ArrayList<>();
    public Siparis(String ad, double fiyat, String kategori) {
        super(ad, fiyat, kategori);
    }

    @Override
    public void ozelBilgiGoster() {
        System.out.println("Sipariş Özel Bilgileri: " + getAd() + " - Toplam Fiyat: " + getFiyat() + " TL");
    }
    public void sepeteEkle(Urun urun) {
        sepet.add(urun);
    }

    public void sepettenCikar(Urun urun) {
        if (sepet.contains(urun)) {
            sepet.remove(urun);
            System.out.println(urun.getAd() + " sepetten çıkarıldı.");
        } else {
            System.out.println("Üzgünüz, belirtilen ürün sepetinizde bulunamadı.");
        }
    }
    public void sepetiTemizle() {
        this.sepet.clear();
        System.out.println("Sepet temizlendi.");
    }

    public double toplamFiyatHesapla() {
        double toplamFiyat = 0;
        for (Urun urun : sepet) {
            toplamFiyat += urun.getFiyat();
        }
        return toplamFiyat;
    }

    public void sepetiGoster() {
        if (sepet.isEmpty()) {
            System.out.println("Sepetiniz boş.");
        } else {
            System.out.println("Sepetinizdeki Ürünler:");
            for (Urun urun : sepet) {
                System.out.println("- " + urun.getAd() + " - Fiyat: " + urun.getFiyat() + " TL");
            }
            System.out.println("Toplam Fiyat: " + toplamFiyatHesapla() + " TL");
        }
    }
    public void siparisiGuncelle(Urun urun, int adet) {
        for (int i = 0; i < adet; i++) {
            sepeteEkle(urun);
        }
        System.out.println(adet + " adet " + urun.getAd() + " sepete eklendi.");
    }
    private static void urunEkleVeGuncelle(Siparis siparis, Menu menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Hangi ürünü eklemek istiyorsunuz? (Ürün adını girin): ");
        String urunAdi = scanner.nextLine();
        Urun urun = menu.getUrunListesi(urunAdi);

        if (urun != null) {
            System.out.print("Kaç adet eklemek istiyorsunuz? ");
            int adet = scanner.nextInt();
            siparis.siparisiGuncelle(urun, adet);
        } else {
            System.out.println("Belirtilen isimde bir ürün bulunamadı.");
        }
    }
    public static void siparisIslemleri(Siparis siparis, Menu menu) {
        int secim;
        Scanner scanner=new Scanner(System.in);
        do {
            System.out.println("Sipariş İşlemleri:");
            System.out.println("1. Sepete Ekle(Kategoriye göre seç)");
            System.out.println("2. Sepete Ekle(Restorana göre seç)");
            System.out.println("3. Sepetten Çıkar");
            System.out.println("4. Siparişi Onayla");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    System.out.println("Kategoriye Göre Ürün Ekleme");
                    menu.kategorileriListele();
                    System.out.println("Kategori Seçin (örneğin: Pizza, Hamburger, Döner): ");
                    scanner.nextLine(); // buffer temizle
                    String kategori = scanner.nextLine();
                    menu.urunleriListeleByCategory(kategori);
                    urunEkleVeGuncelle(siparis, menu);
                    break;

                case 2:
                    System.out.println("Restorana Göre Ürün Ekleme");
                    // Restoranları listele
                    menu.restoranlariListele();
                    System.out.println("Restoran Seçin (örneğin: Restoran A, Restoran B): ");
                    scanner.nextLine(); // buffer temizle
                    String restoranAdi = scanner.nextLine();
                    menu.urunleriListeleByRestaurant(restoranAdi);
                    urunEkleVeGuncelle(siparis, menu);
                    break;
                case 3:
                    System.out.println("Ürün Çıkarma");
                    siparis.sepetiGoster();
                    System.out.print("Hangi ürünü çıkarmak istiyorsunuz? (Ürün adını girin): ");
                    scanner.nextLine(); // buffer temizle
                    String cikarilanUrunAdi = scanner.nextLine();
                    Urun cikarilanUrun = menu.getUrunListesi(cikarilanUrunAdi);

                    if (cikarilanUrun != null) {
                        siparis.sepettenCikar(cikarilanUrun);
                    } else {
                        System.out.println("Belirtilen isimde bir ürün bulunamadı.");
                    }
                    break;
                case 4:
                    siparis.sepetiGoster();
                    System.out.println("Siparişi onaylıyor musunuz? (Evet/Hayır): ");
                    String onay = scanner.next();
                    if (onay.equalsIgnoreCase("Evet")) {
                        System.out.println("Sipariş onaylandı. Toplam Tutar: " + siparis.toplamFiyatHesapla() + " TL");
                        siparis = null;
                        secim=0;
                    } else {
                        System.out.println("Sipariş onaylanmadı.");
                    }
                    break;
                case 0:
                    System.out.println("Sipariş İşlemlerinden Çıkılıyor");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        } while (secim != 0);
    }
}