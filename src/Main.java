import java.util.*;
interface Urun {
    void siparisVer();
    String getAd();
    double getFiyat();
    String getKategori();
    void setAd(String ad);
    void setFiyat(double fiyat);
    void setKategori(String kategori);
}

abstract class TemelBilgiler {
    protected String ad;
    protected double fiyat;
    protected String kategori;

    public TemelBilgiler(String ad, double fiyat, String kategori) {
        this.ad = ad;
        this.fiyat = fiyat;
        this.kategori = kategori;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public abstract void ozelBilgiGoster();
}
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

class Pizza extends Yemek {
    public Pizza(String ad, double fiyat) {
        super(ad, fiyat, "Pizza");
    }
}
class Hamburger extends Yemek {
    public Hamburger(String ad, double fiyat) {
        super(ad, fiyat, "Hamburger");
    }
}

class Doner extends Yemek {
    public Doner(String ad, double fiyat) {
        super(ad, fiyat, "Döner");
    }
}
class Icecek extends Yemek {
    public Icecek(String ad, double fiyat) {
        super(ad, fiyat, "İçecek");
    }
}


class Admin  {
    private final Menu menu;
    private Siparis siparis;
    public Admin(Menu menu) {
        this.menu = menu;
    }

    public void adminIslemleriniYap() {
        Scanner scanner = new Scanner(System.in);
        int secim;

        do {
            System.out.println("Admin İşlemleri:");
            System.out.println("1. Restoran Ekle");
            System.out.println("2. Ürün Ekle");
            System.out.println("3. Ürün Sil");
            System.out.println("4. Ürün Fiyatını Güncelle");
            System.out.println("5. Yeni Sipariş Oluştur");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminizi yapın: ");
            secim = scanner.nextInt();

            switch (secim) {
                case 1:
                    scanner.nextLine();
                    System.out.println("Restoran Adı: ");
                    String yeniRestoranAdi = scanner.nextLine();
                    Restoran yeniRestoran = new Restoran(yeniRestoranAdi, "Adres", "Telefon");
                    menu.restoranEkle(yeniRestoran);
                    System.out.println("Eklenen Restoran: " + yeniRestoranAdi);
                    break;
                case 2:
                    UrunIslemleri.urunEkle(menu);
                    break;
                case 3:
                    UrunIslemleri.urunSil(menu);
                    break;
                case 4:
                    UrunIslemleri.urunFiyatGuncelle(menu);
                    break;
                case 5:
                    if (siparis == null) {
                        siparis = new Siparis("Yeni Sipariş", 0, "");
                    }
                    Siparis.siparisIslemleri(siparis, menu);
                    break;
                case 0:
                    System.out.println("Admin İşlemlerinden Çıkılıyor");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        } while (secim != 0);
    }
}
class Restoran   {
    private String restoranAdi;
    private String adres;
    private String telefon;
    private List<Urun> menu;
    private Menu menu1;

    public Restoran(String restoranAdi, String adres, String telefon) {
        this.restoranAdi = restoranAdi;
        this.adres = adres;
        this.telefon = telefon;
        this.menu = new ArrayList<>();
    }

    public Restoran(String restoranAdi) {
        this(restoranAdi, "Adres", "Telefon");
        if (menu1 != null) {
        }
    }
    public String getAd() {
        return restoranAdi;
    }

    public void setRestoranAdi(String restoranAdi) {
        this.restoranAdi = restoranAdi;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public List<Urun> getMenu() {
        return menu;
    }

    public void setMenu(List<Urun> menu) {
        this.menu = menu;
    }
    public void setMenu(Menu menu) {
        if (this.menu1 == null) {
            this.menu1 = menu;
            menu1.restoranEkle(this);
        }
    }

    public void urunEkle(Urun urun) {
        menu.add(urun);
    }

    public void bilgileriGoster() {
        System.out.println("Restoran Adı: " + restoranAdi);
        System.out.println("Adres: " + adres);
        System.out.println("Telefon: " + telefon);

    }

    public void ozelBilgiGoster() {
        System.out.println("Restoran Özel Bilgileri: " + getAd() );
    }
}

class Menu {
    private List<Urun> urunListesi = new ArrayList<>();
    //urunlistesi adında liste oluşturuyor urun interfaceinin kuulanıldığı nesneleri içeriyor
    private List<Restoran> restoranListesi = new ArrayList<>();

