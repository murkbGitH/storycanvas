/*
 * Copyright (C) 2016 KMY
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package storycanvas.view.part.table;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import net.kmycode.javafx.ColorTreeTableCell;
import net.kmycode.javafx.ImageTreeTableCell;
import net.kmycode.javafx.Messenger;
import storycanvas.message.entity.list.init.MainPlaceTableInitializeMessage;
import storycanvas.message.entity.list.select.MainPlaceTableSelectItemMessage;
import storycanvas.model.entity.Place;

/**
 * 場所のリスト
 *
 * @author KMY
 */
public class PlaceTableViewController implements Initializable {

	private static PlaceTableViewController mainController = null;

	@FXML
	private TreeTableView<Place> placeTable;

	@FXML
	private TreeTableColumn<Place, String> orderColumn;

	@FXML
	private TreeTableColumn<Place, Image> iconColumn;

	@FXML
	private TreeTableColumn<Place, String> nameColumn;

	@FXML
	private TreeTableColumn<Place, Color> colorColumn;

	/**
	 * 自分をメインのリストとして設定します.
	 */
	public void toMain() {
		if (mainController == null) {
			mainController = this;

			// CellValueFactoryを設定
			this.orderColumn.setCellValueFactory(f -> new SimpleStringProperty(Long.toString(f.getValue().getValue().getOrder())));
			this.iconColumn.setCellValueFactory(f -> f.getValue().getValue().iconProperty());
			this.nameColumn.setCellValueFactory(f -> f.getValue().getValue().nameProperty());
			this.colorColumn.setCellValueFactory(f -> f.getValue().getValue().colorProperty());

			// セルファクトリを設定
			this.iconColumn.setCellFactory((TreeTableColumn<Place, Image> param) -> new ImageTreeTableCell<Place>(16, 16));
			this.colorColumn.setCellFactory((TreeTableColumn<Place, Color> param) -> new ColorTreeTableCell<Place>());

			// メッセンジャにイベントを登録
			Messenger.getInstance().apply(MainPlaceTableInitializeMessage.class, this, (m) -> {
				this.placeTable.setRoot(m.getRootTreeItem());
				m.selectedItemProperty().bind(this.placeTable.getSelectionModel().selectedItemProperty());
			});

			// 特定のアイテムを選択するメッセージが来た時の対応
			Messenger.getInstance().apply(MainPlaceTableSelectItemMessage.class, this, (m) -> {
				this.placeTable.getSelectionModel().select(m.getItem());
			});
		}
	}

	/**
	 * 何も選んでいない状態にします.
	 */
	public void unselect() {
		this.placeTable.getSelectionModel().clearSelection();
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize (URL url, ResourceBundle rb) {
		// TODO
	}

}
