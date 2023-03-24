package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Ejemplo de Vista **/
public class VistaPrincipal {

	/** Controlador de presentacion **/
	
	private CtrlPresentacion controladorPresentacion;

	/** Componentes de la interficie grafica **/
	
	private JFrame frameVista = new JFrame("Vista Principal");
	private JPanel panelContenidos = new JPanel();
	private JPanel panelInformacion = new JPanel();
	private JPanel panelInformacionA = new JPanel();
	private JPanel panelInformacion1 = new JPanel();
	private JPanel panelInformacion2 = new JPanel();
	private JPanel panelBotones = new JPanel();
	private JButton buttonCargarAsignaturas = new JButton("Cargar Asignaturas");
	private JButton buttonGestionarAlumnos = new JButton("Gestionar Alumnos");
	private JButton buttonGestionarNotas = new JButton("Gestionar Notas");
	private JButton buttonAsignarNota = new JButton("Asignar Nota");
	private JButton buttonCalcularMedia = new JButton("Calcular Media");
	private JLabel labelPanelInformacion1 = new JLabel("Asignaturas");
	private JComboBox comboboxInformacion1 = new JComboBox();
	private JTextArea textareaInformacion1 = new JTextArea(15, 25);
	private JLabel labelPanelInformacion2 = new JLabel();
	private JTextField textfieldInformacion2 = new JTextField();
	private JSpinner spinnerInformacion2 = new JSpinner();
	private JSlider sliderInformacion2 = new JSlider();

	/** Menus **/
	
	private JMenuBar menubarVista = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenuItem menuitemQuit = new JMenuItem("Quit");
	private JMenu menuOpciones = new JMenu("Opciones");
	private JMenuItem menuitemCargarAsignaturas = new JMenuItem(
			"Cargar Asignaturas");
	private JMenuItem menuitemGestionarAlumnos = new JMenuItem(
			"Gestionar Alumnos");
	private JMenuItem menuitemGestionarNotas = new JMenuItem("Gestionar Notas");
	private JMenuItem menuitemCalcularMedia = new JMenuItem("Calcular Media");

	/** Resto de atributos **/
	
	private int iPanelActivo = 0;
	private Set<String> asignaturas = new HashSet<String>();

	/** Constructora **/

	public VistaPrincipal(CtrlPresentacion pCtrlPresentacion) {
		controladorPresentacion = pCtrlPresentacion;
		inicializarComponentes();
	}

	/** Métodos públicos **/

	public void hacerVisible(boolean activar) {
		buttonGestionarNotas.setEnabled(activar);
		menuitemGestionarNotas.setEnabled(activar);
		buttonCalcularMedia.setEnabled(activar);
		menuitemCalcularMedia.setEnabled(activar);
		frameVista.pack();
		frameVista.setVisible(true);
	}

	public void activar(boolean activar) {
		frameVista.setEnabled(true);
		buttonGestionarNotas.setEnabled(activar);
		buttonCalcularMedia.setEnabled(activar);
	}

	public void desactivar() {
		frameVista.setEnabled(false);
	}

	/** Metodos de las interfaces Listener **/

	public void actionPerformedButtonCargarAsignaturas(ActionEvent event) {
		// Recuperamos el contenido del Combobox
		String comboboxSelectedItem = (String) comboboxInformacion1.getModel()
				.getSelectedItem();
		ArrayList<String> resultDominio = new ArrayList<String>();

		try {
			// Pedimos a dominio que nos de las asignaturas correspodientes al
			// item seleccionado
			resultDominio = controladorPresentacion
					.cargarAsignaturas(comboboxSelectedItem);
		} catch (FileNotFoundException e) {
			System.err.println("El fichero " + comboboxSelectedItem
					+ ".txt no existe.");
			return;
		}

		for (int i = 0; i < resultDominio.size(); i++)
			System.out.println("Obtenido de dominio: " + resultDominio.get(i));

		// Añadimos las asignaturas cargadas a las que ya teníamos
		asignaturas.addAll(resultDominio);

		// Mostramos las asignaturas en un text area
		textareaInformacion1.setText("");
		for (String asignatura : asignaturas)
			textareaInformacion1.append(asignatura + "\n");

		// Mostramos las asignaturas en un spinner
		spinnerInformacion2.setModel(new SpinnerListModel(
				new ArrayList<String>(asignaturas)));
		((DefaultEditor) spinnerInformacion2.getEditor()).getTextField()
				.setEditable(false);
	}

