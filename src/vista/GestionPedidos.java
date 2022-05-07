package vista;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import controlador.ControladorGestionPedidos;


import java.awt.Font;

public class GestionPedidos extends JPanel {

	private static final long serialVersionUID = 1469803496498262897L;
	
	private static JPanel panelGestionPedidos;
	private static JLabel lbl_mesa;
	private static JLabel lbl_Para_llevar;
	private static JTable table;
	private static JButton btn_Mesa_1;
	private static JButton btn_Mesa_2;
	private static JButton btn_Mesa_3;
	private static JButton btn_Mesa_4;
	private static JButton btn_Mesa_8;
	private static JButton btn_Mesa_7;
	private static JButton btn_Mesa_6;
	private static JButton btn_Mesa_5;	
	private static JButton btn_Nuevo_Cliente;
	private static JButton btn_ajustes;
	private static JButton btn_volver;
	private static JButton btn_Pedido;

	private static int ancho = 800;
	private static int alto = 600;
	private static int posicionPanel_x = 100;
	private static int posicionPanel_y = 50;

	public GestionPedidos() {
		
		panelGestionPedidos = new JPanel();
		lbl_Para_llevar = new JLabel("Para llevar");
		lbl_mesa = new JLabel("En mesa");
		table = new JTable();
		btn_Mesa_1 = new JButton("Mesa 1 ");
		btn_Mesa_2 = new JButton("Mesa 2");		
		btn_Mesa_4 = new JButton("Mesa 4");		
		btn_Mesa_3 = new JButton("Mesa 3");		
		btn_Mesa_8 = new JButton("Mesa 8");
		btn_Mesa_7 = new JButton("Mesa 7");		
		btn_Mesa_6 = new JButton("Mesa 6");		
		btn_Mesa_5 = new JButton("Mesa 5");	
		btn_Nuevo_Cliente = new JButton("Nuevo Cliente");		
		btn_ajustes = new JButton("");	
		btn_volver = new JButton("Volver");
		btn_Pedido = new JButton("Ver Pedido");

		establecerManejador();		
		panelGestionPedidos.setVisible(false);
	}
	
	
	
	
	
	
	public static JPanel inicializarComponentes() {

		panelGestionPedidos.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelGestionPedidos.setBounds(posicionPanel_x, posicionPanel_y, ancho, alto);
		panelGestionPedidos.setLayout(null);
		
		btn_Mesa_1.setBounds(10, 70, 107, 23);
		panelGestionPedidos.add(btn_Mesa_1);
		

		btn_Mesa_2.setBounds(142, 70, 89, 23);
		panelGestionPedidos.add(btn_Mesa_2);

		btn_Mesa_4.setBounds(142, 110, 89, 23);
		panelGestionPedidos.add(btn_Mesa_4);
		
		btn_Mesa_3.setBounds(10, 110, 107, 23);
		panelGestionPedidos.add(btn_Mesa_3);
		
		btn_Mesa_8.setBounds(142, 192, 89, 23);
		panelGestionPedidos.add(btn_Mesa_8);
		

		btn_Mesa_7.setBounds(10, 192, 107, 23);
		panelGestionPedidos.add(btn_Mesa_7);
		

		btn_Mesa_6.setBounds(142, 152, 89, 23);
		panelGestionPedidos.add(btn_Mesa_6);
		
		btn_Mesa_5.setBounds(10, 152, 107, 23);
		panelGestionPedidos.add(btn_Mesa_5);
		
		lbl_mesa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_mesa.setBounds(83, 30, 70, 14);
		panelGestionPedidos.add(lbl_mesa);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table.setBounds(269, 74, 155, 145);
		panelGestionPedidos.add(table);
		
		btn_Nuevo_Cliente.setBounds(278, 46, 107, 23);
		panelGestionPedidos.add(btn_Nuevo_Cliente);
		
		btn_ajustes.setForeground(UIManager.getColor("Button.background"));
		btn_ajustes.setIcon(new ImageIcon("C:\\Users\\Ale\\Desktop\\Proyecto DAM\\Version2\\img\\settings (1).png"));
		btn_ajustes.setBounds(395, 46, 26, 24);
		panelGestionPedidos.add(btn_ajustes);
		
		btn_volver.setBounds(354, 9, 70, 19);
		panelGestionPedidos.add(btn_volver);
		
		lbl_Para_llevar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Para_llevar.setBounds(287, 30, 70, 14);
		panelGestionPedidos.add(lbl_Para_llevar);
		
		btn_Pedido.setBounds(142, 254, 89, 23);
		panelGestionPedidos.add(btn_Pedido);
		
		
		return panelGestionPedidos;		
	}
	
	public void establecerManejador() {			
		ControladorGestionPedidos controlador = new ControladorGestionPedidos(this);
		
		table.addMouseListener(controlador);
		btn_Mesa_1.addActionListener(controlador);
		btn_Mesa_2.addActionListener(controlador);
		btn_Mesa_3.addActionListener(controlador);
		btn_Mesa_4.addActionListener(controlador);
		btn_Mesa_8.addActionListener(controlador);
		btn_Mesa_7.addActionListener(controlador);
		btn_Mesa_6.addActionListener(controlador);
		btn_Mesa_5.addActionListener(controlador);	
		btn_Nuevo_Cliente.addActionListener(controlador);
		btn_ajustes.addActionListener(controlador);
		btn_volver.addActionListener(controlador);
		btn_Pedido.addActionListener(controlador);

	
	}






	public static JTable getTable() {
		return table;
	}






	public static JButton getBtn_Mesa_1() {
		return btn_Mesa_1;
	}






	public static JButton getBtn_Mesa_2() {
		return btn_Mesa_2;
	}






	public static JButton getBtn_Mesa_3() {
		return btn_Mesa_3;
	}






	public static JButton getBtn_Mesa_4() {
		return btn_Mesa_4;
	}






	public static JButton getBtn_Mesa_8() {
		return btn_Mesa_8;
	}






	public static JButton getBtn_Mesa_7() {
		return btn_Mesa_7;
	}






	public static JButton getBtn_Mesa_6() {
		return btn_Mesa_6;
	}






	public static JButton getBtn_Mesa_5() {
		return btn_Mesa_5;
	}






	public static JButton getBtn_Nuevo_Cliente() {
		return btn_Nuevo_Cliente;
	}






	public static JButton getBtn_ajustes() {
		return btn_ajustes;
	}






	public static JButton getBtn_volver() {
		return btn_volver;
	}






	public static JButton getBtn_Pedido() {
		return btn_Pedido;
	}
	
	
	
	
	

	
}