package base_lab4;

import java.util.Date;
import java.io.Serializable;
import java.util.Objects;

public class Note implements Comparable<Note>, Serializable {
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;
	
	public Note( String title ) {
		this.title = new String( title );
		this.date = new Date( System.currentTimeMillis() );
	}
	public String getTitle() {
		return title;
	}
	@Override
	public int hashCode() {
		return Objects.hash( title );
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if( !(obj instanceof Note) )
			return false;
		Note ot = (Note) obj;
		return Objects.equals( title, ot.title );
	}
	public int compareTo( Note o ) {
		int r = date.compareTo( o.date );
		if( r > 0 ) return 1;
		else if( r < 0 ) return -1;
		else return 0;
	}
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
