package principal;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import conexiones.Consultas;
import modelo.*;

 //@author Ivii
 
public class Servidor extends Thread implements Protocolo{
	/*
	 * ATRIBUTOS DEL HILO SERVIDOR QUE ATIENDE A UN CLIENTE
	 */
	private static final int PUERTO = 2000;
	private DataInputStream flujo_entrada;
    private DataOutputStream flujo_salida;
	private ObjectInputStream flujoObjEntrada;
	private ObjectOutputStream flujoObjSalida;

	private Socket skCliente;
	private Consultas consultas;

	//Constructor
	public Servidor(Socket cliente) {
		// inicializar socket cliente
		skCliente = cliente;

	}
	
	@Override
	public void run() {
		try {
		super.run();

		// inicializar conexiones
		iniciarConexiones();
		// mientras no se interrumpa el hilo, esperamos recibir una peticion del cliente para atenderla
		while (!isInterrupted()) {

			try {
				switch (flujo_entrada.readUTF()) {
				case LOG:
					gestionLOG();
					break;
				case LISTAR_USUARIOS:
					gestionListarUsuarios();
					break;
				case LISTAR_ALQUILERES:
					gestionListarAlquileres();
					break;
				case INSERTAR_USUARIO:
					gestionInsertarUsuario();
					break;
				case INSERTAR_ALQUILER:
					gestionInsertarAlquiler();
					break;
				case BORRAR_USUARIO:
					gestionBorrarUsuario();
					break;
				case BORRAR_ALQUILER:
					gestionBorrarAlquiler();
					break;
				case ACTUALIZAR_USUARIO:
					gestionActualizarUsuario();
					break;
				case ACTUALIZ_ALQUILER:
					gestionActualizarAlquiler();
					break;
				case LISTAR_USUARIO:
					gestionListarUsuario();
					break;
				case LISTAR_ALQUILER:
					gestionListarAlquiler();
					break;
				case LISTAR_PISTAS:
					gestionListarPista();
					break;
				case SALIR:
					gestionSalir();
				}
				//Controlamos excepciones
			}catch(SocketException e) {
				System.out.println("El cliente se fue");	
				this.interrupt();
			} catch (IOException e) {
				System.out.println("Error de conexiones");
				e.printStackTrace();
				this.interrupt();
			} catch (ClassNotFoundException e) {
				System.out.println("Error en la entrada de datos, no se encuentra la clase");
				this.interrupt();
			}

		}
		//Capturamos cualquier excepcion para que no salga nada rojo.
		}catch(Exception e) {
			System.out.println("FATAL ERROR");
			//y finalmente cerramos el hilo bien para que se puedan volver a conectar.
		}finally {
			System.out.println("Se cierra el servidor del hilo "+this.getName());
			if(skCliente!=null) {
				try {
					skCliente.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el socket");
				}
			}
		}

	}// Fin del Run
	
	private void gestionLOG() throws IOException {

		String usuario = flujo_entrada.readUTF();
		String pass = flujo_entrada.readUTF();
		boolean existe = consultas.validarUsuario(usuario, pass);
		flujo_salida.writeBoolean(existe);
		System.out.println(existe);
		if (existe) {
			flujoObjSalida.writeObject(consultas.getUsuario());
		}

	}
	
	private void gestionListarUsuarios() throws IOException{
		flujoObjSalida.writeObject(consultas.ListarUsuarios());
		
	}
	
	private void gestionListarAlquileres() throws IOException{
		flujoObjSalida.writeObject(consultas.ListarAlquileres());
		
	}
	
	private void gestionInsertarUsuario() throws IOException, ClassNotFoundException{
		Usuario usu = (Usuario) flujoObjEntrada.readObject();
		flujo_salida.writeBoolean(consultas.insertarUsuario(usu));
		
	}
	
	private void gestionInsertarAlquiler() throws IOException, ClassNotFoundException{
		Alquiler alq = (Alquiler) flujoObjEntrada.readObject();
		flujo_salida.writeBoolean(consultas.insertarAlquiler(alq));
		
	}
	
	private void gestionBorrarUsuario() throws IOException, ClassNotFoundException{
		int id = (int) flujoObjEntrada.readObject();
		flujo_salida.writeBoolean(consultas.BorrarUsuario(id));
		
	}
	
	private void gestionBorrarAlquiler() throws IOException, ClassNotFoundException{
		int id = (int) flujoObjEntrada.readObject();
		flujo_salida.writeBoolean(consultas.BorrarAlquiler(id));
		
	}
	
	private void gestionActualizarUsuario() throws IOException, ClassNotFoundException{
		Usuario usu = (Usuario) flujoObjEntrada.readObject();
		int id =flujo_entrada.readInt();
		flujo_salida.writeBoolean(consultas.ModificarUsuario(id,usu));
	}
	
	private void gestionActualizarAlquiler() throws IOException, ClassNotFoundException{
		Alquiler alq = (Alquiler) flujoObjEntrada.readObject();
		int id =flujoObjEntrada.readInt();
		flujo_salida.writeBoolean(consultas.ModificarAlquiler(id,alq));
		
	}
	
	private void gestionListarUsuario() throws IOException{
		int id=flujo_entrada.readInt();
		flujoObjSalida.writeObject(consultas.BuscarUsuario(id));
		
		
	}
	
	private void gestionListarAlquiler() throws IOException {
		int id=flujoObjEntrada.readInt();
		flujoObjSalida.writeObject(consultas.BuscarAlquiler(id));
		
	}
	
	private void gestionListarPista() throws IOException {
		int id=flujoObjEntrada.readInt();
		flujoObjSalida.writeObject(consultas.BuscarPista(id));
		
	}

	private void gestionSalir() throws IOException {

		this.interrupt();

	}
	//Metodo que inicializa los flujos de salida/entrada del servidor con el cliente
	private void iniciarConexiones() throws IOException {
			consultas=new Consultas();
			flujo_entrada = new DataInputStream(skCliente.getInputStream());
			flujo_salida = new DataOutputStream(skCliente.getOutputStream());
			flujoObjSalida = new ObjectOutputStream(skCliente.getOutputStream());
			flujoObjEntrada = new ObjectInputStream(skCliente.getInputStream());
		
	}

	public static void main(String[] args) {


		// Inicio el servidor en el puerto
		try {
			ServerSocket server = new ServerSocket(PUERTO);
			//int numClientes=0;
			while (true) {
				//try {
				System.out.println("Esperando cliente...");
				Socket cliente = server.accept();
				System.out.println("Cliente aceptado: "+cliente.getInetAddress());
				Servidor s=new Servidor(cliente);
				s.start();
				//s.join();
				/*}catch(Exception e) {
					
				}finally {
					
					numClientes++;
				}*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("SE CIERRA EL SERVIDOR");
		}

	}

}
