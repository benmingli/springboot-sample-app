package de.codecentric.springbootsample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mingli on 12/05/18.
 */
public class FF4jProviderImpl implements FF4jProvider {
    private FF4j ff4j;

    public FF4jProviderImpl() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ff4j = new FF4j("od");
                    if (null != listener) {
                        listener.OnFF4jUpdated(ff4j);
                    }
                }
            }
        });
    }

    @Override
    public FF4j getFF4j() {
        return ff4j;
    }

    private FF4jUpdateListener listener;

    @Override
    public void registerFF4jListener(FF4jUpdateListener listener) {
        this.listener = listener;
    }
}
