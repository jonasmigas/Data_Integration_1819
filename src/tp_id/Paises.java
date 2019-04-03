/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_id;

/**
 *
 * @author João
 */
public class Paises {
    String iso;
    String nome;
    String continente; //continente a que pertence
    String pres_rep; //presidente da republica
    String bandeira; //link para imagem da bandeira

    public Paises(String iso, String nome, String continente, String pres_rep, String bandeira) {
        this.iso = iso;
        this.nome = nome;
        this.continente = continente;
        this.pres_rep = pres_rep;
        this.bandeira = bandeira;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getPres_rep() {
        return pres_rep;
    }

    public void setPres_rep(String pres_rep) {
        this.pres_rep = pres_rep;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }
}
