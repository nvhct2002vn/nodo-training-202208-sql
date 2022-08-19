package demo.unit6.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class InvokeExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<FeatureExample.CallableSample> callables = Arrays.asList(new FeatureExample.CallableSample(), new FeatureExample.CallableSample(), new FeatureExample.CallableSample());

        Stream<Future<Integer>> stream = executor.invokeAll(callables).stream();

        Stream<Integer> resultStream1 = stream.map(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        Integer[] results = resultStream1.toArray(Integer[]::new);
        Arrays.stream(results).forEach(System.out::println);
        System.out.println("-----------------------> " + executor.invokeAny(callables));
    }

}
