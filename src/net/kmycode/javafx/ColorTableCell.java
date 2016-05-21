/*
 * KMY ライブラリ (LGPL)
 */
package net.kmycode.javafx;

import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * TableViewで色を表示する
 *
 * @author KMY
 */
public class ColorTableCell<E> extends TableCell<E, Color> {

	private static Color OPAQUE = new Color(0, 0, 0, 0);

	@Override
	protected void updateItem (Color item, boolean empty) {
		super.updateItem(item, empty);
		this.setText("");
		if (item != null) {
			this.setText("");
			this.setBackground(new Background(new BackgroundFill(item, CornerRadii.EMPTY, Insets.EMPTY)));
		}
		else {
			this.setBackground(new Background(new BackgroundFill(ColorTableCell.OPAQUE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}
}
