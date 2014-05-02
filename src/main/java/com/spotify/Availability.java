
package com.spotify;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Availability {

    @Expose
    private String territories;

    public String getTerritories() {
        return territories;
    }

    public void setTerritories(String territories) {
        this.territories = territories;
    }

}
