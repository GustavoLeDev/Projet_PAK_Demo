package parking.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public About()
	{
		setResizable(false);
		setBounds(100, 100, 639, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 131, 100, 154, 115, 119, 0 };
		gbl_contentPanel.rowHeights = new int[] { 123, 81, 42, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel(
					"Parking; Logiciel de gestion de parking");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 5;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel label = new JLabel("");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setIcon(new ImageIcon(About.class
						.getResource("/assets/Ste.png")));
				panel.add(label, BorderLayout.NORTH);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Stephen Abello");
				panel.add(lblNewLabel_1, BorderLayout.SOUTH);
			}
		}

		JPanel panel = new JPanel();
		panel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		{
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setIcon(new ImageIcon(About.class
					.getResource("/assets/Rob.png")));
			panel.add(label, BorderLayout.NORTH);
		}
		{
			JLabel lblRobinAlonzo = new JLabel("Robin Alonzo");
			panel.add(lblRobinAlonzo, BorderLayout.SOUTH);
		}
		{
			JPanel panel1 = new JPanel();
			GridBagConstraints gbc_panel1 = new GridBagConstraints();
			gbc_panel1.fill = GridBagConstraints.BOTH;
			gbc_panel1.insets = new Insets(0, 0, 5, 5);
			gbc_panel1.gridx = 2;
			gbc_panel1.gridy = 1;
			contentPanel.add(panel1, gbc_panel1);
			panel1.setLayout(new BorderLayout(0, 0));

			JLabel label = new JLabel("");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setIcon(new ImageIcon(About.class
					.getResource("/assets/Nab.png")));
			panel1.add(label, BorderLayout.NORTH);
			{
				JLabel lblNabilBoutemeur = new JLabel("Nabil Boutemeur");
				panel1.add(lblNabilBoutemeur, BorderLayout.SOUTH);
			}
		}
		{
			JPanel panel1 = new JPanel();
			GridBagConstraints gbc_panel1 = new GridBagConstraints();
			gbc_panel1.fill = GridBagConstraints.BOTH;
			gbc_panel1.insets = new Insets(0, 0, 5, 5);
			gbc_panel1.gridx = 3;
			gbc_panel1.gridy = 1;
			contentPanel.add(panel1, gbc_panel1);
			panel1.setLayout(new BorderLayout(0, 0));
			{
				JLabel label = new JLabel("");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setIcon(new ImageIcon(About.class
						.getResource("/assets/Adr.png")));
				panel1.add(label);
			}
			{
				JLabel lblAdrienBono = new JLabel("Adrien Bono");
				panel1.add(lblAdrienBono, BorderLayout.SOUTH);
			}
		}
		{
			JPanel panel1 = new JPanel();
			GridBagConstraints gbc_panel1 = new GridBagConstraints();
			gbc_panel1.fill = GridBagConstraints.BOTH;
			gbc_panel1.insets = new Insets(0, 0, 5, 0);
			gbc_panel1.gridx = 4;
			gbc_panel1.gridy = 1;
			contentPanel.add(panel1, gbc_panel1);
			panel1.setLayout(new BorderLayout(0, 0));
			{
				JLabel label = new JLabel("");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setIcon(new ImageIcon(About.class
						.getResource("/assets/Jul.png")));
				panel1.add(label, BorderLayout.NORTH);
			}
			{
				JLabel lblJulianArnaud = new JLabel("Julian Arnaud");
				lblJulianArnaud.setHorizontalAlignment(SwingConstants.LEFT);
				panel1.add(lblJulianArnaud, BorderLayout.SOUTH);
			}
		}
		{
			JLabel label = new JLabel("2015");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.SOUTH;
			gbc_label.gridwidth = 5;
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			contentPanel.add(label, gbc_label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