	public void actionPerformedButtonGestionarAlumnos(ActionEvent event) {
		// Se llama al controlador de presentación para que gestione la
		// activación de una vista Secundaria
		controladorPresentacion.sincronizacionVistaPrincipalASecundaria();
	}

	public void actionPerformedButtonGestionarNotas(ActionEvent event) {
		// Gestión del cambio de panel de la vista Principal
		if (iPanelActivo != 0) {
			iPanelActivo = iPanelActivo % 2 + 1;
			System.out.println("Cambiando a panel " + iPanelActivo);
			panelInformacion.remove(panelInformacionA);
			if (iPanelActivo == 1) {
				// Mostrar el panel 1
				buttonCargarAsignaturas.setEnabled(true);
				menuitemCargarAsignaturas.setEnabled(true);
				panelInformacionA = panelInformacion1;
			} else {
				// Mostrar el panel 2
				buttonCargarAsignaturas.setEnabled(false);
				menuitemCargarAsignaturas.setEnabled(false);
				buttonAsignarNota.setEnabled(asignaturas.size() > 0);
				panelInformacionA = panelInformacion2;
				labelPanelInformacion2.setText("Asigna notas al estudiante "
						+ controladorPresentacion.getAlumnoSeleccionado());
			}
			panelInformacion.add(panelInformacionA);
			frameVista.pack();
			frameVista.repaint();
		}
	}

	public void actionPerformedButtonCalcularMedia(ActionEvent event) {
		// Abre una ventana con un dialogo
		VistaDialogo vistaDialogo = new VistaDialogo();
		String[] strBotones = { "Aceptar" };
		int isel = vistaDialogo.setDialogo(
				"Calcular Nota Media",
				"Nota Media del estudiante "
						+ controladorPresentacion.getAlumnoSeleccionado()
						+ ": "
						+ controladorPresentacion
								.calcularMedia(controladorPresentacion
										.getAlumnoSeleccionado()), strBotones,
				4);
		System.out.println("Resultado del dialogo: " + isel + " "
				+ strBotones[isel]);
	}

	private void actionPerformedAsignarNota(ActionEvent event) {
		// Recupera el valor indicado en el slider
		Double nota = ((double) sliderInformacion2.getValue()) / 10;
		// Recupera el nombre de asignatura indicado en el spinner
		String nombreAsignatura = (String) spinnerInformacion2.getValue();
		// Se llama al controlador de presentación para que gestione la
		// asignación de la nota
		controladorPresentacion.asignarNota(nombreAsignatura, nota);
	}

	/** Asignacion de listeners **/

