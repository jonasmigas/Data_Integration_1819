/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_id;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author João
 */
public class TP_ID {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        procura_por_continente("Africa");

        //Paises p = procura_nomes("Alemanha");
        //System.out.println("Codigo ISO: " + p.getIso());
        //System.out.println("Nome: " + p.getNome());
        //System.out.println("Continente: " + p.getContinente());
        //System.out.println("Presidente da republica: " + p.getPres_rep());
        //System.out.println("Bandeira: " + p.getBandeira());
        //System.out.println(lista);
    }

    static ArrayList procura_por_continente(String procura) throws FileNotFoundException {
        String er = "href=\"" + procura + "/([^0-9]+)\\.php\">";
        Scanner ler = new Scanner(new FileInputStream("mundo.html"));
        Pattern p = Pattern.compile(er);
        Matcher m;
        String linha;
        ArrayList<String> lista = new ArrayList();
        while (ler.hasNext()) {
            linha = ler.next();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                lista.add(m.group(1));
            }
        }
        ler.close();
        return lista;

    }

}
