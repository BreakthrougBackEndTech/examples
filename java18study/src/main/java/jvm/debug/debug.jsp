<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="jvm.debug.*" %>
<%
    InputStream is = new FileInputStream("c:/TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();

    System.out.println("<textarea style='width:1000;height=800'>");
    System.out.println(JavaclassExecuter.execute(b));
    System.out.println("</textarea>");
%>