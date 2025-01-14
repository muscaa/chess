package muscaa.chess.form.field;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class FormFieldRegistry {
	
	public static final Registry<FormFieldValue> REG = Registries.create(Chess.NAMESPACE.path("formfields"));
	
	public static final RegistryKey<FormFieldValue> NULL = REG.register(Chess.NAMESPACE.path("null"),
			key -> new FormFieldValue(key, null));
	public static final RegistryKey<FormFieldValue> BOOLEAN = REG.register(Chess.NAMESPACE.path("boolean"),
			key -> new FormFieldValue(key, Boolean::parseBoolean));
	public static final RegistryKey<FormFieldValue> BYTE = REG.register(Chess.NAMESPACE.path("byte"),
			key -> new FormFieldValue(key, Byte::parseByte));
	public static final RegistryKey<FormFieldValue> SHORT = REG.register(Chess.NAMESPACE.path("short"),
			key -> new FormFieldValue(key, Short::parseShort));
	public static final RegistryKey<FormFieldValue> INT = REG.register(Chess.NAMESPACE.path("int"),
			key -> new FormFieldValue(key, Integer::parseInt));
	public static final RegistryKey<FormFieldValue> FLOAT = REG.register(Chess.NAMESPACE.path("float"),
			key -> new FormFieldValue(key, Float::parseFloat));
	public static final RegistryKey<FormFieldValue> LONG = REG.register(Chess.NAMESPACE.path("long"),
			key -> new FormFieldValue(key, Long::parseLong));
	public static final RegistryKey<FormFieldValue> DOUBLE = REG.register(Chess.NAMESPACE.path("double"),
			key -> new FormFieldValue(key, Double::parseDouble));
	public static final RegistryKey<FormFieldValue> STRING = REG.register(Chess.NAMESPACE.path("string"),
			key -> new FormFieldValue(key, value -> value));
	
	public static void init() {
		REG.init();
	}
}
