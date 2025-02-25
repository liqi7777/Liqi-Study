package com.jz.test.redistest.KDTree;

import com.jz.test.redistest.KDTree.kdtree.KDTree;
import com.jz.test.redistest.KDTree.kdtree.exception.KeyDuplicateException;
import com.jz.test.redistest.KDTree.kdtree.exception.KeyMissingException;
import com.jz.test.redistest.KDTree.kdtree.exception.KeySizeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests based on kddemo.java examples
 */
@Slf4j
public class KDTreeInnerTest {

    private double[] A = {2, 5};
    private double[] B = {1, 1};
    private double[] C = {3, 9};

    private KDTree<String> tree;


    @Before
    public void setUp() {
        final int DIMENSION = 2; //X and Y
        tree = new KDTree<String>(DIMENSION);
    }

    @Test
    public void testInsertion() {
        insertValues();
        Assert.assertEquals(3, tree.size());
    }

    public void testSearchValue() {

        insertValues();

        try {
            Assert.assertEquals("B", tree.search(B));
        } catch (KeySizeException e) {
            Assert.fail();
        }

    }

    @Test
    public void testSearchNearest() {

        insertValues();

        double[] T = {1, 10};

        try {
            Assert.assertEquals("C", tree.nearest(T));
        } catch (KeySizeException e) {
            Assert.fail();
        }
    }

    @Test
    public void testSearchByRadius() {
        insertValues();

        double[] T = {20, 10};

        float radius = 18;

        try {
            Assert.assertEquals("C", tree.nearestEuclidean(T, radius).get(0));
        } catch (KeySizeException e) {
            Assert.fail();
        }

    }

    @Test
    public void testRemove() {

        insertValues();

        try {
            tree.delete(C);
        } catch (KeySizeException e) {
            Assert.fail();
        } catch (KeyMissingException e) {
            Assert.fail();
        }

        double[] T = {1, 10};

        try {
            Assert.assertEquals("A", tree.nearest(T));
        } catch (KeySizeException e) {
            Assert.fail();
        }
    }

    @Test
    public void testDuplicates() {
        double[] key = new double[]{0, 0};

        try {
            tree.insert(key, "value1");
            tree.insert(key, "value2");
            Assert.fail();
        } catch (KeyDuplicateException e) {
        }
    }

    @Test
    public void testSizeMismatch() {
        double[] key = new double[]{0, 0, 0};

        try {
            tree.insert(key, "value");
            Assert.fail();
        } catch (KeySizeException e) {

        }
    }

    private void insertValues() {
        try {
            tree.insert(A, new String("A"));
            tree.insert(B, new String("B"));
            tree.insert(C, new String("C"));
        } catch (Exception e) {
            Assert.fail();
        }

    }

}
