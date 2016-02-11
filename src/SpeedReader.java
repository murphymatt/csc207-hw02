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
	public static void main(String[] args) throws IOException,
			InterruptedException {

		// Check usage
		if (args.length != 5) {
			System.out.println("Usage: SpeedReader <filename> <width>"
					+ " <height> <font size> <wpm>");
			return;
		} // if

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

		while (generator.hasNext()) {
			// local variables for string and centering string
			String currentStr = generator.next();
			int strWidth = metrics.stringWidth(currentStr);
			int letterWidth = strWidth / currentStr.length();
			int fLetter = focusLetters(currentStr);
			int shiftFactor = letterWidth * fLetter;

			// Prints string as black, then focus letter as red
			graphicsContext.setColor(Color.black);
			graphicsContext.drawString(currentStr, centerX - shiftFactor,
					centerY);
			graphicsContext.setColor(Color.red);
			graphicsContext.drawString("" + currentStr.charAt(fLetter),
					centerX, centerY);

			// Sets reading pace to specified time
			Thread.sleep(60000 / wpm);
			panel.clear();
		} // while

		// reading statistics
		generator.in.close();
		graphicsContext.drawString("Words Read: " + generator.getWordCount(),
				20, 10 + fontHeight);
		graphicsContext.drawString(
				"Sentences Read: " + generator.getSentenceCount(), 20,
				10 + (fontHeight * 2));
		Thread.sleep(10000);
	} // void main(String[] args)

	/**
	 * @param str
	 * @return index for focus letter depending on str length
	 */
	public static int focusLetters(String str) {

		int len = str.length();

		if (len <= 1) {
			return 0;
		} else if (len <= 5) {
			return 1;
		} else if (len <= 9) {
			return 2;
		} else if (len <= 13) {
			return 3;
		} else {
			return 4;
		}
	} // int focusLetters(String str)
}
