//Alexandros Galanis icsd14027
import java.util.Random;

public class Exercise1 {

    private static int ARRAY_SIZE = 2000;
    private static int NUMBER_OF_THREADS = 20;

    public static void main(String[] args) {
        int a[] = new int[ARRAY_SIZE];//Adjacent
        int b[] = new int[ARRAY_SIZE];//Opposite
        double c[] = new double[ARRAY_SIZE];//Hypotenuse
        Random rand = new Random();

        for (int i = 0; i < ARRAY_SIZE; i++) {//Initialise arrays of integers and double
            a[i] = rand.nextInt(1000) + 1;//rand will give us from 0 to 999
            b[i] = rand.nextInt(1000) + 1;//so we add +1 to give us from 1 to 1k
            c[i] = 0;
        }

        //serial calculations
        long tStartSerial = System.currentTimeMillis(); // timestamp in milliseconds
        for (int i = 0; i < ARRAY_SIZE; i++) {
            c[i] = Math.sqrt((Math.pow(a[i], 2)) + (Math.pow(b[i], 2)));
        }
        long tEndSerial = System.currentTimeMillis(); // timestamp for ending
        //=====================================================
        //thread calculations
        long tStartThreadWithInitialization = System.currentTimeMillis(); // timestamp for threads with the time of initializations
        ThreadMaster threads[] = new ThreadMaster[20];//Initialise array which will have 20 threads 
        
        long tStartThread = System.currentTimeMillis(); // timestamp for threads
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new ThreadMaster(a, b, c, i * 100);
            threads[i].start();
        }

        try {
            for (int i = 0; i < 20; i++) {
                threads[i].join();// Waiting for all threads to finish so we can collect the finish time after that
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long tEndThread = System.currentTimeMillis(); // timestamp for ending
        long tEndThreadWithInitialization = System.currentTimeMillis(); // timestamp for the ending threads with the time for initialization needed

        //print the first 10 results
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1 + ") A:" + a[i] + "   \t B:" + b[i] + " \t C:" + c[i]);//use \t for tab to be easier on reading it later on
        }
        System.out.println("===================================================");
        System.out.println("Time in milliseconds for Serial Calculations is:" + String.valueOf(tEndSerial - tStartSerial));
        System.out.println("Time in milliseconds for Thread Calculations is:" + String.valueOf(tEndThread - tStartThread));
        System.out.println("Time in milliseconds for Thread Calculations WITH INITIALIZATION:" 
                + String.valueOf(tEndThreadWithInitialization - tStartThreadWithInitialization));
        System.out.println("===================================================");
    }

}
