import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Servidor extends JFrame {

	private JPanel contentPane;

	
	private static Socket socket;
	private static ServerSocket serverSocket;
	private static BufferedReader bufferedReader;
	private static InputStreamReader inputStreamReader;
	private static String message = "";
	
	
	
	 static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
     static Connection conn = null;
     static String username = "root";
     static String password = "";
     static String serverUrl = "jdbc:mysql://localhost:3306/";
     Statement stmt = null;
     String dbName = "test";
     String checkDb = "SELECT SCHEMA_NAME FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME` = '" + dbName + "'";
     ResultSet rs = null;
     boolean dbFound = false;
	
	
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
												
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		try {
			serverSocket = new ServerSocket(7000);
			while(true) {
		
				System.out.println("Servidor corriendo ");

				socket = serverSocket.accept();
				
				System.out.println("\tServidor corriendo por el puerto 7000");
				
				inputStreamReader = new InputStreamReader(socket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader);
				message = bufferedReader.readLine();
				
				System.out.println("\t\t"+message);
			
				if(message.equals("Conectar")) {
					try {
						Class.forName(jdbcDriver);
				        System.out.println("Driver cargado");
					} 
					catch (ClassNotFoundException ex) {
				        System.out.println("Driver fallo");
				        System.out.println(ex.getMessage());
				    }
				    try {
				    	//connecting to xampp server (Apache Server)
				        conn = DriverManager.getConnection(serverUrl, username, password);
				        System.out.println("Conectado a la base de datos correctamente");
				    } 
				    catch (SQLException ex) {
				    	System.out.println("Fallo en la conexion a la base de datos");
				        System.out.println(ex.getMessage());
				    }
				}
				
				/*
				inputStreamReader.close();
				bufferedReader.close();
				serverSocket.close();
				socket.close();
				*/
	      
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}

	/**
	 * Create the frame.
	 */
	public Servidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVIDOR");
		lblNewLabel.setBounds(179, 11, 81, 20);
		contentPane.add(lblNewLabel);
	}
	
	
}
