package vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import controlador.ControladorGestionPedidos;
import modelo.ModeloCliente;
import modelo_bbdd.BbddVentas;

public class GestionPedidos extends JPanel {

	private static final long serialVersionUID = 1469803496498262897L;
	
	private static JPanel panelGestionPedidos;
	private static JLabel lbl_mesa;
	private static JLabel lbl_Para_llevar;
	private static JButton btn_Mesa_1;
	private static JButton btn_Mesa_2;
	private static JButton btn_Mesa_3;
	private static JButton btn_Mesa_4;
	private static JButton btn_Mesa_8;
	private static JButton btn_Mesa_7;
	private static JButton btn_Mesa_6;
	private static JButton btn_Mesa_5;	
	private static JButton btn_Nuevo_Cliente;
	private static JButton btn_Editar_Cliente;
	private static JButton btn_Ver_Pedido;
	private static JButton btn_volver;
	private static JList listaCliente;
    private static ArrayList<ModeloCliente> array_clientes;
    private static DefaultListModel modeloListaCliente;
    private static JScrollPane scroll;    

	public GestionPedidos() {
		super();
		inicializarComponentes();
		establecerManejador();
		creaListaClientes();
	}


	
	public void inicializarComponentes() {		
		
		panelGestionPedidos = VentanaPrincipal.parametrosPanel(800,600);
		panelGestionPedidos.setVisible(false);

		btn_Mesa_1 = VentanaPrincipal.parametrosJButton("Mesa 1",70,130,120,40);		
		panelGestionPedidos.add(btn_Mesa_1);
		
		btn_Mesa_2 = VentanaPrincipal.parametrosJButton("Mesa 2",70, 230, 120,40);
		panelGestionPedidos.add(btn_Mesa_2);
		
		btn_Mesa_3 = VentanaPrincipal.parametrosJButton("Mesa 3",70, 330, 120,40);
		panelGestionPedidos.add(btn_Mesa_3);
		
		btn_Mesa_4 = VentanaPrincipal.parametrosJButton("Mesa 4",70, 430, 120,40);
		panelGestionPedidos.add(btn_Mesa_4);

		btn_Mesa_5 = VentanaPrincipal.parametrosJButton("Mesa 5",230, 130, 120,40);
		panelGestionPedidos.add(btn_Mesa_5);

		btn_Mesa_6 = VentanaPrincipal.parametrosJButton("Mesa 6",230, 230, 120,40);
		panelGestionPedidos.add(btn_Mesa_6);
		
		btn_Mesa_7 = VentanaPrincipal.parametrosJButton("Mesa 7",230, 330, 120,40);
		panelGestionPedidos.add(btn_Mesa_7);
		
		btn_Mesa_8 = VentanaPrincipal.parametrosJButton("Mesa 8",230, 430, 120,40);
		panelGestionPedidos.add(btn_Mesa_8);

		lbl_mesa = VentanaPrincipal.parametrosJlabel("En mesa",180, 30, 120, 40);
		lbl_mesa.setFont(new Font("Manche Condensed",Font.BOLD,(int)(20*VentanaPrincipal.getCordenadaY())));
		panelGestionPedidos.add(lbl_mesa);
		
		lbl_Para_llevar = VentanaPrincipal.parametrosJlabel("Para llevar", 450, 30, 120,40);
		lbl_Para_llevar.setFont(new Font("Manche Condensed",Font.BOLD,(int)(20*VentanaPrincipal.getCordenadaY())));
		panelGestionPedidos.add(lbl_Para_llevar);	
		
		btn_Nuevo_Cliente = VentanaPrincipal.parametrosJButton("Nuevo Cliente",450, 80, 220,40);
		panelGestionPedidos.add(btn_Nuevo_Cliente);
		
		btn_Editar_Cliente = VentanaPrincipal.parametrosJButton("",690, 80, 40,40);
		btn_Editar_Cliente.setForeground(UIManager.getColor("Button.background"));
		btn_Editar_Cliente.setIcon(new ImageIcon("img/settings.png"));
		btn_Editar_Cliente.setEnabled(false);
		panelGestionPedidos.add(btn_Editar_Cliente);
		
		btn_volver = VentanaPrincipal.parametrosJButton("Volver", 710, 20, 70, 20);
		panelGestionPedidos.add(btn_volver);		

		btn_Ver_Pedido = VentanaPrincipal.parametrosJButton("Ver Pedido",540, 500, 120, 40);
		panelGestionPedidos.add(btn_Ver_Pedido);

		listaCliente = new JList();
		listaCliente.setFont(new Font("Manche Condensed",Font.BOLD,(int)(16*VentanaPrincipal.getCordenadaY())));
		listaCliente.setBackground(VentanaPrincipal.getAzulClaro());
		listaCliente.setLayout(null);
		listaCliente.setVisible(true);		
		
		scroll = VentanaPrincipal.parametrosJScrollPane(450, 130, 280, 340);
        scroll.setViewportView(listaCliente);
        panelGestionPedidos.add(scroll);	
	}
	
