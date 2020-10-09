import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings({ "unused", "serial" })
public class MenuPrincipal extends JFrame 
{
	JPanel contentPane;
	private static String caminho;
	private JTable tabela;
	static Pilha p = new Pilha();
	static Fila f = new Fila();
	ResultSet rs;

	public MenuPrincipal() 
	{
		setTitle("Animais em extincao");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 572);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAnimais = new JMenu("Arquivo");
		menuBar.add(mnAnimais);

		JMenuItem mntmNovoAnimal = new JMenuItem("Importar");
		mntmNovoAnimal.setToolTipText("Importa dados de um arquivo txt, clicar na op\u00E7\u00E3o animais em seguida,listar para vizualizar. Coloca os dados no banco de dados tambem.");
		mntmNovoAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				procurarArquivo();
			}
		});
		mnAnimais.add(mntmNovoAnimal);

		JMenuItem mntmExportar = new JMenuItem("Exportar");
		mntmExportar.setToolTipText("Exporta os dados que est\u00E3o no banco de dados depois de terem sido manipulados pela ordena\u00E7\u00E3o ou n\u00E3o em um  novo arquivo txt.");
		mntmExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String path = FileSystemView.getFileSystemView().getHomeDirectory().getPath();
				EscreverTxt escrever = new EscreverTxt();

				for(int i=0;i<JanelaOrdenacao.selectionOrdenado.length;i++)
				{
					escrever.write(JanelaOrdenacao.selectionOrdenado[i]+"\n",path + "\\SelectionSort.txt", true);
					escrever.write(JanelaOrdenacao.bubbleOrdenado[i]   +"\n",path + "\\BubbleSort.txt"   , true);
				}
			}
		});
		mnAnimais.add(mntmExportar);

		JMenu mnAnimais_1 = new JMenu("Animais");
		menuBar.add(mnAnimais_1);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setToolTipText("Lista os dados importados do arquivo txt.");
		mnAnimais_1.add(mntmListar);
		
		mntmListar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				JanelaListar jListar = new JanelaListar();
				jListar.setVisible(true);
			}
		});

		JMenuItem mntmOrdenar = new JMenuItem("Ordenar");
		mntmOrdenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				JanelaOrdenacao jOrdenar = new JanelaOrdenacao();
				jOrdenar.setVisible(true);
			}
		});
		mntmOrdenar.setToolTipText("Ordena os dados atrav\u00E9s de dois metodos de ordena\u00E7\u00E3o.");
		mnAnimais_1.add(mntmOrdenar);
		
		JMenuItem mntmRemover = new JMenuItem("Remover");
		mntmRemover.setToolTipText("Remove animais do banco de dados");
		mntmRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				ResultSet rs;
				String nome = JOptionPane.showInputDialog("Digite o nome do animal a ser excluido:");
				DB.connect("database.db3");
				DB.execQuery("Delete from Animais where nome_animal ='" + nome + "';");
				System.out.println(nome);
				JOptionPane.showMessageDialog(null,"Excluido com sucesso !");				
			}
		});
		
		JMenuItem mntmPesquisar = new JMenuItem("Pesquisar");
		mntmPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String nome = JOptionPane.showInputDialog("Digite o nome do animal a ser pesquisado:");

				if(getDados(nome) == true)
					JOptionPane.showMessageDialog(null,"O animal foi encontrado !");
				else
					JOptionPane.showMessageDialog(null,"Animal não encontrado !");
			}
		});
		mntmPesquisar.setToolTipText("Pesquisa um animal dentro de um arquivo.txt");
		mnAnimais_1.add(mntmPesquisar);
		
		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mntmAdicionar.setToolTipText("Adiciona animais ao banco de dados");
		mnAnimais_1.add(mntmAdicionar);
		mnAnimais_1.add(mntmRemover);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setForeground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/map.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon (img));
		lblNewLabel.setBounds(0, 0, 750, 512);
		this.getContentPane().add(lblNewLabel);
	}
	
	public boolean getDados(String recebeNome) {
		LerTxt leitura    = new LerTxt();
		String conteudo   = "";
		String nome       = "";
		String cientifico = "";
		String extinto    = "";
		String classe     = "";
		String pais       = "";
		
		leitura.lerArquivo(getCaminho());
		
		for(int i=0; i < leitura.getArqTxt().size(); i++) {
			conteudo   = leitura.linhasDocumento(i);
			nome       = conteudo.split(";")[1];
			
			if(recebeNome.equals(nome)) {
				return true;
			}//if			
		}//for
		return false;
	}

	public void procurarArquivo() 
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Buscar arquivo .txt"); // T�tulo
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Mostrar apenas arquivos

		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt"); // Filtro para extens�o
		// .txt
		fileChooser.setFileFilter(filter);

		int decisao = fileChooser.showOpenDialog(null); // Verificador

		if (decisao == JFileChooser.APPROVE_OPTION) { // Verificar se o usu�rio abriu o arquivo ou cancelou

			File file = fileChooser.getSelectedFile(); // Pegar o arquivo selecionado
			//importarField.setText(file.getPath()); // Mostrar o caminho do arquivo no Text Field
			setCaminho(file.getPath());

			fileChooser.setAcceptAllFileFilterUsed(false);
			importarDados();
		}
	}

	public void importarDados() 
	{
		DB.connect("database.db3");
		
		// parametros de leitura/escrita
		LerTxt leitura = new LerTxt();
		FileSystemView system = FileSystemView.getFileSystemView();

		String conteudo = "";
		String nome = "";
		String cientifico = "";
		String extinto = "";
		String classe = "";
		String pais = "";

		tabela = new JTable();
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		String [] colunas = {"Classe", "Nome", "Nome Cientifico","Pais de origem", "Ano extinto"};
		modelo.setColumnIdentifiers(colunas);

		// LEITURA
		leitura.lerArquivo(getCaminho());

		for (int i = 0; i <leitura.arqTxt.size(); i++) 
		{					
			conteudo = leitura.linhasDocumento(i);
			classe = conteudo.split(";")[0];
			nome = conteudo.split(";")[1];
			cientifico = conteudo.split(";")[2];
			pais = conteudo.split(";")[3];
			extinto = conteudo.split(";")[4];

			Object[] objects = {classe, nome, cientifico,pais,extinto};
			modelo.addRow(objects);
			
			DB.execQuery("Insert into Animais ('nome_animal','nome_cientifico','ano_extinto','pais','classe') values ('"+nome+"','"+cientifico+"','"
							+Integer.parseInt(extinto)+"','"+pais+"','"+classe+"')");
			p.push(conteudo);
			f.enqueue(conteudo);
		}
	}

	public static String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}	
}
