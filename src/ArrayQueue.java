import java.lang.reflect.Array;

public class ArrayQueue
{
    double[] arr;
    int front;
    int back;

    ArrayQueue(int size)
    {
        arr = new double[size];
        front = 0;
        back = 0;
    }

    public void enqueue(double value)
    {
        if(!isFull())
        {
            arr[back] = value;
            increment(back);
        }
        else
            System.out.println("ERROR: The ArrayQueue is full.");
    }

    public double dequeue()
    {
        increment(front);
        return arr[front-1];
    }

    public boolean isEmpty()
    {
        return front == back;
    }

    public boolean isFull()
    {
        return (front == 0 && back == arr.length-1) || (back == front - 1);
    }

    public double peek()
    {
        return arr[front];
    }

    public int size()
    {

    }

    public String toString()
    {

    }

    private void increment(int index)
    {
        if(index < arr.length-1)
        {
            index++;
        }
        else
        {
            index = 0;
        }
    }
}