package muscaa.chess.utils;

public class Namespace {
	
	private final String id;
	
	private Namespace(String id) {
		this.id = id;
	}
	
	public NamespacePath path(String id) {
		return NamespacePath.of(this, id);
	}
	
	public String getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Namespace[" + id + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Namespace ns && ns.id.equals(id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	public static Namespace of(String id) {
		return new Namespace(id);
	}
}
