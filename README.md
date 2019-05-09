# Dronfies Labs Geo Utils

This library is the first open source library we're developing in [Dronfies Labs](www.dronfieslabs.com).
Although simple and heavily based on [android-maps-utils](https://github.com/googlemaps/android-maps-utils), it fixes what for us was a fundamental flaw: coupling with Google Maps API. In particular, this library allows us to:

* Parse GeoJSON and KML files and store them in a simple data structure.
* Calculate if a given position intersects a given geometry shape

We'll keep updating this library given on the particular needs of the in-house projects that depend on it.

## What has been adapted:

So far, GeoJson entities have been adapted and tested to work with PolyUtils methods. Support for KML files not yet fully adapted.

