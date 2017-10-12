public class GaussianElimination {
    public static void main(String [] args){
        double [][] A = {{25,5,1,106.8},
                         {64,8,1,177.2},
                         {144,12,1,279.2}};
        /*----------------EXAMPLE FOR TEST----------------------*/
                        /*{{10e-4,1},
                          {-1,2}};*/

                      /*{{1,-3,2,1,-4},
                         {2,-6,1,4,1},
                         {-1,2,3,4,12},
                         {0,-1,1,1,0}};*/  //ถ้าไม่มี partial pivot จะเกิด error จากการหาร 0

                      /*{{1,-1,3,1,1},
                         {0,1,7,1,5},
                         {-4,4,1,-2,2},
                         {2,1,0,1,3}};*/

                     /*{{1,-3,2,1,-4},
                        {0,0,-3,2,9},
                        {0,-1,5,5,8},
                        {0,-1,1,1,0}};*/
        gaussian_Elimination2(A);
    }

    public static void gaussian_Elimination (double [][] A){ //this don't have partial pivot
        double x;
        double [] getDataArray = new double[A[0].length];
        double [] currentData = new double[A[0].length];
        double [][] eliminationArray = A;
            for (int i = 1; i < A.length; i++) {
                for (int j = 0; j < i; j++) {
                    for (int t = 0; t < A[0].length; t++) {
                        getDataArray[t] = A[j][t];
                        currentData[t] = A[i][t];
                    }
                    x = (A[i][j] / A[j][j]);
                    processForwardElimination(eliminationArray,getDataArray,currentData,x,i);
                }
            }
        for (int i = 0 ; i < A.length ; i++) {
            System.out.println(toString(eliminationArray[i]));
        }
        processBackSubstitution(eliminationArray);
    }

    public static void gaussian_Elimination2 (double [][] A){ //with partial pivot
        double x;
        double [] getDataArray = new double[A[0].length];
        double [] currentData = new double[A[0].length];
        double [][] eliminationArray = A;
        for (int i = 0; i < A.length; i++) {
            doSort(eliminationArray,i); //อาจมีบั๊กยัง---ไม่ได้ดู
            for (int j = i+1; j < A.length; j++) {
                for (int t = 0; t < A[0].length; t++) {
                    getDataArray[t] = A[i][t];
                    currentData[t] = A[j][t];
                }
                x = (A[j][i] / A[i][i]);
                processForwardElimination(eliminationArray,getDataArray,currentData,x,j);
            }
        }
        for (int i = 0 ; i < A.length ; i++) {
           System.out.println(toString(eliminationArray[i]));
        }
        processBackSubstitution(eliminationArray);
    }

    public static void processForwardElimination(double[][] A , double [] a , double [] b , double multiplier , int rowToProcess){
        for(int i = 0 ; i < a.length ; i++ ){
            a[i] =  b[i] - (a[i] * multiplier);
        }
        for(int i = 0 ; i < A[0].length ; i++){
            A[rowToProcess][i] = a[i];
        }
    }


    public static void processBackSubstitution(double [][] A){
        double[] xAns = new double[A[0].length];
        double sum;
        for (int i = A.length - 1; i >= 0  ;i--){
            sum = 0;
            for (int j = i + 1 ; j < A.length ; j++){
                sum += (A[i][j] * xAns[j]);
            }
            xAns[i] = (A[i][A.length] - sum) / A[i][i];
        }
        for(int i = 0 ; i < A.length ; i++) {
            System.out.print("Answer is : "+"x"+(i+1)+" = "+String.format("%.4f",xAns[i])+" | ");
        }
    }

    public static String toString(double[] a){
        String b = "[";
        for (int i = 0 ; i < a.length ; i++) {
            if(i == a.length-1){
                b += " | " + String.format("%8.4f",a[i]) + " ] ";
            } else {
                b += String.format("%8.4f",a[i]) + " ";
            }
        }
        return b;
    }

    public static void doSort(double [][] arr , int col){
        for (int i = col; i < arr[0].length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[index][col] == 0 || arr[j][col] == 0){
                    if (Math.abs(arr[j][col]) > Math.abs(arr[index][col]))
                        index = j;
                } else {
                    if (arr[j][col] > arr[index][col])
                        index = j;
                }

            }
            for(int j = 0 ; j < arr[0].length ; j++){
                double smallerNumber = arr[index][j];
                arr[index][j] = arr[i][j];
                arr[i][j] = smallerNumber;
            }
        }
    }
}
