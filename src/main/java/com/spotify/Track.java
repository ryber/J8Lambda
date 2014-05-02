
package com.spotify;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Track {

    @Expose
    private Album album;
    @Expose
    private String name;
    @Expose
    private String popularity;
    @SerializedName("external-ids")
    @Expose
    private List<External_id> external_ids = new ArrayList<External_id>();
    @Expose
    private Double length;
    @Expose
    private String href;
    @Expose
    private List<Artist> artists = new ArrayList<Artist>();
    @SerializedName("track-number")
    @Expose
    private String track_number;
    @Expose
    private Boolean explicit;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public List<External_id> getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(List<External_id> external_ids) {
        this.external_ids = external_ids;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getTrack_number() {
        return track_number;
    }

    public void setTrack_number(String track_number) {
        this.track_number = track_number;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

}
