package p12.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q> {
    Map<Q, Queue<T>> queues = new HashMap<>();

    @Override
    public Set<Q> availableQueues() {  
        //Set<Q> set = new HashSet<Q>();
        //set.equals(this.queues.keySet());
        //return set;
        return queues.keySet();
    }
    
    @Override
    public void openNewQueue(Q queue) {
        if (this.queues.containsKey(queue)) {
            throw new IllegalArgumentException("Queue is already open");
        }
        else {
            this.queues.put(queue, new LinkedList<T>());
        }
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        if (this.queues.containsKey(queue)) {
            return  this.queues.get(queue).isEmpty();            
        }
        else {
            throw new IllegalArgumentException("Queue is not available");
        }
    }

    @Override
    public void enqueue(T elem, Q queue) {
        if (this.queues.containsKey(queue)) {
            this.queues.get(queue).add(elem); 
        }
        else {
            throw new IllegalArgumentException("Queue is not available");
        }
    }

    @Override
    public T dequeue(Q queue) {
        if (this.queues.containsKey(queue)) {
            return this.queues.get(queue).poll();
        }
        else {
            throw new IllegalArgumentException("Queue is not available");
        }
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q, T> mapDequeued = new HashMap<>();
        for(Q queue: this.queues.keySet()) { 
            T element = dequeue(queue);
            mapDequeued.put(queue, element);
        }
        return mapDequeued;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        Set<T> set = new HashSet<T>();
        for(Q queue: this.queues.keySet()) { 
            Queue<T> currentQueue = this.queues.get(queue);
            if (currentQueue != null) {
                set.addAll(currentQueue); 
            }
        }
        return set;
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        throw new UnsupportedOperationException("Unimplemented method 'dequeueAllFromQueue'");
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        throw new UnsupportedOperationException("Unimplemented method 'closeQueueAndReallocate'");
    }

}
