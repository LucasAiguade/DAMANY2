import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class LucasAiguadé_p1_hash {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Escribe el nom del algoritme: ");
            String algoritme = sc.nextLine();
            System.out.print("Escribe un mensaje:");
            String missatge = sc.nextLine();
            System.out.print("Escriu el hash:");
            String hash = sc.nextLine().toUpperCase();
            MessageDigest md = MessageDigest.getInstance(algoritme);
            byte[] hashBytes = md.digest(missatge.getBytes());
            int len = hash.length();
            byte[] hashUsuarioBytes = new byte[len / 2];

            for (int i = 0; i < len; i += 2) {
                hashUsuarioBytes[i / 2] = (byte) ((Character.digit(hash.charAt(i), 16) << 4) + Character.digit(hash.charAt(i + 1), 16));
            }

            System.out.println("\n" + algoritme);
            System.out.println(missatge);

            System.out.print("Hash calculado: ");

            for (byte b : hashBytes) {
                System.out.printf("%02x", b);
            }

            System.out.println();
            System.out.println("Hash introducido: " + hash);

            String textSortida = MessageDigest.isEqual(hashBytes, hashUsuarioBytes) ? "VERIFICAT: El missatge NO ha estat modificat." : "ALERTA: El missatge HA ESTAT MODIFICAT (o el hash no coincideix).";
                System.out.println(textSortida);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Algoritme no suportat");
            }
        }
    }