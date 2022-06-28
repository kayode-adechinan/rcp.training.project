package com.asi.training.ui.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class MinimarkTranslator {

	public static void convert(Reader reader, Writer writer) throws IOException {

		BufferedReader lines = new BufferedReader(reader);

		String line;

		String title = String.valueOf(lines.readLine());

		writer.write("<html><head><title>");
		writer.write(title);
		writer.write("</title></head><body><h1>");
		writer.write("</h1><p>");

		while (null != (line = lines.readLine())) {
			if ("".equals(line)) {
				writer.write("</p><p>");
			} else {
				writer.write(line);
				writer.write('\n');
			}
		}
		writer.write("</p></body></html>");
		writer.flush();

	}

}
