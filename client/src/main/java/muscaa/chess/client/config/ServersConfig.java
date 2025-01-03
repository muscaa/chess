package muscaa.chess.client.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;
import fluff.bin.stream.BinaryInputStream;
import fluff.bin.stream.BinaryOutputStream;
import fluff.functions.gen.obj.VoidFunc1;

public class ServersConfig implements IBinaryData {
	
	private static final int VERSION = 1;
	
	private final List<Server> list = new LinkedList<>();
	
	public void load() {
		try (BinaryInputStream in = new BinaryInputStream(new FileInputStream("servers.dat"))) {
			in.Data(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try (BinaryOutputStream out = new BinaryOutputStream(new FileOutputStream("servers.dat"))) {
			out.Data(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void modify(VoidFunc1<List<Server>> func) {
        func.invoke(list);
        save();
	}
	
	public List<Server> getList() {
		return list;
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		int version = in.Int();
		if (version != VERSION) return;
		
		list.clear();
		
		int len = in.Int();
		for (int i = 0; i < len; i++) {
			Server server = in.Data(new Server());
			
			list.add(server);
		}
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(VERSION);
		
		out.Int(list.size());
		for (Server server : list) {
			out.Data(server);
		}
	}
	
	public static class Server implements IBinaryData {
		
		public String name;
		public String address;
		public int port;
		
		public Server(String name, String address, int port) {
			this.name = name;
			this.address = address;
			this.port = port;
		}
		
		private Server() {}
		
		@Override
		public void readData(IBinaryInput in) throws IOException {
			name = in.LenString();
			address = in.LenString();
			port = in.Int();
		}
		
		@Override
		public void writeData(IBinaryOutput out) throws IOException {
			out.LenString(name);
			out.LenString(address);
			out.Int(port);
		}
	}
}
