package base_lab4;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.List;

public class Folder implements Comparable<Folder>, Serializable {
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder( String s ) {
		name = new String( s );
		notes = new ArrayList<Note>();
	}
	public void addNote( Note n ) {
		notes.add( n );
	}
	public String getName() {
		return name;
	}
	public ArrayList<Note> getNotes() {
		return notes;
	}
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for( Note n : notes) {
			if( n instanceof TextNote ) {
				nText += 1;
			}
			if( n instanceof ImageNote ) {
				nImage += 1;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}
	@Override
	public int hashCode() {
		return Objects.hash( name );
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if( !(obj instanceof Folder) )
			return false;
		Folder ot = (Folder) obj;
		return Objects.equals( name, ot.name );
	}
	public int compareTo( Folder o ) {
		int r = name.compareTo( o.name );
		if( r > 0 ) return 1;
		else if( r < 0 ) return -1;
		else return 0;
	}
	public void sortNotes() {
		Collections.sort( notes );
	}
	
	public ArrayList<Note> searchNotes( String keywords ){
		// firstly ignore case
		keywords = keywords.toLowerCase();
		// split by spaces
		String[] keys = keywords.split("\\s+");
		ArrayList<Note> noteslist = new ArrayList<Note>();
		noteslist.addAll(notes);
		String theOr = "or";
		// loop
		for( int i = 0; i < keys.length ; i++ ) {
			if( keys[i+1].equals(theOr) ) {
				String k1 = keys[i];
				String k2 = keys[i+2];
				for( Note n : notes ) {
					if( n instanceof TextNote ) {
						TextNote tempnote = (TextNote) n;
						String n1 = tempnote.getTitle().toLowerCase();
						String n2 = tempnote.content.toLowerCase();
						if( !(n1.contains(k1) || n1.contains(k2) || n2.contains(k1) || n2.contains(k2)) ) {
							if( noteslist.contains(n) ) {
								noteslist.remove(n);
							}
						}
					} else {
						String n1 = n.getTitle().toLowerCase();
						if( !(n1.contains(k1) || n1.contains(k2)) ) {
							if( noteslist.contains(n) ) {
								noteslist.remove(n);
							}
						}
					}
				}
				i += 2;
			} else {
				String k = keys[i];
				for( Note n : notes ) {
					if( n instanceof TextNote ) {
						TextNote t_note = (TextNote)n;
						String n1 = t_note.getTitle().toLowerCase();
						String n2 = t_note.content.toLowerCase();
						if( !(n1.contains(k) || n2.contains(k)) ) {
							if( noteslist.contains(n) ) {
								noteslist.remove(n);
							}
						}
					} else {
						String n1 = n.getTitle().toLowerCase();
						if( !n1.contains(k) ) {
							if( noteslist.contains(n) ) {
								noteslist.remove(n);
							}
						}
					}
				}
			}
		}
		return noteslist;
	}
}

