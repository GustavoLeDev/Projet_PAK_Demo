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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import parking.business.Vehicule;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AddVehicule extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField immaT;
	private JTextField modelT;
	private JTextField marcT;
	private JTextField propT;
	private Vehicule vehicule;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			AddVehicule dialog = new AddVehicule();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Vehicule getVehicule()
	{
		return vehicule;
	}

	/**
	 * Create the dialog.
	 */
	public AddVehicule()
	{
		setBounds(100, 100, 287, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 2, 0, 0));
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
			JLabel label = new JLabel("Modele");
			contentPanel.add(label);
		}
		{
			modelT = new JTextField();
			contentPanel.add(modelT);
			modelT.setColumns(10);
		}
		{
			JLabel label = new JLabel("Marque");
			contentPanel.add(label);
		}
		{
			marcT = new JTextField();
			contentPanel.add(marcT);
			marcT.setColumns(10);
		}
		{
			JLabel label = new JLabel("Propriétaire");
			contentPanel.add(label);
		}
		{
			propT = new JTextField();
			contentPanel.add(propT);
			propT.setColumns(10);
		}
		{
			JLabel lblType = new JLabel("Type de véhicule");
			contentPanel.add(lblType);
		}
		final JComboBox<String> comboBox;
		{
			comboBox = new JComboBox<String>();

			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Moto",
					"Voiture", "Camion" }));
			contentPanel.add(comboBox);
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
						if(immaT .getText().length() == 0	||
						   modelT.getText().length() == 0	||
						   marcT .getText().length() == 0	||
						   propT .getText().length() == 0)
						{
							JOptionPane.showMessageDialog(null, "Touts les champs sont requis", "Erreur", JOptionPane.ERROR_MESSAGE);
							return;
						}
						String cn = "parking.business." + (String)comboBox.getSelectedItem();
						try
						{
							Constructor<?> constrictor = Class.forName(cn).getConstructor(String.class, String.class, String.class, String.class);
							vehicule = (Vehicule) constrictor.newInstance(new Object[]
									{
										immaT.getText(),
										modelT.getText(),
										marcT.getText(),
										propT.getText()
									});
							setVisible(false);
						}
						catch (InstantiationException e)
						{
							e.printStackTrace();
						}
						catch (IllegalAccessException e)
						{
							e.printStackTrace();
						}
						catch (IllegalArgumentException e)
						{
							e.printStackTrace();
						}
						catch (InvocationTargetException e)
						{
							e.printStackTrace();
						}
						catch (NoSuchMethodException e)
						{
							e.printStackTrace();
						}
							catch (SecurityException e)
						{
							e.printStackTrace();
						}
							catch (ClassNotFoundException e)
						{
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
		return vehicule != null;
	}

}
