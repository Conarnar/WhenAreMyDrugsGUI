package drugs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
	public static String readFile(File file) throws IOException {
		FileReader fr = null;
		try{
			fr = new FileReader(file);
			StringBuilder sb = new StringBuilder();
			int c;
			while ((c = fr.read()) != -1) {
				sb.append((char) c);
			}
			return sb.toString();
		} finally {
			if (fr != null) {
				fr.close();
			}
		}
	}
	
	public static void writeFile(File file, String text) throws IOException {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.print(text);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
}
