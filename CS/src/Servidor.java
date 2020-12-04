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

public class Servidor extends JFrame {

	private JPanel contentPane;

	
	private static Socket socket;
	private static ServerSocket serverSocket;
	private static BufferedReader bufferedReader;
	private static InputStreamReader inputStreamReader;
	private static String message = "";
	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
				
				System.out.println("Servidor corriendo por el puerto 7000");
				
				inputStreamReader = new InputStreamReader(socket.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader);
				message = bufferedReader.readLine();
				
				System.out.println(message);
				
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
