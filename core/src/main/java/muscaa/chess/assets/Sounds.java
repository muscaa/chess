package muscaa.chess.assets;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	private static final List<Sound> REG = new LinkedList<>();
	
	public static final Sound MOVE = create("sounds/move.ogg");
	public static final Sound CAPTURE = create("sounds/capture.ogg");
	public static final Sound NOTIFY = create("sounds/notify.ogg");
	
	private static Sound create(String path) {
		Sound s = Gdx.audio.newSound(Gdx.files.internal(path));
		REG.add(s);
		return s;
	}
	
	public static void init() {}
	
	public static void dispose() {
		for (Sound s : REG) {
			s.dispose();
		}
		REG.clear();
	}
}
