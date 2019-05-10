package com.dronfieslabs.geo.data.kml;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.dronfieslabs.geo.data.Feature;
import com.dronfieslabs.geo.data.Geometry;

import java.util.HashMap;

/**
 * Represents a placemark which is either a {@link KmlPoint},
 * {@link
 * KmlLineString}, {@link KmlPolygon} or a
 * {@link KmlMultiGeometry}. Stores the properties and styles of the
 * place.
 */
public class KmlPlacemark extends Feature{

    private final String mStyle;

    /**
     * Creates a new KmlPlacemark object
     *
     * @param geometry   geometry object to store
     * @param style      style id to store
     * @param properties properties hashmap to store
     */
    public KmlPlacemark(Geometry geometry, String style,
                        HashMap<String, String> properties) {
        super(geometry, style, properties);
        mStyle = style;
    }

    /**
     * Gets the style id associated with the basic_placemark
     *
     * @return style id
     */
    public String getStyleId() {
        return super.getId();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Placemark").append("{");
        sb.append("\n style id=").append(mStyle);
        sb.append("\n}\n");
        return sb.toString();
    }
}