	private void asignarListenersComponentes() {

		// Listeners para los botones

		buttonCargarAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedButtonCargarAsignaturas(event);
			}
		});

		buttonGestionarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedButtonGestionarAlumnos(event);
			}
		});

		buttonGestionarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedButtonGestionarNotas(event);
			}
		});

		buttonCalcularMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedButtonCalcularMedia(event);
			}
		});

		buttonAsignarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedAsignarNota(event);
			}
		});

		// Listeners para las opciones de menu

		menuitemCargarAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JMenuItem) event.getSource()).getText();
				System.out.println("Has seleccionado el menuitem con texto: "
						+ texto);
				actionPerformedButtonCargarAsignaturas(event);
			}
		});

		menuitemGestionarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JMenuItem) event.getSource()).getText();
				System.out.println("Has seleccionado el menuitem con texto: "
						+ texto);
				actionPerformedButtonGestionarAlumnos(event);
			}
		});

		menuitemGestionarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JMenuItem) event.getSource()).getText();
				System.out.println("Has seleccionado el menuitem con texto: "
						+ texto);
				actionPerformedButtonGestionarNotas(event);
			}
		});

		menuitemCalcularMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JMenuItem) event.getSource()).getText();
				System.out.println("Has seleccionado el menuitem con texto: "
						+ texto);
				actionPerformedButtonCalcularMedia(event);
			}
		});

		menuitemQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JMenuItem) event.getSource()).getText();
				System.out.println("Has seleccionado el menuitem con texto: "
						+ texto);
				System.exit(0);
			}
		});

		// Listeners para el resto de componentes

		sliderInformacion2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				int sliderValue = sliderInformacion2.getValue();
				textfieldInformacion2.setText(Integer.toString(sliderValue));
			}
		});

		spinnerInformacion2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// Se recupera el nombre de la asignatura seleccionada en el
				// spinner
				String nombreAsignatura = (String) spinnerInformacion2
						.getValue();
				// Se recupera la nota asignada a esa asignatura
				Double tmp = Double.parseDouble(controladorPresentacion
						.getNota(nombreAsignatura)) * 10.0;
				System.out.println(nombreAsignatura + " " + tmp);
				Integer nota = tmp.intValue();
				// Se muestra la nota de la asignatura a través del slider y
				// del text field
				sliderInformacion2.setValue(nota);
				textfieldInformacion2.setText(nota + "");
			}
		});

	}

	/** Resto de metodos privados **/

	private void inicializarComponentes() {
		inicializarFrameVista();
		inicializarMenubarVista();
		inicializarPanelContenidos();
		inicializarPanelInformacion();
		inicializarPanelInformacion1();
		inicializarPanelInformacion2();
		inicializarPanelBotones();
		asignarListenersComponentes();
	}

	private void inicializarFrameVista() {
		// Tamanyo
		frameVista.setMinimumSize(new Dimension(700, 400));
		frameVista.setPreferredSize(frameVista.getMinimumSize());
		frameVista.setResizable(false);
		// Posicion y operaciones por defecto
		frameVista.setLocationRelativeTo(null);
		frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Se agrega panelContenidos al contentPane (el panelContenidos se
		// podria ahorrar y trabajar directamente sobre el contentPane)
		JPanel contentPane = (JPanel) frameVista.getContentPane();
		contentPane.add(panelContenidos);
	}

	private void inicializarMenubarVista() {
		menuFile.add(menuitemQuit);
		menuOpciones.add(menuitemCargarAsignaturas);
		menuOpciones.add(menuitemGestionarAlumnos);
		menuOpciones.add(menuitemGestionarNotas);
		menuOpciones.add(menuitemCalcularMedia);
		menubarVista.add(menuFile);
		menubarVista.add(menuOpciones);
		frameVista.setJMenuBar(menubarVista);
	}

	private void inicializarPanelContenidos() {
		// Layout
		panelContenidos.setLayout(new BorderLayout());
		// Paneles
		panelContenidos.add(panelBotones, BorderLayout.NORTH);
		panelContenidos.add(panelInformacion, BorderLayout.CENTER);
	}

	private void inicializarPanelInformacion() {
		// El panelInformacion es solo un contenedor para panelInformacionA, que
		// contendra panelInformacion1 (inicialmente) o panelInformacion2
		panelInformacionA = panelInformacion1;
		iPanelActivo = 1;
		panelInformacion.add(panelInformacionA);
	}

	private void inicializarPanelInformacion1() {
		// Layout
		panelInformacion1.setLayout(new BorderLayout());
		// Componentes
		panelInformacion1.add(labelPanelInformacion1, BorderLayout.NORTH);
		comboboxInformacion1.addItem("Fase Selectiva");
		comboboxInformacion1.addItem("Obligatorias");
		comboboxInformacion1.addItem("Computacion");
		panelInformacion1.add(comboboxInformacion1, BorderLayout.EAST);
		textareaInformacion1.setEditable(false);
		panelInformacion1.add(new JScrollPane(textareaInformacion1),
				BorderLayout.SOUTH);
	}

	private void inicializarPanelInformacion2() {
		// Layout (Se puede generar automáticamente mediante un plug-in de
		// Netbeans o Eclipse)
		GroupLayout panelInformacion2Layout = new GroupLayout(panelInformacion2);
		panelInformacion2.setLayout(panelInformacion2Layout);

		panelInformacion2Layout
				.setHorizontalGroup(panelInformacion2Layout
						.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(labelPanelInformacion2)
						.addGap(25, 25, 25)
						.addGroup(
								panelInformacion2Layout
										.createSequentialGroup()
										.addComponent(textfieldInformacion2)
										.addGap(25, 25, 25)
										.addGroup(
												panelInformacion2Layout
														.createParallelGroup(
																GroupLayout.Alignment.CENTER)
														.addComponent(
																sliderInformacion2)
														.addGap(25, 25, 25)
														.addComponent(
																spinnerInformacion2)))
						.addComponent(buttonAsignarNota).addGap(25, 25, 25));

		panelInformacion2Layout.setVerticalGroup(panelInformacion2Layout
				.createSequentialGroup()
				.addGap(25, 25, 25)
				.addComponent(labelPanelInformacion2)
				.addGap(25, 25, 25)
				.addGroup(
						panelInformacion2Layout
								.createParallelGroup(
										GroupLayout.Alignment.CENTER)
								.addComponent(textfieldInformacion2)
								.addGroup(
										panelInformacion2Layout
												.createSequentialGroup()
												.addComponent(
														sliderInformacion2)
												.addComponent(
														spinnerInformacion2)))

				.addGap(25, 25, 25).addComponent(buttonAsignarNota)
				.addGap(25, 25, 25));

		// Componentes
		textfieldInformacion2.setText("...");
		textfieldInformacion2.setPreferredSize(new Dimension(40, 40));
		textfieldInformacion2.setEditable(false);

		String[] spinnerStrings = { "..." };
		SpinnerListModel spinnerStringsModel = new SpinnerListModel(
				spinnerStrings);
		spinnerInformacion2.setModel(spinnerStringsModel);
		spinnerInformacion2.setPreferredSize(new Dimension(500, 20));
		sliderInformacion2.setMinimum(0);
		sliderInformacion2.setMaximum(100);
		sliderInformacion2.setValue(0);
		sliderInformacion2.setMajorTickSpacing(10);
		sliderInformacion2.setOrientation(JSlider.HORIZONTAL);
		sliderInformacion2.setPaintLabels(true);
		sliderInformacion2.setPaintTicks(false);
		sliderInformacion2.setPreferredSize(new Dimension(100, 50));
	}

	private void inicializarPanelBotones() {
		// Layout
		panelBotones.setLayout(new FlowLayout());
		// Componentes
		panelBotones.add(buttonCargarAsignaturas);
		panelBotones.add(buttonGestionarAlumnos);
		panelBotones.add(buttonGestionarNotas);
		panelBotones.add(buttonCalcularMedia);
		// Tooltips
		buttonCargarAsignaturas
				.setToolTipText("Llama al controlador de dominio con la informacion del ComboBox");
		buttonGestionarAlumnos
				.setToolTipText("Abre una nueva ventana sincronizada");
		buttonGestionarNotas.setToolTipText("Cambia el panel de informacion");
		buttonCalcularMedia.setToolTipText("Abre un Dialogo modal simple");
	}
}
