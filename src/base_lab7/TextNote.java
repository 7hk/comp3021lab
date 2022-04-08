package base_lab7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.InputStreamReader;

public class TextNote extends Note {
	private static final long serialVersionUID = 1L;//solve warning
	String content;
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			FileInputStream fis = new FileInputStream(absolutePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			result = in.readLine(); //anyway get everything
			in.close();
			fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "") {
			pathFolder = ".";
		}
		try {
			File file = new File(pathFolder + File.separator + this.getTitle().replace(' ', '_') + ".txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(content);
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}