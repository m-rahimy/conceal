package ir.mrahimy.conceal.data;

import java.io.IOException;

import ir.mrahimy.conceal.util.Wave;

public class WavUtil {
    public static Waver fromWaveData(Wave.WavFile file) {

        final int BUF_SIZE = 5001;
        long[] buffer = new long[BUF_SIZE * file.getNumChannels()];

        int framesRead = 0;

        do {
            try {
                framesRead = file.readFrames(buffer, BUF_SIZE);
            } catch (Wave.WavFileException | IOException e) {
                e.printStackTrace();
            }
        } while (framesRead != 0);

        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Waver(buffer, file.getSampleRate());
    }
}
