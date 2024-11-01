package muscaa.chess.gui;

import java.util.function.Consumer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class Widgets {
	
	public static final String FONT_DEFAULT = "default-font";
	public static final String FONT_SMALL = "small-font";
	public static final String FONT_TITLE = "title-font";
	public static final int WIDTH = 500;
	public static final int HEIGHT = 50;
	
	public static VisTable table(boolean fillParent) {
		VisTable table = new VisTable();
		table.defaults()
				.pad(4.0F)
				;
		table.setFillParent(fillParent);
		return table;
	}
	
	public static Cell<?> empty(Table table, int size) {
		return table.add()
				.size(size, size)
				;
	}
	
	public static Cell<?> empty(Table table) {
		return empty(table, 25);
	}
	
	public static Cell<VisTextButton> button(Table table, String text, Consumer<VisTextButton> action, int width, int height) {
		VisTextButton button = new VisTextButton(text);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            	if (action != null) action.accept(button);
            }
        });
        return table.add(button)
        		.size(width, height)
        		;
	}
	
	public static Cell<VisTextButton> button(Table table, String text, Consumer<VisTextButton> action) {
		return button(table, text, action, WIDTH, HEIGHT);
	}
	
	public static Cell<VisLabel> label(Table table, String font, String text, Color color) {
		VisLabel label = new VisLabel(text, font, color);
		return table.add(label)
				;
	}
	
	public static Cell<VisSlider> slider(Table table, float min, float max, float step, float value, Consumer<VisSlider> action, int width, int height) {
		VisSlider slider = new VisSlider(min, max, step, false);
		slider.setValue(value);
		slider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (action != null) action.accept(slider);
			}
		});
		return table.add(slider)
				.size(width, height)
				;
	}
	
	public static Cell<VisSlider> slider(Table table, float min, float max, float step, float value, Consumer<VisSlider> action) {
		return slider(table, min, max, step, value, action, WIDTH, HEIGHT);
	}
	
	public static Cell<VisCheckBox> checkbox(Table table, String text, boolean value, Consumer<VisCheckBox> action, int width, int height) {
		VisCheckBox checkbox = new VisCheckBox(text, value);
		checkbox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (action != null) action.accept(checkbox);
			}
		});
		
		checkbox.clearChildren();
		checkbox.add(checkbox.getLabel());
		checkbox.add(checkbox.getImageStack());
		
		checkbox.getLabelCell().size(width - height, height);
		
		return table.add(checkbox)
				.size(width, height)
				;
	}
	
	public static Cell<VisCheckBox> checkbox(Table table, String text, boolean value, Consumer<VisCheckBox> action) {
		return checkbox(table, text, value, action, WIDTH, HEIGHT);
	}
}
