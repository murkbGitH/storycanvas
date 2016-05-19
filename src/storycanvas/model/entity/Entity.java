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
package storycanvas.model.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import storycanvas.resource.Resources;

/**
 * エンティティをあらわす抽象クラス
 * 
 * @author KMY
 */
public abstract class Entity implements Comparable<Entity> {

//<editor-fold defaultstate="collapsed" desc="プロパティ">
	/**
	 * エンティティのID.
	 */
	private final IntegerProperty id = new SimpleIntegerProperty();

	public int getId () {
		return id.get();
	}

	public void setId (int value) {
		id.set(value);
	}

	public IntegerProperty idProperty () {
		return id;
	}

	/**
	 * エンティティの名前.
	 */
	private final StringProperty name = new SimpleStringProperty();

	public String getName () {
		return name.get();
	}

	public void setName (String value) {
		name.set(value);
	}

	public StringProperty nameProperty () {
		return name;
	}

	/**
	 * エンティティのアイコン.
	 */
	private final ObjectProperty<Image> icon = new SimpleObjectProperty<>(this.getDefaultIcon());

	public Image getDefaultIcon () {
		return Resources.getLargeIcon(this.getResourceName());
	}

	public Image getIcon () {
		return icon.get();
	}

	public void setIcon (Image value) {
		icon.set(value);
	}

	public ObjectProperty<Image> iconProperty () {
		return icon;
	}

	/**
	 * エンティティの順番.
	 */
	private final IntegerProperty order = new SimpleIntegerProperty();

	public int getOrder () {
		return order.get();
	}

	public void setOrder (int value) {
		order.set(value);
	}

	public IntegerProperty orderProperty () {
		return order;
	}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="メソッド">
	/**
	 * そのエンティティの、リソース上の名前を取得します。
	 * 例えば人物の場合、「person」が返ります。
	 * @return リソース上の名前
	 */
	protected abstract String getResourceName();

	/**
	 * エンティティ同士の比較。一覧表示するときの初期配置に影響することを想定します。
	 * @param other 比較対象
	 * @return 比較結果
	 */
	@Override
	public int compareTo(Entity other) {
		return this.getOrder() > other.getOrder() ? 1 : this.getOrder() == other.getOrder() ? 0 : -1;
	}
//</editor-fold>

}
