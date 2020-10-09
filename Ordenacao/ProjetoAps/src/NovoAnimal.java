import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class NovoAnimal extends JFrame 
{
	private JPanel contentPane;
	private JTextField nomeAnimal;
	private JTextField nomeCientifico;
	private JFormattedTextField anoExtinto;
	private JFrame self;
	JComboBox<String> classeComboBox;	
	JComboBox<String> paisComboBox;
	
	public NovoAnimal() 
	{
		DB.connect("database.db3");
		ResultSet res;
		
		self = this;
		setTitle("Novo animal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 548);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		MaskFormatter formatarAno = new MaskFormatter(); 

		try {
			formatarAno.setMask("####");
		}
		catch (ParseException pe) {
			pe.printStackTrace();
		}
		catch ( Exception ex ) {

			ex.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(0, 0, 576, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNovoAnimal = new JLabel("Novo animal");
		lblNovoAnimal.setForeground(new Color(255, 255, 255));
		lblNovoAnimal.setBounds(147, 11, 250, 30);
		panel.add(lblNovoAnimal);
		lblNovoAnimal.setBackground(new Color(255, 255, 255));
		lblNovoAnimal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNovoAnimal.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 24));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(135, 206, 250));
		panel_1.setBounds(28, 80, 510, 317);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblClasse = new JLabel("Classe:");
		lblClasse.setForeground(new Color(255, 255, 255));
		lblClasse.setBounds(19, 16, 101, 20);
		panel_1.add(lblClasse);
		lblClasse.setFont(new Font("Lato", Font.ITALIC, 14));
		//----------------------------------------------

		classeComboBox = new JComboBox<String>();
		classeComboBox.setBounds(140, 16, 84, 22);
		panel_1.add(classeComboBox);
		classeComboBox.setFont(new Font("Gadugi", Font.PLAIN, 12));
		
		DB.connect("database.db3");
		res = DB.query("select nome_classe from Classes;");
		
		try {
			while (res.next()) 
			{
				String nome = res.getString("nome_classe");
				classeComboBox.addItem(nome);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nomeAnimal = new JTextField();
		nomeAnimal.setBounds(140, 49, 355, 20);
		panel_1.add(nomeAnimal);
		nomeAnimal.setColumns(10);

		nomeCientifico = new JTextField();
		nomeCientifico.setBounds(140, 80, 355, 20);
		panel_1.add(nomeCientifico);
		nomeCientifico.setColumns(10);

		JLabel lblNomeCientfico = new JLabel("Nome cient\u00EDfico:");
		lblNomeCientfico.setForeground(new Color(255, 255, 255));
		lblNomeCientfico.setBackground(new Color(255, 255, 255));
		lblNomeCientfico.setBounds(19, 81, 101, 14);
		panel_1.add(lblNomeCientfico);
		lblNomeCientfico.setFont(new Font("Lato", Font.ITALIC, 14));

		JLabel lblAnoExtinto = new JLabel("Ano extinto:");
		lblAnoExtinto.setForeground(new Color(255, 255, 255));
		lblAnoExtinto.setBounds(19, 112, 101, 14);
		panel_1.add(lblAnoExtinto);
		lblAnoExtinto.setFont(new Font("Lato", Font.ITALIC, 14));

		anoExtinto = new JFormattedTextField();
		anoExtinto.setBounds(140, 111, 59, 20);
		panel_1.add(anoExtinto);
		anoExtinto.setColumns(10);
		formatarAno.install(anoExtinto);

		JLabel lblPasPertencente = new JLabel("Pa\u00EDs pertencente:");
		lblPasPertencente.setForeground(new Color(255, 255, 255));
		lblPasPertencente.setBounds(19, 143, 124, 14);
		panel_1.add(lblPasPertencente);
		lblPasPertencente.setFont(new Font("Lato", Font.ITALIC, 14));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Lato", Font.ITALIC, 14));
		lblNome.setBounds(19, 49, 101, 16);
		panel_1.add(lblNome);
		
		paisComboBox = new JComboBox<String>();
		paisComboBox.setBounds(140, 142, 162, 20);
		panel_1.add(paisComboBox);
		paisComboBox.addItem("Selecione um País");
		
		DB.connect("database.db3");
		res = DB.query("select nome_pais from Paises;");
		try {
			while (res.next()) 
			{
				String nome = res.getString("nome_pais");
				paisComboBox.addItem(nome);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 250));
		panel_3.setBounds(149, 408, 257, 95);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSalvar = new JLabel("Adicionar");
		lblSalvar.setBounds(158, 11, 85, 17);
		panel_3.add(lblSalvar);
		lblSalvar.setForeground(Color.WHITE);
		lblSalvar.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(10, 11, 60, 17);
		panel_3.add(lblVoltar);
		lblVoltar.setForeground(Color.WHITE);
		lblVoltar.setFont(new Font("Lato", Font.BOLD | Font.ITALIC, 14));

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setToolTipText("Voltar para menu principal");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				self.dispose();
			}
		});
		
		btnVoltar.setIcon(new ImageIcon("C:\\Users\\Felipe\\Desktop\\back.png"));
		btnVoltar.setBounds(10, 48, 89, 23);
		panel_3.add(btnVoltar);
		btnVoltar.setBackground(UIManager.getColor("CheckBox.background"));

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.setToolTipText("Adiciona um animal na fila,pilha e no banco de dados");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				EscreverTxt escrita = new EscreverTxt();
				String novoTexto;
				String novoDado;

				novoTexto = classeComboBox.getSelectedItem().toString() + ";" + nomeAnimal.getText() + ";"
						+ nomeCientifico.getText() + ";" + paisComboBox.getSelectedItem() +";" + anoExtinto.getText()+";";

				novoDado = "INSERT INTO Animais (nome_animal,nome_cientifico,ano_extinto,pais,classe) values ('"
						+ nomeAnimal.getText() + "','" + nomeCientifico.getText() + "','" + anoExtinto.getText() + "','"
						+ paisComboBox.getSelectedItem() + "','" + classeComboBox.getSelectedItem() + "')";

				DB.connect("database.db3");
				DB.execQuery(novoDado);
				escrita.write(novoTexto, MenuPrincipal.getCaminho(), true);
				MenuPrincipal.p.push(novoTexto);
				MenuPrincipal.f.enqueue(novoTexto);
				JanelaListar.listarPilha();
				JanelaListar.listarFila();

				self.dispose();
			}
		});
		
		btnAdd.setBounds(158, 48, 89, 23);
		panel_3.add(btnAdd);
	}
}

