package drugs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

import org.json.JSONException;

public class DrugsWindow {
	private final JFrame frame;
	
	public DrugsWindow() {
		frame = new JFrame("When Are My Drugs?");
		createWindow();
	}
	
	private void createWindow() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		frame.add(outerPanel);

		JPanel innerPanel = new JPanel(new GridBagLayout());
		PrescriptionList list = new PrescriptionList(frame, innerPanel);
		
		JScrollPane scrollPane = new JScrollPane(innerPanel);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		outerPanel.add(scrollPane, gbc);

		JButton addButton = new JButton("Add Prescription");
		addButton.addActionListener(list);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.weighty = 0;
		outerPanel.add(addButton, gbc);
		
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File file) {
				String[] parts = file.getName().split("\\.");
				return file.isDirectory() || (parts.length > 1 && parts[parts.length - 1].equalsIgnoreCase("json"));
			}

			@Override
			public String getDescription() {
				return "JSON files";
			}
		};
		
		JButton importButton = new JButton("Import");
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Import");
				fc.setFileFilter(filter);
				if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					try {
						list.fromJson(FileManager.readFile(fc.getSelectedFile()));
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(frame, "The file could not be found.", "File Not Found", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "The file could not be read.", "Error Reading File", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (JSONException e) {
						JOptionPane.showMessageDialog(frame, "The file could not be parsed.", "Error Parsing File", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		outerPanel.add(importButton, gbc);
		
		JButton exportButton = new JButton("Export");
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Export");
				fc.setFileFilter(filter);
				fc.setSelectedFile(new File("prescriptions.json"));
				if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					try {
						FileManager.writeFile(fc.getSelectedFile(), list.toJson());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "The file could not be saved.", "Error Saving File", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		});
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.SOUTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		outerPanel.add(exportButton, gbc);
		
		frame.setSize(400, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
