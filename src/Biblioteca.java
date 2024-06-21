import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class Biblioteca extends JFrame implements ActionListener {

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtUsuario;
    private JTextArea txtAreaResultado;
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        super("Biblioteca");

        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(920, 500);
        setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        txtTitulo = new JTextField();
        txtTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setFont(new Font("Arial", Font.BOLD, 16));
        txtAutor = new JTextField();
        txtAutor.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel lblUsuario = new JLabel("Usuario ID:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Arial", Font.BOLD, 16));

        panelFormulario.add(lblTitulo);
        panelFormulario.add(txtTitulo);
        panelFormulario.add(lblAutor);
        panelFormulario.add(txtAutor);
        panelFormulario.add(lblUsuario);
        panelFormulario.add(txtUsuario);


        JPanel panelAcciones = new JPanel(new GridLayout(1, 5, 10, 10)); 
        panelAcciones.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btnRegistrarLibro = new JButton("Registrar Libro");
        btnRegistrarLibro.addActionListener(this);
        btnRegistrarLibro.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarLibro.setBackground(new Color(0, 153, 51));
        btnRegistrarLibro.setForeground(Color.WHITE);
        btnRegistrarLibro.setPreferredSize(new Dimension(30, 30));

        JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
        btnRegistrarUsuario.addActionListener(this);
        btnRegistrarUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarUsuario.setBackground(new Color(0, 102, 204));
        btnRegistrarUsuario.setForeground(Color.WHITE);
        btnRegistrarUsuario.setPreferredSize(new Dimension(30, 30));

        JButton btnRealizarPrestamo = new JButton("Realizar Prestamo");
        btnRealizarPrestamo.addActionListener(this);
        btnRealizarPrestamo.setFont(new Font("Arial", Font.BOLD, 14));
        btnRealizarPrestamo.setBackground(new Color(204, 0, 0));
        btnRealizarPrestamo.setForeground(Color.WHITE);
        btnRealizarPrestamo.setPreferredSize(new Dimension(30, 30));

        JButton btnDevolverLibro = new JButton("Devolver Libro");
        btnDevolverLibro.addActionListener(this);
        btnDevolverLibro.setFont(new Font("Arial", Font.BOLD, 14));
        btnDevolverLibro.setBackground(new Color(0, 102, 204));
        btnDevolverLibro.setForeground(Color.WHITE);
        btnDevolverLibro.setPreferredSize(new Dimension(30, 30));

        JButton btnInventario = new JButton("Inventario");
        btnInventario.addActionListener(this);
        btnInventario.setFont(new Font("Arial", Font.BOLD, 14));
        btnInventario.setBackground(new Color(0, 153, 51));
        btnInventario.setForeground(Color.WHITE);
        btnInventario.setPreferredSize(new Dimension(30, 30));

        panelAcciones.add(btnRegistrarLibro);
        panelAcciones.add(btnRegistrarUsuario);
        panelAcciones.add(btnRealizarPrestamo);
        panelAcciones.add(btnDevolverLibro);
        panelAcciones.add(btnInventario);

        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Arial", Font.PLAIN, 16));
        txtAreaResultado.setLineWrap(true);
        txtAreaResultado.setWrapStyleWord(true);
        txtAreaResultado.setBackground(new Color(240, 240, 240));
        txtAreaResultado.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        scrollPane.setPreferredSize(new Dimension(560, 200));

        JPanel panelPrincipal = new JPanel(new BorderLayout()); 
        panelPrincipal.add(panelFormulario, BorderLayout.NORTH);
        panelPrincipal.add(panelAcciones, BorderLayout.CENTER);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelPrincipal);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Registrar Libro":
                registrarLibro();
                break;
            case "Registrar Usuario":
                registrarUsuario();
                break;
            case "Realizar Prestamo":
                realizarPrestamo();
                break;
            case "Devolver Libro":
                devolverLibro();
                break;
            case "Inventario":
                mostrarInventario();
                break;
        }
    }

    private void registrarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        if (!titulo.isEmpty() && !autor.isEmpty()) {
            Libro libro = new Libro(titulo, autor);
            libros.add(libro);
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAreaResultado.setText("Libro registrado con éxito.");
        } else {
            txtAreaResultado.setText("Por favor, complete todos los campos.");
        }
    }

    private void registrarUsuario() {
        String id = txtUsuario.getText();
        if (!id.isEmpty()) {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del usuario:");
            if (nombre != null && apellido != null && !nombre.isEmpty() && !apellido.isEmpty()) {
                Usuario usuario = new Usuario(nombre, apellido, id);
                usuarios.add(usuario);
                txtUsuario.setText("");
                txtAreaResultado.setText("Usuario registrado con éxito.");
            } else {
                txtAreaResultado.setText("Por favor, complete todos los campos.");
            }
        } else {
            txtAreaResultado.setText("Por favor, ingrese el ID del usuario.");
        }
    }

    private void realizarPrestamo() {
        String titulo = txtTitulo.getText();
        String idUsuario = txtUsuario.getText();
        if (!titulo.isEmpty() && !idUsuario.isEmpty()) {
            Libro libro = buscarLibro(titulo);
            Usuario usuario = buscarUsuario(idUsuario);
            if (libro != null && usuario != null) {
                LocalDate fechaPrestamo = LocalDate.now();
                LocalDate fechaDevolucion = fechaPrestamo.plusWeeks(2);
                Prestamo prestamo = new Prestamo(libro, usuario, fechaPrestamo, fechaDevolucion);
                prestamos.add(prestamo);
                txtTitulo.setText("");
                txtUsuario.setText("");
                txtAutor.setText("");
                txtAreaResultado.setText("Préstamo realizado con éxito.");
            } else {
                txtAreaResultado.setText("Libro o usuario no encontrado.");
            }
        } else {
            txtAreaResultado.setText("Por favor, complete todos los campos.");
        }
    }

    private void devolverLibro() {
        String titulo = txtTitulo.getText();
        if (!titulo.isEmpty()) {
            Prestamo prestamo = buscarPrestamo(titulo);
            if (prestamo != null) {
                prestamos.remove(prestamo);
                txtTitulo.setText("");
                txtAutor.setText("");
                txtUsuario.setText("");
                txtAreaResultado.setText("Libro devuelto con éxito.");
            } else {
                txtAreaResultado.setText("No se encontró un préstamo para ese libro.");
            }
        } else {
            txtAreaResultado.setText("Por favor, ingrese el título del libro.");
        }
    }

    private void mostrarInventario() {
        StringBuilder inventario = new StringBuilder("Inventario de libros:\n");
        for (Libro libro : libros) {
            inventario.append(libro.toString()).append("\n");
        }
        txtAreaResultado.setText(inventario.toString());
    }

    private Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equalsIgnoreCase(id)) {
                return usuario;
            }
        }
        return null;
    }

    private Prestamo buscarPrestamo(String titulo) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().getTitulo().equalsIgnoreCase(titulo)) {
                return prestamo;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception ex) {
                Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
            }
            new Biblioteca();
        });
    }
}