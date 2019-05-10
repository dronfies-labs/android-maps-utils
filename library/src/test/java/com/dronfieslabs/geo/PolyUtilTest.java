package com.dronfieslabs;

import android.provider.ContactsContract;

import com.dronfieslabs.geo.PolyUtil;
import com.dronfieslabs.geo.data.DataPolygon;
import com.dronfieslabs.geo.data.geojson.GeoJsonPolygon;
import com.google.android.gms.maps.model.LatLng;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mathias on 09/05/2019.
 */
public class PolyUtilTest {
    private DataPolygon polygon;
    private DataPolygon polygon2;

    @Before
    public void setUp() throws Exception {
        /*
        Case 1. Estadio Centenario.
         */

        LatLng a = new LatLng(-34.892153,-56.158485);
        LatLng b = new LatLng(-34.894714,-56.159333);
        LatLng c = new LatLng(-34.895532,-56.159193);
        LatLng d = new LatLng(-34.895664,-56.158989);
        LatLng e = new LatLng(-34.896113,-56.15886);
        LatLng f = new LatLng(-34.896263,-56.159021);
        LatLng g = new LatLng(-34.897037,-56.158882);
        LatLng h = new LatLng(-34.8987,-56.156532);
        LatLng i = new LatLng(-34.898339,-56.152466);
        LatLng j = new LatLng(-34.896852,-56.151511);
        LatLng k = new LatLng(-34.895893,-56.151468);
        LatLng l = new LatLng(-34.895559,-56.151211);
        LatLng m = new LatLng(-34.893948,-56.15134);
        LatLng n = new LatLng(-34.893667,-56.151486);
        LatLng o = new LatLng(-34.891546,-56.155574);

        ArrayList<LatLng> parqueBattle = new ArrayList<>();

        parqueBattle.add(a);
        parqueBattle.add(b);
        parqueBattle.add(c);
        parqueBattle.add(d);
        parqueBattle.add(e);
        parqueBattle.add(f);
        parqueBattle.add(g);
        parqueBattle.add(h);
        parqueBattle.add(i);
        parqueBattle.add(j);
        parqueBattle.add(k);
        parqueBattle.add(l);
        parqueBattle.add(m);
        parqueBattle.add(n);
        parqueBattle.add(o);

        LatLng za = new LatLng( -34.894785,-56.151363);
        LatLng zb = new LatLng(-34.894363,-56.151385);
        LatLng zc = new LatLng(-34.893887,-56.151589);
        LatLng zd = new LatLng(-34.893403,-56.152061);
        LatLng ze = new LatLng(-34.893289,-56.152426);
        LatLng zf = new LatLng(-34.893315,-56.153455);
        LatLng zg = new LatLng(-34.894081,-56.154346);
        LatLng zh = new LatLng(-34.895753,-56.153005);

        ArrayList<LatLng> estadioHalf = new ArrayList<>();
        estadioHalf.add(za);
        estadioHalf.add(zb);
        estadioHalf.add(zc);
        estadioHalf.add(zd);
        estadioHalf.add(ze);
        estadioHalf.add(zf);
        estadioHalf.add(zg);
        estadioHalf.add(zh);

        ArrayList<List<LatLng>> listOfLists = new ArrayList<>();
        listOfLists.add(parqueBattle);
        listOfLists.add(estadioHalf);

        polygon = new GeoJsonPolygon(listOfLists);

        LatLng a1 = new LatLng(-1.023442,2.134739);
        LatLng a2 = new LatLng(2.601205,2.068756);
        LatLng a3 = new LatLng(2.864577,-4.830658);
        LatLng a4 = new LatLng(-4.512369,-5.18222);

        ArrayList<LatLng> oceanOuter = new ArrayList<>();
        oceanOuter.add(a1);
        oceanOuter.add(a2);
        oceanOuter.add(a3);
        oceanOuter.add(a4);

        LatLng b1 = new LatLng(0.976076,-2.853119);
        LatLng b2 = new LatLng(-1.836063,-2.984955);
        LatLng b3 = new LatLng(0.009326,0.882233);
        LatLng b4 = new LatLng(1.547224,0.882233);

        ArrayList<LatLng> oceanInner = new ArrayList<>();
        oceanInner.add(b1);
        oceanInner.add(b2);
        oceanInner.add(b3);
        oceanInner.add(b4);

        ArrayList<List<LatLng>> obstOcean = new ArrayList<>();
        obstOcean.add(oceanOuter);
        obstOcean.add(oceanInner);

        polygon2 =  new GeoJsonPolygon(obstOcean);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPointIsInPolygon() throws Exception {
        LatLng point = new LatLng(-34.893986,-56.156718); // This point is inside the outer boundary and outside the hole.
        Assert.assertEquals(true, PolyUtil.pointIsInPolygon(point, polygon, true));

        LatLng pointInStadium = new LatLng(-34.894496,-56.15278); // This point is inside the stadium i.e. the hole.
        Assert.assertEquals(false, PolyUtil.pointIsInPolygon(pointInStadium, polygon, true));

        LatLng pointBetweenEstadioAndOutside = new LatLng(-34.892948,-56.153483);
        Assert.assertEquals(true, PolyUtil.pointIsInPolygon(pointBetweenEstadioAndOutside, polygon, true));
    }

    @Test
    public void testPointInEdge() {
        LatLng pointInStadium = new LatLng(-34.894496,-56.15278); // This point is inside the stadium i.e. the hole.
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointInStadium, polygon, true, 1));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointInStadium, polygon, true, 2));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointInStadium, polygon, true, 5));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointInStadium, polygon, true, 10));

        LatLng pointBetweenEstadioAndOutside = new LatLng(-34.892948,-56.153483);
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointBetweenEstadioAndOutside, polygon, true, 1));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointBetweenEstadioAndOutside, polygon, true, 2));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointBetweenEstadioAndOutside, polygon, true, 5));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(pointBetweenEstadioAndOutside, polygon, true, 10));
        Assert.assertEquals(true, PolyUtil.pointIsCloseToTheBorder(pointBetweenEstadioAndOutside, polygon, true, 50));

        LatLng point = new LatLng(-0.769385,1.811948);
        Assert.assertEquals(true, PolyUtil.pointIsInPolygon(point, polygon2, true));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(point, polygon2, false, 500));
        Assert.assertEquals(false, PolyUtil.pointIsCloseToTheBorder(point, polygon2, false, 10000));
        Assert.assertEquals(true, PolyUtil.pointIsCloseToTheBorder(point, polygon2, false, 50000));
        Assert.assertEquals(true, PolyUtil.pointIsCloseToTheBorder(point, polygon2, false, 100000));
    }

}