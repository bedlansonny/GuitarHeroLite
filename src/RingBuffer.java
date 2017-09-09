public class RingBuffer
{
    double[] arr;
    int front;
    int back;
    int size;

    RingBuffer(int size)
    {
        arr = new double[size];
        front = -1;
        back = -1;
        size = 0;
    }

    //for testing purposes
    RingBuffer(double[] init)
    {
        arr = init;
        front = 0;
        back = arr.length-1;
        size = arr.length;
    }

    public void enqueue(double value)
    {
        if(!isFull())
        {
            size++;
            back = incrementValue(back);
            if(size == 1)
                front = incrementValue(front);
            arr[back] = value;
        }
        else
            System.out.println("enqueue ERROR: RingBuffer is full.\n" + toString());

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
                front = incrementValue(front);
            }

            size--;
            //testing
            //System.out.println(toString() + "\nfront: " + front + "\nback: " + back + "\nsize: " + size);
            return output;
        }
        else
        {
            System.out.println("dequeue ERROR: RingBuffer is empty.\n" + toString());
            return 0;
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        System.out.println("isFull() printing: \n" + toString());
        return size == arr.length;
        //return (front == 0 && back == arr.length-1) || (back == front - 1);
    }

    public double peek()
    {
        if(size > 0)
        {
            //System.out.println(toString() + "\nfront: " + front + "\nback: " + back + "\nsize: " + size);
            return arr[front];
        }
        else
        {
            System.out.println("peek ERROR: RingBuffer is empty.\n" + toString());
            return 0;
        }
    }

    //amount in queue or maximum capacity???
    public int size()
    {
        return size;
    }

    public String toString()
    {
        String output = "[";
        for(int i = 0; i < arr.length; i++)
        {
            output += arr[i] + ", ";
        }
        output += "]";

        output += "\nfront = " + front + "\nback = " + back + "\nsize = " + size + "\ncapacity = " + arr.length;

        return output;

        /*
        if(size == 0)
            return "[]";

        String output = "[";
        int index = front;
        for(int count = 0; count < size-1; count++)
        {
            output += arr[index] + ", ";
            index = incrementValue(index);
        }
        output += arr[index] + "]";

        return output;
        */
    }

    private int incrementValue(int index)
    {
        if(index < arr.length-1)
            return index + 1;
        else
            return 0;
    }
}
