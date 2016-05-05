/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 */
public class PL {

    public static String programName;
    
    public static void main(String argv[]) {
        if (argv.length == 0) {
            System.out.println("Inserta nombre de archivo\n"
                    + "( Usage : java Analizador <inputfile> )");
        } else {
            for (int i = 0; i < argv.length; i++) {
                programName = argv[i];
                PracticaLexico lexico = null;
                try {
                    lexico = new PracticaLexico(new java.io.FileReader(argv[i]));
                    parser sintactico = new parser(lexico);
                    sintactico.parse();
                    /*FileOutputStream nuevoArchivo;
                    PrintStream ps;
                    try{
                        nuevoArchivo = new FileOutputStream(argv[i]+".html");
                        ps = new PrintStream(nuevoArchivo);
                        
                        ps.close();
                    }catch (FileNotFoundException e){
                        System.out.println("El archivo no se ha podido encontrar");
                    }*/
                } catch (java.io.FileNotFoundException e) {
                    System.out.println("Archivo \"" + argv[i] + "\" no encontrado.");
                } catch (java.io.IOException e) {
                    System.out.println("Error durante la lectura del"
                            + " archivo \"" + argv[i] + "\".");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Excepcion:");
                    e.printStackTrace();
                }
            }
        }
    }
}
