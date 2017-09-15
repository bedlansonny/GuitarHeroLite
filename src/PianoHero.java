import java.awt.event.KeyEvent;
import java.util.*;

public class PianoHero
{
    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;

        ArrayList<Character> activeNotes = new ArrayList<>();

        PianoString[] strings = new PianoString[37];
        for (int i = 0; i < 37; i++)
        {
            strings[i] = new PianoString(CONCERT_A * Math.pow(1.05956, i-24));
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        // the main input loop
        while (true)
        {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped())
            {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.contains(Character.toString(key)) && !activeNotes.contains(key))
                {
                    strings[keyboard.indexOf(key)].pluck();
                    activeNotes.add(key);
                }

            }

            Iterator<Character> iter = activeNotes.iterator();

            while (iter.hasNext())
            {
                char note = iter.next();

                if (!StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar(note)))
                {
                    //strings[keyboard.indexOf(note)].clear();
                    strings[keyboard.indexOf(note)].kill();
                    iter.remove();
                }

            }

            /*
            for(char note : activeNotes)
            {

                if(!StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar(note)))
                {
                    strings[keyboard.indexOf(note)].clear();
                }
            }
            */



            // compute the superposition of the samples
            double sample2 = 0;
            for(PianoString str : strings)
            {
                sample2 += str.sample();
            }

            // send the result to standard audio
            StdAudio.play(sample2);

            // advance the simulation of each guitar string by one step
            for(PianoString str : strings)
            {
                str.tic();
            }

        }
    }

}