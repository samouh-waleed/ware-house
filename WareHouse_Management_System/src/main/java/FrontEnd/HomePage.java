package FrontEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
	JFrame f;

	public HomePage() {
		f = new JFrame();

		JLabel label = new JLabel("Select view");

		int labelX = (f.getWidth() - label.getPreferredSize().width) / 2;

		label.setBounds(labelX, 10, 380, 20);
		f.add(label);

		JButton b1 = new JButton("Admin");
		b1.setBounds(10, 40, 380, 40);

		f.add(b1);

		JButton b2 = new JButton("User");
		b2.setBounds(10, 80, 380, 40);

		f.add(b2);

		f.setSize(400, 200);
		f.setLayout(null);
		f.setVisible(true);

		labelX = (f.getWidth() - label.getPreferredSize().width) / 2;
		label.setBounds(labelX, 10, 380, 20);

		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				FrontLogin login = new FrontLogin();

				f.setVisible(false);
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ProductOrderingClient client = new ProductOrderingClient();
				f.setVisible(false);
			}
		});
	}
}
