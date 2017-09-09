public class GuitarString
{
    RingBuffer queue;
    int time;

    public GuitarString(double frequency)
    {
        time = 0;
        queue = new RingBuffer((int)Math.ceil(44100/frequency));
    }

    //for testing purposes
    public GuitarString(double[] init)
    {
        time = 0;
        queue = new RingBuffer(init);
    }

    public void pluck()
    {
        while(!queue.isFull())
        {
            queue.enqueue(Math.random()-.5);
        }
    }

    public void tic()
    {
        time++;
        double n1 = queue.dequeue();
        double n2 = queue.peek();
        double result = (n1+n2)*.994*.5;
        queue.enqueue(result);

        //testing
        System.out.println(queue);
    }

    public double sample()
    {
        if(!queue.isEmpty())
            return queue.peek();
        else
            return 0;
    }

    public int time()
    {
        return time;
    }

    public String toString()
    {
        return queue.toString();
    }
}