    public void urunEkle(Urun urun) {
        urunListesi.add(urun);
    }

    public void restoranEkle(Restoran restoran) {
        restoranListesi.add(restoran);
    }

    public Urun getUrunListesi(String urunAdi) {
        for (Urun urun : urunListesi) {
            if (urun.getAd().equalsIgnoreCase(urunAdi)) {
                return urun;
            }
        }
        return null;
    }

    public List<String> kategorileriGetir() {//ürünlerin kategorilerini topluyor
        Set<String> kategoriler = new HashSet<>();
        for (Urun urun : urunListesi) {
            kategoriler.add(urun.getKategori());
            //her ürün nesnesinin kategorisini getiriyor
            //aynı aktegroideki ürünler bir kez eklendi böykece
        }
        return new ArrayList<>(kategoriler);
    }

    public void kategorileriListele() {
        List<String> kategoriler = kategorileriGetir();
        System.out.println("Mevcut Kategoriler:");
        for (String kategori : kategoriler) {
            System.out.println("- " + kategori);
        }
    }
    public void urunleriListeleByCategory(String kategori) {
        System.out.println("Menüdeki Ürünler (" + kategori + "):");
        for (Urun urun : urunListesi) {
            if (urun.getKategori().equalsIgnoreCase(kategori)) {
                System.out.println("- " + urun.getAd() + " - Fiyat: " + urun.getFiyat() + " TL");
            }
        }
    }
    public void urunleriListeleByRestaurant(String restoranAdi) {
        for (Restoran restoran : restoranListesi) {
            if (restoran.getAd().equalsIgnoreCase(restoranAdi)) {
                System.out.println(restoran.getAd() + " Menüsü:");
                for (Urun urun : restoran.getMenu()) {
                    System.out.println(urun.getAd() + " - " + urun.getFiyat() + " TL");
                }
                return;
            }
        }
        System.out.println("Belirtilen restoran bulunamadı.");
    }
    public List<Restoran> getRestoranListesi() {
        return restoranListesi;
    }

    public void urunleriListele() {
        System.out.println("Menüdeki Ürünler:");
        for (Urun urun : urunListesi) {
            System.out.println("- " + urun.getAd() + " (" + urun.getKategori() + ") - Fiyat: " + urun.getFiyat() + " TL");
        }
    }

    public void restoranlariListele() {
        Set<String> restoranAdlari = new HashSet<>(); // Her restoranın adını tek bir kere tutmak için Set kullanıyoruz
        System.out.println("Kayıtlı Restoranlar:");
        for (Restoran restoran : restoranListesi) {
            restoranAdlari.add(restoran.getAd());
        }
        for (String restoranAdi : restoranAdlari) {
            System.out.println("- " + restoranAdi);
        }
    }
    public Restoran getRestoran(String restoranAdi) {
        for (Restoran restoran : restoranListesi) {
            if (restoran.getAd().equalsIgnoreCase(restoranAdi)) {
                return restoran;
            }
        }
        return null;
    }
    public boolean urunSil(String urunAdi) {
        for (Urun urun : urunListesi) {
            if (urun.getAd().equalsIgnoreCase(urunAdi)) {
                urunListesi.remove(urun);
                return true;
            }
        }
        return false;
    }

}
class UrunIslemleri {

    private static Scanner scanner = new Scanner(System.in);

