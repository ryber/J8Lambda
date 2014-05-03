package examples;



import com.google.gson.Gson;
import com.spotify.ArtistRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompletableFuturesExample {

    List<String> artists = Arrays.asList(
            "gary.numan.json"
            ,"david.bowie.json"
            ,"kraftwerk.json"
            ,"information.society.json"
    );



    private ArtistRecord downloadSite(final String site) {
        try {
            InputStream url = CompletableFuturesExample.class.getResourceAsStream("/" + site);
            Gson gson = new Gson();
           return gson.fromJson(new InputStreamReader(url, "UTF-8"), ArtistRecord.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Double calculateRelevance(ArtistRecord doc) {
        return doc.getTracks()
                .stream()
                .mapToDouble(x -> Double.parseDouble(x.getPopularity()))
                .average()
                .getAsDouble();
    }

    private void run() throws Exception {
        List<CompletableFuture<Double>> relevanceFutures = artists.stream().
                map(site -> supplyAsync(() -> downloadSite(site))).
                map(docFuture -> docFuture.thenCompose(doc -> supplyAsync(() -> calculateRelevance(doc))))
                .collect(Collectors.<CompletableFuture<Double>>toList());

        CompletableFuture<List<Double>> doubles = sequence(relevanceFutures);
        CompletableFuture<OptionalDouble> maxRelevance = doubles.thenApply(relevances ->
                        relevances.stream().
                                mapToDouble(Double::valueOf).average()
        );

        System.out.println("maxRelevance.get().getAsDouble() = " + maxRelevance.get().getAsDouble());

    }

    private static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                        futures.stream().
                                map(future -> future.join()).
                                collect(Collectors.<T>toList())
        );
    }

    public static void main(String[] args) throws Exception{
        CompletableFuturesExample e = new CompletableFuturesExample();
        e.run();
    }

    public class ArtistSummary{
        public String name;
        public Double avgRating;
    }
}
