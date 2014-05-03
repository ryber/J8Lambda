package examples;


import com.spotify.ArtistRecord;

import java.util.stream.Stream;

public class ParallelsExample {
    Stream<String> artists = Stream.of(
            "gary.numan.json"
            , "david.bowie.json"
            , "kraftwerk.json"
            , "information.society.json"
    );


    private void run() {
         Double result = artists.parallel()
                 .map(fileName -> SpotifyUtils.downloadSite(fileName))
                 .mapToDouble(artist -> SpotifyUtils.calculateRelevance(artist))
                 .average().getAsDouble();

        System.out.println(result);
    }


    public static void main(String[] args){
        ParallelsExample e = new ParallelsExample();
        e.run();
    }
}
