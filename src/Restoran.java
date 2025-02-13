import java.util.ArrayList;
import java.util.List;

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
