package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<link rel=\"apple-touch-icon\" href=\"https://img.zohostatic.com/iam/m4004.31/images/apple-touch-icon.png\">\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<title>ZOHO Accounts</title>\n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\n");
      out.write("\t\t\tbody {margin: 0px;padding:0px;font-family: \"Open sans\";font-weight: 300}\n");
      out.write("\t\t\th3 {margin:0px;font-size:14px;}\n");
      out.write("\t\t\t.hide {display:none;}\n");
      out.write("\t\t\t\n");
      out.write("\t</style>\n");
      out.write("    <link href=\"https://css.zohostatic.com/iam/m4004.31/css/iam3login.css\" rel=\"stylesheet\">\n");
      out.write("\t<body>\n");
      out.write("\t<table width=\"100%\" id=\"outertable\" class=\"mobile-login\" style=\"\">\n");
      out.write("\t     <tbody><tr valign=\"top\">\n");
      out.write("\t\t<td>\n");
      out.write("\t\t\t<div class=\"logocolor mlogin\"><span class=\"colorred\"></span><span class=\"colorgreen\"></span><span class=\"colorblue\"></span><span class=\"coloryellow\"></span></div>\n");
      out.write("\t\t</td>\n");
      out.write("\t    </tr>\n");
      out.write("\t    <tr>\n");
      out.write("\t\t\t<td align=\"center\">\n");
      out.write("\t\t</td>\n");
      out.write("\t\t</tr>\n");
      out.write("\t\t <tr>\n");
      out.write("\t\t     <td align=\"center\">\n");
      out.write("\t\t\t\t\t<div class=\"mobilelogo\"></div>\n");
      out.write("\t\t    \t\t<div class=\"logo-top\"></div>\n");
      out.write("\t\t    \t\t<form name=\"login\" id=\"login\" action=\"/login\" onsubmit=\"\" method=\"post\" style=\"width: 500px;height: 100%;margin-left:-20px;\" class=\"\">\n");
      out.write("\t\t\t\t\t\t<table width=\"100%\" id=\"table\">\n");
      out.write("\t\t\t\t\t\t<tbody>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t<td> Email / Phone :</td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("                          <input type=\"text\" name=\"Emailid\" id=\"Emailid\" class=\"unauthinputText\" placeholder=\"Email / Phone\">\n");
      out.write("                      \t</td>\n");
      out.write("                      \t</tr>\n");
      out.write("                      <tr>\n");
      out.write("\t\t\t\t\t\t<td> Current Password:</td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("                          <input type=\"password\" name=\"password\" id=\"password\" class=\"unauthinputText\" onkeyup=\"animatePlaceHolder(this);if(event.keyCode == 13){checkIEAndSubmit(document.login);return false;}\" placeholder=\"Current Password\">\n");
      out.write("                     \t</td>\n");
      out.write("                     \t</tr>\n");
      out.write("                      <tr>\n");
      out.write("\t\t\t\t\t\t<td> New Password:</td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("                          <input type=\"password\" name=\"newpassword\" id=\"newpassword\" class=\"unauthinputText\"  onkeyup=\"animatePlaceHolder(this);if(event.keyCode == 13){checkIEAndSubmit(document.login);return false;}\" placeholder=\"New Password\">\n");
      out.write("                      </td>\n");
      out.write("                      </tr>\n");
      out.write("                      <tr>\n");
      out.write("\t\t\t\t\t\t<td> Confirm Password:</td>\n");
      out.write("\t\t\t\t\t\t<td>\n");
      out.write("                          <input type=\"password\" name=\"confirmpassword\" id=\"confirmpassword\" class=\"unauthinputText\" onkeyup=\"animatePlaceHolder(this);if(event.keyCode == 13){checkIEAndSubmit(document.login);return false;}\" placeholder=\"Confirm Password\">\n");
      out.write("                      </td>\n");
      out.write("                      </tr>\n");
      out.write("                      <tr>\n");
      out.write("                      <td>\n");
      out.write("\t\t\t\t\t\t\t<input class=\"loginbut\" type=\"button\" value=\"Submit\" onclick=\"Login()\"> \n");
      out.write("\t\t\t\t\t  </td>                    \n");
      out.write("                      </tr>\n");
      out.write("                      </tbody>\n");
      out.write("                      </table>\n");
      out.write("                      </form>\n");
      out.write("              </td>\n");
      out.write("\t\t  </tr>\n");
      out.write("\t\t  </tbody>\n");
      out.write("\t\t  </table>\n");
      out.write("\t\t  <div id=\"response\" style=\"visibility:hidden;\">\n");
      out.write("\t\t  </div> \n");
      out.write("\t\t  <script>\n");
      out.write("\t\t  function Login()\n");
      out.write("\t\t \t{\n");
      out.write("\t\t \t\tvar Email=document.getElementById(\"Emailid\").value;\n");
      out.write("\t\t \t\tvar password=document.getElementById(\"password\").value;\n");
      out.write("\t\t \t\tvar newPassword=document.getElementById(\"newpassword\").value\n");
      out.write("\t\t \t\tvar confirmPassword=document.getElementById(\"confirmpassword\").value\n");
      out.write("\t\t \t\tvar filter= /^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-z0-9]{2,4})+$/;\n");
      out.write("\t\t\t  \tif(!filter.test(Email) )\n");
      out.write("\t\t\t  \t{\n");
      out.write("\t\t\t  \t\talert(\"Please enter valid email addresses\");\n");
      out.write("\t\t\t  \t\treturn false;\n");
      out.write("\t\t\t  \t}\n");
      out.write("\t\t \t\txmlHttp=createXmlHttpRequest();\n");
      out.write("\t\t\t\txmlHttp.open(\"POST\",\"loginaction.action\",true);\n");
      out.write("\t\t \t\txmlHttp.onreadystatechange=handleStateChange;\n");
      out.write("\t\t \t\txmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n");
      out.write("\t\t \t\t//xmlHttp.send(\"Email=\"+Email+\"Password=\"+password+\"NewPassword=\"+newPassword+\"ConfirmPassword=\"+confirmPassword);\n");
      out.write("\t\t \t\txmlHttp.send(\"Email=\"+Email+\"&\"+\"Password=\"+password+\"&\"+\"NewPassword=\"+newPassword+\"&\"+\"ConfirmPassword=\"+confirmPassword);\n");
      out.write("\t\t \n");
      out.write("\t\t \t\t//xmlHttp.send(\"Password=\"+password)\n");
      out.write("\t\t \t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\t\t \tfunction createXmlHttpRequest()\n");
      out.write("\t\t \t{\n");
      out.write("\n");
      out.write("\t\t \t\tif(window.ActiveXObject)\n");
      out.write("\t\t \t\t{\n");
      out.write("\t\t \t\t\txmlHttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("\t\t \t\t}\n");
      out.write("\t\t \t\telse if(window.XMLHttpRequest)\n");
      out.write("\t\t \t\t{\n");
      out.write("\t\t \t\t\txmlHttp=new XMLHttpRequest();\n");
      out.write("\t\t \t\t}\n");
      out.write("\t\t \t\treturn xmlHttp;\n");
      out.write("\t\t \t}\n");
      out.write("\t\t \tfunction handleStateChange()\n");
      out.write("\t\t \t{\n");
      out.write("\n");
      out.write("\t\t \t\tif(xmlHttp.readyState==4)\n");
      out.write("\t\t \t\t{\n");
      out.write("\t\t \t\t\n");
      out.write("\t\t \t\t\tif(xmlHttp.status==200)\n");
      out.write("\t\t \t\t\t{\n");
      out.write("\t \t\t\t\t\tdocument.getElementById('outertable').style.visibility = \"hidden\"; \n");
      out.write("\t\t\t\t\t\tdocument.getElementById('table').style.visibility = \"hidden\"; \n");
      out.write("\t \t\t\t\t\tdocument.getElementById('response').style.visibility = \"visible\"; \n");
      out.write("\t \t\t\t\t\tdocument.getElementById('response').innerHTML = xmlHttp.responseText;\n");
      out.write("\t\t \t\t\t}\n");
      out.write("\n");
      out.write("\t\t \t\t\telse\n");
      out.write("\t\t \t\t\t{\n");
      out.write("\t\t \t\t\t\t\tdocument.write(\"Error loading page\"+xmlHttp.status+\":\"+xmlHttp.statusText);\n");
      out.write("\t\t \t\t\t}\n");
      out.write("\n");
      out.write("\t\t \t\t}\n");
      out.write("\t\t \t}\n");
      out.write("\t\t\t</script>\n");
      out.write("\t<script>\n");
      out.write("\t\t \tfunction validate(){\n");
      out.write("\t\t \t        var newPassword = document.getElementById(\"newpassword\").value;\n");
      out.write("\t\t \t        var confirmPassword = document.getElementById(\"confirmpassword\").value;\n");
      out.write("\t\t \t        if (newPassword != confirmPassword) {\n");
      out.write("\t\t \t            alert(\"Passwords do not match.\");\n");
      out.write("\t\t \t            return false;\n");
      out.write("\t\t \t        }\n");
      out.write("\t\t \t        return true;\n");
      out.write("\t\t \t        \n");
      out.write("\t\t \t    }\n");
      out.write("\t\t \t \n");
      out.write("\t\t \t</script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
