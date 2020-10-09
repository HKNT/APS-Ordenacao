import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class JanelaListar extends JFrame {

	private JPanel contentPane;
	private static JTable tabelaPilha;
	private static JTable tabelaFila;
	private static JScrollPane scrollPanePilha;
	private static JScrollPane scrollPaneFila;
	static DefaultTableModel modeloPilha;
	static DefaultTableModel modeloFila;
	
	public JanelaListar() 
	{
		setTitle("Listar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 982, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelLista = new JPanel();
		panelLista.setBackground(new Color(153, 255, 255));
		panelLista.setBounds(0, 0, 978, 113);
		contentPane.add(panelLista);
		panelLista.setLayout(null);

		JLabel lblListagemDeAnimais = new JLabel("Lista de animais");
		lblListagemDeAnimais.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 30));
		lblListagemDeAnimais.setBounds(399, 21, 266, 49);
		panelLista.add(lblListagemDeAnimais);

		JPanel panelCorpoLista = new JPanel();
		panelCorpoLista.setBackground(new Color(204, 255, 255));
		panelCorpoLista.setBounds(0, 113, 978, 538);
		contentPane.add(panelCorpoLista);
		panelCorpoLista.setLayout(null);

		scrollPanePilha = new JScrollPane();
		scrollPanePilha.setBounds(10, 51, 471, 318);
		panelCorpoLista.add(scrollPanePilha);

		scrollPaneFila = new JScrollPane();
		scrollPaneFila.setBounds(496, 51, 472, 318);
		panelCorpoLista.add(scrollPaneFila);

		JButton btnPush = new JButton("PUSH");
		btnPush.setToolTipText("Insere um novo dado na pilha e no banco de dados");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				NovoAnimal j = new NovoAnimal();
				j.setVisible(true);
			}
		});
		btnPush.setBounds(95, 487, 89, 23);
		panelCorpoLista.add(btnPush);

		JButton btnPop = new JButton("POP");
		btnPop.setToolTipText("Retira um dado da pilha");
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{							
				MenuPrincipal.p.pop();
				listarPilha();
			}
		});
		
		btnPop.setBounds(278, 487, 89, 23);
		panelCorpoLista.add(btnPop);

		JButton btnInserir = new JButton("INSERIR");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				NovoAnimal j = new NovoAnimal();
				j.setVisible(true);
			}
		});
		btnInserir.setToolTipText("Insere um novo dado na fila");
		btnInserir.setBounds(587, 487, 89, 23);
		panelCorpoLista.add(btnInserir);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setToolTipText("Retira um dado da fila");
		btnRemover.setBounds(790, 487, 104, 23);
		panelCorpoLista.add(btnRemover);

		JLabel lblFila = new JLabel("Fila");
		lblFila.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 22));
		lblFila.setBounds(716, 11, 56, 29);
		panelCorpoLista.add(lblFila);

		JLabel lblPilha = new JLabel("Pilha");
		lblPilha.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 22));
		lblPilha.setBounds(214, 11, 82, 29);
		panelCorpoLista.add(lblPilha);

		listarPilha();
		listarFila();
	}
	
	public static void listarPilha() 
	{
		String conteudo = "";
		String nome = "";
		String cientifico = "";
		String ano_extinto = "";
		String classe = "";
		String pais = "";
		int aux = MenuPrincipal.p.tamanhoPilha();

		tabelaPilha = new JTable();		
		modeloPilha = (DefaultTableModel) tabelaPilha.getModel();
		String[] colunas = { "Classe", "Nome", "Nome Cientifico", "Pais de origem","Ano Extinto" };
		modeloPilha.setColumnIdentifiers(colunas);

		MenuPrincipal.p.mostrar();
		
		System.out.println("Existem " + MenuPrincipal.p.tamanhoPilha() + "elementos");

		for (int i = 0; i < MenuPrincipal.p.tamanhoPilha(); i++) 
		{
			conteudo = MenuPrincipal.p.listarElementos(aux);
			
			classe = conteudo.split(";")[0];
			 nome = conteudo.split(";")[1];
			 cientifico = conteudo.split(";")[2];
			pais = conteudo.split(";")[3];
			ano_extinto = conteudo.split(";")[4];

			Object[] objects = { classe, nome, cientifico, pais, ano_extinto };
			modeloPilha.addRow(objects);
			aux--;
		}

		scrollPanePilha.setViewportView(tabelaPilha);
	}
	
	public static void listarFila() 
	{
		String conteudo = "";
		String nome = "";
		String cientifico = "";
		String ano_extinto = "";
		String classe = "";
		String pais = "";
		
		tabelaFila = new JTable();		
		modeloFila = (DefaultTableModel) tabelaFila.getModel();
		String[] colunas = { "Classe", "Nome", "Nome Cientifico", "Pais de origem", "Ano Extinto" };
		modeloFila.setColumnIdentifiers(colunas);
				
		System.out.println("Existem " + MenuPrincipal.f.qntdeElementos + "elementos");

		for (int i = 0; i < MenuPrincipal.f.tamanhoFila(); i++) 
		{
			conteudo = MenuPrincipal.f.listarElementos(i);
			
			classe = conteudo.split(";")[0];
			nome = conteudo.split(";")[1];
			cientifico = conteudo.split(";")[2];
			pais = conteudo.split(";")[3];
			ano_extinto = conteudo.split(";")[4];

			Object[] objects = { classe, nome, cientifico,  pais, ano_extinto};
			
			modeloFila.addRow(objects);
		}

		scrollPaneFila.setViewportView(tabelaFila);

	}	
}
