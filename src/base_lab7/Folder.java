package base_lab7;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Arrays; //solve warnings
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note note: notes) {
			if (note instanceof TextNote) nText++;
			else if (note instanceof ImageNote) nImage++;
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> result = new ArrayList<Note>(notes);//shallow copy
		List<Note> temp = new ArrayList<Note>(); 
		
		keywords = keywords.toLowerCase(); //case-insensitive
		String[]parts = keywords.split(" ");
		
		String c1,c2; //at most 2 components
		for (int i=0; i<parts.length; ++i) {
			c1 = parts[i];
			if (i < parts.length-1 && parts[i+1].equalsIgnoreCase("or")) {
				i+=2;
				c2 = parts[i];
				//A or B				
				for (Note note: result) {
					if (note instanceof TextNote) {
						TextNote thisNote = (TextNote) note;
						String thisTitle = thisNote.getTitle().toLowerCase();
						String thisContent = thisNote.content.toLowerCase();
						if (thisTitle.contains(c1) || thisTitle.contains(c2) || thisContent.contains(c1) || thisContent.contains(c2)) {
							temp.add(thisNote);
						}
					}
					else if (note instanceof ImageNote) {
						String thisTitle = note.getTitle().toLowerCase();
						if (thisTitle.contains(c1) || thisTitle.contains(c2)) {
							temp.add(note);
						}
					}
				}
				result = temp;
				temp = new ArrayList<Note>();
			}else {
				//A
				for (Note note: result) {
					if (note instanceof TextNote) {
						TextNote thisNote = (TextNote) note;
						if (thisNote.getTitle().toLowerCase().contains(c1) || thisNote.content.toLowerCase().contains(c1)) {
							temp.add(thisNote);
						}
					}
					else if (note instanceof ImageNote) {
						if (note.getTitle().toLowerCase().contains(c1)) {
							temp.add(note);
						}
					}
				}
				result = temp;
				temp = new ArrayList<Note>();
			}
		}
		return result;
	}
}