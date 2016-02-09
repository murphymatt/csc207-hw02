import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread;
import java.awt.*;

public class SpeedReader {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// Check usage
		if(args.length != 5) {
			System.out.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			return;
		}
		
		// Unpack command line arguments
		String filename = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		int centerX = width / 2;
		int centerY = height / 2;
		int fontSize = Integer.parseInt(args[3]);
		int wpm = Integer.parseInt(args[4]);
		
		// Create drawing panel
		DrawingPanel panel = new DrawingPanel(width, height);
		Graphics graphicsContext = panel.getGraphics();
		Font font = new Font("Courier", Font.BOLD, fontSize);
		graphicsContext.setFont(font);
		FontMetrics metrics = graphicsContext.getFontMetrics();
		int fontHeight = metrics.getHeight();
		centerY -= fontHeight;
		
		WordGenerator generator = new WordGenerator(filename);
		
		while(generator.hasNext()){
			String currentStr = generator.next();
			int strWidth = metrics.stringWidth(currentStr);

			graphicsContext.drawString(currentStr, centerX - (strWidth / 2), centerY);
			Thread.sleep(60000 / wpm);
			panel.clear();
		}
		generator.in.close();
		graphicsContext.drawString("Words Read: " + generator.getWordCount(), 100, 100);
		graphicsContext.drawString("Sentences Read: " + generator.getSentenceCount(), 100, 150);
		Thread.sleep(10000);
	}

}
