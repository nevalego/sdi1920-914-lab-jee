
package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito")
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletCarrito() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		HashMap<String, Integer> carrito = 
				(HashMap<String, Integer>) request.getSession().getAttribute("carrito"); 													// }

		String producto = request.getParameter("producto");
		if (producto != null) {
			insertarEnCarrito(carrito, producto);
		} 
		
		// Retornar la vista con parámetro "carrito"
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request,
		 response);

	}

//	private String carritoEnHTML(ConcurrentHashMap<String, Integer> carrito) {
//		String carritoEnHTML = "";
//		for (String key : carrito.keySet())
//			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
//		return carritoEnHTML;
//	}

	private void insertarEnCarrito(HashMap<String, Integer> carrito, String claveProducto) {
		if (carrito.get(claveProducto) == null)
			carrito.put(claveProducto, new Integer(1));
		else {
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
			carrito.put(claveProducto, new Integer(numeroArticulos + 1));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}