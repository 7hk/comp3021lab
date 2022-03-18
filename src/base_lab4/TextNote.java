package base_lab4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note {
	protected String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote( String s ) {
		super( s );
		content = s;
	}
	public TextNote( String title, String content ) {
		super( title );
		this.content = content;
	}
	public TextNote( File f ) {
		super( f.getName() );
		this.content = getTextFromFile( f.getAbsolutePath() );
	}
	private String getTextFromFile( String absolutePath ) {
		StringBuilder tempResult = new StringBuilder();
		try {
			FileInputStream fis = new FileInputStream( absolutePath );
			InputStreamReader iread = new InputStreamReader( fis );
			BufferedReader bread = new BufferedReader( iread );
			String tempStr = "";
			while( (tempStr = bread.readLine()) != null ) {
				tempResult.append( tempStr );
			}
			bread.close();
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		return tempResult.toString();
	}
	public String getContent() {
		return content;
	}
	public void exportTextToFile( String pathFolder ) {
		if( pathFolder == "" ) {
			pathFolder = ".";
		}
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll( " ", "_" ) + ".txt" );
		try {
			FileWriter fw = new FileWriter( file.getAbsoluteFile() );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write( this.content );
			bw.close();
		} catch( FileNotFoundException e ) {
			e.printStackTrace();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
	}
}
