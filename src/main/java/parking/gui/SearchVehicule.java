package parking.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchVehicule extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField immaT;
	private String immatriculation;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			SearchVehicule dialog = new SearchVehicule();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getImmat()
	{
		return immatriculation;
	}

	/**
	 * Create the dialog.
	 */
	public SearchVehicule()
	{
		setBounds(100, 100, 289, 126);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel label = new JLabel("Immatriculation");
			contentPanel.add(label);
		}
		{
			immaT = new JTextField();
			contentPanel.add(immaT);
			immaT.setColumns(10);
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
						if(immaT.getText().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Touts les champs sont requis", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
						}
						try
						{
							immatriculation = immaT.getText();
							setVisible(false);
						}
						catch (IllegalArgumentException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (SecurityException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean getValue()
	{
		return immatriculation.length() != 0;
	}

}
