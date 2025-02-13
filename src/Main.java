import java.util.*;

















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

    

