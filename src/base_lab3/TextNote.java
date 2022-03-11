package base_lab3;

public class TextNote extends Note {
	protected String content;
	
	public TextNote( String s ) {
		super( s );
		content = s;
	}
	public TextNote( String title, String content ) {
		super( title );
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	
}
