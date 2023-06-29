package parking.gui;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import parking.business.Constante;
import parking.business.Facture;
import parking.business.Parking;
import parking.exception.PasAssezDObservateurException;
import parking.exception.PlaceOccupeeException;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class ParkingUI
{
	private JFrame frame;
	private Parking parking = Parking.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ParkingUI window = new ParkingUI();
					window.frame.setVisible(true);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private List<PlaceButton> placeButtons = new ArrayList<PlaceButton>();

	/**
	 * Create the application.
	 * 
	 * @throws PasAssezDObservateurException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public ParkingUI() throws PasAssezDObservateurException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws PasAssezDObservateurException
	 */
	private void initialize() throws PasAssezDObservateurException
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 828, 491);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				Facture.save();
			}
		});
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Imprimer");
		mntmNewMenuItem_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				final BufferedImage img = new BufferedImage(frame.getWidth(),
						frame.getHeight(), BufferedImage.TYPE_INT_BGR);
				frame.paint(img.getGraphics());
				PrinterJob pj = PrinterJob.getPrinterJob();
				pj.setPrintable(new Printable()
				{
					@Override
					public int print(Graphics arg0, PageFormat arg1, int arg2)
							throws PrinterException
					{
						if (arg2 != 0) return NO_SUCH_PAGE;
						arg0.drawImage(img, 0, 0, img.getWidth(),
								img.getHeight(), null);
						return PAGE_EXISTS;
					}

				});
				try
				{
					pj.print();
				}
				catch (PrinterException e)
				{
					JOptionPane.showMessageDialog(frame,
							"Impression impossible, le fichier sera sauvegardé :\n"
									+ e.getMessage());
					try
					{
						ImageIO.write(img, "png", new File(randStr() + ".png"));
					}
					catch (IOException e1)
					{
						System.err.println("Couldn't save screenshot");
					}
				}
			}
		});
		mnFichier.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.LEFT);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Quitter");
		mntmNewMenuItem_3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispatchEvent(new WindowEvent(frame,
						WindowEvent.WINDOW_CLOSING));
			}
		});

		JMenuItem menuItem = new JMenuItem("A propos");
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				About a = new About();
				a.setModal(true);
				a.setLocationRelativeTo(null);
				a.setVisible(true);
			}
		});
		mnFichier.add(menuItem);
		mnFichier.add(mntmNewMenuItem_3);

		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);

		JMenuItem mntmAjouterUnVhicule = new JMenuItem("Ajouter un véhicule");
		mntmAjouterUnVhicule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AddVehicule av = new AddVehicule();
				av.setModal(true);
				av.setVisible(true);
				if (!av.getValue()) return;
				try
				{
					Parking.getInstance().park(av.getVehicule());
				}
				catch (PlaceOccupeeException e)
				{
					JOptionPane.showMessageDialog(null,
							"Plus de place disponible", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnActions.add(mntmAjouterUnVhicule);

		JMenuItem mntmChercherUnVhicule = new JMenuItem("Chercher un véhicule");
		mntmChercherUnVhicule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				SearchVehicule sv = new SearchVehicule();
				sv.setModal(true);
				sv.setVisible(true);
				if (!sv.getValue()) return;
				int location = Parking.getInstance().getLocation(sv.getImmat());
				if (location == -1)
				{
					JOptionPane.showMessageDialog(null,
							"Le vehicule recherché est introuvable", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null,
							"Le vehicule se trouve à la place n°" + location,
							"Recherche", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		mnActions.add(mntmChercherUnVhicule);

		mntmAjouterUnVhicule = new JMenuItem("Afficher les factures");
		mntmAjouterUnVhicule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				FacturesHist fd = new FacturesHist();
				fd.setModal(true);
				fd.setVisible(true);
				fd.setModal(false);
			}
		});
		mnActions.add(mntmAjouterUnVhicule);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		for (int i = 0; i < Constante.nbPlaceParticulier
				+ Constante.nbPlaceTranporteur; ++i)
			placeButtons.add(new PlaceButton());

		parking.observePlaces(placeButtons);
		for (PlaceButton pb : placeButtons)
		{
			JPanel jp = new JPanel();
			if (pb.getPlace().isTransporteur()) pb
					.setPreferredSize(new Dimension(32, 90));
			else pb.setPreferredSize(new Dimension(32, 64));
			jp.setPreferredSize(new Dimension(32, 95));

			jp.add(pb);
			panel.add(jp);
		}
		frame.setLocationRelativeTo(null);
	}

	private static String randStr()
	{
		return "Crash_dmp_" + new Random().nextInt();
	}
}
