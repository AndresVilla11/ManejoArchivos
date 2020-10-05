package dominio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestInmobiliaria implements Serializable {

	private static List<Inmueble> listaInmueble = new ArrayList<Inmueble>();
	private static List<Propietario> listaPro = new ArrayList<Propietario>();
	private static int opcion = -1;
	private static Scanner teclado = new Scanner(System.in);
	private final static Logger LOG = Logger.getLogger("bitacora.subnivel.Control");
	private static int inmNum = 0;

	public static void main(String[] args) {
		while (opcion != 0) {
			LOG.info("Elige una opcion: \n" + "1. Leer el archivo.\n" + "2. Cambiar estado de inmueble\n"
					+ "3. Consultar inmueble por tipo. \n" + "4. Consultar inmueble por propietario. \n"
					+ "5. Mostrar toda la informacion. \n" + "6. Mostrar inmuebles por antiguedad\n"
					+ "7. Mostrar inmuebles con administracion antigua.\n" + "8. Serializacion.\n"
					+ "9. Deserializacion.\n" + "0. Salir.\n");
			opcion = Integer.parseInt(teclado.nextLine());
			switch (opcion) {
			case 1:
				LOG.info("Digite el codigo del inmueble: ");
				String codigo = teclado.nextLine();
				LOG.info("Digite el nombre del archivo: ");
				listaInmueble.add(leerArchivo(teclado.nextLine(), codigo));
				break;
			case 2:
				LOG.info("Digite el codigo del inmueble: ");
				cambiarEstado(teclado.nextLine());
				break;
			case 3:
				LOG.info("Digite el nombre del archivo: ");
				String nombreArc = teclado.nextLine();
				LOG.info("Digite el tipo del inmueble: ");
				consultaTipo(teclado.nextLine(), nombreArc);
				break;
			case 4:
				LOG.info("Digite el nombre del archivo: ");
				nombreArc = teclado.nextLine();
				LOG.info("Digite el propietario: ");
				consultaPropietario(teclado.nextLine(), nombreArc);
				break;
			case 5:
				mostrarInmueble();
				break;
			case 6:
				LOG.info("Digite el nombre del archivo: ");
				nombreArc = teclado.nextLine();
				LOG.info("Digite el año: ");
				consultaAntigua(Integer.parseInt(teclado.nextLine()), nombreArc);
				break;
			case 7:
				LOG.info("Digite el nombre del archivo: ");
				nombreArc = teclado.nextLine();
				LOG.info("Digite la fecha completa dd-mm-yyyy: ");
				consultaAdm(teclado.nextLine().split("-"), nombreArc);
				break;
			case 8:
				break;
			case 9:
				break;
			case 0:
				LOG.info("Hasta luego.");
				break;
			}
		}
	}

	public static void escribir(String nombreArchivo, List<Inmueble> inmueble) {
		File archivo = new File(nombreArchivo + ".txt");
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo));
			salida.println(inmueble);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Inmueble leerArchivo(String nombreArchivo, String codigo) {
		File archivo = new File(nombreArchivo + ".txt");
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(archivo));
			String linea = entrada.readLine();
			Inmueble inmueble = new Inmueble();
			int proNum = 0;
			while (linea != null) {
				if ((linea.equalsIgnoreCase("#inmueble")) && (inmNum <= 10)) {
					linea = entrada.readLine();
					linea = entrada.readLine();
					linea = entrada.readLine();
					String[] renglonInmueble = linea.split("\\*");
					inmueble.setTipo(renglonInmueble[0].replaceAll("\\s", ""));
					inmueble.setDireccion(renglonInmueble[1].replaceAll("\\s", ""));
					inmueble.setCiudad(renglonInmueble[2].replaceAll("\\s", ""));
					inmueble.setFoto(renglonInmueble[3].replaceAll("\\s", ""));
					inmueble.setFechaCon(renglonInmueble[4].replaceAll("\\s", ""));
					inmueble.setEstado(renglonInmueble[5].replaceAll("\\s", ""));
					inmueble.setValor(renglonInmueble[6].replaceAll("\\s", ""));
					inmueble.setCodigo(codigo);
					linea = entrada.readLine();
					linea = entrada.readLine();
					inmNum++;
				}
				if ((linea.equalsIgnoreCase("#propietarios")) && (proNum < 3)) {
					linea = entrada.readLine();
					linea = entrada.readLine();
					while (!linea.equalsIgnoreCase("0") && (proNum < 3)) {
						Propietario pro = new Propietario();
						String[] renglonPro = linea.split("\\*");
						pro.setNombre(renglonPro[0].replaceAll("\\s", ""));
						pro.setTelefono(renglonPro[1].replaceAll("\\s", ""));
						listaPro.add(pro);
						linea = entrada.readLine();
						proNum++;
					}
					inmueble.setPropietarios(listaPro);
				}
				linea = entrada.readLine();
			}
			return inmueble;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void cambiarEstado(String codigo) {
		for (Inmueble inmueble : listaInmueble) {
			if ((inmueble.getCodigo().equalsIgnoreCase(codigo))) {
				String estado = (inmueble.getEstado().equalsIgnoreCase("venta")) ? "ocupado" : "libre";
				estado = (inmueble.getEstado().equalsIgnoreCase("alquiler")) ? "ocupado" : "libre";
				inmueble.setEstado(estado);
			}
		}
	}

	public static void consultaTipo(String tipo, String nombreArchivo) {
		List<Inmueble> inmuebleList = new ArrayList<Inmueble>();
		for (Inmueble inmueble : listaInmueble) {
			if ((inmueble.getTipo().equalsIgnoreCase(tipo)) && (inmueble.getEstado().equalsIgnoreCase("libre"))) {
				inmuebleList.add(inmueble);
			}
		}
		escribir(nombreArchivo, inmuebleList);
	}

	public static void consultaPropietario(String propieratorio, String nombreArchivo) {
		List<Inmueble> inmuebleList = new ArrayList<Inmueble>();
		for (Inmueble inmueble : listaInmueble) {
			for (Propietario propietarios : inmueble.propietarios) {
				if (propieratorio == propietarios.getNombre()) {
					inmuebleList.add(inmueble);
				}
			}
		}
		escribir(nombreArchivo, inmuebleList);
	}

	public static void mostrarInmueble() {
		for (Inmueble inmueble : listaInmueble) {
			LOG.info(inmueble.toString());
		}
	}

	public static void consultaAntigua(int antiguedad, String nombreArchivo) {
		List<Inmueble> inmuebleList = new ArrayList<Inmueble>();
		for (Inmueble inmueble : listaInmueble) {
			String[] fecha = inmueble.getFechaCon().split("-");
			if (antiguedad < (Integer.parseInt(fecha[2]))) {
				inmuebleList.add(inmueble);
			}
		}
		escribir(nombreArchivo, inmuebleList);
	}

	public static void consultaAdm(String[] adm, String nombreArchivo) {
		List<Inmueble> inmuebleList = new ArrayList<Inmueble>();
		for (Inmueble inmueble : listaInmueble) {
			String[] fecha = inmueble.getFechaAdm().split("-");
			if ((Integer.parseInt(adm[0]) < Integer.parseInt(fecha[0]))
					&& ((Integer.parseInt(adm[1]) <= Integer.parseInt(fecha[1])))
					&& ((Integer.parseInt(adm[2]) <= Integer.parseInt(fecha[2])))) {
				inmuebleList.add(inmueble);
			}
		}
		escribir(nombreArchivo, inmuebleList);
	}

}