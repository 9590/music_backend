package com.guoguoyun.web.controller.app.controller;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Starter {

    public static void main(String[] args) {

        AudioInputStream din = null;

        try {

            URL url = new URL("http://localhost:8094/profile/upload/2022/02/18/582828ad-c50e-4ecd-a7ae-b3fa74450dc5.mp3");

            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();

            InputStream bufferedIn = new BufferedInputStream(httpcon.getInputStream());

            AudioInputStream in = AudioSystem.getAudioInputStream(bufferedIn);

            AudioFormat baseFormat = in.getFormat();

            AudioFormat decodedFormat = new AudioFormat(

                    AudioFormat.Encoding.PCM_SIGNED,

                    baseFormat.getSampleRate(), 16, baseFormat.getChannels(),

                    baseFormat.getChannels() * 2, baseFormat.getSampleRate(),

                    false);

            din = AudioSystem.getAudioInputStream(decodedFormat, in);

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);

            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);

            if (line != null) {

                line.open(decodedFormat);

                byte[] data = new byte[4096];

// Start

                line.start();

                int nBytesRead;

                while ((nBytesRead = din.read(data, 0, data.length)) != -1) {

                    line.write(data, 0, nBytesRead);

                }

// Stop

                line.drain();

                line.stop();

                line.close();

                din.close();

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (din != null) {

                try {
                    din.close();
                } catch (IOException e) {
                }

            }

        }

    }

}
