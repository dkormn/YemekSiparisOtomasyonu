import java.util.Scanner;

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