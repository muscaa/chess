package muscaa.chess.gui;

import java.util.function.Consumer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Widgets {
	
	public static VisTable table(boolean fillParent) {
		VisTable table = new VisTable();
		table.defaults()
				.pad(4.0F)
				;
		table.setFillParent(fillParent);
		return table;
	}
	
	public static Cell<?> empty(Table table) {
		return table.add()
				.size(20.0F, 20.0F)
				;
	}
	
	public static Cell<VisTextButton> button(Table table, String text, Consumer<VisTextButton> action) {
		VisTextButton button = new VisTextButton(text);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
            	if (action != null) action.accept(button);
            }
        });
        return table.add(button)
        		.size(500.0F, 50.0F)
        		;
	}
}
