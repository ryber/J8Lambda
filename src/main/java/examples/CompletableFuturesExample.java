package examples;



import com.google.gson.Gson;
import com.spotify.ArtistRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompletableFuturesExample {
    ExecutorService executor = Executors.newFixedThreadPool(4);

    List<String> artists = Arrays.asList(
            "gary.numan.json"
            //, "Be Bop Deluxe", "Iron Maiden", "KMFDM"
    );



    private ArtistRecord downloadSite(final String site) {
        try {
            InputStream url = CompletableFuturesExample.class.getResourceAsStream("resources/" + site);
            Gson gson = new Gson();
           return gson.fromJson(new InputStreamReader(url, "UTF-8"), ArtistRecord.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Double calculateRelevance(ArtistRecord doc) {
        return doc.getTracks().stream().mapToDouble(x -> Double.parseDouble(x.getPopularity())).average().getAsDouble();
    }

    private void run() {
        List<CompletableFuture<Double>> relevanceFutures = artists.stream().
                map(site -> supplyAsync(() -> downloadSite(site), executor)).
                map(docFuture -> docFuture.thenCompose(doc -> supplyAsync(() -> calculateRelevance(doc)))).
                collect(Collectors.<CompletableFuture<Double>>toList());

        System.out.println(relevanceFutures);
    }

    public static void main(String[] args){
        CompletableFuturesExample e = new CompletableFuturesExample();
        e.run();
    }

    public class ArtistSummary{
        public String name;
        public Double avgRating;
    }
}
