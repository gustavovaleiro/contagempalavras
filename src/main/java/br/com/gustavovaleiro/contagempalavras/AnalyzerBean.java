package br.com.gustavovaleiro.contagempalavras;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.*;

@Named("analyzerBean")
@RequestScoped
public class AnalyzerBean {
    private String frase;
    private Map<String, Integer> resultados;

    public void analisar() {
        if (frase == null || frase.trim().isEmpty()) {
            this.resultados = Collections.emptyMap();
            return;
        }
        String[] tokens = frase.toLowerCase()
                .split("[\\s\\p{Punct}]+");
        Map<String, Integer> mapa = new LinkedHashMap<>();
        for (String w : tokens) {
            if (w.isEmpty()) continue;
            mapa.put(w, mapa.getOrDefault(w, 0) + 1);
        }
        this.resultados = mapa;
    }

    public String getFrase() { return frase; }
    public void setFrase(String frase) { this.frase = frase; }
    public Map<String, Integer> getResultados() { return resultados; }
    public int getDistintasCount() { return resultados == null ? 0 : resultados.size(); }
}
