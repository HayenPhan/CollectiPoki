package collectipoki.com;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// Perform these tasks on a background thread
public class AppExecutors {

    // Singleton method (restricts the instantiation of a class and ensures that only one instance of the class exists in the java virtual machine.)
    private static AppExecutors instance;

    public static AppExecutors getInstance() {
        if(instance == null) {
            instance = new AppExecutors();
        }

        return instance;
    }


    /* Add extra functionality to executors ->
     - Using these 3 threadpools to do all the work required in the application
     - It can schedule commands to run after a given delay
     - An Executor in general that is used to execute runnable tasks. You could do it as well on the main thread as on the background thread.
     - For this project this executor is RUNNING as the BACKGROUND THREAD.

     */
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
        return mNetworkIO;
    }


}
