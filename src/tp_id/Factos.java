/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_id;

/**
 *
 * @author joaop
 */
public class Factos {
    String iso;
    String cod_telef; //código telefónico do país
    String cod_inter; //código da internet do país
    String capital;
    String cid_mais_pop;
    String hino;
    String moeda;
    String populacao;
    String area;
    String lista_idiomas;

    public Factos(String iso, String cod_telef, String cod_inter, String capital, String cid_mais_pop, String hino, String moeda, String populacao, String area, String lista_idiomas) {
        this.iso = iso;
        this.cod_telef = cod_telef;
        this.cod_inter = cod_inter;
        this.capital = capital;
        this.cid_mais_pop = cid_mais_pop;
        this.hino = hino;
        this.moeda = moeda;
        this.populacao = populacao;
        this.area = area;
        this.lista_idiomas = lista_idiomas;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCod_telef() {
        return cod_telef;
    }

    public void setCod_telef(String cod_telef) {
        this.cod_telef = cod_telef;
    }

    public String getCod_inter() {
        return cod_inter;
    }

    public void setCod_inter(String cod_inter) {
        this.cod_inter = cod_inter;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCid_mais_pop() {
        return cid_mais_pop;
    }

    public void setCid_mais_pop(String cid_mais_pop) {
        this.cid_mais_pop = cid_mais_pop;
    }

    public String getHino() {
        return hino;
    }

    public void setHino(String hino) {
        this.hino = hino;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getPopulacao() {
        return populacao;
    }

    public void setPopulacao(String populacao) {
        this.populacao = populacao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLista_idiomas() {
        return lista_idiomas;
    }

    public void setLista_idiomas(String lista_idiomas) {
        this.lista_idiomas = lista_idiomas;
    }
    
}