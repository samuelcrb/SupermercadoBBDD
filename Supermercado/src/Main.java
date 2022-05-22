import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws LinealIncorrectoException, SQLException {
		
		Supermercado supermercado = new Supermercado();
		
		int op;
		boolean repetir = true;
		
		while(repetir) {
			try {
				do {
					System.out.println("1. Dar de alta un producto.\n" + "2. Eliminar un producto.\n" + 
							"3. Consultar existencias.\n"+"4. Consultar inventario.\n"+ "5.Salir.\n\n" + "Introduce una opción: ");
					
					Scanner teclado = new Scanner(System.in);
					op = teclado.nextInt(); 
				
					switch (op) {
	
					case 1:
						
						String letraLineal = null; 
						String codigo,nombre;
						int num_existencias;
						
						System.out.println("Introduce el lineal");
						letraLineal = teclado.next();
							
						System.out.println("Introduce el código del producto");
						codigo = teclado.next();
							
						System.out.println("Introduce el nombre del producto");
						nombre = teclado.next();
							
						System.out.println("Introduce el numero de existencias");
						num_existencias = teclado.nextInt();
							
						supermercado.añadir(nombre, codigo, num_existencias, letraLineal);
						break;
						
					case 2:
						repetir = true;
						while(repetir) {
							
							try {
								
								String referencia;
								System.out.println("Introduce la referencias del producto");
								referencia = teclado.next();
								if(referencia == null || !referencia.getClass().getSimpleName().equals("String")) {
									throw new ProductoIncorrectoException();
								}
								
								supermercado.eliminar(referencia);
								repetir = false;
							} catch (ProductoIncorrectoException e) {
								System.err.println(e.getMessage());
							}catch(Exception e) {
								System.err.println("Producto no encontrado");
							}
							
						}
						
						break;
						
					case 3:
						String op2;
						repetir = true;
						while(repetir) {
							
							try {
								
								do {
									
									System.out.println("¿Que existencias quieres consultar?\n" + 
											"a. Por lineal\n" + "b.Por tipo\n" + "c.Total\n" + "d.Salir");
									op2 = teclado.next();
									
									switch (op2) {
									
									case "a":
										System.out.println("¿Que lineal quieres consultar?\n");
										letraLineal = teclado.next();										
										System.out.println(supermercado.existenciasLineal(letraLineal));
										break;
										
									case "b":
										System.out.println("¿Que tipo de producto quieres consultar?\n");
										String tipo = teclado.next();
										System.out.println(supermercado.existenciasTipo(tipo));
										break;
										
									case "c":
										System.out.println(supermercado.existenciasTotales());
										break;
										
									case "d":
										break;
										
									default:
										throw new InputMismatchException();
									}
									
									
								} while (!op2.equals("d"));
								
								supermercado.getProductoDAO().cerrarConexion();
								repetir = false;
								
							} catch (InputMismatchException e) {
								System.err.println("Error: Opcion invalida");
							}
						}

						break;
						
					case 4:
						repetir = true;
						while(repetir) {
							
							try {
								do {
									
									System.out.println("¿Que inventario quieres ver?\n" + 
											"a. Por lineal\n" + "b.Total\n" + "c.Salir");
									op2 = teclado.next();
									
									switch (op2) {
									
									case "a":
										System.out.println("¿Que lineal quieres consultar para ver su inventario?\n");
										letraLineal = teclado.next();
										System.out.println(supermercado.inventarioLineal(letraLineal));
										break;
										
									case "b":
										System.out.println(supermercado.inventarioTotal());
										break;
										
									case "c":
										break;
										
									default:
										throw new InputMismatchException();
										
									}
									
								} while (!op2.equals("c"));
								repetir = false;
								
							} catch (InputMismatchException e) {
								System.err.println("Error: Opcion invalida");
							}
							
						}
						
						break;
						
					case 5:
						System.out.println("Programa finalizado, cerrando conexión...");
						supermercado.getProductoDAO().cerrarConexion();
						break;
						
					default:
						
						throw new InputMismatchException();
					}
					
				}while(op != 5);
				
				repetir = false;
				break;
				
			} catch (InputMismatchException e) {
				System.err.println("Error: El valor introducido no es el esperado");
			}
		}

	}

}
