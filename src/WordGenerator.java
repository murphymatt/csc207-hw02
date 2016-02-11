import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordGenerator {
	
	public Scanner in;
	public int words;
	public int sentences;
	
	public WordGenerator(String filename) throws FileNotFoundException{
		this.in = new Scanner(new File(filename)); 
	}
	
	public boolean hasNext() throws IOException{
		return in.hasNext();
	}
	
	public String next() throws IOException{
		if(!in.hasNext()){return "";}
		
		String nextStr = in.next();
		char lastChar = nextStr.charAt(nextStr.length() - 1);
				
		this.words++;
		if(lastChar == '.' || lastChar == '!' || lastChar == '?') { 
		    this.sentences++;
		    // account for quotation marks in counting sentences
		} else if (lastChar == '\"') {
		    char prevChar = nextStr.charAt(nextStr.length() - 2);
		    if(prevChar == '.' || prevChar == '!' || prevChar == '?') { 
			    this.sentences++;
		    }
		}
		
		return nextStr;
	}
	
	public int getWordCount(){
		return this.words;
	}
	
	public int getSentenceCount(){
		return this.sentences;
	}
	
}
