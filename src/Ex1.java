/**
 * Introduction to Computer Science 2026, Ariel University,
 * Ex1: arrays, static functions and JUnit
 * https://docs.google.com/document/d/1GcNQht9rsVVSt153Y8pFPqXJVju56CY4/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 *
 * This class represents a set of static methods on a polynomial functions - represented as an array of doubles.
 * The array {0.1, 0, -3, 0.2} represents the following polynomial function: 0.2x^3-3x^2+0.1
 * This is the main Class you should implement (see "add your code below")
 *
 * @author boaz.benmoshe

 */
public class Ex1 {
    /**
     * Epsilon value for numerical computation, it serves as a "close enough" threshold.
     */
    public static final double EPS = 0.001; // the epsilon to be used for the root approximation.
    /**
     * The zero polynomial function is represented as an array with a single (0) entry.
     */
    public static final double[] ZERO = {0};

    /**
     * Computes the f(x) value of the polynomial function at x.
     *
     * @param poly - polynomial function
     * @param x
     * @return f(x) - the polynomial function value at x.
     * this function gets a double number and array polinom and return its sum value inside the polinom
     */
    public static double f(double[] poly, double x) {
        double ans = 0;
        for (int i = 0; i < poly.length; i++) {
            double c = Math.pow(x, i);
            ans += c * poly[i];
        }
        return ans;
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an epsilon eps.
     * This function computes an x value (x1<=x<=x2) for which |p(x)| < eps,
     * assuming p(x1)*p(x2) <= 0.
     * This function should be implemented recursively.
     *
     * @param p   - the polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p(x)| < eps.
     * this function gets polinom ( array of double) 2 double or range and epsilone
     * and return x double between x1 to x2 that his polinom value in erech mochlat is smaller than epsilon
     */
    public static double root_rec(double[] p, double x1, double x2, double eps) {
        double f1 = f(p, x1);
        double x12 = (x1 + x2) / 2;
        double f12 = f(p, x12);
        if (Math.abs(f12) < eps) {
            return x12;
        }
        if (f12 * f1 <= 0) {
            return root_rec(p, x1, x12, eps);
        } else {
            return root_rec(p, x12, x2, eps);
        }
    }

    /**
     * This function computes a polynomial representation from a set of 2D points on the polynom.
     * The solution is based on: //	http://stackoverflow.com/questions/717762/how-to-calculate-the-vertex-of-a-parabola-given-three-points
     * Note: this function only works for a set of points containing up to 3 points, else returns null.
     *
     * @param xx
     * @param yy
     * @return an array of doubles representing the coefficients of the polynom.
     * this function gets 2 array double of x and y value and return array double of mekadmin for the polinom
     * hint - with Lagrange Polynomial
     */
    public static double[] PolynomFromPoints(double[] xx, double[] yy) {
        double[] ans = null;
        int lx = xx.length;
        int ly = yy.length;
        if (xx != null && yy != null && lx == ly && lx > 1 && lx < 4) {
            if (lx == 2) {
                if (xx[0] == xx[1] )
                    return ans;
                ans = new double[2];
                double shipoa = (yy[0] - yy[1]) / (xx[0] - xx[1]);
                double c = yy[1] - shipoa * xx[1];
                ans[0] = c;
                ans[1] = shipoa;
                return ans;


            }
            if (lx == 3) {
                ans = new double[3];
                double denominator1 = (xx[0] - xx[1]) * (xx[0] - xx[2]);
                double denominator2 = (xx[1] - xx[0]) * (xx[1] - xx[2]);
                double denominator3 = (xx[2]-xx[0])*(xx[2]-xx[1]);
                if (denominator3 ==0 || denominator2 ==0 || denominator1 ==0)
                    return null;
                ans[0] += yy[0]/denominator1;
                ans[1] += yy[0]*(-1*(xx[1]+xx[2]))/denominator1;
                ans[2] += yy[0]*(xx[1]*xx[2])/denominator1;

                ans[0] += yy[1]/denominator2;
                ans[1] += yy[1]*(-1*(xx[0]+xx[2]))/denominator2;
                ans[2] += yy[1]*(xx[0]*xx[2])/denominator2;

                ans[0] += yy[2]/denominator3;
                ans[1] += yy[2]*(-1*(xx[0]+xx[1]))/denominator3;
                ans[2] += yy[2]*(xx[0]*xx[1])/denominator3;


            }
        }
        else {
            ans = null;
        }
        return ans;
    }

    /**
     * Two polynomials functions are equal if and only if they have the same values f(x) for n+1 values of x,
     * where n is the max degree (over p1, p2) - up to an epsilon (aka EPS) value.
     *
     * @param p1 first polynomial function
     * @param p2 second polynomial function
     * @return true iff p1 represents the same polynomial function as p2.
     * this function gets 2 array double of polinom ( mekadmim ) and check if both of them are equals
     */
    public static boolean equals(double[] p1, double[] p2) {
        boolean ans = true;
        p1 = remove(p1);
        p2 = remove(p2);
        if (p1.length != p2.length)
            return false;
        for (int i = 0;i<p1.length;i++)
        {
            if (Math.abs(p1[i] - p2[i] )> EPS){
                ans = false;
                break;
            }

        }

        return ans;
    }
    // this function remove all 0 value from array of double
    public  static double[] remove(double[] p) {
        int count = 0;
       for (int i = 0; i< p.length; i++)
       {
           if (p[i]==0.0)
              count++;
       }
       double [] a = new double[p.length-count];
       for (int i =0,j =0;i<a.length;i++)
       {
           if (p[i] != 0.0)
               a[j++]= p[i];
       }
       return a;



    }

    /**
     * Computes a String representing the polynomial function.
     * For example the array {2,0,3.1,-1.2} will be presented as the following String  "-1.2x^3 +3.1x^2 +2.0"
     *
     * @param poly the polynomial function represented as an array of doubles
     * @return String representing the polynomial function:
     * this function gets array double of polinom (mekadmin) and return string that represent him
     */
    public static String poly(double[] poly) {
        String ans = "";
        if (poly.length == 0) {
            ans = "0";
        } else {
            if (poly.length == 1)
                return String.valueOf(poly[0]);
            for (int i = poly.length-1;i>= 0;i--)
            {
                if (poly[i]==0)
                    i++;

                if (poly[i]>0 && i != poly.length-1)
                ans = ans + " +";

                if (i>1)
                ans = ans + poly[i] + "x^" + i + " " ;
                if (i == 1)
                    ans = ans + poly[i] + "x";
                if (i ==0)
                    ans = ans + poly[i];



            }

        }
        return ans;
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an epsilon eps. This function computes an x value (x1<=x<=x2)
     * for which |p1(x) -p2(x)| < eps, assuming (p1(x1)-p2(x1)) * (p1(x2)-p2(x2)) <= 0.
     *
     * @param p1  - first polynomial function
     * @param p2  - second polynomial function
     * @param x1  - minimal value of the range
     * @param x2  - maximal value of the range
     * @param eps - epsilon (positive small value (often 10^-3, or 10^-6).
     * @return an x value (x1<=x<=x2) for which |p1(x) - p2(x)| < eps.
     * the function checks for hisor 2 polinom in erech mochlat if it smaller then epsilon
     * a hint - we need to do hisor between to polinom and activate on the resulte the function root_rec
     */
    public static double sameValue(double[] p1, double[] p2, double x1, double x2, double eps) {
        double ans = x1;
        if (p1 == null)
            return root_rec(p2,x1,x2,eps);
        if (p2 == null)
            return root_rec(p1,x1,x2,eps);
        double [] p3 = new double[Math.max(p1.length,p2.length)];
       for (int i =0;i< p3.length;i++){
           if (i< p1.length && i< p2.length)
               p3[i] = p1[i] - p2[i];
           if (i>= p1.length && i<p2.length)
               p3[i] = -1*p2[i];
           if (i>=p2.length && i< p1.length)
               p3[i] = p1[i];


       }

       ans = root_rec(p3,x1,x2,eps);
        return ans;
    }

    /**
     * Given a polynomial function (p), a range [x1,x2] and an integer with the number (n) of sample points.
     * This function computes an approximation of the length of the function between f(x1) and f(x2)
     * using n inner sample points and computing the segment-path between them.
     * assuming x1 < x2.
     * This function should be implemented iteratively (none recursive).
     *
     * @param p                - the polynomial function
     * @param x1               - minimal value of the range
     * @param x2               - maximal value of the range
     * @param numberOfSegments - (A positive integer value (1,2,...).
     * @return the length approximation of the function between f(x1) and f(x2).
     * this function gets 2 double array of polinom and a range between x1 to x2 and int numberOfSegments that divide her
     * * hint - we divide the range (x2-x1) in numberOfSegments -1 = distance between points , and start from the beginning and every time adding,distance between points
     * * and checking the value for current x in f() for p1 and for p2
     * * doing the Equation of distance - root(shoresh) of (x2-x1)^2 + (y2-y1)^2
     * * and sum all of this to length (return length)
     */
    public static double length(double[] p, double x1, double x2, int numberOfSegments) {
        double ans = x1;
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * Given two polynomial functions (p1,p2), a range [x1,x2] and an integer representing the number of Trapezoids between the functions (number of samples in on each polynom).
     * This function computes an approximation of the area between the polynomial functions within the x-range.
     * The area is computed using Riemann's like integral (https://en.wikipedia.org/wiki/Riemann_integral)
     *
     * @param p1                - first polynomial function
     * @param p2                - second polynomial function
     * @param x1                - minimal value of the range
     * @param x2                - maximal value of the range
     * @param numberOfTrapezoid - a natural number representing the number of Trapezoids between x1 and x2.
     * @return the approximated area between the two polynomial functions within the [x1,x2] range.
     * the function get 2 polinom a range and int numberOfTrapezoid and return shetach
     * hint - between the area of x1-x2 we divide it to numberOfTrapezoid -1
     * for every ketah his area = erechfun * rochav ketah (for example if x1-x2 = 10 and numberOfTrapezoid -1 = 4 , so the rochav is 2.5
     * and we calculate for each beginning her value in the function , in this example (0,2.5,5,7.5) in the function = erechfun
     * and sum of all of them = ans )
     *
     */
    public static double area(double[] p1, double[] p2, double x1, double x2, int numberOfTrapezoid) {
        double ans = 0;
        /** add you code below

         /////////////////// */
        return ans;
    }

    /**
     * This function computes the array representation of a polynomial function from a String
     * representation. Note:given a polynomial function represented as a double array,
     * getPolynomFromString(poly(p)) should return an array equals to p.
     * the function gets polinom in type string for example " 3x^2 +6x + 5 "
     * and return polinom in type array double
     * we assume that if there is "x" inside p afterword must be ^ (hezka) also if x^1
     *
     * @param p - a String representing polynomial function.
     * @return
     */
    public static double[] getPolynomFromString(String p) {
        double[] ans = ZERO;//  -1.0x^2 +3.0x +2.0
        p = p.replace(" ", "");
        if (p.length() == 0)
            return ans;
        if (p.indexOf("x") == -1) {
            double[] a = new double[1];
            a[0] = Double.parseDouble(p);
            return a;
        }
        if (p.charAt(0) != '+' && p.charAt(0) != '-')
            p = "+" + p;
        String[] eivar = p.split("(?=[+-])");
        int bighezkz = 0;
        int hezka = 0;
        for (int i = 0; i < eivar.length; i++) {
            if (eivar[i].contains("x")) {
                hezka = gethezkafromstring(eivar[i]);
                if (hezka > bighezkz)
                    bighezkz = hezka;
            }
        }
        double num = 0.0;
        int hezka1 =0;
        double[] pitaron = new double[bighezkz + 1];
        for (int i =0;i< pitaron.length;i++)
        {
            if (eivar[i].contains("x")) {
                num = getdoublefromstring(eivar[i]);
                hezka1 = gethezkafromstring(eivar[i]);
                pitaron[hezka1] = num;
            }
            else {
                pitaron [0] =Double.parseDouble(eivar[i]);
                break;
            }

        }

       return pitaron;



    }
    // this function gets a string variant of eivar in the polinom and return his hezka
    public static int gethezkafromstring(String p1) {
        int bighezkast = p1.indexOf("x");
        if (!p1.contains("^"))
            return 1;
        return Integer.parseInt(p1.substring(bighezkast + 2));


    }
// this function gets a string variant of eivar in the polinom and return his mekadem in double type
    public static double getdoublefromstring(String p2) {
        int point = 0;
        int i = 0;
        int xid = p2.indexOf("x");
        if (xid == 0)
            return 1;
        int o = 0;
        if (p2.charAt(0) == '+')
            o++;
        double m = Double.parseDouble(p2.substring(0, xid));
        return m;







        }




	/**
	 * This function computes the polynomial function which is the sum of two polynomial functions (p1,p2)
     * the function add 2 polinom and return the sum of them (also polinom )
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] add(double[] p1, double[] p2) {
		double [] ans = ZERO;//
        if (p1.length ==0 )
            return p2;
        if (p2.length ==0)
            return p1;
        double [] sum = new double[Math.max(p1.length, p2.length)];
        for (int i = 0;i< sum.length;i++)
        {
           if (i<p1.length)
               sum[i] += p1[i];
           if (i< p2.length)
               sum[i] += p2[i];



        }

		return sum;
	}
	/**
	 * This function computes the polynomial function which is the multiplication of two polynoms (p1,p2)
     * the function gets 2 polinom ( array double ) and cofelet between then
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double[] mul(double[] p1, double[] p2) {
		double [] ans = ZERO;//
        if (p1 ==null || p2 == null)
            return ans;
        double [] p3 = new double[p1.length + p2.length -1];
        for (int i = 0;i<p1.length;i++) {
            for (int j = 0; j < p2.length; j++) {
                if (p3[i + j] == 0)
                    p3[i+j] = p1[i]*p2[j];
                else
                    p3[i+j] += p1[i]*p2[j];


            }
        }

		return p3;
	}
	/**
	 * This function computes the derivative of the p0 polynomial function.
     * function gets double array of polinom and return its nigzeret
	 * @param po
	 * @return
     * we assume that for every cell in the array there is a number ( it can also be 0 )
     * and its chezka is always its place in the array for example -
     * a[2,3,4] = reprezent the polinom = 2*x^0 , 3*x^1 ,4*x^2
	 */
	public static double[] derivative (double[] po) {
		double [] ans = ZERO;//
        if (po.length<2)
            return ans;
        double [] nigzeret = new double[po.length-1];
        for (int i =0;i<nigzeret.length;i++)
        {
            nigzeret[i]= po[i+1]*(i+1);
        }

		return nigzeret;
	}
}
