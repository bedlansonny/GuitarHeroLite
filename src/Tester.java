public class Tester
{
    public static void main(String args[])
    {
        RingBuffer buff = new RingBuffer(20);
        buff.enqueue(1);
        System.out.println(buff);
        buff.enqueue(2);
        buff.enqueue(3);
        buff.enqueue(4);
        System.out.println(buff);
        System.out.println(buff.dequeue());
        System.out.println(buff);

    }
}
