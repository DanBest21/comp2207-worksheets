import java.net.*;

class UDPSender
{
    public static void main(String [] args)
    {
        try
        {
            InetAddress address =
                    InetAddress.getByName("Dan-PC");
            DatagramSocket socket = new DatagramSocket();

            DatagramSocket ackSocket = new DatagramSocket(4322);
            byte[] ackBuf = new byte[256];

            for (int i = 0; i < 10; i++)
            {
                byte[] buf = String.valueOf(i).getBytes();
                DatagramPacket packet =
                        new DatagramPacket(buf, buf.length, address, 4321);
                socket.send(packet);
                System.out.println("send DatagramPacket "
                        + new String(packet.getData()) + " "
                        + packet.getAddress() + ":"
                        + packet.getPort());

                DatagramPacket ackPacket = new DatagramPacket(ackBuf, ackBuf.length);
                ackSocket.receive(ackPacket);
                System.out.println("DatagramPacket received "
                        + new String(ackPacket.getData()) + " "
                        + ackPacket.getAddress() + ":"
                        + ackPacket.getPort());

                Thread.sleep(2000);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}