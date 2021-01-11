/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Counter {
    private int currentCount;

    /**
     * Constructor.
     *
     * @param currentCount is the starting count of the counter.
     */
    public Counter(int currentCount) {
        this.currentCount = currentCount;
    }

    /**
     * The function add number to current count.
     *
     * @param number is the number we add to our current counter.
     */
    public void increase(int number) {
        this.currentCount += number;
    }

    /**
     * The function subtract number from current count.
     *
     * @param number is the number we subtract to our current counter.
     */
    public void decrease(int number) {
        this.currentCount -= number;
    }

    /**
     * The function returns the current count.
     *
     * @return the function returns the current count.
     */
    public int getValue() {
        return this.currentCount;
    }
}
