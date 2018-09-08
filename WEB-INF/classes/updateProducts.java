import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.util.*;

public class updateProducts extends HttpServlet implements Serializable{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		OracleDB odb = new OracleDB();
		int id=0;
		HashMap<Integer,Product> pro = odb.getLessThanFifty();
		StringBuilder s = new StringBuilder();
		
       s.append("<div id=\"vieworderscontent\">\n"
				    +"<fieldset>\n"
				    +"<legend>\n"
				    +"Inventory Management\n"
				    +"</legend>");
		for(Map.Entry<Integer,Product> entry: pro.entrySet())
		{
		Product p = entry.getValue();
		id=p.getId();
					 s.append("<table  class=\"table table-hover table-condensed cart\">\n"

			        +"<thead>\n"
			        +"<tr>\n"
			            +"<th style=\"width:50%\">\n"
			               +" Product \n"
			            +"</th>\n"
			            +"<th style=\"width:10%\">\n"
			                +"Available\n"
			            +"</th>\n"
			            +"<th style=\"width:9%\">\n"
			                +"OrderQuantity\n"
			            +"</th>\n"

			            +"<th style=\"width:9%\">\n"
			                +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
			            +"</th>\n"
			            +"<th style=\"width:6%\">\n"
			                +"actions\n"
			            +"</th>\n"
			        +"</tr>\n"
			        +"</thead>\n"
			        +"<tbody>\n"
			        +"<tr>\n"
			            +"<td data-th=\"Product\">\n"
			                +"<div class=\"row\">\n"
			                    +"<div class=\"col-sm-2 hidden-xs\">\n"
			                        +"<img src=\"images/"+p.getImgpath()+"\" alt=\"...\" class=\"img-responsive viewordersimg\"/>\n"
			                    +"</div>\n"
			                    +"<div class=\"col-sm-10\">\n"
			                        +"<h4 class=\"nomargin\">\n"
			                            +""+p.getName()+"\n"
			                        +"</h4>\n"
			                        +"<p>\n"
			                          +"  Here comes the Product description if any.\n"
			                           +" This is just a sample description.\n"
			                        +"</p>\n"
			                    +"</div>\n"
			                +"</div>\n"
			            +"</td>\n"
			            +"<td data-th=\"Available\">\n"
			                +""+p.getInventoryCount()+"\n"
			            +"</td>\n"
			            +"<form action=\"updateProductsDB\" method=\"get\">\n"
			                +"<td data-th=\"Quantity\" >\n"
			                  +"&nbsp;&nbsp;&nbsp;&nbsp;\n"
			                    +"<input type=\"number\" class=\"form-control text-center\" name=\"qtyup\" >\n"
			                +"</td>\n"

			                +"<td data-th=\"Subtotal\" class=\"text-center\">\n"
			                   +" \n"
			                +"</td>\n"
			                +"<input type=\"hidden\" id=\"prodid\" name=\"prodid\" value=\""+id+"\">"

			                +"<td class=\"actions\" data-th=\"actions\">\n"
			                    
			                     // +"<a type =\"button\" href=\"updateProductsDB?prodid="+p.getId()+"\" class=\"btn btn-info btn-sm\">\n"
			                     // +"Order\n"
			                     // +"</a>\n"
			                     +"<input type=\"submit\" class=\"btn btn-info btn-sm\" value=\"submit\" >"
			                     
			                +"</td>\n"
			            +"</form>\n"
			        +"</tr>\n"



			        +"</tbody>");

		}
		  s.append("<tfoot>\n"
							        +"<tr class=\"visible-xs\">\n"
							        +"<td class=\"text-center\"><strong> </strong></td>\n"
							        +"</tr>\n"
							        +"<tr>\n"
							        +"<td><a href=\"HomeServlet\" class=\"btn btn-warning\"><i class=\"fa fa-angle-left\"></i> Home</a></td>\n"
							        +"<td colspan=\"2\" class=\"hidden-xs\"></td>\n"
							        +"<td class=\"hidden-xs text-center\"><strong></strong></td>\n"
							        +"<td><a href=\"EndDB\" class=\"btn btn-success btn-block\">Complete Order <i class=\"fa fa-angle-right\"></i></a></td>\n"
							        +"</tr>\n"
							        +"</tfoot>\n"
							        );
		s.append(" </table>\n"
				    +"</fieldset>\n"
				+"</div>\n");
			Utilities utility = new Utilities(printWriter,request);
		 utility.printHtml("header.html","");
        utility.printHtml("sidebar.html","");
        utility.printHtml("cart",s.toString());
        utility.printHtml("footer.html","");
	
	}
}



























// +"<div id=\"vieworderscontent\">\n"
//     +"<fieldset>\n"
//     +"<legend>\n"
//     +"Inventory Management\n"
//     +"</legend>\n"



//     +"<table  class=\"table table-hover table-condensed cart\">\n"

//         +"<thead>\n"
//         +"<tr>\n"
//             +"<th style=\"width:50%\">\n"
//                +" Product \n"
//             +"</th>\n"
//             +"<th style=\"width:10%\">\n"
//                 +"Available\n"
//             +"</th>\n"
//             +"<th style=\"width:9%\">\n"
//                 +"OrderQuantity\n"
//             +"</th>\n"

//             +"<th style=\"width:9%\">\n"
//                 +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subtotal\n"
//             +"</th>\n"
//             +"<th style=\"width:6%\">\n"
//                 +"actions\n"
//             +"</th>\n"
//         +"</tr>\n"
//         +"</thead>\n"
//         +"<tbody>\n"
//         +"<tr>\n"
//             +"<td data-th=\"Product\">\n"
//                 +"<div class=\"row\">\n"
//                     +"<div class=\"col-sm-2 hidden-xs\">\n"
//                         +"<img src=\"images/laptopStickers.jpg\" alt=\"...\" class=\"img-responsive viewordersimg\"/>\n"
//                     +"</div>\n"
//                     +"<div class=\"col-sm-10\">\n"
//                         +"<h4 class=\"nomargin\">\n"
//                             +"Laptop Stickers\n"
//                         +"</h4>\n"
//                         +"<p>\n"
//                           +"  Here comes the Product description if any.
//                             This is just a sample description.\n"
//                         +"</p>\n"
//                     +"</div>\n"
//                 +"</div>\n"
//             +"</td>\n"
//             +"<td data-th=\"Price\">\n"
//                 $98
//             +"</td>\n"
//             +"<form action=\"HomeServlet\" method=\"get\">\n"
//                 +"<td data-th=\"Quantity\" >\n"
//                   +"&nbsp;&nbsp;&nbsp;&nbsp;\n"
//                     +"<input type=\"number\" class=\"form-control text-center\" value=\"1\">\n"
//                 +"</td>\n"

//                 +"<td data-th=\"Subtotal\" class=\"text-center\">\n"
//                    +" $196\n"
//                 +"</td>\n"
//                 +"<td class=\"actions\" data-th=\"actions\">\n"
                    
//                      +"<a type =\"button\" href=\"#\" class=\"btn btn-info btn-sm\">\n"
//                      +"Order\n"
//                      +"</a>\n"
                     
//                 +"</td>\n"
//             +"</form>\n"
//         +"</tr>\n"



//         +"</tbody>\n"

//    +" </table>\n"
//     +"</fieldset>\n"
// +"</div>\n"