//Alexandros Galanis icsd14027

import java.util.Arrays;

class ThreadMaster extends Thread {

    private int[] firstArray;//Adjacent
    private int[] secondArray;//Opposite
    private double[] resultsArray;//Hypotenuse
    private int index;

    // constructor method
    public ThreadMaster(int[] firstArray, int[] secondArray, double[] resultsArray, int index) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
        this.resultsArray = resultsArray;
        this.index = index;
    }

    //compute and store in the resultArray the Hypotenuse number using the Pythagoras Theorem
    //based on Adjacent number which is firstArray and Opposite number which is secondArray
    public void run() {

        //System.out.println("Thread Name:"+getName());//used it to be sure that i have 20 threads
        for (int i = index; i < index + 100; i++) {//we want to start from 0 to 2000 and since we have 20 threads we have to give 2000/20=100 calculations per thread

            resultsArray[i] = Math.sqrt((Math.pow(firstArray[i], 2)) + (Math.pow(secondArray[i], 2)));
        }
    }
}
