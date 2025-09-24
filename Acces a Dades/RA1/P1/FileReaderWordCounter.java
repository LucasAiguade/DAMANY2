import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class FileReaderWordCounter {
    public static void main(String[] args) throws IOException {

        String rutaArxiu = "arxiu.txt";

        try {
            FileWriter fw = new FileWriter(rutaArxiu);
            String text = """
                Carlos I
                    A              e
                hola     banan        a                  
                """; 
            fw.write(text);
            fw.close(); 
        } catch (IOException e) {
            System.out.println("⚠️ Error d'entrada/sortida: " + e.getMessage());

        } catch(SecurityException e ){
            System.out.println("⚠️ No tens permisos per accedir al fitxer");
        } 

        try {
            FileReader fr = new FileReader(rutaArxiu);
            int c;
            int totalDeCaracteres = 0;
            int totalDeParaules = 0;
            boolean dintreDePalabra = false;

            HashMap<Character, Integer> caracterMesRepetitHashMap = new HashMap<>();

            while ((c = fr.read()) != -1) {
                System.out.print((char) c);

                if (c != '\n' && c != '\r') {
                    totalDeCaracteres++;
                }

                if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                    if (dintreDePalabra) {
                        totalDeParaules++;
                        dintreDePalabra = false;
                    }
                } else {
                    if (!dintreDePalabra) {
                        dintreDePalabra = true;
                    }
                }

                char lletraScanner = Character.toLowerCase((char) c);
                if (Character.isLetter(lletraScanner)) {caracterMesRepetitHashMap.put(lletraScanner,caracterMesRepetitHashMap.getOrDefault(lletraScanner, 0) + 1
                    );
                }
            }

            if (dintreDePalabra) {
                totalDeParaules++;
            }

            System.out.println("Total caràcteres: " + totalDeCaracteres);
            System.out.println("Total paraules: " + totalDeParaules);

            int valorMesGran = 0;
            char lletraMesApareguda = ' '; 

            for (char car : caracterMesRepetitHashMap.keySet()) {
                int valorActual = caracterMesRepetitHashMap.get(car);
                if (valorActual > valorMesGran) {
                    valorMesGran = valorActual;
                    lletraMesApareguda = car;
                }
            }
            System.out.println( "La lletra que més apareix és la " + lletraMesApareguda + " un número de " + valorMesGran + " vegada/es" );

        } catch (FileNotFoundException e) {
            System.out.println("URL del fitxer no trobada");
        } catch (UnsupportedEncodingException e){
            System.out.println("Codificació de caràcters no suportada");
        }
    }
}
