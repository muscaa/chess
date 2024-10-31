package muscaa.chess.gui;

import java.util.function.Consumer;
import java.util.function.Function;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Widgets {
	
	public static void button(Function<Actor, Cell<? extends Actor>> addFunc, String text, Consumer<VisTextButton> action) {
		VisTextButton button = new VisTextButton(text);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
            	action.accept(button);
            }
        });
        addFunc.apply(button)
        		.size(button.getWidth() * 1.5F, button.getHeight() * 1.5F)
        		.row()
        		;
	}
}
