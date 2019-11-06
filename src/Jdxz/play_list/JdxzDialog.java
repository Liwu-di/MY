package Jdxz.play_list;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JdxzDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public JdxzDialog() {
		setAlwaysOnTop(true);
		setIconImage(new ImageIcon("jdxz.png").getImage());
		setSize(350, 115);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily = new JTextField();
			txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily.setEditable(false);
			txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily.setForeground(new Color(255, 0, 0));
			txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily.setHorizontalAlignment(SwingConstants.CENTER);
			// txtAreYouSure.setText("Are You Sure To Clearn History?");
			contentPanel.add(txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily);
			txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				buttonPane.add(panel);
				{
					okButton = new JButton("OK");
					panel.add(okButton);
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				{
					cancelButton = new JButton("Cancel");
					panel.add(cancelButton);
					cancelButton.setActionCommand("Cancel");
				}
			}
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setText(String string) {
		txtAreYouSureToClearTheAllRecordAndItCannotBefoundBcakEasily.setText(string);
	}

}
