package muscaa.chess.config.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.functions.gen.obj._boolean._boolean.Func3ObjBooleanBoolean;
import fluff.functions.gen.obj._byte._byte.Func3ObjByteByte;
import fluff.functions.gen.obj._double._double.Func3ObjDoubleDouble;
import fluff.functions.gen.obj._float._float.Func3ObjFloatFloat;
import fluff.functions.gen.obj._int._int.Func3ObjIntInt;
import fluff.functions.gen.obj._long._long.Func3ObjLongLong;
import fluff.functions.gen.obj._short._short.Func3ObjShortShort;
import fluff.functions.gen.obj.obj.obj.Func3;
import muscaa.chess.config.IConfig;
import muscaa.chess.config.IValue;
import muscaa.chess.config.base.values.BaseValueBoolean;
import muscaa.chess.config.base.values.BaseValueByte;
import muscaa.chess.config.base.values.BaseValueDouble;
import muscaa.chess.config.base.values.BaseValueFloat;
import muscaa.chess.config.base.values.BaseValueInt;
import muscaa.chess.config.base.values.BaseValueLong;
import muscaa.chess.config.base.values.BaseValueObject;
import muscaa.chess.config.base.values.BaseValueShort;
import muscaa.chess.config.base.values.BaseValueString;
import muscaa.chess.config.values.IValueBoolean;
import muscaa.chess.config.values.IValueByte;
import muscaa.chess.config.values.IValueDouble;
import muscaa.chess.config.values.IValueFloat;
import muscaa.chess.config.values.IValueInt;
import muscaa.chess.config.values.IValueLong;
import muscaa.chess.config.values.IValueObject;
import muscaa.chess.config.values.IValueShort;
import muscaa.chess.config.values.IValueString;

public abstract class BaseConfig<C> implements IConfig {
	
	protected final Map<Class<?>, Object> constructors = new HashMap<>();
	protected final List<BaseValue<?, C>> values = new LinkedList<>();
	
	public IValueBoolean Boolean(String key, boolean defaultValue) {
		Func3ObjBooleanBoolean<BaseValueBoolean<C>, String> constructor = (Func3ObjBooleanBoolean<BaseValueBoolean<C>, String>) constructors.get(boolean.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueBoolean<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putBoolean(Func3ObjBooleanBoolean<BaseValueBoolean<C>, String> constructor) {
		constructors.put(boolean.class, constructor);
	}
	
	public IValueByte Byte(String key, byte defaultValue) {
		Func3ObjByteByte<BaseValueByte<C>, String> constructor = (Func3ObjByteByte<BaseValueByte<C>, String>) constructors.get(byte.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueByte<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putByte(Func3ObjByteByte<BaseValueByte<C>, String> constructor) {
		constructors.put(byte.class, constructor);
	}
	
	public IValueShort Short(String key, short defaultValue) {
		Func3ObjShortShort<BaseValueShort<C>, String> constructor = (Func3ObjShortShort<BaseValueShort<C>, String>) constructors.get(short.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueShort<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putShort(Func3ObjShortShort<BaseValueShort<C>, String> constructor) {
		constructors.put(short.class, constructor);
	}
	
	public IValueInt Int(String key, int defaultValue) {
		Func3ObjIntInt<BaseValueInt<C>, String> constructor = (Func3ObjIntInt<BaseValueInt<C>, String>) constructors.get(int.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueInt<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putInt(Func3ObjIntInt<BaseValueInt<C>, String> constructor) {
        constructors.put(int.class, constructor);
	}
	
	public IValueFloat Float(String key, float defaultValue) {
		Func3ObjFloatFloat<BaseValueFloat<C>, String> constructor = (Func3ObjFloatFloat<BaseValueFloat<C>, String>) constructors.get(float.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueFloat<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putFloat(Func3ObjFloatFloat<BaseValueFloat<C>, String> constructor) {
        constructors.put(float.class, constructor);
	}
	
	public IValueLong Long(String key, long defaultValue) {
		Func3ObjLongLong<BaseValueLong<C>, String> constructor = (Func3ObjLongLong<BaseValueLong<C>, String>) constructors.get(long.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueLong<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putLong(Func3ObjLongLong<BaseValueLong<C>, String> constructor) {
        constructors.put(long.class, constructor);
	}
	
	public IValueDouble Double(String key, double defaultValue) {
		Func3ObjDoubleDouble<BaseValueDouble<C>, String> constructor = (Func3ObjDoubleDouble<BaseValueDouble<C>, String>) constructors.get(double.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueDouble<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putDouble(Func3ObjDoubleDouble<BaseValueDouble<C>, String> constructor) {
		constructors.put(double.class, constructor);
	}
	
	public IValueString String(String key, String defaultValue, String value) {
		Func3<BaseValueString<C>, String, String, String> constructor = (Func3<BaseValueString<C>, String, String, String>) constructors.get(String.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueString<C> v = constructor.invoke(key, defaultValue, defaultValue);
		return (IValueString) value(v);
	}
	
	protected void putString(Func3<BaseValueString<C>, String, String, String> constructor) {
		constructors.put(String.class, constructor);
	}
	
	public <V> IValueObject<V> Object(String key, V defaultValue, V value) {
		Func3<BaseValueObject<C, V>, String, V, V> constructor = (Func3<BaseValueObject<C, V>, String, V, V>) constructors.get(Object.class);
		if (constructor == null) throw new UnsupportedOperationException("No boolean constructor found!");
		
		BaseValueObject<C, V> v = constructor.invoke(key, defaultValue, defaultValue);
		return value(v);
	}
	
	protected void putObject(Func3<BaseValueObject<C, ?>, String, ?, ?> constructor) {
		constructors.put(Object.class, constructor);
	}
	
	@Override
	public <T extends IValue<T>> T value(T value) {
		if (!(value instanceof BaseValue bv)) throw new IllegalArgumentException("Value must be an instance of BaseValue!");
		
		values.add(bv);
		return value;
	}
}