	public void establecerManejador() {			
		ControladorGestionPedidos controlador = new ControladorGestionPedidos(this);
		btn_Mesa_1.addMouseListener(controlador);
		btn_Mesa_2.addMouseListener(controlador);
		btn_Mesa_3.addMouseListener(controlador);
		btn_Mesa_4.addMouseListener(controlador);
		btn_Mesa_8.addMouseListener(controlador);
		btn_Mesa_7.addMouseListener(controlador);
		btn_Mesa_6.addMouseListener(controlador);
		btn_Mesa_5.addMouseListener(controlador);		
		btn_Nuevo_Cliente.addMouseListener(controlador);
		btn_Editar_Cliente.addMouseListener(controlador);
		btn_volver.addMouseListener(controlador);
		btn_Ver_Pedido.addMouseListener(controlador);		
		listaCliente.addMouseListener(controlador);
		
		btn_Nuevo_Cliente.addActionListener(controlador);
		btn_Editar_Cliente.addActionListener(controlador);		
		btn_Mesa_1.addActionListener(controlador);
		btn_Mesa_2.addActionListener(controlador);
		btn_Mesa_3.addActionListener(controlador);
		btn_Mesa_4.addActionListener(controlador);
		btn_Mesa_8.addActionListener(controlador);
		btn_Mesa_7.addActionListener(controlador);
		btn_Mesa_6.addActionListener(controlador);
		btn_Mesa_5.addActionListener(controlador);		
		btn_Ver_Pedido.addActionListener(controlador);		
		btn_volver.addActionListener(controlador);
	
	}


	public static ArrayList<ModeloCliente> creaListaClientes() {
		BbddVentas.listarClientes();
		array_clientes = BbddVentas.getArrayClientes();
		modeloListaCliente = new DefaultListModel();
		for (ModeloCliente c : array_clientes) {
			modeloListaCliente.addElement(c.getNombre());
		
		}	
		//se alinean las celdas al centro
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		listaCliente.setCellRenderer(posicion);
		listaCliente.setModel(modeloListaCliente);
		return array_clientes;
	}
	
	
	 public static int clienteSeleccionado() throws NullPointerException {
		 int indiceSeleccionado = listaCliente.getSelectedIndex();
		return indiceSeleccionado;		 
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

	public static JButton getBtn_Editar_Cliente() {
		return btn_Editar_Cliente;
	}

	public static JButton getBtn_Ver_Pedido() {
		return btn_Ver_Pedido;
	}

	public static JButton getBtn_volver() {
		return btn_volver;
	}

	public static JPanel getPanelGestionPedidos() {
		return panelGestionPedidos;
	}

	public static JList getListaCliente() {
		return listaCliente;
	}
	
}