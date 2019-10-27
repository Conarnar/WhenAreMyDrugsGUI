package drugs;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrescriptionList implements ActionListener {
	private List<Prescription> prescriptions = new ArrayList<>();
	private final JFrame mainFrame;
	private final JPanel panel;
	
	public PrescriptionList(JFrame mainFrame, JPanel panel) {
		this.mainFrame = mainFrame;
		this.panel = panel;
	}

	public void updatePanel() {
		panel.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		for (int i = 0; i < prescriptions.size(); i++) {
			Prescription prescription = prescriptions.get(i);
			JCheckBox checkBox = new JCheckBox("", prescription.getEnabled());
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					prescription.setEnabled(checkBox.isSelected());
				}
			});
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.PAGE_START;
			panel.add(checkBox, gbc);
			JLabel label = new JLabel(prescription.getName());
			gbc.gridx = 1;
			gbc.gridy = i;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.PAGE_START;
			panel.add(label, gbc);
			JButton button = new JButton("Edit");
			button.addActionListener(prescription);
			gbc.gridx = 2;
			gbc.gridy = i;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.anchor = GridBagConstraints.PAGE_START;
			panel.add(button, gbc);
		}
		JLabel label = new JLabel("");
		gbc.gridx = 1;
		gbc.gridy = prescriptions.size();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(label, gbc);
		panel.updateUI();
	}
	
	public JFrame getMainFrame() {
		return mainFrame;
	}
	
	public void remove(Prescription prescription) {
		prescriptions.remove(prescription);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		JDialog dialog = new JDialog(mainFrame, "Add Prescription", Dialog.ModalityType.APPLICATION_MODAL);
		
		JPanel dialogPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel nameLabel = new JLabel("Drug name: ");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(nameLabel, gbc);
		
		JTextField textField = new JTextField(30);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 6;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(textField, gbc);
		
		String[] days = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		JCheckBox[] checkBoxes = new JCheckBox[7];
		
		for (int i = 0; i < days.length; i++) {
			checkBoxes[i] = new JCheckBox(days[i]);
			gbc.fill = GridBagConstraints.NONE;
			gbc.gridx = i;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 1;
			dialogPanel.add(checkBoxes[i], gbc);
		}
		
		JLabel timeLabel = new JLabel("Time: ");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(timeLabel, gbc);
		
		JSpinner spinnerHour = new JSpinner(new SpinnerNumberModel(12, 1, 12, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerHour, gbc);
		
		JSpinner spinnerMinute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerMinute, gbc);
		
		JSpinner spinnerPeriod = new JSpinner(new SpinnerListModel(new String[] { "AM", "PM" }));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerPeriod, gbc);
		
		JLabel remindLabel = new JLabel("Early reminder: ");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(remindLabel, gbc);
		
		JSpinner spinnerRemind = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerRemind, gbc);
		
		JLabel remindLabel2 = new JLabel("minutes");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(remindLabel2, gbc);
		
		JLabel repeatLabel = new JLabel("Repeat");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(repeatLabel, gbc);
		
		JSpinner spinnerRepeat = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerRepeat, gbc);
		
		JLabel repeatLabel2 = new JLabel("times in");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(repeatLabel2, gbc);
		
		JSpinner spinnerInterval = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerInterval, gbc);
		
		JLabel repeatLabel3 = new JLabel("hour intervals");
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(repeatLabel3, gbc);
		
		JButton button = new JButton("Add");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Prescription prescription = new Prescription(PrescriptionList.this);
				
				int days = 0;
				for (int i = 0; i < checkBoxes.length; i++) {
					if (checkBoxes[i].isSelected()) {
						days |= (1 << i);
					}
				}
				
				int time = ((Integer) spinnerMinute.getValue()).intValue()
						+ (((Integer) spinnerHour.getValue()).intValue() % 12) * 60
						+ (spinnerPeriod.getValue().equals("PM") ? 12 * 60 : 0);
				
				prescription.setName(textField.getText());
				prescription.setDays(days);
				prescription.setTime(time);
				prescription.setReminder(((Integer) spinnerRemind.getValue()).intValue());
				prescription.setRepeat(((Integer) spinnerRepeat.getValue()).intValue());
				prescription.setInterval(((Integer) spinnerInterval.getValue()).intValue());
				prescription.setEnabled(true);
				
				prescriptions.add(prescription);
				updatePanel();
				dialog.dispose();
			}
		});
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(button, gbc);
		
		JButton button3 = new JButton("Cancel");
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				dialog.dispose();
			}
		});
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(button3, gbc);
		
		dialog.add(dialogPanel);
		
		dialog.setSize(600, 300);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	public String toJson() throws JSONException {
		JSONArray json = new JSONArray();
		
		for (Prescription prescription: prescriptions) {
			JSONObject obj = new JSONObject();
			obj.put("name", prescription.getName());
			obj.put("days", prescription.getDays());
			obj.put("time", prescription.getTime());
			obj.put("reminder", prescription.getReminder());
			obj.put("repeat", prescription.getRepeat());
			obj.put("interval", prescription.getInterval());
			obj.put("enabled", prescription.getEnabled());
			json.put(obj);
		}
		
		return json.toString();
	}
	
	public void fromJson(String jString) throws JSONException {
		JSONArray array = new JSONArray(jString);
		prescriptions.clear();
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			Prescription prescription = new Prescription(this);
			prescription.setName(obj.getString("name"));
			prescription.setDays(obj.getInt("days"));
			prescription.setTime(obj.getInt("time"));
			prescription.setReminder(obj.getInt("reminder"));
			prescription.setRepeat(obj.getInt("repeat"));
			prescription.setInterval(obj.getInt("interval"));
			prescription.setEnabled(obj.getBoolean("enabled"));
			prescriptions.add(prescription);
		}
		
		updatePanel();
	}
}
