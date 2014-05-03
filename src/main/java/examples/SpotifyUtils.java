package examples;


import com.google.gson.Gson;
import com.spotify.ArtistRecord;

import java.io.InputStream;
import java.io.InputStreamReader;

public class SpotifyUtils {
    public static ArtistRecord downloadSite(final String site) {
        try {
            InputStream url = CompletableFuturesExample.class.getResourceAsStream("/" + site);
            Gson gson = new Gson();
            return gson.fromJson(new InputStreamReader(url, "UTF-8"), ArtistRecord.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Double calculateRelevance(ArtistRecord doc) {
        return doc.getTracks()
                .stream()
                .mapToDouble(x -> Double.parseDouble(x.getPopularity()))
                .average()
                .getAsDouble();
    }
}
