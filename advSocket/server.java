import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class server{
	private static String clientChannel="client";
	private static String serverChannel="server";
	private static String channelType="channel";
	
	public static void main(String[] args)throws IOException{
		int port=5555;
		String localhost="localhost";

		//create a new serversocketchannel
		ServerSocketChannel channel=ServerSocketChannel.open();

		//bind
		channel.bind(new InetSocketAddress(localhost, port));

		//mark it as non blocking 
		channel.configureBLocking(false);

		Selector selector = Selector.open();
		SelectionKey socetServerSelectionKey = channel.register(selector, SelectionKey.OP_ACCEPT);
		Map<String, String> properties=new HashMap<String, String>();
		properties.put(channelType, serverChannel);
		socketServerSelectionKey.attach(properties);

		for(;;){
			if(selector.select()==0)
				continue;
			Set<SelectionKey> selKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				if(((Map<!--?, ?-->)key.attachment()).get(channelType).equals(serverChannel)){
					ServerSocketChannel servSC=(ServerSocketChannel) key.channel();
					ServerChannel clientSC=ServerSocketChannel.accept();
					if(clientSC != null){
					//set the client connection to be nonblocking
						clientSC.configureBlocking(false);
						SelectionKey clientKey = clientSC.register(selector,SelectionKey.OP_READ,SelectionKey.OP_WRITE);
						Map<String,String> clientproperties = new HashMap<String, String>();
						clientproperties.put(channelType, clientChannel);
						clientKey.attach(clientproperties);

					//for new client
						CharBuffer buff=CharBuffer.wrap("Hello client");
						while(buff.hasRemaining()){
							clientSC.write(Charset.defaultCharset().encode(buff));
						}
					buff.clear();
					}
				}
				else
				{
				//data is available
					ByteBuffer buff=ByteBuffer.allocate(20);
					SocketChannel clientChannel = (SocketChannel)key.channel();
					int bytesRead = 0;
					if(key.isReadable()){
						if((bytesRead=clientChannel.read(buff))>0){
							System.out.println(Charset.defaultCharset().decode(buff));
							buff.clear();
						}
					if(bytesRead<0){
						clientChannel.close();
						}
				}
		}
		iterator.remove();
	

	}
}
