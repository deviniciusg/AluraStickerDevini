import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Key;
import java.util.List;
import java.util.Map;

public class App {
    private static JsonParser jsonParser;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os filmes
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();  
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        // pegar os dados que interessam (título, poster, rank)
        var parse = new JsonParser();  
        List<Map<String, String>> listaDeFilmes = parse.parse(body);
        System.out.println(listaDeFilmes.size());
        
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("titulo");

            InputStream inputStream = new URL(urlImagem).openStream();        
            String nomeArquivo = titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

           System.out.println(filme.get("title"));
           System.out.println();
            
        
           
        }
         
    }
}

// new FileInputStream(new File("entrada/filme-maior.jpg"));
// InputStream inputStream = new URL().openStream();
