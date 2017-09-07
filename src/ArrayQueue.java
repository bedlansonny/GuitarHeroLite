public class ArrayQueue
{
    double[] arr;
    int front;
    int back;
    int size;

    ArrayQueue(int size)
    {
        arr = new double[size];
        front = -1;
        back = -1;
        size = 0;
    }

    public void enqueue(double value)
    {
        if(!isFull())
        {
            increment(back);
            if(size == 1)
                increment(front);
            size++;
            arr[back] = value;
        }
        else
            System.out.println("ERROR: ArrayQueue is full.");

    }

    public double dequeue()
    {
        if(size > 0)
        {
            double output = arr[front];

            if(size == 1)
            {
                front = -1;
                back = -1;
            }
            else
            {
                increment(front);
            }

            size--;
            return output;
        }
        else
        {
            System.out.println("ERROR: ArrayQueue is empty.");
            return 666;
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == arr.length;
        //return (front == 0 && back == arr.length-1) || (back == front - 1);
    }

    public double peek()
    {
        if(size > 0)
            return arr[front];
        else
        {
            System.out.println("ERROR: ArrayQueue is empty.");
            return 666;
        }
    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        String output = "[";
        int index = front;
        for(int count = 0; count < size-1; count++)
        {
            output += arr[index] + ", ";
            increment(index);
        }
        output += arr[index] + "]";

        return output;
    }

    private void increment(int index)
    {
        if(index < arr.length-1)
            index++;
        else
            index = 0;
    }
}
