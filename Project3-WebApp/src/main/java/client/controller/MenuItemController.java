package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import client.service.FaceClient;
import server.ServerMainHandler;

/**
 * Controller for the menu items used to connect to the server, start the server & close the application.
 */
public class MenuItemController {
	public MenuItemController(JMenuItem serverConnect, JMenuItem serverConsole, JMenuItem exit) {
		serverConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField host = new JTextField();
				host.setText("localhost");
				JTextField port = new JTextField();
				port.setText("8000");
				Object[] fields = { "Host Name", host, "Port", port };
				int input = JOptionPane.showConfirmDialog(null, fields, "Connect To Server",
						JOptionPane.OK_CANCEL_OPTION);
				if (input == JOptionPane.OK_OPTION) {
					try {
						FaceClient.connect(host.getText(), port.getText());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, FaceClient.isConnected() ? "Connected to " + host.getText()
							: "Not connected to " + host.getText());
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientController.close();
			}
		});
		serverConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ServerMainHandler();
			}
		});
	}
}
