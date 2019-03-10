package by.tolkun.cashier.main;

import by.tolkun.cashier.entity.RestaurantCheck;
import by.tolkun.cashier.entity.RestaurantCustomer;
import by.tolkun.cashier.exception.WrongArgumetException;
import by.tolkun.cashier.factory.RestaurantCashierFactory;
import by.tolkun.cashier.factory.RestaurantCustomerFactory;
import by.tolkun.cashier.reader.CustomerDataReader;
import by.tolkun.cashier.repository.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class to demonstrate the work of multithreading app. Object reads from file.
 *
 * @author Kirill Tolkun
 */
public final class MainReading {

    /**
     * Number of cashiers.
     */
    private static final int NUM_CASHIERS = 2;

    /**
     * Logger of class {@code MainReading}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MainReading.class);

    /**
     * Private default constructor.
     */
    private MainReading() {
    }

    /**
     * Run application.
     *
     * @param args of console
     */
    public static void main(final String[] args) {
//        Create and add cashiers to restaurant
        RestaurantCashierFactory restaurantCashierFactory
                = new RestaurantCashierFactory();
        for (int i = 0; i < NUM_CASHIERS; i++) {
            Restaurant.getInstance()
                    .add(restaurantCashierFactory.createCashier());
        }

//        Read input.txt
        CustomerDataReader reader = new CustomerDataReader();
        List<List<String>> groupsLines = reader.read("data/input.txt");

//        Create and run customers
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<List<RestaurantCheck>>> futures = new ArrayList<>();
        RestaurantCustomerFactory restaurantCustomerFactor
                = new RestaurantCustomerFactory();
        for (List<String> groupLines : groupsLines) {
            try {
                RestaurantCustomer customer = restaurantCustomerFactor
                        .createCustomer(
                                Restaurant.getInstance(),
                                groupLines
                                        .toArray(new String[groupLines.size()])
                        );
                futures.add(executorService.submit(customer));
            } catch (WrongArgumetException e) {
                LOGGER.error(e);
            }
        }

        executorService.shutdown();

//        Get and print result of customer execution
        futures.forEach(future -> {
            try {
                future.get().forEach(System.out::println);
                future.get().forEach(LOGGER::debug);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        });
        System.out.println("Main is stopped!");
        LOGGER.debug("Main is stopped!");
    }
}
