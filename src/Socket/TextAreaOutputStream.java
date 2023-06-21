/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Socket;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream {

	private final JTextArea txtArea;
    private final StringBuilder sb = new StringBuilder();
    
	public TextAreaOutputStream(JTextArea txtArea) {
		this.txtArea = txtArea;
	}

	@Override
	public void write(int b) throws IOException {
		if (b == '\r') {
            return;
        }
        if (b == '\n') {
            final String text = sb.toString() + "\n";

            txtArea.append(text);
            sb.setLength(0);
        } else {
            sb.append((char) b);
        }
	}
}