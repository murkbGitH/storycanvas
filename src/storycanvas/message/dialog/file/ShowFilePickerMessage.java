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
package storycanvas.message.dialog.file;

import javafx.beans.property.StringProperty;
import net.kmycode.javafx.Message;

/**
 * ファイル選択を表示するメッセージ
 *
 * @author KMY
 */
public abstract class ShowFilePickerMessage extends Message {

	private final StringProperty selectedFileName;
	private final String fileType;
	private final String defaultPath;

	public ShowFilePickerMessage(StringProperty selectedFileName, String fileType, String defaultPath) {
		this.selectedFileName = selectedFileName;
		this.fileType = fileType;
		this.defaultPath = defaultPath;
	}

	public StringProperty selectedFileNameProperty() {
		return this.selectedFileName;
	}

	public String getFileType() {
		return this.fileType;
	}

	public String getDefaultPath() {
		return this.defaultPath;
	}

}
