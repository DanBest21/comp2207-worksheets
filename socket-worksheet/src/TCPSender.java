import java.io.*;
import java.net.*;

class TCPSender
{
    public static void main(String [] args)
    {
        try
        {
            Socket socket = new Socket("Dan-PC",4322);
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            for (int i = 0; i < 10; i++)
            {
                out.println("TCP message " + i); out.flush();
                System.out.println("TCP message "+i+" sent");
                Thread.sleep(1000);
                System.out.println(in.readLine());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}