package br.com.gustavovaleiro.contagempalavras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerBeanTest {

    private AnalyzerBean bean;

    @BeforeEach
    void setUp() {
        bean = new AnalyzerBean();
    }

    @Test
    void analisar_fraseNull_deveRetornarMapaVazio() {
        bean.setFrase(null);
        bean.analisar();
        assertNotNull(bean.getResultados());
        assertTrue(bean.getResultados().isEmpty());
        assertEquals(0, bean.getDistintasCount());
    }

    @Test
    void analisar_fraseVazia_deveRetornarMapaVazio() {
        bean.setFrase("   ");
        bean.analisar();
        assertNotNull(bean.getResultados());
        assertTrue(bean.getResultados().isEmpty());
        assertEquals(0, bean.getDistintasCount());
    }

    @Test
    void analisar_fraseSimples_contagemCorreta() {
        bean.setFrase("Teste teste outro");
        bean.analisar();
        Map<String,Integer> res = bean.getResultados();
        assertEquals(2, res.get("teste"));
        assertEquals(1, res.get("outro"));
        assertEquals(2, bean.getDistintasCount());
    }

    @Test
    void analisar_comPontuacao_eEspacosExtras() {
        bean.setFrase(" Olá, mundo! Olá...   JSF/PrimeFaces ");
        bean.analisar();
        Map<String,Integer> res = bean.getResultados();
        assertEquals(2, res.get("olá"));
        assertEquals(1, res.get("mundo"));
        assertEquals(1, res.get("jsf"));
        assertEquals(1, res.get("primefaces"));
        assertEquals(4, bean.getDistintasCount());
    }
}
