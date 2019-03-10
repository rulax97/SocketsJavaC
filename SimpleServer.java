// SimpleServer.java: a simple server program
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;
//*Autor: Raul Ernesto Perez Barcenas*//
//*Matricula: 148661*//
//*Version: 1.0*//
//*Asignatura: Programacion Integrativa (UACJ)*//
public class SimpleServer
{
    public static String data="";
    public static String type="",sensorname="",time="",date="",Checksum="";
    public static String st="";
    public static OutputStream os = null;
    public static int checksum;
    public static String u_1="",u_2="",u_3="",u_4="",u_5="",u_6="";
    public static void main(String[] args) throws IOException
    {
      if (args.length < 1)
      {
        System.out.println("Usage SimpleServer portNumber");
        System.exit(1);
      }
      int port = Integer.parseInt(args[0]);
      ServerSocket s = new ServerSocket(port);
      while(true)
      {
        //data=st;
        Socket s1=s.accept();
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        byte[] buffer = new byte[255];
        dis.read(buffer,0,35);
        st = new String (buffer);
        char[] comp = st.toCharArray();
        int compare = (int) comp[0];
        if(compare==85)
        {
          data=st;
          try
          {
            data=data.trim();
            u_1=data.substring(0,1);
            u_2=data.substring(1,9);
            u_3=data.substring(9,17);
            u_4=data.substring(17,23);
            u_5=data.substring(23,31);
            u_6=data.substring(31,34);
            BufferedWriter out = new BufferedWriter (new FileWriter("medicion.csv", true));
            out.write(u_1);
            out.write(",");
            out.write(u_2);
            out.write(",");
            out.write(u_3);
            out.write(",");
            out.write(u_4);
            out.write(",");
            out.write(u_5);
            out.write(",");
            out.write(u_6);
            out.write("\n");
            out.close();
          }
          catch (IOException e)
          {
            System.out.println("Excepcion ocurrida: " + e);
          }
        }
        if(compare==82)
        {
          data=st;
          try
          {
            data=data.trim();
            BufferedWriter out = new BufferedWriter (new FileWriter("transaccion.csv", true));
            out.write(data.substring(0,1));
            out.write(",");
            out.write(data.substring(1,9));
            out.write(",");
            out.write(data.substring(9,17));
            out.write(",");
            out.write(data.substring(17,20));
            out.write("\n");
            out.close();
          }
          catch (IOException e)
          {
            System.out.println("Excepcion ocurrida: " + e);
          }
        }
        System.out.println(st);
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream (s1out);
        dos.close();
        dis.close();
        s1In.close();
        s1out.close();
        s1.close();
    }
  }
}
