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
package storycanvas.view.control.date;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import storycanvas.model.date.DateFormatException;
import storycanvas.model.date.StoryDate;

/**
 * FXML Controller class
 *
 * @author KMY
 */
public class StoryDatePicker extends GridPane implements Initializable {

	private final StoryDatePickerPopup popup = new StoryDatePickerPopup();

//<editor-fold defaultstate="collapsed" desc="プロパティ">
	/**
	 * 選択結果の日付.
	 */
	public StoryDate getDate () {
		return this.popup.getDate();
	}

	public void setDate (StoryDate value) {
		this.popup.setDate(value);
	}

	public ObjectProperty<StoryDate> dateProperty () {
		return this.popup.dateProperty();
	}
//</editor-fold>

	@FXML
	private Button popupButton;

	@FXML
	private Button delButton;

	@FXML
	private TextField dateFormat;

	// リスナーの中にいるフラグ（文字列と日付を相互変換するリスナがあるので、StackOverFlowを引き起こさないため）
	private boolean inDateFormatListener;

	public StoryDatePicker() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoryDatePicker.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		// 日付を設定したら、それをテキストフィールドに反映
		this.dateProperty().addListener(e -> {
			if (!this.inDateFormatListener) {
				this.inDateFormatListener = true;
				if (this.getDate() != null) {
					this.dateFormat.setText(this.getDate().toString());
				} else {
					this.dateFormat.setText("");
				}
				this.inDateFormatListener = false;
			}
		});

		this.popupButton.setOnAction((e) -> {
			// 日付選択ポップアップを表示
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			this.popup.show(this.popupButton, mousePoint.x, mousePoint.y);
		});

		this.delButton.setOnAction(e -> {
			// 日付をクリア
			this.setDate(null);
		});

		this.dateFormat.textProperty().addListener(e -> {
			// テキストボックスの日付を取得。エラーなく取得できれば文字色を黒に、そうでなければ赤にする
			if (!this.inDateFormatListener) {
				this.inDateFormatListener = true;
				StoryDate date = null;
				try {
					date = this.getDate().getCalendar().fromDateString(this.dateFormat.getText());
					this.setDate(date);
					this.dateFormat.setStyle("-fx-text-fill:black");
				} catch (DateFormatException ex) {
					this.dateFormat.setStyle("-fx-text-fill:red");
				}
				this.inDateFormatListener = false;
			}
		});

		// カレンダーでの日付指定を、テキストボックスの表示に反映
		this.popup.setOnAutoHide(e -> this.readDate());
	}

	private void readDate() {
		StoryDate date = this.popup.getDate();
		this.dateFormat.setText(date.toString());
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize (URL url, ResourceBundle rb) {
		// TODO
	}

}
