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
        back = 0; // will this work???
    }

    public void enqueue(double value)
    {
        arr[back] = value;
        back++;
    }
}