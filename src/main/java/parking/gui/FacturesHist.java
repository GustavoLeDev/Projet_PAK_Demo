package parking.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import parking.business.Facture;

import javax.swing.AbstractListModel;

public class FacturesHist extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args)
	{
		try
		{
			FacturesHist dialog = new FacturesHist();
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
	public FacturesHist()
	{
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));

		{
			JList<Facture> list = new JList<Facture>();
			list.setModel(new AbstractListModel<Facture>()
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public int getSize()
				{
					return Facture.getFactures().size();
				}

				public Facture getElementAt(int index)
				{
					return Facture.getFactures().get(index);
				}
			});

			list.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent evt)
				{
					@SuppressWarnings("unchecked")
					JList<Facture> list = (JList<Facture>) evt.getSource();
					if (evt.getClickCount() == 2)
					{
						FactureDialog fd = new FactureDialog((Facture) list.getSelectedValue());
						fd.setModal(true);
						fd.setVisible(true);
						fd.setModal(false);
					}
				}
			});
			contentPanel.add(list);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
