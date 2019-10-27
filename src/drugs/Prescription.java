package drugs;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class Prescription implements ActionListener {
	private final PrescriptionList list;
	private String name;
	private int days;
	private int time;
	private int reminder;
	private int repeat;
	private int interval;
	private boolean enabled;
	
	public Prescription(PrescriptionList list) {
		this.list = list;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getDays() {
		return days;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getReminder() {
		return reminder;
	}
	
	public void setReminder(int reminder) {
		this.reminder = reminder;
	}
	
	public int getRepeat() {
		return repeat;
	}
	
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	
	public int getInterval() {
		return interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		JDialog dialog = new JDialog(list.getMainFrame(), "Edit Prescription", Dialog.ModalityType.APPLICATION_MODAL);
		
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
		
		JTextField textField = new JTextField(getName(), 30);
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
			checkBoxes[i] = new JCheckBox(days[i], (getDays() & 1 << i) == 1 << i);
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
		
		int h = (getTime() / 60) % 12;
		JSpinner spinnerHour = new JSpinner(new SpinnerNumberModel(h == 0 ? 12 : h, 1, 12, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerHour, gbc);
		
		JSpinner spinnerMinute = new JSpinner(new SpinnerNumberModel(getTime() % 60, 0, 59, 1));
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(spinnerMinute, gbc);
		
		JSpinner spinnerPeriod = new JSpinner(new SpinnerListModel(new String[] { "AM", "PM" }));
		spinnerPeriod.setValue(getTime() / (12 * 60) == 0 ? "AM" : "PM");
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
		
		JSpinner spinnerRemind = new JSpinner(new SpinnerNumberModel(getReminder(), 0, 120, 1));
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
		
		JSpinner spinnerRepeat = new JSpinner(new SpinnerNumberModel(getRepeat(), 0, 23, 1));
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
		
		JSpinner spinnerInterval = new JSpinner(new SpinnerNumberModel(getInterval(), 1, 10, 1));
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
		
		JButton button = new JButton("Save");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				int days = 0;
				for (int i = 0; i < checkBoxes.length; i++) {
					if (checkBoxes[i].isSelected()) {
						days |= (1 << i);
					}
				}
				
				int time = ((Integer) spinnerMinute.getValue()).intValue()
						+ (((Integer) spinnerHour.getValue()).intValue() % 12) * 60
						+ (spinnerPeriod.getValue().equals("PM") ? 12 * 60 : 0);
				
				setName(textField.getText());
				setDays(days);
				setTime(time);
				setReminder(((Integer) spinnerRemind.getValue()).intValue());
				setRepeat(((Integer) spinnerRepeat.getValue()).intValue());
				setInterval(((Integer) spinnerInterval.getValue()).intValue());
				
				list.updatePanel();
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
		
		JButton button2 = new JButton("Remove");
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				list.remove(Prescription.this);
				list.updatePanel();
				dialog.dispose();
			}
		});
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		dialogPanel.add(button2, gbc);
		
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
}
