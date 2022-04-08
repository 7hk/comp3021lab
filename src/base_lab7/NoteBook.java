package base_lab7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			//this = n...
			folders = n.folders;
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1: folders) {
			if (f1.getName().equals(folderName)) {
				f = f1;
				break;
			}
		}
		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		
		for (Note n: f.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.getNotes().add(note);
		return true;
	}
	
	public void sortFolders() {
		//first sort notes for each folder
		for (Folder f1: folders) { 
			f1.sortNotes();
		}
		//then sort the folder list
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> result = new ArrayList<Note>();
		for (Folder folder: folders) {
			result.addAll(folder.searchNotes(keywords));
		}
		return result;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
