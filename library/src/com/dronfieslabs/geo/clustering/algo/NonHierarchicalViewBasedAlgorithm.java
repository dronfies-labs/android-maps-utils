/*
 * Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dronfieslabs.geo.clustering.algo;

import com.dronfieslabs.geo.clustering.ClusterItem;
import com.dronfieslabs.geo.projection.SphericalMercatorProjection;
import com.dronfieslabs.geo.quadtree.PointQuadTree;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.dronfieslabs.geo.geometry.Bounds;
import com.dronfieslabs.geo.projection.Point;

import java.util.Collection;

/**
 * This algorithm works the same way as {@link NonHierarchicalDistanceBasedAlgorithm} but works, only in
 * visible area. It requires to be reclustered on camera movement because clustering is done only for visible area.
 * @param <T>
 */
public class NonHierarchicalViewBasedAlgorithm<T extends ClusterItem>
        extends NonHierarchicalDistanceBasedAlgorithm<T> implements ScreenBasedAlgorithm<T> {

    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1);

    private int mViewWidth;
    private int mViewHeight;

    private LatLng mMapCenter;

    public NonHierarchicalViewBasedAlgorithm(int screenWidth, int screenHeight) {
        mViewWidth = screenWidth;
        mViewHeight = screenHeight;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mMapCenter = cameraPosition.target;
    }

    @Override
    protected Collection<QuadItem<T>> getClusteringItems(PointQuadTree<QuadItem<T>> quadTree, int discreteZoom) {
        return quadTree.search(getVisibleBounds(discreteZoom));
    }

    @Override
    public boolean shouldReclusterOnMapMovement() {
        return true;
    }

    /**
     * Update view width and height in case map size was changed.
     * You need to recluster all the clusters, to update view state after view size changes.
     * @param width map width
     * @param height map height
     */
    public void updateViewSize(int width, int height) {
        mViewWidth = width;
        mViewHeight = height;
    }

    private Bounds getVisibleBounds(int zoom) {
        if (mMapCenter == null) {
            return new Bounds(0, 0, 0, 0);
        }

        Point p = PROJECTION.toPoint(mMapCenter);

        final double halfWidthSpan = mViewWidth / Math.pow(2, zoom) / 256 / 2;
        final double halfHeightSpan = mViewHeight / Math.pow(2, zoom) / 256 / 2;

        return new Bounds(
                p.x - halfWidthSpan, p.x + halfWidthSpan,
                p.y - halfHeightSpan, p.y + halfHeightSpan);
    }
}
