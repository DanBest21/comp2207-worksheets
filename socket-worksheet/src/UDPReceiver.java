import java.net.*;

class UDPReceiver
{
    public static void main(String [] args)
    {
        try
        {
            DatagramSocket socket = new DatagramSocket(4321);
            byte[] buf = new byte[256];

            DatagramSocket ackSocket = new DatagramSocket();

            for (int i = 0; i < 20; i++)
            {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("receive DatagramPacket "
                        + (new String(packet.getData())).trim() + " "
                        + packet.getAddress() + ":"
                        + packet.getPort());

                byte[] ackBuf = String.valueOf(i).getBytes();
                DatagramPacket ackPacket = new DatagramPacket(ackBuf, ackBuf.length, packet.getAddress(), 4322);
                ackSocket.send(ackPacket);
                System.out.println("acknowledge DatagramPacket "
                        + new String(ackPacket.getData()) + " "
                        + ackPacket.getAddress() + ":"
                        + ackPacket.getPort());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
