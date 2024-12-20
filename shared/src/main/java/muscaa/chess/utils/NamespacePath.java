package muscaa.chess.utils;

import muscaa.chess.Chess;

public class NamespacePath {
	
	private final Namespace namespace;
	private final String path;
	private final String asString;
	
	private NamespacePath(Namespace namespace, String path) {
		this.namespace = namespace;
		this.path = path;
		this.asString = namespace.getID() + ":" + path;
	}
	
	public Namespace getNamespace() {
		return namespace;
	}
	
	public String getPath() {
		return path;
	}
	
	@Override
	public String toString() {
		return asString;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof NamespacePath nspath && nspath.asString.equals(asString);
	}
	
	@Override
	public int hashCode() {
		return asString.hashCode();
	}
	
	public static NamespacePath of(Namespace namespace, String path) {
		return new NamespacePath(namespace, path);
	}
	
	public static NamespacePath of(String namespaceID, String path) {
		return of(Namespace.of(namespaceID), path);
	}
	
	public static NamespacePath of(String id) {
		String[] split = id.split(":");
		return split.length == 1 ? Chess.NAMESPACE.path(id) : of(split[0], split[1]);
	}
}
