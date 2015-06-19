package proactor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.StringTokenizer;

public class NioUpdateProfileEventHandler implements NioEventHandler {

	private static final int TOKEN_NUM = 5;

	private AsynchronousSocketChannel channel;
	private ByteBuffer buffer;
	
	private void updateProfile(String[] params) {
		System.out.println("UpdateProfile ->" 
				+ " id : " + params[0]
				+ " password : " + params[1]
				+ " name : " + params[2]
				+ " age : " + params[3]
				+ " gender : " + params[4]);
		
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {

		if (result == -1) {
			try {
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (result > 0) {
			buffer.flip();

			String data = new String(buffer.array());

			String[] params = new String[TOKEN_NUM];
			StringTokenizer token = new StringTokenizer(data, "|");

			int i = 0;

			while (token.hasMoreTokens()) {
				params[i++] = token.nextToken();
			}
			updateProfile(params);
			

			try{
				buffer.clear();
				channel.close();
			}catch(IOException e){
				e.printStackTrace();
			}

		}

	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getHandle() {
		return "0x6001";
	}

	@Override
	public int getDataSize() {
		return 1024;
	}

	@Override
	public void initialize(AsynchronousSocketChannel channel, ByteBuffer buffer) {
		this.channel = channel;
		this.buffer = buffer;
	}

}
