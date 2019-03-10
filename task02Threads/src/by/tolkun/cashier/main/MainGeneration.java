package by.tolkun.cashier.main;

import by.tolkun.cashier.entity.RestaurantCashier;
import by.tolkun.cashier.entity.RestaurantCheck;
import by.tolkun.cashier.entity.RestaurantOrder;
import by.tolkun.cashier.exception.WrongArgumetException;
import by.tolkun.cashier.factory.RestaurantCustomerFactory;
import by.tolkun.cashier.factory.RestaurantOrderFactory;
import by.tolkun.cashier.repository.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Class to demonstrate the work of multithreading app. Object generates
 * automatically.
 *
 * @author Kirill Tolkun
 */
public final class MainGeneration {

    /**
     * Number of cashiers.
     */
    private static final int NUM_CASHIERS = 2;

    /**
     * Number customers.
     */
    private static final int NUM_CUSTOMERS = 11;

    /**
     * Maximum of {@code Order}'s complexity.
     */
    private static final int MAX_ORDER_COMPLEXITY = 9;

    /**
     * Number of milliseconds for timeout during the creating {@code Customer}
     * with high {@code CustomerPriority}.
     */
    private static final int CUSTOMER_RUN_TIMEOUT_MS = 120;

    /**
     * Logger of class {@code Main}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MainGeneration.class);

    /**
     * Private default constructor.
     */
    private MainGeneration() {
    }

    /**
     * Run application.
     *
     * @param args of console
     * @throws WrongArgumetException the wrong argument exception
     */
    public static void main(final String[] args) {
//        Create and add cashiers to restaurant
        for (int i = 0; i < NUM_CASHIERS; i++) {
            Restaurant.getInstance().add(new RestaurantCashier());
        }

//        Create and run customers
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<List<RestaurantCheck>>> futures = new ArrayList<>();
        RestaurantOrderFactory restaurantOrderFactory
                = new RestaurantOrderFactory();
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            List<RestaurantOrder> orders = new ArrayList<>();
            try {
//                Create orders for customers
                orders.add(restaurantOrderFactory
                        .createOrder(
                                new Random()
                                        .nextInt(MAX_ORDER_COMPLEXITY) + 1,
                                false));
                if (i % 3 == 0) {
                    orders.add(restaurantOrderFactory
                            .createOrder(
                                    new Random()
                                            .nextInt(MAX_ORDER_COMPLEXITY) + 1,
                                    true));
                    orders.add(restaurantOrderFactory
                            .createOrder(
                                    new Random()
                                            .nextInt(MAX_ORDER_COMPLEXITY) + 1,
                                    true));
                    orders.add(restaurantOrderFactory
                            .createOrder(
                                    new Random()
                                            .nextInt(MAX_ORDER_COMPLEXITY) + 1,
                                    false));
                }

//                Create and run customers
                RestaurantCustomerFactory restaurantCustomerFactory
                        = new RestaurantCustomerFactory();
                futures.add(executorService.submit(
                        restaurantCustomerFactory
                                .createCustomer(
                                        Restaurant.getInstance(),
                                        orders
                                )
                ));
            } catch (WrongArgumetException e) {
                LOGGER.error(e);
            }
            try {
                TimeUnit.MILLISECONDS
                        .sleep(CUSTOMER_RUN_TIMEOUT_MS);
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
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
