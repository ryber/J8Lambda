package examples;


import com.google.gson.Gson;
import com.spotify.ArtistRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ParallelsExample {
    Stream<String> artists = Stream.of(
            "gary.numan.json"
            , "david.bowie.json"
            , "kraftwerk.json"
            , "information.society.json"
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

    private void run() {
         Double result = artists.parallel()
                 .map(fileName -> downloadSite(fileName))
                 .mapToDouble(artist -> calculateRelevance(artist))
                 .average().getAsDouble();

        System.out.println(result);
    }

    private double calculateRelevance(ArtistRecord doc) {
        return doc.getTracks()
                .stream()
                .mapToDouble(x -> Double.parseDouble(x.getPopularity()))
                .average()
                .getAsDouble();
    }

    public static void main(String[] args){
        ParallelsExample e = new ParallelsExample();
        e.run();
    }
}
