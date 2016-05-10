import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class client{
	public static void main(String[] args)throws IOException,InterruptedException
	{
		int port = 3535;
		SocketChannel channel=SocketChannel.open();
		
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("localhost",port));

		while(!channel.finishConnect())	{}
		
		while(true)
		{
			ByteBuffer buffA=ByteBuffer.allocate(20);
			int count =0;
			String message="";
			while((count=channel.read(buffA))>0)
			{
				buffA.flip();
				message+=Charset.defaultCharset().decode(buffA);

			}
			if(message.length()>0)
			{
				System.out.println(message);
				CharBuffer buff=CharBuffer.wrap("Hello!");
				while(buff.hasRemaining())
				{
					channel.write(Charset.defaultCharset().encode(buff));
				}
			message="";
			}
		}
	}
}
