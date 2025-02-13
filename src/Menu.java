import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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