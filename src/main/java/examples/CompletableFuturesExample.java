package examples;



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


    private void run() throws Exception {
        List<CompletableFuture<Double>> relevanceFutures = artists.stream().
                map(site -> supplyAsync(() -> SpotifyUtils.downloadSite(site))).
                map(docFuture -> docFuture.thenCompose(doc -> supplyAsync(() -> SpotifyUtils.calculateRelevance(doc))))
                .collect(Collectors.<CompletableFuture<Double>>toList());

        CompletableFuture<OptionalDouble> doubles = sequence(relevanceFutures)
                .thenApply(relevances ->
                        relevances.stream().
                                mapToDouble(Double::valueOf).average()
        );

        System.out.println("maxRelevance.get().getAsDouble() = " + doubles.get().getAsDouble());

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
}
