package eProduct;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.element.Element;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
	

/**
 * Servlet implementation class AddElementServlet
 */
public class AddElementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SessionFactory sessionFactory = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		sessionFactory = hibernateUtil.getSessionFactory();		
	}
	

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     
	 String Name = request.getParameter("productName");
	 BigDecimal Price = new BigDecimal(request.getParameter("productPrice"));
 
     Element product = new Element();
     product.setProductName(Name);
     product.setProductPrice(Price);

     Configuration configuration = new Configuration().configure();
     try (SessionFactory factory = configuration.buildSessionFactory();
             Session session = factory.openSession()) {

         Transaction transaction = session.beginTransaction();
         session.save(product);
         transaction.commit();
     }
     
     
     

     response.sendRedirect("Element.jsp");
 }
     
     
     
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			PrintWriter out = response.getWriter();
			out.println("<html><body>");

			Session session = sessionFactory.openSession();
			
			List<Element> products= session.createQuery("from Element").list();
			out.println("<table border=1>");
			out.println("<tr><th>ID<th> PRODUCT NAME <th>PRODUCT PRICE<th></tr>");
			
			for(Element p: products) {
				out.println("<tr>");
				out.printf("<td> %s", p.getID());
				out.printf("<td> %s", p.getProductName());
				out.printf("<td> %s", p.getProductPrice());
			}
			
			out.println("</table border=1>");
			session.close();
			
			out.println("</body></html>");
			out.close();
 }
}
