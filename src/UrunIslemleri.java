import java.util.Scanner;

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