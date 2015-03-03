/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.gridsearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

/**
 *
 * @author sol
 */
public class GridSearchNGTest {

    public GridSearchNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    class toSearch {

        @Override
        public String toString() {
            return "toSearch [a=" + a + ", b=" + b + ", c=" + c + "]";
        }
        String a;
        int b;
        double c;
    }

    /**
     * Test of search method, of class GridSearch.
     */
    @org.testng.annotations.Test
    public void testSearch() throws Exception {
        System.out.println("search");
        
        Map<String, List> map = new HashMap<String, List>();
        map.put("a", Arrays.asList("h",  "j"));
        map.put("b", Arrays.asList(2, 4, 5, 7));
        map.put("c", Arrays.asList(1., 1.5, 2., 3., 5.));
        GridSearchBuilder gs = new GridSearchBuilder(map);
        toSearch ts = new toSearch();
        GridSearch search = gs.build(ts);
        while (search.hasNext()) {
            search.next();
            System.out.printf("ts %d / %d %s\n", search.getSearchIndex(), search.getGridSize(), ts);
        }
    }

}
