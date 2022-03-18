package base_lab4;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable {
	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;
	
	public NoteBook() {
		folders = new ArrayList<Folder> ();
	}
	public NoteBook( String file ) {
		try {
			FileInputStream fis = new FileInputStream( file );
			ObjectInputStream in  = new ObjectInputStream( fis );
			NoteBook n = (NoteBook)in.readObject();
			this.folders = new ArrayList<Folder>( n.folders ); // shallow
			in.close();
		} catch( Exception e ) {
			e.printStackTrace();			
		}
		
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
	public boolean save( String file ) {
		try {
			FileOutputStream fos = new FileOutputStream( file );
			ObjectOutputStream out = new ObjectOutputStream( fos );
			out.writeObject( this );
			out.close();
		} catch ( Exception e ) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
