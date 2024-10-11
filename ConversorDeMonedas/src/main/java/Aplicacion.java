import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacion
{
    public static String conversion(String monedaInicial, String monedaFinal, double cantidad)
    {
        if (cantidad < 0) {
            throw new NumberFormatException();
        }

        String direccion = "https://v6.exchangerate-api.com/v6/6ab715630644058c1e3a60b0/pair/"
                + monedaInicial +"/" + monedaFinal + "/" + cantidad;

        String resultado = "";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try
        {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setPrettyPrinting()
                    .create();

            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            Double resultadoBruto = Double.valueOf(gson.fromJson(json, ResultadoConversion.class).conversionResult());

            resultado  = decimalFormat.format(resultadoBruto);
        }
        catch(IOException | InterruptedException e)
        {
            System.out.println("Error al recuperar los datos:");
            System.out.println(e.getMessage());
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Petición mal formulada:");
            e.getMessage();
        }

        return resultado;
    }

    public static String opciones()
    {
        String respuesta = "";
        Scanner sc = new Scanner(System.in);
        int numero = 0;

        while(numero < 1 || numero > 20) {
            System.out.println("""
                    Seleccione el número de la moneda correspondiente
                    
                    1. Dólar estadounidense (USD)
                    2. Euro (EUR)
                    3. Yen japonés (JPY)
                    4. Libra esterlina (GBP)
                    5. Franco suizo (CHF)
                    6. Dólar canadiense (CAD)
                    7. Dólar australiano (AUD)
                    8. Renminbi chino (CNY)
                    9. Dólar de Hong Kong (HKD)
                    10. Peso mexicano (MXN)
                    11. Corona noruega (NOK)
                    12. Dólar de Singapur (SGD)
                    13. Won surcoreano (KRW)
                    14. Rupia india (INR)
                    15. Rublo ruso (RUB)
                    16. Real brasileño (BRL)
                    17. Rand sudafricano (ZAR)
                    18. Corona sueca (SEK)
                    19. Peso colombiano (COP)
                    20. Dólar neozelandés (NZD)
                    """);

            try
            {
                numero = Integer.valueOf(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                numero = 0;
            }

            switch (numero) {
                case 1: {
                    respuesta = "USD";
                    break;
                }
                case 2: {
                    respuesta = "EUR";
                    break;
                }
                case 3: {
                    respuesta = "JPY";
                    break;
                }
                case 4: {
                    respuesta = "GBP";
                    break;
                }
                case 5: {
                    respuesta = "CHF";
                    break;
                }
                case 6: {
                    respuesta = "CAD";
                    break;
                }
                case 7: {
                    respuesta = "AUD";
                    break;
                }
                case 8: {
                    respuesta = "CNY";
                    break;
                }
                case 9: {
                    respuesta = "HKD";
                    break;
                }
                case 10: {
                    respuesta = "MXN";
                    break;
                }
                case 11: {
                    respuesta = "NOK";
                    break;
                }
                case 12: {
                    respuesta = "SGD";
                    break;
                }
                case 13: {
                    respuesta = "KRW";
                    break;
                }
                case 14: {
                    respuesta = "INR";
                    break;
                }
                case 15: {
                    respuesta = "RUB";
                    break;
                }
                case 16: {
                    respuesta = "BRL";
                    break;
                }
                case 17: {
                    respuesta = "ZAR";
                    break;
                }
                case 18: {
                    respuesta = "SEK";
                    break;
                }
                case 19: {
                    respuesta = "COP";
                    break;
                }
                case 20: {
                    respuesta = "NZD";
                    break;
                }
                default: {
                    System.out.println("Opcion no válida");
                    break;
                }
            }
        }

        return respuesta;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ciclo:
        while(true)
        {
            System.out.println("-+-+-+-+- Conversor De Monedas -+-+-+-+-");
            System.out.println("""
                    1. Convertir monedas
                    2. Ver historial
                    3. Salir
                    """);

            int numero = 0;
            try
            {
                numero = Integer.valueOf(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                numero = 0;
            }

            switch (numero)
            {
                case 0:
                {
                    System.out.println("Opcion incorrecta");
                }
                case 1:
                {
                    System.out.println("Seleccione la moneda inicial:");
                    String monedaInicial = opciones();

                    System.out.println();

                    System.out.println("Seleccione la moneda de destino:");
                    String monedaFinal = opciones();

                    System.out.println();

                    System.out.println("Escriba la cantidad a convertir:");

                    try
                    {
                        double cantidad = Double.valueOf(sc.nextLine());

                        String valor = conversion(monedaInicial, monedaFinal, cantidad);

                        System.out.println("El valor es " + valor);

                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                                .ofPattern("yyyy/MM/dd HH:mm:ss");
                        String fecha = LocalDateTime.now().format(dateTimeFormatter);

                        File archivo = new File("src/docs/historial.txt");

                        try(FileWriter fw = new FileWriter(archivo, true))
                        {
                            fw.write(String.format("Origen: %s, Destino: %s, Cantidad: %.2f, Fecha: %s"
                                    , monedaInicial, monedaFinal, cantidad, fecha));
                        }
                        catch (IOException e)
                        {
                            System.out.println("Error al guardar la conversión");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Cantidad no válida");
                    }
                    break;
                }
                case 2:
                {
                    File archivo = new File("src/docs/historial.txt");

                    try (FileReader fr = new FileReader(archivo);
                         BufferedReader br = new BufferedReader(fr)) {

                        String linea;
                        while ((linea = br.readLine()) != null) {
                            System.out.println(linea);
                        }
                    }
                    catch (IOException e)
                    {
                        System.out.println("No se encontró el registro");
                    }
                    break;
                }
                case 3:
                {
                    System.out.println("Gracias por usar el convertidor :)");
                    break ciclo;
                }
                default:
                {
                    System.out.println("Opción no válida");
                    break;
                }
            }

            System.out.println();
        }

        sc.close();
    }
}
