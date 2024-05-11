package PriorityQueue;

import java.util.Comparator;
import java.util.LinkedList;

public class PriorityQueue<E>
{
    private LinkedList<E> queue;
    private Comparator<? super E> comparator;

    public PriorityQueue(Comparator<? super E> comparator)
    {
        queue = new LinkedList<>();
        this.comparator = comparator;
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public void add(E item)
    {
        for (E e: queue)
        {
            if (comparator.compare(e, item) < 0)
            {
                queue.add(queue.indexOf(e), item);
                return;
            }
        }

        queue.add(item);
    }

    public void update(E item)
    {
        queue.remove(item);
        queue.add(item);
    }

    public E poll()
    {
        return queue.poll();
    }

    public String toString() {
        return queue.toString();
    }
}