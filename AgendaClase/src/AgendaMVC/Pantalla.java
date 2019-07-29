package AgendaMVC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Pantalla extends JFrame implements ActionListener  {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private JLabel labelTitulo;
	 JTable miTabla1;
	 JScrollPane mibarra1;
	 private JButton btnNewButton; 

	public Pantalla() {
		  setSize(1200, 800);
		  setTitle("Agenda");
		  setLocationRelativeTo(null);
		  setResizable(true);
		   
		  inicializaComponentes();
		  construirTabla();
		 }
	 
	 private void inicializaComponentes() {
		  getContentPane().setLayout(null);
		 
		  labelTitulo = new JLabel();
		  labelTitulo.setBounds(27, 11, 400, 30);
		  labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		  labelTitulo.setText("Agenda FP DUAL");
		  labelTitulo.setFont(new java.awt.Font("Verdana", 1, 18));
		  getContentPane().add(labelTitulo);
		   
		  mibarra1=new JScrollPane();
		  mibarra1.setBounds(27, 72,1000,600);
		  getContentPane().add(mibarra1);
		   
		  btnNewButton = new JButton("Actualizar");
		  btnNewButton.setBounds(938, 718, 100, 30);
		  getContentPane().add(btnNewButton);
		  btnNewButton.addActionListener(this);  
		 }
	 
	 public void construirTabla() {
		  String titulos[]={ "ID", "NOMBRE",
				  "APELLIDOS","MODULO","EMAIL" };
		  
		  String informacion[][]= obtenerMatriz();
		  
		  miTabla1=new JTable(informacion,titulos);
		  
		  
		  mibarra1.setViewportView(miTabla1);
	 }
	
	 public String[][] obtenerMatriz() {
		   
		  AgendaDAO agenda = new AgendaDAO();
		  
		  ArrayList<Identificadores> miLista = agenda.buscarResultadosConMatriz();
		   
		  String matrizInfo[][]=new String[miLista.size()][5];
		   
		  for (int i = 0; i < miLista.size(); i++) {
		   matrizInfo[i][0]=miLista.get(i).getIdentificador()+"";
		   matrizInfo[i][1]=miLista.get(i).getNombre()+"";
		   matrizInfo[i][2]=miLista.get(i).getApellidos()+"";
		   matrizInfo[i][3]=miLista.get(i).getModulo()+"";
		   matrizInfo[i][4]=miLista.get(i).getEmail()+"";
		  }
		    
		  return matrizInfo;
		 }
	 

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnNewButton) {
			   construirTabla();
			  }
	}

}
