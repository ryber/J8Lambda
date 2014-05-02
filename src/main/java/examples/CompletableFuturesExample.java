package examples;



import org.w3c.dom.Document;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFuturesExample {
    private String downloadSite(final String site) {
        return "";
    }

    ExecutorService executor = Executors.newFixedThreadPool(4);

    List<String> topSites = Arrays.asList(
            "www.google.com", "www.youtube.com", "www.yahoo.com", "www.msn.com"
    );

    List<CompletableFuture<Double>> relevanceFutures = topSites.stream().
            map(site -> CompletableFuture.supplyAsync(() -> downloadSite(site), executor)).
            map(contentFuture -> contentFuture.thenApply(this::parse)).
            map(docFuture -> docFuture.thenCompose(this::calculateRelevance)).
            collect(Collectors.<CompletableFuture<Double>>toList());

    private CompletableFuture<Double> calculateRelevance(Document doc) {
        return null;
    }

    private Document parse(String xml){
        return null;
    }
}
