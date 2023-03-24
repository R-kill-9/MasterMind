package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;

/** Ejemplo de Vista **/
public class VistaSecundaria {

	/** Controlador de presentacion **/

	private CtrlPresentacion controladorPresentacion;

	/** Componentes de la interficie grafica **/

	private JFrame frameVista = new JFrame("Vista Secundaria");
	private JPanel panelContenidos = new JPanel();
	private JPanel panelInformacion = new JPanel();
	private JPanel panelBotones = new JPanel();
	private JButton buttonCrearAlumno = new JButton("Crear Alumno");
	private JButton buttonSeleccionarAlumno = new JButton("Seleccionar Alumno");
	private JButton buttonEliminarAlumno = new JButton("Eliminar Alumno");
	private JButton buttonVolver = new JButton("Volver");
	private JTextArea textAreaInformacion = new JTextArea();
	private JLabel labelAlumno = new JLabel("Nombre Alumno:");
	private JSpinner spinnerAlumnos = new JSpinner();
	private JLabel alumnoSeleccionado = new JLabel("Alumno Seleccionado: ...");

	/** Resto de atributos **/

	private Integer numeroAlumnos = 0;

	/** Constructora **/

	public VistaSecundaria(CtrlPresentacion pCtrlPresentacion) {
		controladorPresentacion = pCtrlPresentacion;
		inicializarComponentes();
	}

	/** Métodos públicos **/

	public void hacerVisible() {
		if (numeroAlumnos == 0) {
			buttonEliminarAlumno.setEnabled(false);
			buttonSeleccionarAlumno.setEnabled(false);
		}
		setAlumnoSeleccionado();
		setSpinner();
		frameVista.pack();
		frameVista.setVisible(true);
	}

	public void hacerInvisible() {
		frameVista.setVisible(false);
	}

	/** Metodos de las interfaces Listener **/

	public void actionPerformedCrearAlumno(ActionEvent event) {
		// Recuperamos el texto del Text Area
		String nombreAlumno = textAreaInformacion.getText();
		if (!nombreAlumno.equals("...")) {
			// Si el alumno con el nombre indicado no existía, lo creamos
			if (controladorPresentacion.crearAlumno(nombreAlumno))
				++numeroAlumnos;
			if (numeroAlumnos > 0) {
				buttonEliminarAlumno.setEnabled(true);
				buttonSeleccionarAlumno.setEnabled(true);
			}
			setSpinner();
		}
	}

	public void actionPerformedSeleccionarAlumno(ActionEvent event) {
		// Recuperamos el nombre que hay en el Spinner
		String nombreAlumno = (String) spinnerAlumnos.getValue();
		// Seleccionamos ese alumno
		controladorPresentacion.setAlumnoSeleccionado(nombreAlumno);
		// Escribimos el nombre del alumno seleccionado en el JLabel
		alumnoSeleccionado.setText("Alumno Seleccionado: " + nombreAlumno);
	}

	public void actionPerformedEliminarAlumno(ActionEvent event) {
		// Recuperamos el nombre que hay en el Spinner
		String nombreAlumno = (String) spinnerAlumnos.getValue();
		// Eliminamos ese alumno
		controladorPresentacion.eliminarAlumno(nombreAlumno);
		--numeroAlumnos;
		if (numeroAlumnos == 0) {
			buttonEliminarAlumno.setEnabled(false);
			buttonSeleccionarAlumno.setEnabled(false);
		}
		setAlumnoSeleccionado();
		setSpinner();
	}

	public void actionPerformedButtonVolver(ActionEvent event) {
		controladorPresentacion.sincronizacionVistaSecundariaAPrincipal();
	}

	/** Asignacion de listeners **/

	private void asignarListenersComponentes() {

		// Listeners para los botones

		buttonCrearAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedCrearAlumno(event);
			}
		});

		buttonSeleccionarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedSeleccionarAlumno(event);
			}
		});

		buttonEliminarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedEliminarAlumno(event);
			}
		});

		buttonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String texto = ((JButton) event.getSource()).getText();
				System.out.println("Has clickado el boton con texto: " + texto);
				actionPerformedButtonVolver(event);
			}
		});
	}

	/** Resto de metodos privados **/

	private void inicializarComponentes() {
		inicializarFrameVista();
		inicializarPanelContenidos();
		inicializarPanelInformacion();
		inicializarPanelBotones();
		asignarListenersComponentes();
	}

	private void inicializarFrameVista() {
		// Tamanyo
		frameVista.setMinimumSize(new Dimension(500, 200));
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

	private void inicializarPanelContenidos() {
		// Layout
		panelContenidos.setLayout(new BorderLayout());
		// Paneles
		panelContenidos.add(panelBotones, BorderLayout.EAST);
		panelContenidos.add(panelInformacion, BorderLayout.CENTER);
	}

	private void inicializarPanelInformacion() {
		// Layout (Se puede generar automáticamente mediante un plug-in de
		// Netbeans o Eclipse)
		GroupLayout layout = new GroupLayout(panelInformacion);
		panelInformacion.setLayout(layout);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGap(25, 25, 25)
				.addGroup(
						layout.createParallelGroup()
								.addComponent(alumnoSeleccionado)

								.addComponent(spinnerAlumnos)
								.addGroup(
										layout.createSequentialGroup()
												.addComponent(labelAlumno)
												.addComponent(
														textAreaInformacion)))
				.addGap(25, 25, 25));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGap(25, 25, 25)
				.addComponent(alumnoSeleccionado)
				.addGap(25, 25, 25)
				.addComponent(spinnerAlumnos)
				.addGap(25, 25, 25)
				.addGroup(
						layout.createParallelGroup().addComponent(labelAlumno)
								.addComponent(textAreaInformacion))
				.addGap(25, 25, 25));

		// Componentes
		textAreaInformacion.setText("...");
		setAlumnoSeleccionado();
		setSpinner();
	}

	private void inicializarPanelBotones() {
		// Layout
		panelBotones.setLayout(new GridLayout(0, 1));
		// Botones
		panelBotones.add(buttonSeleccionarAlumno);
		panelBotones.add(buttonCrearAlumno);
		panelBotones.add(buttonEliminarAlumno);
		panelBotones.add(buttonVolver);
	}

	public void setSpinner() {
		// Recuperamos el nombre de todos los alumnos en el sistema
		String[] allAlumnos = controladorPresentacion.getAllAlumnos();
		if (allAlumnos.length == 0)
			allAlumnos = new String[] { "..." };
		// Metemos los nombres en el Spinner
		spinnerAlumnos.setModel(new SpinnerListModel(allAlumnos));
		((DefaultEditor) spinnerAlumnos.getEditor()).getTextField()
				.setEditable(false);
	}

	public void setAlumnoSeleccionado() {
		// Recuperamos el nombre del alumno seleccionado
		String alumno = controladorPresentacion.getAlumnoSeleccionado();
		if (alumno == null)
			alumno = "...";
		// Escribimos ese nombre en el JLabel
		alumnoSeleccionado.setText("Alumno Seleccionado: " + alumno);
	}
}
