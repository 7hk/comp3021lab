package base_lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder> ();
	}
	public boolean createTextNote( String folderName, String title ) {
		TextNote note = new TextNote( title );
		return insertNote( folderName, note );
	}
	public boolean createImageNote( String folderName, String title ) {
		ImageNote note = new ImageNote( title );
		return insertNote( folderName, note );
	}
	public boolean insertNote( String folderName, Note note ) {
		Folder f = null;
		for( Folder ff : folders ) {
			if( ff.getName().equals( folderName ) ) {
				f = ff;
				break;
			}
		}
		if( f == null ) {
			f = new Folder( folderName );
			//f.addNote( note );
			folders.add( f );
			//return true;
		}
		for( Note n : f.getNotes() ) {
			if( n.equals( note ) ) {
				System.out.println( "Creating note " + note.getTitle() + " under folder " + folderName + " failed" );
				return false;
			}
		}
		f.addNote( note );
		return true;
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	public void sortFolders() {
		for( Folder f : folders ) {
			f.sortNotes();
		}
		Collections.sort( folders );
	}
	public List<Note> searchNotes( String keywords ) {
		List<Note> rtn = new ArrayList<Note>();
		for( Folder f : folders ) {
			rtn.addAll( f.searchNotes( keywords ) );
		}
		return rtn;
	}
	public boolean createTextNote( String fname, String title, String content ) {
		TextNote note = new TextNote( title, content );
		return insertNote( fname, note );
	}
}
