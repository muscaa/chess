package muscaa.chess.client.layer;

import com.badlogic.gdx.InputProcessor;

public class LayerUtils {
	
	public static final ILayer EMPTY = new ILayer() {};
	
	public static ILayer fromGdx(InputProcessor processor) {
		return new GdxLayer() {
			
			@Override
			protected InputProcessor getProcessor() {
				return processor;
			}
		};
	}
	
	public static InputProcessor toGdx(ILayer layer) {
		return new LayerInputProcessor() {
			
			@Override
			protected ILayer getLayer() {
				return layer;
			}
		};
	}
}
