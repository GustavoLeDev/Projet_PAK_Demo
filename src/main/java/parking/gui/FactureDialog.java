package parking.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import parking.business.Facture;
import parking.business.Vehicule;
import parking.business.Voiture;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

public class FactureDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Vehicule vehicule;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			FactureDialog dialog = new FactureDialog(new Voiture("sdf", "s", "sd", "sdf"));
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
	final JTextArea lblcont;
	public FactureDialog(parking.business.Vehicule arg)
	{
		this(new Facture(arg));
	}

	public FactureDialog(parking.business.Facture arg)
	{
		this();
		System.out.println(arg);
		lblcont.setText(arg.toString());		
	}
	
	public FactureDialog()
	{
		setResizable(false);
		setBounds(100, 100, 287, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblcont = new JTextArea("[cont]");
			lblcont.setWrapStyleWord(true);
			lblcont.setTabSize(4);
			lblcont.setMaximumSize(new Dimension(280, 200));
			lblcont.setText("<html></html>");
			contentPanel.add(lblcont);
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
			{
				JButton cancelButton = new JButton("Imprimer");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							lblcont.print();
							dispose();
						}
						catch (PrinterException e)
						{
							System.err.println(e.getLocalizedMessage());
						}
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
