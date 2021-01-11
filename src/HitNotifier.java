/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface HitNotifier {
    /**
     * The function add hl as a listener to hit events.
     *
     * @param hl is the new listener.
     */
    void addHitListener(HitListener hl);

    /**
     * The function remove hl from the list of listeners to hit events.
     *
     * @param hl is the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}
