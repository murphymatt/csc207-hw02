import java.io.FileNotFoundException;
import java.lang.Thread;
import java.awt.*;

public class SpeedReader {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		// Check usage
		if(args.length != 5) {
			System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			return;
		}
		
		// Unpack command line arguments
		String filename = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);
		
		// Create drawing panel
		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics graphicsContext = panel.getGraphics();
		Font font = new Font("Courier", Font.BOLD, fontSize);
		graphicsContext.setFont(font);
		
		WordGenerator generator = new WordGenerator(filename);
		
		
		generator.in.close(); 
	}

}
