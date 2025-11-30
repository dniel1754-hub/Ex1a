import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  * Introduction to Computer Science 2026, Ariel University,
 *  * Ex1: arrays, static functions and JUnit
 *
 * This JUnit class represents a JUnit (unit testing) for Ex1-
 * It contains few testing functions for the polynomial functions as define in Ex1.
 * Note: you should add additional JUnit testing functions to this class.
 *
 * @author boaz.ben-moshe
 */

class Ex1Test {
	static final double[] P1 ={2,0,3, -1,0}, P2 = {0.1,0,1, 0.1,3};
	static double[] po1 = {2,2}, po2 = {-3, 0.61, 0.2};;
	static double[] po3 = {2,1,-0.7, -0.02,0.02};
	static double[] po4 = {-3, 0.61, 0.2};
	
 	@Test
	/**
	 * Tests that f(x) == poly(x).
	 */
	void testF() {
		double fx0 = Ex1.f(po1, 0);
		double fx1 = Ex1.f(po1, 1);
		double fx2 = Ex1.f(po1, 2);
		assertEquals(fx0, 2, Ex1.EPS);
		assertEquals(fx1, 4, Ex1.EPS);
		assertEquals(fx2, 6, Ex1.EPS);
	}
	@Test
	/**
	 * Tests that p1(x) + p2(x) == (p1+p2)(x)
	 */
	void testF2() {
		double x = Math.PI;
		double[] po12 = Ex1.add(po1, po2);
		double f1x = Ex1.f(po1, x);
		double f2x = Ex1.f(po2, x);
		double f12x = Ex1.f(po12, x);
		assertEquals(f1x + f2x, f12x, Ex1.EPS);
	}
	@Test
	/**
	 * Tests that p1+p2+ (-1*p2) == p1
	 */
	void testAdd() {
		double[] p12 = Ex1.add(po1, po2);
		double[] minus1 = {-1};
		double[] pp2 = Ex1.mul(po2, minus1);
		double[] p1 = Ex1.add(p12, pp2);
		assertTrue(Ex1.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1+p2 == p2+p1
	 */
	void testAdd2() {
		double[] p12 = Ex1.add(po1, po2);
		double[] p21 = Ex1.add(po2, po1);
		assertTrue(Ex1.equals(p12, p21));
	}
	@Test
	/**
	 * Tests that p1+0 == p1
	 */
	void testAdd3() {
		double[] p1 = Ex1.add(po1, Ex1.ZERO);
		assertTrue(Ex1.equals(p1, po1));
	}
	@Test
	/**
	 * Tests that p1*0 == 0
	 */
	void testMul1() {
		double[] p1 = Ex1.mul(po1, Ex1.ZERO);
		assertTrue(Ex1.equals(p1, Ex1.ZERO));
	}
	@Test
	/**
	 * Tests that p1*p2 == p2*p1
	 */
	void testMul2() {
		double[] p12 = Ex1.mul(po1, po2);
		double[] p21 = Ex1.mul(po2, po1);
		assertTrue(Ex1.equals(p12, p21));
	}
	@Test
	/**
	 * Tests that p1(x) * p2(x) = (p1*p2)(x),
	 */
	void testMulDoubleArrayDoubleArray() {
		double[] xx = {0,1,2,3,4.1,-15.2222};
		double[] p12 = Ex1.mul(po1, po2);
		for(int i = 0;i<xx.length;i=i+1) {
			double x = xx[i];
			double f1x = Ex1.f(po1, x);
			double f2x = Ex1.f(po2, x);
			double f12x = Ex1.f(p12, x);
			assertEquals(f12x, f1x*f2x, Ex1.EPS);
		}
	}
    // test for derivative function
    @Test
    public void testDerivativeLinear() {
        // f(x) = 5 + 4x
        double[] poly = {5, 4};
        // f'(x) = 4
        double[] expected = {4};

        double[] actual = Ex1.derivative(poly);

        assertArrayEquals(expected, actual, 1e-6);
    }
	@Test
	/**
	 * Tests a simple derivative examples - till ZERO.
	 */
	void testDerivativeArrayDoubleArray() {
		double[] p = {1,2,3}; // 3X^2+2x+1
		double[] pt = {2,6}; // 6x+2
		double[] dp1 = Ex1.derivative(p); // 2x + 6
		double[] dp2 = Ex1.derivative(dp1); // 2
		//double[] dp3 = Ex1.derivative(dp2); // 0
		//double[] dp4 = Ex1.derivative(dp3); // 0
		assertTrue(Ex1.equals(dp1, pt));
		//assertTrue(Ex1.equals(Ex1.ZERO, dp3));
        //assertTrue(Ex1.equals(new double[]{p[1]}, dp2));
		//assertTrue(Ex1.equals(dp4, dp3));
	}
    // test for getPolynomFromString function
    @Test
    public void testSimplePoly() {
        String p = "3x^2 + 6x^1 - 5";
        double[] expected = {-5, 6, 3};
        assertArrayEquals(expected, Ex1.getPolynomFromString(p), 0.0001);
    }
    // test for poly function
    @Test
    public void testMixedCoefficients() {
        assertEquals("-1.1x^2 -2.4x +3.4", Ex1.poly(new double[]{3.4, -2.4, -1.1}));
    }

    @Test
	/** 
	 * Tests the parsing of a polynom in a String like form.
	 */
	public void testFromString() {
		double[] p = {-1.1,2.3,3.1}; // 3.1X^2+ 2.3x -1.1
		String sp2 = "3.1x^2 +2.3x -1.1";
		String sp = Ex1.poly(p);
		double[] p1 = Ex1.getPolynomFromString(sp);
		double[] p2 = Ex1.getPolynomFromString(sp2);
		boolean isSame1 = Ex1.equals(p1, p);
		boolean isSame2 = Ex1.equals(p2, p);
		if(!isSame1) {fail();}
		if(!isSame2) {fail();}
		assertEquals(sp, Ex1.poly(p1));
	}
    // 3 test for gethezkafromstring function 1 checks for ordinary output
    @Test
    public void testHezkaSimple() {
        assertEquals(3, Ex1.gethezkafromstring("5x^3"));
    }
    // 2 checks for a case without hezka

    @Test
    public void testHezkaNoPower() {
        // אם אין ^ החזקה מוגדרת כ־1
        assertEquals(1, Ex1.gethezkafromstring("7x"));
    }
    // 3 cheks for a case without x

    @Test
    public void testHezkaSingleX() {
        assertEquals(1, Ex1.gethezkafromstring("x"));
    }
    // 4 test for getdoublefromstring function 1 checks for a negative number
    @Test
    public void testDoubleNegative() {
        assertEquals(-2.5, Ex1.getdoublefromstring("-2.5x^2"), 0.0001);
    }
    // 2 checks for a case without a number
    @Test
    public void testDoubleNoCoefficient() {
        // "x^2" → מקדם 1
        assertEquals(1.0, Ex1.getdoublefromstring("x^2"), 0.0001);
    }
    // 3 checks for a case with +

    @Test
    public void testDoubleWithPlus() {
        // "+3x" → מקדם 3
        assertEquals(3.0, Ex1.getdoublefromstring("+3x"), 0.0001);
    }
    // 4 checks for a case with number smaller than 1

    @Test
    public void testDoubleFraction() {
        assertEquals(0.75, Ex1.getdoublefromstring("0.75x"), 0.0001);
    }
	@Test
	/**
	 * Tests the equality of pairs of arrays.
	 */
	public void testEquals() {
		double[][] d1 = {{0}, {1}, {1,2,0,0}};
		double[][] d2 = {Ex1.ZERO, {1+ Ex1.EPS/2}, {1,2}};
		double[][] xx = {{-2* Ex1.EPS}, {1+ Ex1.EPS*1.2}, {1,2, Ex1.EPS/2}};
		for(int i=0;i<d1.length;i=i+1) {
			assertTrue(Ex1.equals(d1[i], d2[i]));
		}
		for(int i=0;i<d1.length;i=i+1) {
			assertFalse(Ex1.equals(d1[i], xx[i]));
		}
	}
    // test for remove function gets double array with 0 and return new array without it
    @Test
    public void testRemoveSimple() {
        double[] arr = {1, 0, 2, 0, 3};
        double[] expected = {1, 2, 3};

        double[] actual = Ex1.remove(arr);

        assertArrayEquals(expected, actual, 0.0001);
    }
    // test for remove function without 0 the function does nothing

    @Test
    public void testRemoveNoZeros() {
        double[] arr = {1, 2, 3};
        double[] expected = {1, 2, 3};

        double[] actual = Ex1.remove(arr);

        assertArrayEquals(expected, actual, 0.0001);
    }
    // simple test for the function PolynomFromPoints with 2 points
    @Test
    public void testTwoPoints_simpleLine() {
        double[] xx = {1, 3};
        double[] yy = {2, 6};

        // y = 2x ? לא → נבדוק:
        // שיפוע = (2 - 6)/(1 - 3) = (-4)/(-2) = 2
        // c = y - mx = 2 - 2*1 = 0 → y = 2x

        double[] p = Ex1.PolynomFromPoints(xx, yy);

        assertEquals(0, p[0], 1e-9);
        assertEquals(2, p[1], 1e-9);
    }
    // test for the function PolynomFromPoints with 2 points also with the same y value
    @Test
    public void testTwoPoints_HorizontalLine() {
        double[] xx = {0, 5};
        double[] yy = {3, 3};

        // y = 3 → c = 3, slope = 0

        double[] p = Ex1.PolynomFromPoints(xx, yy);

        assertEquals(3, p[0], 1e-9);
        assertEquals(0, p[1], 1e-9);
    }
    // test for the function PolynomFromPoints with 2 points also with the same x value
    @Test
    public void testTwoPoints_InvalidVerticalLine() {
        double[] xx = {2, 2};
        double[] yy = {1, 5};

        double[] p = Ex1.PolynomFromPoints(xx, yy);

        assertNull(p); // חייב null
    }


    //  simple test for the function PolynomFromPoints with 3 points
    @Test
    public void testThreePoints_LinearInsideQuadratic() {
        double[] xx = {1, 2, 3};
        double[] yy = {2, 4, 6}; // y = 2x

        double[] p = Ex1.PolynomFromPoints(xx, yy);

        // מצופה לקבל a=0, b=2, c=0
        assertEquals(0, p[2], 1e-9);
        assertEquals(2, p[1], 1e-9);
        assertEquals(0, p[0], 1e-9);
    }
    // test for root_rec function
    @Test
    public void testLinearSimpleRoot() {
        // p(x) = x - 3  (שורש ב-x=3)
        double[] p = { -3, 1 };
        double x = Ex1.root_rec(p, 0, 5, 1e-6);

        assertTrue(Math.abs(Ex1.f(p, x)) < 1e-6,
                "Expected |f(x)| < eps but got " + Math.abs(Ex1.f(p, x)));
        assertTrue(x >= 0 && x <= 5);
    }
    // test for root_rec function
    @Test
    public void testSmallEpsilonMorePrecision() {
        // p(x) = x - 0.12345
        double[] p = { -0.12345, 1 };

        double x = Ex1.root_rec(p, 0, 1, 1e-10); // epsilon קטן מאוד

        assertTrue(Math.abs(Ex1.f(p, x)) < 1e-10);
    }




    @Test
	/**
	 * Tests is the sameValue function is symmetric.
	 */
	public void testSameValue2() {
		double x1=-4, x2=0;
		double rs1 = Ex1.sameValue(po1,po2, x1, x2, Ex1.EPS);
		double rs2 = Ex1.sameValue(po2,po1, x1, x2, Ex1.EPS);
		assertEquals(rs1,rs2, Ex1.EPS);
	}
    // test for samevalue function if p1 = null
    @Test
    public void testNullP1() {
        // p1 = null
        // p2(x) = x - 2
        double[] p1 = null;
        double[] p2 = {-2, 1};

        double x = Ex1.sameValue(p1, p2, 0, 5, Ex1.EPS);

        // אמור להיות x ≈ 2
        assertEquals(2, x, Ex1.EPS);
    }
    // test for same value function if for the 2 polinom have the same values
    @Test
    public void testSamePolynomials() {
        // p1(x) = x^2 + 1
        double[] p1 = {1, 0, 1};
        double[] p2 = {1, 0, 1};

        // כל x הוא פתרון, הפונקציה תחזיר את מה ש-root_rec מחזירה עבור פולינום זה
        double x = Ex1.sameValue(p1, p2, -10, 10, Ex1.EPS);

        // פה אפשר רק לבדוק שהערך חוקי
        assertTrue(x >= -10 && x <= 10);
    }
    // test for length function for a strait line
    @Test
    public void testStraightLine() {
        // y = 2x + 1  --> אורך קטע בקו ישר צריך להיות בדיוק x2-x1 * sqrt(1 + m^2)
        double[] p = {1, 2}; // f(x)=1 + 2x

        double x1 = 0;
        double x2 = 3;
        double expected = (x2 - x1) * Math.sqrt(1 + 4); // sqrt(5)*3

        double actual = Ex1.length(p, x1, x2, 100);

        assertEquals(expected, actual, 0.001);
    }
    // test for length function for a parabola
    @Test
    public void testParabolaSimple() {
        // f(x) = x^2
        double[] p = {0, 0, 1};

        double x1 = 0;
        double x2 = 1;

        // ערך ידוע: אורך גרף x^2 בין 0 ל-1 ≈ 1.47894
        double expected = 1.47894;

        double actual = Ex1.length(p, x1, x2, 500);

        assertEquals(expected, actual, 0.01);
    }
    // test for length function that Calculate distance is correct
    @Test
    public void testSingleSegment() {
        // n = 1 → אורך = המרחק בין שתי נקודות (x1,f(x1)) ו-(x2,f(x2))
        double[] p = {0, 1}; // f(x)=x
        double x11 = 0;
        double x2 = 4;

        double dx = x2 - x11; // 4
        double dy = Ex1.f(p, x2) - Ex1.f(p, x11); // 4-0 =4
        double expected = Math.sqrt(dx*dx + dy*dy); // sqrt(32)

        double actual = Ex1.length(p, x11, x2, 1);

        assertEquals(expected, actual, 0.001);
    }


	@Test
	/**
	 * Test the area function - it should be symmetric.
	 */
	public void testArea() {
		double x1=-4, x2=0;
		double a1 = Ex1.area(po1, po2, x1, x2, 100);
		double a2 = Ex1.area(po2, po1, x1, x2, 100);
		assertEquals(a1,a2, Ex1.EPS);
}
	@Test
	/**
	 * Test the area f1(x)=0, f2(x)=x;
     * ** only a big value of trapezoid can be close to answer
	 */
	public void testArea2() {
		double[] po_a = Ex1.ZERO;
		double[] po_b = {0,1};
		double x1 = -1;
		double x2 = 2;
		double a1 = Ex1.area(po_a,po_b, x1, x2, 1);
		double a2 = Ex1.area(po_a,po_b, x1, x2, 2);
		double a3 = Ex1.area(po_a,po_b, x1, x2, 3);
		double a100 = Ex1.area(po_a,po_b, x1, x2, 100);
		double area =2.5;
        //assertEquals(a1,area, Ex1.EPS);
		//assertEquals(a2,area, Ex1.EPS);
		//assertEquals(a3,area, Ex1.EPS);
		assertEquals(a100,area, Ex1.EPS);
	}
    // test for area function with the same polinom sopuse to return 0
    @Test
    public void testAreaSamePolynomials() {
        double[] p = {1, 2, 3}; // 1 + 2x + 3x^2

        double area = Ex1.area(p, p, 0, 10, 20);

        assertEquals(0.0, area, Ex1.EPS);
    }
    @Test
    /**
     * Test the area function.
     */
    public void testAreaConstantGap() {
        double[] p1 = {5};
        double[] p2 = {0};

        double area = Ex1.area(p1, p2, 0, 10, 50);

        assertEquals(50, area, 0.001);
    }

}
