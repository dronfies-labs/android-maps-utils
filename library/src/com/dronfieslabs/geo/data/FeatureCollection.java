package com.dronfieslabs.geo.data;

import com.google.android.gms.maps.model.LatLngBounds;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Mathias on 09/05/2019.
 *
 * New class: Group features in a feature collection, to be able to parse GeoJson and Kml and decouple from Google Maps.
 */

public abstract class FeatureCollection {
    private final Set<Feature> mFeatures;
    private LatLngBounds mBoundingBox;

    public LatLngBounds getBoundingBox() {
        return mBoundingBox;
    }

    protected void setBoundingBox(LatLngBounds boundingBox) {
        mBoundingBox = boundingBox;
    }

    protected FeatureCollection() {
        mFeatures = new HashSet<Feature>();
    }

    protected void addFeature(Feature feature) {
        mFeatures.add(feature);
    }

    public Iterator<Feature> getFeatures() {
        return mFeatures.iterator();
    }

    @Override
    public String toString() {
        return "FeatureCollection{" +
                "mFeatures=" + mFeatures +
                ", mBoundingBox=" + mBoundingBox +
                '}';
    }
}
