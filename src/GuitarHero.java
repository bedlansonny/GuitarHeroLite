public class GuitarHero
{
    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);


        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++)
        {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(1.05956, i-24));
        }
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if(keyboard.contains(Character.toString(key)))
                {
                    strings[keyboard.indexOf(key)].pluck();
                }

                /*
                if (key == 'a')
                {
                    stringA.pluck();
                }
                if (key == 'c')
                {
                    stringC.pluck();
                }
                */
            }

            // compute the superposition of the samples
            //double sample = stringA.sample() + stringC.sample();

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

            //stringA.tic();
            //stringC.tic();
        }
    }

}