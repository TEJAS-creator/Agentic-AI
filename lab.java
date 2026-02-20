/******************************************************************
==========================
LAB 1 – OOP CONCEPTS
==========================
******************************************************************/

/* ---------------------------------------------------------------
LAB 1 – EXERCISE 1
Book Class – Encapsulation
---------------------------------------------------------------- */

class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private boolean available;

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else
            System.out.println("Invalid price");
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
}

public class BookDemo {
    public static void main(String[] args) {
        Book b = new Book();
        b.setBookId(101);
        b.setTitle("Java Programming");
        b.setAuthor("James Gosling");
        b.setPrice(450);
        b.setAvailable(true);

        System.out.println("Book ID: " + b.getBookId());
        System.out.println("Title: " + b.getTitle());
        System.out.println("Author: " + b.getAuthor());
        System.out.println("Price: " + b.getPrice());
        System.out.println("Available: " + b.isAvailable());
    }
}


/* ---------------------------------------------------------------
LAB 1 – EXERCISE 2
Constructor Overloading + Inheritance
---------------------------------------------------------------- */

class Room {
    int roomNo;
    String roomType;
    double basePrice;

    Room(int roomNo, String roomType) {
        this.roomNo = roomNo;
        this.roomType = roomType;
    }

    Room(int roomNo, String roomType, double basePrice) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    void display() {
        System.out.println(roomNo + " " + roomType + " " + basePrice);
    }
}

class DeluxeRoom extends Room {
    boolean wifi;
    boolean breakfast;

    DeluxeRoom(int roomNo, String roomType, double basePrice,
               boolean wifi, boolean breakfast) {
        super(roomNo, roomType, basePrice);
        this.wifi = wifi;
        this.breakfast = breakfast;
    }

    void display() {
        super.display();
        System.out.println("WiFi: " + wifi + " Breakfast: " + breakfast);
    }
}

class RoomDemo {
    public static void main(String[] args) {
        Room r1 = new Room(101, "Standard", 2000);
        DeluxeRoom r2 = new DeluxeRoom(102, "Deluxe", 3500, true, true);

        r1.display();
        r2.display();
    }
}


/* ---------------------------------------------------------------
LAB 1 – EXERCISE 3
Runtime Polymorphism – Room Tariff
---------------------------------------------------------------- */

class RoomTariff {
    int roomNo;
    double baseTariff;

    RoomTariff(int roomNo, double baseTariff) {
        this.roomNo = roomNo;
        this.baseTariff = baseTariff;
    }

    double calculateTariff() {
        return baseTariff;
    }
}

class StandardRoomTariff extends RoomTariff {
    StandardRoomTariff(int r, double t) {
        super(r, t);
    }

    double calculateTariff() {
        return baseTariff + 500;
    }
}

class LuxuryRoomTariff extends RoomTariff {
    LuxuryRoomTariff(int r, double t) {
        super(r, t);
    }

    double calculateTariff() {
        return baseTariff + 2000;
    }
}

class TariffDemo {
    public static void main(String[] args) {
        RoomTariff r;

        r = new StandardRoomTariff(201, 3000);
        System.out.println("Standard Room Tariff: " + r.calculateTariff());

        r = new LuxuryRoomTariff(202, 3000);
        System.out.println("Luxury Room Tariff: " + r.calculateTariff());
    }
}


/* ---------------------------------------------------------------
LAB 1 – EXERCISE 4
Abstract Class + Interface
---------------------------------------------------------------- */

interface Amenities {
    void provideWifi();
    void provideBreakfast();
}

abstract class AbstractRoom {
    int roomNo;
    double basePrice;

    AbstractRoom(int roomNo, double basePrice) {
        this.roomNo = roomNo;
        this.basePrice = basePrice;
    }

    abstract double calculateTariff();

    void displayRoomDetails() {
        System.out.println("Room No: " + roomNo);
    }
}

class StandardRoomAbstract extends AbstractRoom implements Amenities {
    StandardRoomAbstract(int r, double p) {
        super(r, p);
    }

    double calculateTariff() {
        return basePrice + 500;
    }

    public void provideWifi() {
        System.out.println("WiFi Available");
    }

    public void provideBreakfast() {
        System.out.println("Breakfast Available");
    }
}

class AbstractRoomDemo {
    public static void main(String[] args) {
        AbstractRoom r = new StandardRoomAbstract(301, 2500);
        r.displayRoomDetails();
        System.out.println("Tariff: " + r.calculateTariff());
    }
}


/******************************************************************
==========================
LAB 2 – WRAPPER & ENUM
==========================
******************************************************************/

/* ---------------------------------------------------------------
LAB 2 – EXERCISE 1
Wrapper Class + Autoboxing
---------------------------------------------------------------- */

class BillingDemo {
    public static void main(String[] args) {
        int days = 3;
        double tariff = 2000;

        Integer d = days;     // Autoboxing
        Double t = tariff;

        double totalBill = d * t; // Unboxing
        System.out.println("Total Bill Amount: " + totalBill);
    }
}


/* ---------------------------------------------------------------
LAB 2 – EXERCISE 2
Enum – Room Type
---------------------------------------------------------------- */

enum RoomType {
    STANDARD(2000),
    DELUXE(3500),
    SUITE(5000);

    int pricePerDay;

    RoomType(int price) {
        pricePerDay = price;
    }

    int calculateCost(int days) {
        return pricePerDay * days;
    }
}

class EnumRoomDemo {
    public static void main(String[] args) {
        RoomType room = RoomType.DELUXE;
        System.out.println("Total Cost: " + room.calculateCost(3));
    }
}


/******************************************************************
==========================
LAB 3 – MULTITHREADING
==========================
******************************************************************/

/* ---------------------------------------------------------------
LAB 3 – EXERCISE 1
Hotel Room Service System
---------------------------------------------------------------- */

class Service extends Thread {
    String task;

    Service(String task) {
        this.task = task;
    }

    public void run() {
        System.out.println(task + " started");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println(task + " completed");
    }
}

class HotelServiceDemo {
    public static void main(String[] args) {
        new Service("Room Cleaning").start();
        new Service("Food Delivery").start();
        new Service("Maintenance").start();
    }
}


/* ---------------------------------------------------------------
LAB 3 – EXERCISE 2
Online Order Processing System
---------------------------------------------------------------- */

class Order extends Thread {
    String orderName;

    Order(String orderName) {
        this.orderName = orderName;
    }

    public void run() {
        System.out.println(orderName + " processing");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        System.out.println(orderName + " completed");
    }
}

class OrderProcessingDemo {
    public static void main(String[] args) {
        new Order("Order 1").start();
        new Order("Order 2").start();
        new Order("Order 3").start();
    }
}


/******************************************************************
==========================
LAB 4 – SYNCHRONIZATION
==========================
******************************************************************/

/* ---------------------------------------------------------------
LAB 4 – EXERCISE 1
Synchronized Room Booking System
---------------------------------------------------------------- */

class Hotel {
    int availableRooms = 1;

    synchronized void bookRoom(String customerName) {
        if (availableRooms > 0) {
            System.out.println(customerName + " booked a room");
            availableRooms--;
        } else {
            System.out.println(customerName + " waiting for room");
        }
    }
}

class Customer extends Thread {
    Hotel hotel;
    String name;

    Customer(Hotel hotel, String name) {
        this.hotel = hotel;
        this.name = name;
    }

    public void run() {
        hotel.bookRoom(name);
    }
}

class SyncBookingDemo {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        new Customer(hotel, "Customer A").start();
        new Customer(hotel, "Customer B").start();
    }
}
