package pck3;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
@SuppressWarnings({"serial"})
public class TrafficLight extends javax.swing.JFrame {

	public TrafficLight() {
		initComponents();
		MoveThread1 th = new MoveThread1();
		th.start();
		vector.add(new Ampel(Color.green));
		vector.add(new Ampel(Color.yellow));
		vector.add(new Ampel(Color.red));
	}



	Vector<Ampel> vector = new Vector<Ampel>();

	class Ampel {

		private Color color;

		public Ampel() {
		}

		public Ampel(Color color) {
			this.color = color;
		}

		/**
		 * @return the color
		 */
		public Color getColor() {
			return color;
		}

		/**
		 * @param color
		 *            the color to set
		 */
		public void setColor(Color color) {
			this.color = color;
		}
	}

	class MoveThread1 extends Thread {

		public void run() {
			Graphics g = panel.getGraphics();

			for (int i = 0; i < 8; i++) {
				try {
					sleep(1000);
				} catch (InterruptedException ex) {
					Logger.getLogger(TrafficLight.class.getName()).log(
							Level.SEVERE, null, ex);
				}
				g.setColor(Color.lightGray);
				g.fillOval(150, 10, 100, 100);
				g.fillOval(290, 10, 100, 100);
				g.fillOval(10, 10, 100, 100);
				if (i < 2) {// Grün
					g.setColor(vector.get(0).getColor());
					g.fillOval(10, 10, 100, 100);
				} else if (i < 4) {// Gelb
					g.setColor(vector.get(1).getColor());
					g.fillOval(150, 10, 100, 100);
				} else if (i < 8) {// Rot
					g.setColor(vector.get(2).getColor());
					g.fillOval(290, 10, 100, 100);
				}
				if (i + 1 == 8) {
					i = -1;// Reset
				}
				// }
			}
		}
	}

	private void initComponents() {

		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		panel = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Ampel");
		jPanel2.add(jLabel1);

		getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(
				panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 266,
				Short.MAX_VALUE));

		getContentPane().add(panel, java.awt.BorderLayout.CENTER);
		getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

		pack();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new TrafficLight().setVisible(true);
			}
		});
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel panel;

}