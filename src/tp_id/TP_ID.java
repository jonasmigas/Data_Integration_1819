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

        //procura_pais_por_continente("Ásia");
        //procura_continente_do_pais("Portugal"); //funciona
        //procura_iso_do_pais("Portugal"); //funciona
        //procura_pr_do_pais("Portugal"); //não funciona aqui. penso que é que por causa de estar a tentar fazer match de 3 linhas numa linha. acho que não fizemos nada parecido nas aulas
        procura_link_bandeira_do_pais("Ruanda"); //nao funciona. não percebo porque, visto que é só uma linha
        //procura_capital("Angola"); //funciona
        //cidade_mais_populosa("Portugal"); //não funciona pelo mesmo motivo
        //procura_hino("França"); //funciona
        //procura_moeda("Portugal"); //não funciona pelo mesmo motivo
        //procura_cod_telef("Portugal"); //não funciona pelo mesmo motivo
        //procura_cod_internet("Portugal"); //não funciona pelo mesmo motivo

    }

    //procurar paises por continente fonte s2
    static ArrayList procura_pais_por_continente(String procura) throws FileNotFoundException {
        String er = "href=\"[^0-9]+/[^0-9]+\\.php\">([^0-9]+)</a></td><td>[^0-9]+</td><td>" + procura + "</td>";
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

    //procurar continente de um país fonte s2
    static String procura_continente_do_pais(String procura) throws FileNotFoundException, IOException {
        String link = "https://www.sport-histoire.fr/pt/Geografia/Paises_por_ordem_alfabetica.php";
        String nome = null;
        HttpRequestFunctions.httpRequest1(link, "", "continente_pais.html");
        String er = "<td><a href=\"[^\"]+\\.php\">" + procura + "</a></td><td>[^<]+</td><td>([^<]+)</td>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("continente_pais.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                nome = m.group(1);
            }
        }
        ler.close();
        return nome;
    }

    //procurar codiso 3 de um país fonte s2
    static String procura_iso_do_pais(String procura) throws FileNotFoundException, IOException {
        String link = "https://www.sport-histoire.fr/pt/Geografia/Codigos_ISO_Paises.php";
        String codiso = null;
        HttpRequestFunctions.httpRequest1(link, "", "codiso_pais.html");
        String er = "<a href=\"[^\"]+/[^\"]+\\.php\">" + procura + "</a></td><td>[^<]+</td><td class=\"centrer\">([^<]+)</td>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("codiso_pais.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                codiso = m.group(1);
            }
        }
        ler.close();
        return codiso;
    }

    //procurar presidente da república um país fonte s1
    static String procura_pr_do_pais(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String presidente = null;
        HttpRequestFunctions.httpRequest1(link, procura, "pr_pais.html");
        String er = "<a href=[^<]+>Presidente</a>[^>]*[^u]*[^<]*\\n</td>\\n<td style=[^<]+<a href=[^>]+>([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("pr_pais.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                presidente = m.group(1);
            }
        }
        ler.close();
        return presidente;
    }

    //procurar presidente da república um país fonte s1
    static String procura_link_bandeira_do_pais(String procura) throws FileNotFoundException, IOException {
        String link = "http://pt.wikipedia.org/wiki/Lista_de_Estados_soberanos_e_territórios_dependentes_por_continente";
        String bandeira = null;
        HttpRequestFunctions.httpRequest1(link, "", "bandeira_pais.html");
        String er = "<td><span class=\"flagicon\"><a href=\"/wiki/[^\"]+\" title=\"" + procura + "\"><img alt=\"[^\"]+\" src=\"[^\"]+\" decoding=\"async\" width=\"[0-9]+\" height=\"[0-9]+\" class=\"thumbborder\" srcset=\"[^,]+,([^\"]+)\" data-file-width=\"[0-9]+\" data-file-height=\"[0-9]+\" /></a></span>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("bandeira_pais.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                bandeira = m.group(1);
            }
        }
        ler.close();
        return bandeira;
    }

    //procurar cod telefone de um país fonte s1
    static String procura_cod_telef(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String cod_tel = null;
        HttpRequestFunctions.httpRequest1(link, procura, "cod_telef.html");
        String er = "<a href=\"/wiki/[^\"]+\" title=\"Lista de códigos telefónicos\">[^\"]+</a></b>\\n</td>\\n<td style=\"[^\"]+\"><code>([^<]+)</code>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("cod_telef.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                cod_tel = m.group(1);
            }
        }
        ler.close();
        return cod_tel;
    }

    //procurar cod internet de um país fonte s1
    static String procura_cod_internet(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String cod_net = null;
        HttpRequestFunctions.httpRequest1(link, procura, "cod_internet.html");
        String er = "<td style=\"[^\"]+\"><b><a href=\"/wiki/Lista_de_TLDs\" title=\"Lista de TLDs\">[^<]+</a></b>\\n"
                + "</td>\\n"
                + "<td style=\"[^\"]+\"><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("cod_internet.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                cod_net = m.group(1);
            }
        }
        ler.close();
        return cod_net;
    }

    //procurar capital de um país fonte s1
    static String procura_capital(String procura) throws FileNotFoundException, IOException {
        String link = "https://www.sport-histoire.fr/pt/Geografia/Paises_por_ordem_alfabetica.php";
        String capital = null;
        HttpRequestFunctions.httpRequest1(link, "", "capital.html");
        String er = "<td><a href=\"[^\"]+\\.php\">" + procura + "</a></td><td>([^<]+)</td><td>[^<]+</td>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("capital.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                capital = m.group(1);
            }
        }
        ler.close();
        return capital;
    }

    //procurar cidade mais populosa de um país fonte s1
    static String cidade_mais_populosa(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String populosa = null;
        HttpRequestFunctions.httpRequest1(link, procura, "cidade_populosa.html");
        String er = "<td style=\"[^\"]+\"><b>Cidade mais populosa</b>\\r?\\n</td>\\r?\\n<td style=\"[^\"]+\"><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("cidade_populosa.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                populosa = m.group(1);
            }
        }
        ler.close();
        return populosa;
    }

    //procurar hino de um país fonte s1
    static String procura_hino(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String hino = null;
        HttpRequestFunctions.httpRequest1(link, procura, "hino_nacional.html");
        String er = "<a href=\"/wiki/Hino_nacional\" title=\"Hino nacional\">Hino nacional</a>: <i><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("hino_nacional.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                hino = m.group(1);
            }
        }
        ler.close();
        return hino;
    }

    //procurar moeda de um país fonte s1
    static String procura_moeda(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/ISO_4217";
        String moeda = null;
        HttpRequestFunctions.httpRequest1(link, "", "moeda.html");
        String er = "<td><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">([^<]+)</a></td>\\n"
                + "<td><span class=\"flagicon\"><img alt=\"\" src=\"[^\"]+\" decoding=\"async\" width=\"[^\"]+\" "
                + "height=\"[^\"]+\" class=\"thumbborder\" srcset=\"[^\"]+\" data-file-width=\"[^\"]+\" data-file-height=\"[^\"]+\" />"
                + "&#[^\"]+;</span><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">" + procura + "</a>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("moeda.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                moeda = m.group(1);
            }
        }
        ler.close();
        return moeda;
    }

}
