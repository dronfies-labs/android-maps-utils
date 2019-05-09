package com.dronfieslabs.geo.data.geojson;

import com.dronfieslabs.geo.data.FeatureCollection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GeoJsonFeatureCollection extends FeatureCollection {

    /**
     * Create a GeoJsonFeatureCollection from a string.
     * @param jsonAsString Collection of features in a string.
     */
    public GeoJsonFeatureCollection(String jsonAsString) {
        JSONObject geoJsonFile = stringToJsonObject(jsonAsString);
        if (geoJsonFile == null) {
            throw new IllegalArgumentException("GeoJSON file cannot be null");
        }

        GeoJsonParser parser = new GeoJsonParser(geoJsonFile);
        // Assign GeoJSON bounding box for FeatureCollection
        this.setBoundingBox(parser.getBoundingBox());

        for (GeoJsonFeature feature : parser.getFeatures()) {
            this.addFeature(feature);
        }
    }

    @Deprecated
    public GeoJsonFeatureCollection(JSONObject geoJsonFile) {
        if (geoJsonFile == null) {
            throw new IllegalArgumentException("GeoJSON file cannot be null");
        }
        GeoJsonParser parser = new GeoJsonParser(geoJsonFile);
        // Assign GeoJSON bounding box for FeatureCollection
        this.setBoundingBox(parser.getBoundingBox());

        for (GeoJsonFeature feature : parser.getFeatures()) {
            this.addFeature(feature);
        }
    }

    /**
     * Takes a character input stream and converts it into a JSONObject
     *
     * @param stream character input stream representing the GeoJSON file
     * @return JSONObject with the GeoJSON data
     * @throws IOException   if the file cannot be opened for read
     * @throws JSONException if the JSON file has poor structure
     */

    @Deprecated
    private static JSONObject createJsonFileObject(InputStream stream)
            throws IOException, JSONException {
        String line;
        StringBuilder result = new StringBuilder();
        // Reads from stream
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // Read each line of the GeoJSON file into a string
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        // Converts the result string into a JSONObject
        return new JSONObject(result.toString());
    }

    private static JSONObject stringToJsonObject(String jsonAsString) {
        JSONObject geoJsonFile = null;
        try {
            geoJsonFile = new JSONObject(jsonAsString);
        } catch (JSONException e) {
            if (e.getCause() != null) {
                throw new IllegalArgumentException("Invalid string supplied: " + e.getMessage(), e.getCause());
            } else {
                throw new IllegalArgumentException("Invalid string supplied: " + e.getMessage());
            }
        }
        return geoJsonFile;
    }

}
