package covers1624.sheetcutter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by covers1624 on 10/28/2015.
 */
public class SheetCutter {

	public static int splitSize = 16;
	public static File curFolder = new File(System.getProperty("user.dir"));
	private static File outFolder;
	private static File inFile;
	private static boolean debug;

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			switch (arg) {
			case "-help":
			case "--help":
				System.out.println("Help for ItemSheetCutter:");
				System.out.println("-in <FileName> : Specifies the input file. it will only grab input files from the current directory.");
				System.out.println("-res <TexPackSize> : For example, normal minecraft assets are in 16x16 format, you may have a texture pack for 128x128, Specify that here or it will default to 16.");
				System.out.println("All cut textures will be placed in /out/<InFileName>/");
				System.exit(0);
			case "-in":
				inFile = new File(curFolder, args[i + 1]);
				break;
			case "-res":
				splitSize = Integer.valueOf(args[i + 1]);
				break;
			case "-debug":
				debug = true;
				break;
			}
		}
		if (inFile == null || !inFile.exists()){
			System.err.println("Please specify a file that exist with the arg -in <FileName>");
			System.exit(1);
		}
		outFolder = new File(curFolder, "out/" + inFile.getName().substring(0, inFile.getName().lastIndexOf(".")));
		if (!outFolder.exists()) {
			outFolder.mkdirs();
		}

		BufferedImage bufferedImage = getImage(inFile);
		if (bufferedImage == null) {
			return;
		}
		runCropLoop(bufferedImage, inFile.getName().substring(0, inFile.getName().lastIndexOf(".")));
	}

	public static BufferedImage crop(BufferedImage src, int x, int y) {
		BufferedImage dest = new BufferedImage(splitSize, splitSize, BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics graphics = dest.getGraphics();
		graphics.drawImage(src, 0, 0, splitSize, splitSize, x, y, x + splitSize, y + splitSize, null);
		graphics.dispose();
		return dest;
	}

	public static BufferedImage getImage(File fileToLoad) {
		if (!fileToLoad.exists() || fileToLoad.isDirectory()) {
			System.err.println();
			System.err.println("====ERROR====");
			System.err.println("Was unable to read InputFile..");
			System.err.println("====ERROR====");
			System.err.println();
			return null;
		}
		try {
			return ImageIO.read(fileToLoad);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void saveImage(BufferedImage image, String fileName) {
		try {
			ImageIO.write(image, "png", new File(outFolder, fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void runCropLoop(BufferedImage bufferedImage, String origionalFileName) {
		//2 16 size loops as we have 16 rows of 16 different images, uses split size.
		//U and V for my sanity as that is what the Tessellator for minecraft uses.
		int u = 0;
		int v = 0;
		for (int i = 0; i < 16; i++) {//V
			for (int j = 0; j < 16; j++) {//U
				//System.out.println(String.format("U: %s V: %s", u, v));
				saveImage(crop(bufferedImage, u, v), origionalFileName + "-" + v + "-" + u);
				u += splitSize;
			}
			u = 0;
			v += splitSize;
		}
	}
}
