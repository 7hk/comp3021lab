package base;

import java.util.Date;
import java.util.Objects;

public class Note {
	private Date date;
	private String title;
	
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
	
}