    public static void urunEkle(Menu menu) {
        System.out.println("Ürün Ekleme");
        System.out.print("Ürün Adı: ");
        String ad = scanner.nextLine();
        System.out.print("Fiyat: ");
        double fiyat = scanner.nextDouble();
        scanner.nextLine(); // buffer temizle
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine();
        menu.restoranlariListele();
        System.out.print("Hangi restorana eklemek istiyorsunuz? (Restoran adını girin): ");
        String restoranAdi = scanner.nextLine();
        Restoran restoran = menu.getRestoran(restoranAdi);
//var olan restoranlardan biriini seçip nesneye atama
        if (restoran != null) {
            Urun yeniUrun = new Yemek(ad, fiyat, kategori);
            //girilen bilgilerle yeni yemek nesnesi oluştu
            restoran.urunEkle(yeniUrun);
            menu.urunEkle(yeniUrun);
            System.out.println("Ürün başarıyla eklendi.");
        } else {
            System.out.println("Belirtilen isimde bir restoran bulunamadı.");
        }
    }
    public static void urunSil(Menu menu) {
        System.out.println("Ürün Silme:");
        menu.urunleriListele();
        System.out.print("Hangi ürünü silmek istiyorsunuz? (Ürün adını girin): ");
        String urunAdi = scanner.nextLine();

        boolean urunSilindi = menu.urunSil(urunAdi);

        if (urunSilindi) {
            System.out.println("Ürün başarıyla silindi.");
        } else {
            System.out.println("Belirtilen isimde bir ürün bulunamadı.");
        }
    }
    public static void urunFiyatGuncelle(Menu menu) {
        System.out.println("Ürün Fiyatı Güncelleme");
        menu.urunleriListele();
        System.out.print("Hangi ürünün fiyatını güncellemek istiyorsunuz? (Ürün adını girin): ");
        String urunAdi = scanner.nextLine();
        Urun urun = menu.getUrunListesi(urunAdi);

        if (urun != null) {
            System.out.print("Yeni Fiyat: ");
            double yeniFiyat = scanner.nextDouble();
            urun.setFiyat(yeniFiyat);
            System.out.println("Ürün fiyatı başarıyla güncellendi.");
        } else {
            System.out.println("Belirtilen isimde bir ürün bulunamadı.");
        }
    }

}

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
public class Main {
    public static void main(String[] args) {

        Restoran restoran1 = new Restoran("Restoran 1", "Adres 1", "03742482347");
        Restoran restoran = new Restoran("Dominos", "Adres Dominos", "123456789");
        Restoran restoran2 = new Restoran("Pizza Lazza", "Adres Pizza Lazza", "987654321");

        Menu menu = new Menu();
        menu.restoranEkle(restoran1);
        menu.restoranEkle(restoran);
        menu.restoranEkle(restoran2);

        menu.urunEkle(new Hamburger("Cheeseburger", 190.0));
        menu.urunEkle(new Hamburger("Chicken Burger", 180.0));
        menu.urunEkle(new Pizza("Margherita", 250.0));
        menu.urunEkle(new Pizza("Pepperoni", 160.0));
        menu.urunEkle(new Doner("Et Döner", 150.0));
        menu.urunEkle(new Doner("Tavuk Döner", 120.0));

        restoran1.setMenu(menu);
        restoran.setMenu(menu);
        restoran2.setMenu(menu);

        List<Urun> menuRestoran1 = new ArrayList<>();
        menuRestoran1.add(new Hamburger("Cheeseburger", 190.0));
        menuRestoran1.add(new Pizza("Margherita", 250.0));
        restoran1.getMenu().addAll(menuRestoran1);

        List<Urun> menuRestoran2 = new ArrayList<>();
        menuRestoran2.add(new Hamburger("Chicken Burger", 180.0));
        menuRestoran2.add(new Pizza("Pepperoni", 160.0));
        restoran2.getMenu().addAll(menuRestoran2);

        List<Urun> menuDominos = new ArrayList<>();
        menuDominos.add(new Pizza("Margarita", 220.0));
        menuDominos.add(new Pizza("Vegetarian", 200.0));
        menuDominos.add(new Hamburger("Classic Burger", 180.0));
        restoran.getMenu().addAll(menuDominos);

        Admin admin = new Admin(menu);
        admin.adminIslemleriniYap();
    }
}

    

