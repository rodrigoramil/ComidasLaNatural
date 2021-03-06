package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

import modelo_bbdd.BbddPedido;
import modelo_bbdd.BbddProductosAlmacen;
import modelo_bbdd.BbddVentas;
import vista.Cliente;
import vista.GestionPedidos;
import vista.Pedido;
import vista.ProductosAlmacen;
import vista.Recetario;
import vista.VentanaPrincipal;

public class ControladorGestionPedidos implements ActionListener, MouseListener  {

	private GestionPedidos panelGestionPedidos;
	private static boolean nuevoCliente = false;
	int clienteSelecionado;
	int clienteBBDD;
	private static int idPedido;
	private static boolean mesa;
	
	public ControladorGestionPedidos(GestionPedidos panelGestionPedidos) {
		this.panelGestionPedidos = panelGestionPedidos;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		clienteSelecionado = GestionPedidos.clienteSeleccionado();
		
		if (e.getSource() == GestionPedidos.getBtn_Nuevo_Cliente()) {
			VentanaPrincipal.getPanelGestionPedidos().setVisible(false);
			VentanaPrincipal.getPanelCliente().setVisible(true);

			Cliente.getTfd_nombre().setText("");
			Cliente.getTfd_telefono().setText("");
			GestionPedidos.getListaCliente().clearSelection();
			nuevoCliente = true;

		}
		
		
		if (e.getSource() == GestionPedidos.getBtn_Editar_Cliente()) {
				
				VentanaPrincipal.getPanelGestionPedidos().setVisible(false);
				VentanaPrincipal.getPanelCliente().setVisible(true);
				nuevoCliente = false;
				
			try {
				for (int i = 0; i < BbddVentas.getArrayClientes().size(); i++) {
					clienteBBDD = BbddVentas.getArrayClientes().get(i).getId()-9;					
					if (clienteSelecionado==clienteBBDD) {	
						Cliente.getTfd_nombre().setText(BbddVentas.getArrayClientes().get(i).getNombre());
						Cliente.getTfd_telefono().setText(BbddVentas.getArrayClientes().get(i).getTelefono());
					}
				}		
				
			} catch (NullPointerException errorSelectorVacio) {				
				JOptionPane.showMessageDialog(panelGestionPedidos, "Selecciona cliente a editar");
			}			
		}
		
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_1()) {		
			cambioVentanaPedido();		
			GestionPedidos.getListaCliente().clearSelection();			
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(0).getNombre());	
			int numMesa = 0;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_2()) {
			cambioVentanaPedido();	
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(1).getNombre());
			int numMesa = 1;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_3()) {
			cambioVentanaPedido();	
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(2).getNombre());
			int numMesa = 2;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_4()) {
			cambioVentanaPedido();		
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(3).getNombre());
			int numMesa = 3;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_5()) {
			cambioVentanaPedido();		
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(4).getNombre());
			int numMesa = 4;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_6()) {
			cambioVentanaPedido();
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;		
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(5).getNombre());
			int numMesa = 5;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_7()) {
			cambioVentanaPedido();			
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(6).getNombre());
			int numMesa = 6;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Mesa_8()) {
			
			VentanaPrincipal.getPanelGestionPedidos().setVisible(false);
			VentanaPrincipal.getPanelPedido().setVisible(true);	
			
			GestionPedidos.getListaCliente().clearSelection();
			mesa = true;
			
			Pedido.getLbl_Num_Mesa().setText(BbddVentas.listarClientes().get(7).getNombre());
			
			int numMesa = 7;
			pedidoMesa(numMesa);
			Pedido.listarPedido(BbddPedido.listarPedido());
		}
		
		if (e.getSource() == GestionPedidos.getBtn_Ver_Pedido()) {
			cambioVentanaPedido();
			mesa = false;
			clienteSelecionado = clienteSelecionado+9;
			idPedido=0;
			BbddPedido.listarTodosPedidos();
			for (int i = 0; i < BbddPedido.getArrayPedido().size(); i++) {		
				if (clienteSelecionado==BbddPedido.getArrayPedido().get(i).getIdCliente()) {
					idPedido = BbddPedido.getArrayPedido().get(i).getIdPedido();
				}
			}
			
			Pedido.listarPedido(BbddPedido.listarPedido());
			
			BbddVentas.listarClientes();
			for (int j = 0; j < BbddVentas.getArrayClientes().size(); j++) {
				clienteBBDD = BbddVentas.getArrayClientes().get(j).getId();				
				if (clienteSelecionado==clienteBBDD) {
					Pedido.getLbl_Num_Mesa().setText(BbddVentas.getArrayClientes().get(j).getNombre());
				}
			}

		}
		
		if (e.getSource() == GestionPedidos.getBtn_volver()) {
			VentanaPrincipal.getPanelGestionPedidos().setVisible(false);
			VentanaPrincipal.getPanelMenuPrincipal().setVisible(true);
			GestionPedidos.getListaCliente().clearSelection();
		}


		
	}

	
	public void pedidoMesa( int numMesa) {
		idPedido=0;
		BbddPedido.listarTodosPedidos();		
		for (int i = 0; i < BbddPedido.getArrayPedido().size(); i++) {	
			if (BbddVentas.getArrayClientes().get(numMesa).getId()==BbddPedido.getArrayPedido().get(i).getIdCliente()) {
				idPedido = BbddPedido.getArrayPedido().get(i).getIdPedido();
			}
		}
	}
	
	
	
	public void cambioVentanaPedido() {
		Pedido.getBtn_Modificar().setEnabled(false);
		Pedido.getBtn_Eliminar().setEnabled(false);
		VentanaPrincipal.getPanelGestionPedidos().setVisible(false);
		VentanaPrincipal.getPanelPedido().setVisible(true);
	}
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) { // Al hacer clic con el raton

		
	}

	@Override
	public void mousePressed(MouseEvent e) { // Al pulsar raton
		if (!GestionPedidos.getListaCliente().isSelectionEmpty()) {
			GestionPedidos.getBtn_Editar_Cliente().setEnabled(true);
			GestionPedidos.getBtn_Ver_Pedido().setEnabled(true);
		}

		
	}

	@Override
	public void mouseReleased(MouseEvent e) { // al no interactuar con el

		
	}

	@Override
	public void mouseEntered(MouseEvent e) { // al tener el raton encima
		if (e.getSource() == GestionPedidos.getBtn_Editar_Cliente()) {
			GestionPedidos.getBtn_Editar_Cliente().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Editar_Cliente().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_1()) {
			GestionPedidos.getBtn_Mesa_1().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_1().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_2()) {
			GestionPedidos.getBtn_Mesa_2().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_2().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_3()) {
			GestionPedidos.getBtn_Mesa_3().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_3().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_4()) {
			GestionPedidos.getBtn_Mesa_4().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_4().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_5()) {
			GestionPedidos.getBtn_Mesa_5().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_5().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_6()) {
			GestionPedidos.getBtn_Mesa_6().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_6().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_7()) {
			GestionPedidos.getBtn_Mesa_7().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_7().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_8()) {
			GestionPedidos.getBtn_Mesa_8().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Mesa_8().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Nuevo_Cliente()) {
			GestionPedidos.getBtn_Nuevo_Cliente().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Nuevo_Cliente().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Ver_Pedido()) {
			GestionPedidos.getBtn_Ver_Pedido().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_Ver_Pedido().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_volver()) {
			GestionPedidos.getBtn_volver().setBackground(VentanaPrincipal.getAzulOscuro());
			GestionPedidos.getBtn_volver().setForeground(VentanaPrincipal.getAzulClaro());;
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) { //al salir el raton de encima
		if (e.getSource() == GestionPedidos.getBtn_Editar_Cliente()) {
			GestionPedidos.getBtn_Editar_Cliente().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Editar_Cliente().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_1()) {
			GestionPedidos.getBtn_Mesa_1().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_1().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_2()) {
			GestionPedidos.getBtn_Mesa_2().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_2().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_3()) {
			GestionPedidos.getBtn_Mesa_3().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_3().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_4()) {
			GestionPedidos.getBtn_Mesa_4().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_4().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_5()) {
			GestionPedidos.getBtn_Mesa_5().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_5().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_6()) {
			GestionPedidos.getBtn_Mesa_6().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_6().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_7()) {
			GestionPedidos.getBtn_Mesa_7().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_7().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Mesa_8()) {
			GestionPedidos.getBtn_Mesa_8().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Mesa_8().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Nuevo_Cliente()) {
			GestionPedidos.getBtn_Nuevo_Cliente().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Nuevo_Cliente().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_Ver_Pedido()) {
			GestionPedidos.getBtn_Ver_Pedido().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_Ver_Pedido().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
		if (e.getSource() == GestionPedidos.getBtn_volver()) {
			GestionPedidos.getBtn_volver().setBackground(VentanaPrincipal.getAzulClaro());
			GestionPedidos.getBtn_volver().setForeground(VentanaPrincipal.getAzulOscuro());;
		}
	}

	public static boolean getNuevoCliente() {
		return nuevoCliente;
	}

	public static void setNuevoCliente(boolean nuevoCliente) {
		ControladorGestionPedidos.nuevoCliente = nuevoCliente;
	}

	public static boolean isMesa() {
		return mesa;
	}

	public static void setMesa(boolean mesa) {
		ControladorGestionPedidos.mesa = mesa;
	}

	public static int getIdPedido() {
		return idPedido;
	}

	public static void setIdPedido(int idPedido) {
		ControladorGestionPedidos.idPedido = idPedido;
	}


	
	
}
