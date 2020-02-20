import java.io.*;
import java.net.*;

class TCPReceiverThreaded
{
    public static void main(String [] args)
    {
        try
        {
            ServerSocket ss = new ServerSocket(4322);
            for (;;)
            {
                try
                {
                    final Socket client = ss.accept();
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(client.getInputStream()));
                                String line;

                                PrintWriter out = new PrintWriter(client.getOutputStream());

                                while((line = in.readLine()) != null)
                                {
                                    System.out.println(line + " received from " + client.getInetAddress());
                                    out.println("Message received: " + line);
                                    out.flush();
                                }

                                client.close();
                            }
                            catch (Exception e)
                            {
                                // Do nothing in the case of an exception here.
                            }
                        }
                    }).start();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}