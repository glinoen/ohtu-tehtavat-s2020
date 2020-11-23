package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ohtu.verkkokauppa.Tuote;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void SetUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipä", 2));
        when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "beyblade", 7));

        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);  
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
      

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tuplaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(7));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void tuplaLeipaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(4));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void saldoNollaOstoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(2));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void aloitaAsiointiNollaa() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");
        k.aloitaAsiointi();
        k.tilimaksu("pekka", "12345");
        
        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(0));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void viiteNollaa() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.tilimaksu("pekka", "12345");
        verify(viite, times(1)).uusi();

        k.tilimaksu("pekka", "12345");
        verify(viite, times(2)).uusi();
        
        k.tilimaksu("pekka", "12345");
        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void poistaKoristaPalauttaaVarastoon() {
                    

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli maitoa
        k.poistaKorista(2);
        verify(varasto, times(1)).palautaVarastoon(any(Tuote.class));

    }
}