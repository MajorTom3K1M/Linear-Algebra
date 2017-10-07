public class GuassianSeidel {
    public static void main(String [] args){
        double[][] A = {{1,-3,2,1},
                        {2,-6,1,4},
                        {0,-1,1,1}};
        double[] b = {-4,1,12,0};
        gaussian_Seidel(A,b);
    }
    public static void gaussian_Seidel (double [][] A , double [] b){
        int count = 0;
        boolean stop = false;
        double EPSILON = 1e-8;
        double sum = 0;
        double xNew[] = new double[b.length];
        double xOld[] = new double[b.length];
        double errorValue[] = new double[b.length];

        while(!stop) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (j != i) sum += A[i][j] * xOld[j];
                }
                xNew[i] = (b[i] - sum) / (A[i][i]);
                sum = 0;
                if (Math.abs(xNew[i] - xOld[i]) > EPSILON) {
                    errorValue[i] = Math.abs(xNew[i] - xOld[i]);
                    xOld[i] = xNew[i];
                } else {
                    stop = true;
                }
            }
            count++;
            System.out.println("Iteration"+ count +" : " + toString(xNew) + "\tError : " +  toString(errorValue));
        }
        System.out.print("Answers : ");
        for (int i = 0 ; i < b.length ; i++) {
            System.out.print("x"+(i+1)+" = "+Math.round(xNew[i])+" | ");
        }
    }

    public static String toString(double[] a){
        String b = "[";
        for (int i = 0 ; i < a.length ; i++) {
            if(i == a.length-1){
                b += String.format("%.4f",a[i]) + "]";
            } else {
                b += String.format("%.4f",a[i]) + ", ";
            }
        }
        return b;
    }
}
