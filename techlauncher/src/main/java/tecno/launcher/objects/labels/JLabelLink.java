package tecno.launcher.objects.labels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JLabelLink extends JLabel {
	String url;
	
	public JLabelLink() {
		super();
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setForeground(Color.blue);
		Font font = getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		setFont(font.deriveFont(attributes));
		
		addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                	System.out.println("Opening url: " + url);
                    Desktop.getDesktop().browse(new URI(url));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
	}
	
	public void setURL(String URL) {
		this.url = URL;
	}
}
