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
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmValue;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 *
 * @author João
 */
public class TP_ID {

    public static void main(String[] args) throws FileNotFoundException, IOException, SaxonApiException {
        // TODO code application logic here

        //procura_pais_por_continente("Ásia");
        //procura_continente_do_pais("Portugal"); //funciona
        //procura_iso_do_pais("Portugal"); //funciona
        //procura_pr_do_pais("Islândia"); //funciona
        //procura_link_bandeira_do_pais("Espanha"); //funciona
        //procura_capital("Portugal"); //funciona
        //cidade_mais_populosa("Portugal"); //funciona 
        //procura_hino("Itália"); //funciona em alguns
        //procura_moeda("Alemanha"); //funciona
        //procura_cod_telef("Alemanha"); //funciona
        //procura_cod_internet("China"); //funciona
        //procura_linguas_pais();// por fazer
        adicionaPaisesFicheiro("Ruanda");
        //adicionaFactosFicheiro("França");
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
        String er = "<a href=[^<]+>Presidente</a>[^>]*[^u]*[^<]*";
        String er2 = "<td style=[^<]+<a href=[^>]+>([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Scanner ler = new Scanner(new FileInputStream("pr_pais.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = ler.nextLine();
                linha = ler.nextLine();
                m = p2.matcher(linha);
                if (m.find()) {
                    System.out.println(m.group(1));
                    presidente = m.group(1);
                }
            }
        }
        ler.close();
        return presidente;
    }

    //procurar presidente da república um país fonte s1
    static String procura_link_bandeira_do_pais(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String bandeira = null;
        HttpRequestFunctions.httpRequest1(link, procura, "bandeira_pais.html");
        String er = "title=\"Bandeira d[^0-9]+ [^0-9]+\"><img alt=\"Bandeira d[^0-9]+ [^0-9]+\" src=\"(//upload.wikimedia.org/wikipedia/commons/thumb/[0-9a-z]+/[0-9a-z]+/Flag_of_[A-Za-z_]+.svg/125px-Flag_of_[A-Za-z_]+.svg.png)\"";
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
        String er = "<td style=\"[^>]+><b><a href=\"[^\"]+\" title=\"Lista de códigos telefónicos\">Cód. telef.</a></b>";
        String er2 = "<td style=\"[^>]+><code>([^<]+)</code>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Scanner ler = new Scanner(new FileInputStream("cod_telef.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = ler.nextLine();
                linha = ler.nextLine();
                m = p2.matcher(linha);
                if (m.find()) {
                    System.out.println(m.group(1));
                    cod_tel = m.group(1);
                }
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
        String er = "<td style=\"[^\"]+\"><b><a href=\"/wiki/Lista_de_TLDs\" title=\"Lista de TLDs\">[^<]+</a></b>";
        String er2 = "<td style=\"[^\"]+\"><a href=\"/wiki/[^\"]+\" title=\"[^\"]+\">([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Scanner ler = new Scanner(new FileInputStream("cod_internet.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = ler.nextLine();
                linha = ler.nextLine();
                m = p2.matcher(linha);
                if (m.find()) {
                    System.out.println(m.group(1));
                    cod_net = m.group(1);
                }
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
        String er = "<b>Cidade mais populosa</b>";
        String er2 = "title=\"[a-zA-Z\\d]+\">([a-zA-Z\\d]+)</a>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Scanner ler = new Scanner(new FileInputStream("cidade_populosa.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = ler.nextLine();
                linha = ler.nextLine();
                m = p2.matcher(linha);
                if (m.find()) {
                    System.out.println(m.group(1));
                    populosa = m.group(1);
                }
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
        String er = "<a href=\"/wiki/Hino_nacional\" title=\"Hino nacional\">Hino nacional</a>: (<i>)*<a href=\"/wiki/[^\"]+\"([^=]+=\"[^\"]+\")*[^=]+=\"[^\"]+\">(<i>)*([^<]+)</[ai]>";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("hino_nacional.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(4));
                hino = m.group(4);
            }
        }
        ler.close();
        return hino;
    }

    //procurar moeda de um país fonte s1
    static String procura_moeda(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/";
        String moeda = null;
        HttpRequestFunctions.httpRequest1(link, procura, "moeda.html");
        String er = "<td style=\"[^>]+><b><a href=\"[^\"]+\" title=\"Moeda\">Moeda</a></b>";
        String er2 = "<td style=\"[^>]+><a href=\"[^\"]+\"( class=\"[^\"]+\")? title=\"[^>]+>([^<]+)</a>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Scanner ler = new Scanner(new FileInputStream("moeda.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                linha = ler.nextLine();
                linha = ler.nextLine();
                m = p2.matcher(linha);
                if (m.find()) {
                    System.out.println(m.group(2));
                    moeda = m.group(2);
                }
            }
        }
        ler.close();
        return moeda;
    }

    public static Document adicionaPais(Paises p, Document doc) {

        Element raiz;
        if (doc == null) {
            raiz = new Element("paises"); //cria <paises>...</paises>
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
        }

        Element pais = new Element("pais");

        Attribute iso = new Attribute("iso", p.getIso());
        pais.setAttribute(iso);

        Element nome = new Element("nome");
        nome.addContent(p.getNome());

        Element continente = new Element("continente");
        continente.addContent(p.getContinente());

        Element presidente = new Element("presidente");
        presidente.addContent(p.getPres_rep());

        Element link_flag = new Element("link_flag");
        link_flag.addContent(p.getBandeira());

        pais.addContent(nome);
        pais.addContent(continente);
        pais.addContent(presidente);
        pais.addContent(link_flag);
        raiz.addContent(pais);

        return doc;

    }

    public static void adicionaPaisesFicheiro(String paisInt) throws FileNotFoundException, IOException, SaxonApiException {

        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        Element raiz;
        if (doc == null) {
            raiz = new Element("paises"); //cria <paises>...</paises>
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
        }

        String iso = procura_iso_do_pais(paisInt);

        String verif_rep = "//pais[@iso='" + iso + "']";
        //System.out.println(verif_rep);
        XdmValue res = XPathFunctions.executaXpath(verif_rep, "paises.xml");
        if (res != null && res.size() > 0) {
            System.out.println("PAÍS JÁ EXISTE!");
        } else {
            String continente = procura_continente_do_pais(paisInt);
            String presidente = procura_pr_do_pais(paisInt);
            String link_flag = procura_link_bandeira_do_pais(paisInt);

            Paises p = new Paises(iso, paisInt, continente, presidente, link_flag);
            doc = adicionaPais(p, doc);

            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "paises.xml");
        }
    }
    
    
    public static Document adicionaFactos(Factos f, Document doc) {

        Element raiz;
        if (doc == null) {
            raiz = new Element("factos"); //cria <paises>...</paises>
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
        }

        Element pais = new Element("pais");

        Attribute iso = new Attribute("iso", f.getIso());
        pais.setAttribute(iso);

        Element nome = new Element("nome");
        nome.addContent(f.getNome());

        Element cod_telef = new Element("cod_telef");
        cod_telef.addContent(f.getCod_telef());
        
        Element cod_inter = new Element("cod_inter");
        cod_inter.addContent(f.getCod_inter());
        
        Element capital = new Element("capital");
        capital.addContent(f.getCapital());
        
        Element cid_mais_pop = new Element("cid_mais_pop");
        cid_mais_pop.addContent(f.getCid_mais_pop());
        
        Element hino = new Element("hino");
        hino.addContent(f.getHino());
        
        Element moeda = new Element("moeda");
        moeda.addContent(f.getMoeda());
        
        Element populacao = new Element("populacao");
        populacao.addContent(f.getPopulacao());
        
        Element area = new Element("area");
        area.addContent(f.getArea());
        
        //Element lista_idiomas = new Element("lista_idiomas");
        //lista_idiomas.addContent(f.getLista_idiomas())

        pais.addContent(nome);
        pais.addContent(cod_telef);
        pais.addContent(cod_inter);
        pais.addContent(capital);
        pais.addContent(cid_mais_pop);
        pais.addContent(hino);
        pais.addContent(moeda);
        pais.addContent(populacao);
        pais.addContent(area);
        //pais.addContent(lista_idiomas);
        raiz.addContent(pais);

        return doc;

    }
    
    public static void adicionaFactosFicheiro(String paisInt) throws FileNotFoundException, IOException, SaxonApiException {

        Document doc = XMLJDomFunctions.lerDocumentoXML("factos.xml");
        Element raiz;
        if (doc == null) {
            raiz = new Element("paises"); //cria <paises>...</paises>
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
        }

        String iso = procura_iso_do_pais(paisInt);

        String verif_rep = "//pais[@iso='" + iso + "']";
        //System.out.println(verif_rep);
        XdmValue res = XPathFunctions.executaXpath(verif_rep, "factos.xml");
        if (res != null && res.size() > 0) {
            System.out.println("PAÍS JÁ EXISTE!");
        } else {
            String cod_telef = procura_cod_telef(paisInt);
            String cod_inter = procura_cod_internet(paisInt);
            String capital = procura_capital(paisInt);
            String cid_mais_pop = cidade_mais_populosa(paisInt);;
            String hino = procura_hino(paisInt);
            String moeda = procura_moeda(paisInt);
            String populacao ="teste";
            String area ="teste";

            Factos f = new Factos(iso, paisInt, cod_telef, cod_inter, capital, cid_mais_pop, hino, moeda, populacao, area, "testar idioma");
            doc = adicionaFactos(f, doc);

            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "factos.xml");
        }
    }

}