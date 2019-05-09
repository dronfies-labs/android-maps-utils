package com.dronfieslabs.geo.data.geojson;

import com.google.android.gms.maps.model.LatLngBounds;
import com.dronfieslabs.geo.data.Feature;
import com.dronfieslabs.geo.data.Geometry;

import java.util.HashMap;

/**
 * A GeoJsonFeature has a geometry, bounding box, id and set of properties. Styles are also stored
 * in this class.
 */
public class GeoJsonFeature extends Feature {

    private final String mId;

    private final LatLngBounds mBoundingBox;

    /**
     * Creates a new GeoJsonFeature object
     * @param geometry    type of geometry to assign to the feature
     * @param id          common identifier of the feature
     * @param properties  hash map of containing properties related to the feature
     * @param boundingBox bounding box of the feature
     */
   public GeoJsonFeature(Geometry geometry, String id,
                         HashMap<String, String> properties, LatLngBounds boundingBox) {
        super(geometry, id, properties);
        mId = id;
        mBoundingBox = boundingBox;
    }

    /**
     * Store a new property key and value
     *
     * @param property      key of the property to store
     * @param propertyValue value of the property to store
     * @return previous value with the same key, otherwise null if the key didn't exist
     */
    public String setProperty(String property, String propertyValue) {
        return super.setProperty(property, propertyValue);
    }


    /**
     * Removes a given property
     *
     * @param property key of the property to remove
     * @return value of the removed property or null if there was no corresponding key
     */
    public String removeProperty(String property) {
        return super.removeProperty(property);
    }


   /**
     * Sets the stored Geometry and redraws it on the layer if it has already been added
     *
     * @param geometry Geometry to set
     */
    public void setGeometry(Geometry geometry) {
        super.setGeometry(geometry);
        setChanged();
        notifyObservers();
    }

    /**
     * Gets the array containing the coordinates of the bounding box for the feature. If
     * the feature did not have a bounding box then null will be returned.
     *
     * @return LatLngBounds containing bounding box of the feature, null if no bounding box
     */
    public LatLngBounds getBoundingBox() {
        return mBoundingBox;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Feature{");
        sb.append("\n bounding box=").append(mBoundingBox);
        sb.append(",\n geometry=").append(getGeometry());
        sb.append(",\n id=").append(mId);
        sb.append(",\n properties=").append(getProperties());
        sb.append("\n}\n");
        return sb.toString();
    }

}
