package org.agenticai.aiastool.geo;

import java.util.List;

public record GeoResults(List<GeoResult> results) {

    public GeoResult getFirst() {
        return results.get(0);
    }

}
