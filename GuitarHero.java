import java.util.*;

public class GuitarHero
{
    public static void main(String[] args)
    {

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;

        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++)
        {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(1.05956, i-24));
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        ArrayList<Character> playedNotes = new ArrayList<>();
        ArrayList<Integer> playedTimes = new ArrayList<>();
        int timestamp = 0;
        int playstamp = 0;
        boolean recording = false;
        boolean playing = false;
        int lasttime = 0;

        // the main input loop
        while (true)
        {
            if(recording)
            {
                timestamp++;
            }

            if(playing)
            {
                playstamp++;

                if(playedTimes.contains(playstamp)) //problem: only plays one note if two are played at once when playing over recording. may be problem in recording.
                {
                    strings[keyboard.indexOf(playedNotes.get(playedTimes.indexOf(new Integer(playstamp))))].pluck();
                }

                if(playstamp ==  lasttime)
                {
                    playing = false;
                    playstamp = 0;
                }
            }

            /*
            if(playing)     //plays all the notes at once
            {

                for(int i = 0; i < playedTimes.get(playedTimes.size()-1); i++)
                {
                    if(playedTimes.contains(i))
                    {
                        strings[keyboard.indexOf(playedNotes.get(playedTimes.indexOf(new Integer(i))))].pluck();
                    }
                }
            }
            */

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.contains(Character.toString(key)))
                {
                    strings[keyboard.indexOf(key)].pluck();

                    if(recording)
                    {
                        playedNotes.add(key);
                        playedTimes.add(timestamp);
                    }
                }

                if(key == '`')
                {
                    recording = !recording;
                    if(timestamp > lasttime)
                        lasttime = timestamp;
                    timestamp = 0;
                }

                if(key == '~')
                {
                    playing = !playing;
                    playstamp = 0;
                }

                if(key == '*')
                {
                    playedNotes = new ArrayList<>();
                    playedTimes = new ArrayList<>();
                    lasttime = 0;
                }

                if(key == '+')
                {
                    recording = !recording;
                    playing = recording;
                }

            }

            // compute the superposition of the samples
            double sample2 = 0;
            for(GuitarString str : strings)
            {
                sample2 += str.sample();
            }

            // send the result to standard audio
            StdAudio.play(sample2);

            // advance the simulation of each guitar string by one step
            for(GuitarString str : strings)
            {
                str.tic();
            }

        }
    }

}