/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_id;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmValue;
import org.jdom2.Attribute;
import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

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
        //cidade_mais_populosa("França"); //funciona 
        //procura_hino("Brasil"); //funciona em alguns
        //procura_moeda("Alemanha"); //funciona
        //procura_cod_telef("Alemanha"); //funciona
        //procura_cod_internet("China"); //funciona
        //procura_lingua_oficial("Portugal");                              // por fazer
        //validarDocumentoDTD("paises.xml", "paises.dtd");
        //validarDocumentoDTD("factos.xml", "factos.dtd");
        //validarDocumentoXSD("paises.xml", "paises.xsd");
        //validarDocumentoXSD("factos.xml", "factos.xsd");                //está a dar erros
        //adicionaInfoAmbosFicheiros("Luxemburgo");
        //testarHTML_FLAGS();
        //procura_lingua_oficial("Portugal");// funciona
        //procura_populacao("Portugal");//funciona milhoes
        //procura_area("Portugal");//funciona km2
        //adicionaPaisesFicheiro("França");
        //removePaisFicheiros("Brasil");
        //adicionaInfoAmbosFicheiros("Portugal");
        //adicionaPaisesFicheiro("Ruanda");
        //adicionaFactosFicheiro("França");
        //alteraPresidente("França", "Chirac");
        //alteraPopulacao("Angola", "23");
        //alteraCidadePopulosa("Angola", "Benguela");
        procura_pais_por_idioma("Português");
        //procura_pais_por_continente("América");
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
        String er2 = "<td style=\"[^>]+>([^>]+>)?([a-zA-Z\\d]+)";
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
                    System.out.println(m.group(2));
                    populosa = m.group(2);
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

    //procurar linguas oficiais de um país fonte s1
    static ArrayList procura_lingua_oficial(String procura) throws FileNotFoundException, IOException {
        String link = "https://pt.wikipedia.org/wiki/Lista_de_Estados_soberanos";
        ArrayList<String> lingua = new ArrayList();
        HttpRequestFunctions.httpRequest1(link, "", "linguas.html");
        String er = "<img alt=\"" + procura + "\"";
        String er2 = "title=\"Língua [^\"]+\">([^<]+)</a>";
        String er3 = "</td>";
        Pattern p = Pattern.compile(er);
        Pattern p2 = Pattern.compile(er2);
        Pattern p3 = Pattern.compile(er3);
        Scanner ler = new Scanner(new FileInputStream("linguas.html"));
        Matcher m;
        Matcher m1;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                while (ler.hasNextLine()) {
                    linha = ler.nextLine();
                    m = p2.matcher(linha);
                    m1 = p3.matcher(linha);
                    if (m1.find()) {
                        ler.close();
                        return lingua;
                    }
                    if (m.find()) {
                        lingua.add(m.group(1));
                    }
                }
            }
        }
        ler.close();
        return lingua;
    }
    //procurar populacao total de um pais fonte s2 (ano 2019)

    static String procura_populacao(String procura) throws FileNotFoundException, IOException {
        String link = "https://www.sport-histoire.fr/pt/Geografia/Paises_por_populacao.php";
        String populacao = null;
        HttpRequestFunctions.httpRequest1(link, "", "populacao.html");
        String er = "<a href=\"[^>]+>" + procura + "</a></td><td>[^<]+</td><td [^>]+>([^<]+)</td";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("populacao.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                populacao = m.group(1);
            }
        }
        ler.close();
        return populacao;
    }

    //procurar area total de um pais fonte s2
    static String procura_area(String procura) throws FileNotFoundException, IOException {
        String link = "https://www.sport-histoire.fr/pt/Geografia/Paises_por_area.php";
        String area = null;
        HttpRequestFunctions.httpRequest1(link, "", "area.html");
        String er = "<a href=\"[^>]+>" + procura + "</a></td><td>[^<]+</td><td [^>]+>([^<]+)</td";
        Pattern p = Pattern.compile(er);
        Scanner ler = new Scanner(new FileInputStream("area.html"));
        Matcher m;
        String linha;
        while (ler.hasNextLine()) {
            linha = ler.nextLine();
            m = p.matcher(linha);
            if (m.find()) {
                System.out.println(m.group(1));
                area = m.group(1);
            }
        }
        ler.close();
        return area;

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
            System.out.println("PAÍS JÁ EXISTE NO FICHEIRO \"paises.xml\"!");
        } else {
            String continente = procura_continente_do_pais(paisInt);
            String presidente = procura_pr_do_pais(paisInt);
            String link_flag = procura_link_bandeira_do_pais(paisInt);

            Paises p = new Paises(iso, paisInt, continente, presidente, link_flag);
            doc = adicionaPais(p, doc);

            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "paises.xml");
        }
    }

    public static Document adicionaFactos(Factos f, Idiomas d, Document doc, int tam, ArrayList lista) {

        Element raiz;
        if (doc == null) {
            raiz = new Element("factos");
            doc = new Document(raiz);
        } else {
            raiz = doc.getRootElement();
        }

        Element pais = new Element("pais");

        Attribute iso = new Attribute("iso", f.getIso());
        pais.setAttribute(iso);

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
        pais.addContent(cod_telef);
        pais.addContent(cod_inter);
        pais.addContent(capital);
        pais.addContent(cid_mais_pop);
        pais.addContent(hino);
        pais.addContent(moeda);
        pais.addContent(populacao);
        pais.addContent(area);

        Element lista_idiomas = new Element("lista_idiomas");
        pais.addContent(lista_idiomas);
        for (int i = 0; i < tam; i++) {
            String formatado = lista.get(i).toString().replace(",", "").replace("[", "").replace("]", "");
            d = new Idiomas(formatado);
            Element idioma = new Element("idioma");
            idioma.addContent(d.getIdioma());
            lista_idiomas.addContent(idioma);
        }

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
            System.out.println("PAÍS JÁ EXISTE NO FICHEIRO \"factos.xml\"!");
        } else {
            String cod_telef = procura_cod_telef(paisInt);
            String cod_inter = procura_cod_internet(paisInt);
            String capital = procura_capital(paisInt);
            String cid_mais_pop = cidade_mais_populosa(paisInt);
            String hino = procura_hino(paisInt);
            String moeda = procura_moeda(paisInt);
            String populacao = procura_populacao(paisInt);
            String area = procura_area(paisInt);
            ArrayList lista_idiomas = procura_lingua_oficial(paisInt);

            Factos f = new Factos(iso, cod_telef, cod_inter, capital, cid_mais_pop, hino, moeda, populacao, area);

            int tam = lista_idiomas.size();
            String formatado = lista_idiomas.toString().replace(",", "").replace("[", "").replace("]", "");
            Idiomas d = new Idiomas(formatado);
            doc = adicionaFactos(f, d, doc, tam, lista_idiomas);

            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "factos.xml");
        }
    }

    public static void adicionaInfoAmbosFicheiros(String paisInt) throws IOException, FileNotFoundException, SaxonApiException {

        adicionaPaisesFicheiro(paisInt);
        adicionaFactosFicheiro(paisInt);

    }

    //remover um pais dos ficheiro paises.xml e factos.xml
    public static Document removePaisFicheiros(String procura) {
        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        String iso = "";
        Element raiz;
        raiz = doc.getRootElement();
        List todosPaises = raiz.getChildren("pais");
        boolean found = false;

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getChild("nome").getText().equals(procura)) {
                iso = pais.getAttributeValue("iso");
                pais.getParent().removeContent(pais);
                System.out.println("Pais removido com sucesso do ficheiro livros.xml!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pais " + procura + " não foi encontrado");
        }

        Document doc2 = XMLJDomFunctions.lerDocumentoXML("factos.xml");
        Element raiz2;
        raiz2 = doc2.getRootElement();
        List factos = raiz2.getChildren("pais");
        boolean found2 = false;

        for (int i = 0; i < factos.size(); i++) {
            Element pais = (Element) factos.get(i); //obtem pais i da Lista
            if (pais.getAttributeValue("iso").equals(iso)) {
                pais.getParent().removeContent(pais);
                System.out.println("Pais removido com sucesso do ficheiro factos.xml!");
                found2 = true;
            }
        }
        if (!found2) {
            System.out.println("Pais " + procura + " não foi encontrado");
        }
        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "paises.xml");
        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc2, "factos.xml");

        return doc;
    }

    //funcao para alterar o presidente do pais inserido no parametro
    public static Document alteraPresidente(String procura, String novoPresidente) {
        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        Element raiz;
        raiz = doc.getRootElement();
        List todosPaises = raiz.getChildren("pais");
        boolean found = false;

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getChild("nome").getText().equals(procura)) {
                System.out.println(pais.getChild("nome").getText() + " Presidente: " + pais.getChild("presidente").getText());
                pais.getChild("presidente").setText(novoPresidente);
                System.out.println("Novo presidente: " + pais.getChild("presidente").getText());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pais " + procura + " não foi encontrado");
            return null;
        }

        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "paises.xml");
        return doc;
    }

    //funcao para alterar a alteracao do pais inserida no parametro
    public static Document alteraPopulacao(String procura, String novaPopulacao) {
        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        Element raiz;
        raiz = doc.getRootElement();
        List todosPaises = raiz.getChildren("pais");
        String iso = "";
        boolean found = false;

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getChild("nome").getText().equals(procura)) {
                iso = pais.getAttributeValue("iso");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pais " + procura + " não foi encontrado");
            return null;
        }

        doc = XMLJDomFunctions.lerDocumentoXML("factos.xml");
        raiz = doc.getRootElement();
        todosPaises = raiz.getChildren("pais");

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getAttributeValue("iso").equals(iso)) {
                System.out.println("Populacao: " + pais.getChild("populacao").getText());
                pais.getChild("populacao").setText(novaPopulacao);
                System.out.println("Nova populacao: " + pais.getChild("populacao").getText());
            }
        }

        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "factos.xml");
        return doc;
    }

    //alterar a cidade mais populosa
    public static Document alteraCidadePopulosa(String procura, String novaCidadeMaisPop) {
        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        Element raiz;
        raiz = doc.getRootElement();
        List todosPaises = raiz.getChildren("pais");
        String iso = "";
        boolean found = false;

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getChild("nome").getText().equals(procura)) {
                iso = pais.getAttributeValue("iso");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Pais " + procura + " não foi encontrado");
            return null;
        }

        doc = XMLJDomFunctions.lerDocumentoXML("factos.xml");
        raiz = doc.getRootElement();
        todosPaises = raiz.getChildren("pais");

        for (int i = 0; i < todosPaises.size(); i++) {
            Element pais = (Element) todosPaises.get(i); //obtem pais i da Lista
            if (pais.getAttributeValue("iso").equals(iso)) {
                System.out.println("Cidade mais populosa: " + pais.getChild("cid_mais_pop").getText());
                pais.getChild("cid_mais_pop").setText(novaCidadeMaisPop);
                System.out.println("Nova cidade mais pop.: " + pais.getChild("cid_mais_pop").getText());

            }
        }
        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "factos.xml");
        return doc;
    }

    public static void testarHTML_FLAGS() {
        Document doc = XMLJDomFunctions.lerDocumentoXML("paises.xml");
        if (doc != null) {
            Document novo = JDOMFunctions_XSLT.transformaDocumento(doc, "paises.xml", "transf_html.xsl");
            XMLJDomFunctions.escreverDocumentoParaFicheiro(novo, "teste.html");
            doc = XMLJDomFunctions.lerDocumentoXML("teste.html");
            String t = XMLJDomFunctions.escreverDocumentoString(doc);
            String url = "teste.html";
            File htmlFile = new File(url);
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } catch (IOException ex) {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //validacao dtd
    public static String validarDocumentoDTD(String xmlFile, String DTDFile) throws IOException {
        Document doc = XMLJDomFunctions.lerDocumentoXML(xmlFile);
        File f = new File(DTDFile);
        String s = " ";
        if (doc != null && f.exists()) { //DTD e XML existem
            Element raiz = doc.getRootElement();
            //Atribuir DTD ao ficheiro XML
            DocType dtd = new DocType(raiz.getName(), DTDFile);
            doc.setDocType(dtd);
            //Gravar o XML com as alterações em disco
            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, xmlFile);
            //CHAMAR A FUNÇÃO DE VALIDAÇÃO por DTD
            Document docDTD = JDOMFunctions_Validar.validarDTD(xmlFile);
            if (docDTD == null) {
                System.out.println("INVALIDO por DTD");
                s = "Ficheiro " + xmlFile + " INVALIDADO por DTD";
                return s;
            } else {
                System.out.println("VALIDO por DTD");
                s = "Ficheiro " + xmlFile + " VALIDO por DTD";
                return s;
            }
        }
        return s;
    }

    //validacao xsd
    public static String validarDocumentoXSD(String xmlFile, String XSDFile) {
        Document doc = XMLJDomFunctions.lerDocumentoXML(xmlFile);
        File f = new File(XSDFile);
        String s = " ";
        if (doc != null && f.exists()) {//XSD e XML existem
            Element raiz = doc.getRootElement();
            //Atribuir XSD ao ficheiro XML
            Namespace XSI = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            raiz.addNamespaceDeclaration(XSI);
            raiz.setAttribute(new Attribute("noNamespaceSchemaLocation", XSDFile, Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance")));
            //Gravar o XML com as alterações em disco
            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, xmlFile);
            //CHAMAR A FUNÇÃO DE VALIDAÇÃO por XSD
            Document docXSD = JDOMFunctions_Validar.validarXSD(xmlFile);
            if (docXSD == null) {
                System.out.println("INVALIDO por XSD");
                s = "Ficheiro " + xmlFile + " INVALIDADO por DTD";
                return s;
            } else {
                System.out.println("VALIDO por XSD");
                s = "Ficheiro " + xmlFile + " VALIDO por DTD";
                return s;
            }
        }
        return s;
    }


    //procurar paises por continente fonte
    static void procura_pais_por_continente(String procura) throws FileNotFoundException, SaxonApiException {

     //procurar paises por continente fonte
    public static String procura_pais_por_continente(String procura) throws FileNotFoundException, SaxonApiException {
        String xp = "//pais[continente = '" + procura + "']/nome";
        XdmValue res = XPathFunctions.executaXpath(xp, "paises.xml");
        String s = XPathFunctions.listaResultado(res);
        if (res == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0) {
            System.out.println("Sem resultados");
            s = "Pesquisa sem Resultados";
            return s;
        } else {
            System.out.println(s);
            return s;
        }
        return s;
    }

    //procurar países que falem um certo idioma
    static void procura_pais_por_idioma(String procura) throws FileNotFoundException, SaxonApiException {
        String xp = "//pais/lista_idiomas[idioma = '" + procura + "']/../@iso";
        XdmValue res = XPathFunctions.executaXpath(xp, "factos.xml");
        String s = XPathFunctions.listaResultado(res);
        System.out.println(s);
        String xp2 = "//pais[@iso=\"" + s + "\"]";

        XdmValue res1 = XPathFunctions.executaXpath(xp2, "paises.xml");
        String s1 = XPathFunctions.listaResultado(res1);
        System.out.println(s1);
        if (res == null || res1 == null) {
            System.out.println("Ficheiro XML não existe");
        } else if (res.size() == 0 || res1.size() == 0) {
            System.out.println("Sem resultados");
        } else {
            //System.out.println(s);
            System.out.println(s1);
        }
    }

}
