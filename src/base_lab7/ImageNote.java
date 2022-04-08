package base_lab7;

import java.io.File;

public class ImageNote extends Note {
	private static final long serialVersionUID = 1L;//solve warning
	File image;
	
	public ImageNote(String title) {
		super(title);
	}
}