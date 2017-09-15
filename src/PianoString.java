public class PianoString
{
    RingBuffer queue;
    int time;
    boolean dying;

    public PianoString(double frequency)
    {
        time = 0;
        queue = new RingBuffer((int)Math.ceil(44100/frequency));
        dying = false;
    }

    //for testing purposes
    public PianoString(double[] init)
    {
        time = 0;
        queue = new RingBuffer(init);
    }

    public void pluck()
    {
        unkill();

        //empty the queue first
        clear();

        while(!queue.isFull())
        {
            queue.enqueue(Math.random()-.5);
        }
    }

    public void clear()
    {
        queue = new RingBuffer(queue.capacity());
    }

    public void tic()
    {
        time++;
        double n1 = queue.dequeue();
        double n2 = queue.peek();
        double result;

        if(dying)
        {
            result = (n1+n2)*.5*.8;  //no decay factor means slur pedal is held!! - double result = (n1+n2)/2;
        }
        else
        {
            result = n1;
        }

        queue.enqueue(result);

        //testing
        //System.out.println(queue);
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

    public void kill()
    {
        dying = true;
    }

    public void unkill()
    {
        dying = false;
    }
}
