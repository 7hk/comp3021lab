package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
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
	
}
