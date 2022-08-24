package demo.unit6.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executors = Executors.newWorkStealingPool(4);

        FeatureExample.CallableSample sample = new FeatureExample.CallableSample();

        List<FeatureExample.CallableSample> callables = Arrays.asList(sample, sample, sample, sample);

        executors.invokeAny(callables);
    }

}
