package by.tolkun.cashier.repository;

import by.tolkun.cashier.entity.RestaurantCashier;
import by.tolkun.cashier.exception.CashierAmountException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class repository provides storing of objects of class
 * {@code RestaurantCashier}.
 *
 * @author Kirill Tolkun
 */
public final class Restaurant {

    /**
     * List of cashiers.
     */
    private List<RestaurantCashier> repo;

    /**
     * Default constructor.
     */
    private Restaurant() {
        repo = new ArrayList<>();
    }

    /**
     * Inner private static class for initialization {@code INSTANCE}.
     */
    private static class SingletonHolder {

        /**
         * Instance of {@code Restaurant}.
         */
        private static final Restaurant INSTANCE = new Restaurant();
    }

    /**
     * Get instance of {@code Restaurant}.
     *
     * @return instance of {@code Restaurant}
     */
    public static Restaurant getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Get capacity of restaurant.
     *
     * @return number of cashiers in {@code Restaurant}
     */
    public int getCapasity() {
        return repo.size();
    }

    /**
     * Get cashier by index.
     *
     * @param i index of cashier in List<RestaurantCashier>
     * @return {@code Cashier}
     */
    public RestaurantCashier getCashier(final int i) {
        return repo.get(i);
    }

    /**
     * Add cashier to restaurant.
     *
     * @param cashier to add to the {@code Restaurant}
     * @return {true} if {@code Cashier} added, {@code false} otherwise
     */
    public boolean add(final RestaurantCashier cashier) {
        return repo.add(cashier);
    }

    /**
     * Remove {@code Cashier} from the {@code Restaurant}.
     *
     * @param cashier to remove from the {@code Restaurant}
     * @return {true} if {@code Cashier} removed, {@code false} otherwise
     */
    public boolean remove(final RestaurantCashier cashier) {
        return repo.remove(cashier);
    }

    /**
     * Find cashier with shortest queue.
     *
     * @return {@code Cashier} with shortest queue
     * @throws CashierAmountException if restaurant hasn't any cashier
     */
    public RestaurantCashier findCashierByShortestQueue()
            throws CashierAmountException {
        return repo
                .stream()
                .min(Comparator.comparing(RestaurantCashier::getQueueLength))
                .orElseThrow(() -> new CashierAmountException("No cashiers."));
    }
}
