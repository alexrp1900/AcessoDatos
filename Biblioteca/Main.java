package Biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// Crear el codigo para que funcione la BBDD
	public static ArrayList<Llibre> leer(String ruta) {
		

		ArrayList<Llibre> libros = new ArrayList();

		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea;
			int cont = 0;
			while ((linea = br.readLine()) != null) {
				if (cont >= 1) {
					String[] al = linea.split(";");
					Llibre ll = new Llibre();

					for (int i = 0; i < al.length; i++) {
						if (al[i].equals("")) {
							al[i] = "";
						}
					}

					ll.settitulo(al[0]);
					ll.setAutor(al[1]);
					ll.setanyo_nac(al[2]);
					ll.setanyo_publi(al[3]);
					ll.setEditorial(al[4]);
					ll.setnum_pag(al[5]);

					libros.add(ll);
				}
				cont++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return libros;
	}

	// Main
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<Llibre> llibre = leer(".\\src\\Biblioteca\\AE04_T1_4_JDBC_Dades.csv");
		int cont = 0;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");

			Statement s = conexion.createStatement();

			for (int i = 0; i < llibre.size(); i++) {

				String titulo = llibre.get(i).gettitulo();
				String autor = llibre.get(i).getAutor();
				String anyo_nac = llibre.get(i).getanyo_nac();
				String anyo_publi = llibre.get(i).getanyo_publi();
				String editorial = llibre.get(i).getEditorial();
				String num_pag = llibre.get(i).getnum_pag();

				PreparedStatement ps = conexion.prepareStatement(
						"INSERT INTO `libro` (`titulo`, `autor`, `anyo_nac`, `anyo_publi`, `editorial`, `num_pag`) VALUES ('"
								+ titulo + "','" + autor + "','" + anyo_nac + "','" + anyo_publi + "','" + editorial
								+ "','" + num_pag + "')");
				ps.executeUpdate();

				ResultSet rs = s.executeQuery("SELECT * FROM `libro`");

				while (rs.next()) {
					cont++;
				}

				rs.close();
				ps.close();
			}

			ResultSet rs = s
					.executeQuery(("SELECT `titulo`, `autor`, `anyo_publi` FROM `libro` WHERE `anyo_nac` <= 1950"));
			while (rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}

			rs = s.executeQuery(("SELECT `editorial` FROM `libro` WHERE `anyo_nac` >= 2001"));
			while (rs.next()) {
				System.out.println(rs.getString(1));
			}

			System.out.println("¿Quieres hacer otra consulta?");
			String x = sc.nextLine();

			if (x.equals("si")) {
				System.out.print("¡Escribe la consulta!");
				String consulta = sc.nextLine();

				rs = s.executeQuery("SELECT * FROM `libro` WHERE Id");
				ResultSetMetaData rsmd = rs.getMetaData();
				while (rs.next()) {
					System.out.print(rs.getString(1));
					for (int i = 1; i < rsmd.getColumnCount(); i++) {
						System.out.print(rs.getString(i) + " ");
					}
					System.out.println();
				}
			}

			rs.close();
			s.close();
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}