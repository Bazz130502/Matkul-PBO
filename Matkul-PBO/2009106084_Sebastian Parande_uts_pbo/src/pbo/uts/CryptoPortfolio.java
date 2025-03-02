package pbo.uts;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Kelas utama untuk Crypto
// Enkapsulasi: Menggunakan atribut private dengan getter dan setter
class Crypto {
    private String name;
    private double balance;
    private double price; // Harga dalam USD

    // Konstruktor: Untuk inisialisasi aset crypto
    public Crypto(String name, double price) {
        this.name = name;
        this.price = price;
        this.balance = 0.0;
    }

    // Getter dan Setter
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Menambah saldo crypto
    public void buy(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Berhasil membeli " + amount + " " + name);
        } else {
            System.out.println("⚠️ Jumlah pembelian harus lebih dari 0!");
        }
    }

    // Mengurangi saldo crypto
    public void sell(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Berhasil menjual " + amount + " " + name);
        } else {
            System.out.println("⚠️ Saldo tidak mencukupi atau jumlah tidak valid.");
        }
    }

    // Menghitung nilai aset dalam USD
    public double getTotalValue() {
        return balance * price;
    }
}

// Kelas utama untuk manajemen portofolio
public class CryptoPortfolio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menyimpan data crypto dalam HashMap
        Map<Integer, Crypto> cryptoMap = new HashMap<>();
        cryptoMap.put(1, new Crypto("Bitcoin (BTC)", 95000));
        cryptoMap.put(2, new Crypto("Ethereum (ETH)", 2500));
        cryptoMap.put(3, new Crypto("Solana (SOL)", 170));
        cryptoMap.put(4, new Crypto("Toncoin (TON)", 5));
        cryptoMap.put(5, new Crypto("XRP (XRP)", 3));

        // Perulangan(Looping): Agar program terus berjalan sampai pengguna keluar. Disini 
        // Disini menggunakan looping (do-while) untuk menu utama.
        int choice;
        do {
            // Menampilkan menu utama
            System.out.println("\n=== Portfolio Crypto ===");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Beli Crypto");
            System.out.println("3. Jual Crypto");
            System.out.println("4. Lihat Total Nilai Portofolio (USD)");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            choice = scanner.nextInt();

            // Switch-case: Digunakan dalam menu utama untuk berbagai operasi
            switch (choice) {
                case 1:
                    System.out.println("\n=== Saldo Crypto ===");
                    for (Map.Entry<Integer, Crypto> entry : cryptoMap.entrySet()) {
                        Crypto crypto = entry.getValue();
                        System.out.println(crypto.getName() + " - " + crypto.getBalance() + " unit");
                    }
                    break;

                case 2:
                    System.out.println("\n=== Pilih Crypto untuk Dibeli ===");
                    for (Map.Entry<Integer, Crypto> entry : cryptoMap.entrySet()) {
                        System.out.println(entry.getKey() + ". " + entry.getValue().getName());
                    }
                    System.out.print("Masukkan nomor crypto: ");
                    int buyChoice = scanner.nextInt();
                    if (cryptoMap.containsKey(buyChoice)) {
                        System.out.print("Masukkan jumlah yang ingin dibeli: ");
                        double amount = scanner.nextDouble();
                        cryptoMap.get(buyChoice).buy(amount);
                    } else {
                        System.out.println("⚠️ Pilihan tidak valid!");
                    }
                    break;

                case 3:
                    System.out.println("\n=== Pilih Crypto untuk Dijual ===");
                    for (Map.Entry<Integer, Crypto> entry : cryptoMap.entrySet()) {
                        System.out.println(entry.getKey() + ". " + entry.getValue().getName());
                    }
                    System.out.print("Masukkan nomor crypto: ");
                    int sellChoice = scanner.nextInt();
                    if (cryptoMap.containsKey(sellChoice)) {
                        System.out.print("Masukkan jumlah yang ingin dijual: ");
                        double amount = scanner.nextDouble();
                        cryptoMap.get(sellChoice).sell(amount);
                    } else {
                        System.out.println("⚠️ Pilihan tidak valid!");
                    }
                    break;

                case 4:
                    System.out.println("\n=== Total Nilai Portofolio ===");
                    double totalValue = 0;
                    for (Crypto crypto : cryptoMap.values()) {
                        totalValue += crypto.getTotalValue();
                    }
                    System.out.println("Total nilai portofolio dalam USD: $" + totalValue);
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan aplikasi Crypto Portfolio!");
                    break;

                default:
                    System.out.println("⚠️ Pilihan tidak valid! Silakan coba lagi.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
