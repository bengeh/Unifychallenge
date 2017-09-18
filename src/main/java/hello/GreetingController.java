package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;



import java.nio.ByteBuffer;

import java.io.*;
import java.net.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import javax.sound.sampled.*;
import javax.swing.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/")
    public static void call() throws IOException{
        URL url = new URL("https://api.random.org/json-rpc/1/invoke");

        String message;
        JSONObject json = new JSONObject();
        json.put("jsonrpc", "2.0");
        json.put("method", "generateIntegers");
        json.put("id", 3076);

        //JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        item.put("apiKey", "cbf83332-7d2a-4852-ba5b-dc6ec38e8328");
        item.put("n", 52);
        item.put("min", 1);
        item.put("max", 52);
        item.put("replacement", false);
        //array.put(item);

        json.put("params", item);

        message = json.toString();
        System.out.println(message);


        URLConnection urlc = url.openConnection();
        urlc.setDoOutput(true);
        urlc.setAllowUserInteraction(false);

        PrintStream ps = new PrintStream(urlc.getOutputStream());
        ps.print(message);
        ps.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));

        String l = null;
        while((l=br.readLine()) != null){
            System.out.println(l);
            /*JSONObject json = (JSONObject) JSONSerializer.toJSON(l);
            JSONObject resultarr = json.getJSONObject("result");
            JSONObject randomresult = resultarr.getJSONObject("random");
            JSONObject dataarr = randomresult.getJSONObject("data");
            System.out.println(" data set: " + dataarr);*/

        }





        br.close();



    }

    @RequestMapping("/rgbpicture")
    public static void rgbpicture(){
        //image dimension
        int width = 128;
        int height = 128;
        //create buffered image object img
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //file object
        File f = null;
        //create random image pixel by pixel
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int a = (int)(Math.random()*256); //alpha
                int r = (int)(Math.random()*256); //red
                int g = (int)(Math.random()*256); //green
                int b = (int)(Math.random()*256); //blue

                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel

                img.setRGB(x, y, p);
            }
        }
        //write image
        try{
            f = new File("D:\\Image\\Output.png");
            ImageIO.write(img, "png", f);
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    @RequestMapping("/sound")
    public static void sound(){
        final int SAMPLE_SIZE = 2;
        final int PACKET_SIZE = 5000;

        SourceDataLine line = null;
        boolean exitExecution = false;
        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format, PACKET_SIZE * 2);

            if (!AudioSystem.isLineSupported(info)) {
                throw new LineUnavailableException();
            }

            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ByteBuffer buffer = ByteBuffer.allocate(PACKET_SIZE);

        //Random random = new Random();
        while (exitExecution == false) {
            buffer.clear();
            for (int i=0; i < PACKET_SIZE /SAMPLE_SIZE; i++) {
                buffer.putShort((short) (20 * Short.MAX_VALUE));
            }
            line.write(buffer.array(), 0, buffer.position());
        }

        line.drain();
        line.close();
    }

}
