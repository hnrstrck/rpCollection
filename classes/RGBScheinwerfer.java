/**
*   Die Klasse RGBScheinwerfer wird fuer die Modellierung und Umsetzung des Theaterstuecks benoetigt.
*   Ein RGB-Scheinwerfer besteht intern aus drei LEDs, die über drei verschiedene Pins angesprochen werden.
*
*	Hinweis:
*	Fuer die Abfrage der einzelnen Attributwerte haette auch auf das Objekt "RPRGB" zurueckgegriffen werden koennen.
*	Dann haetten die SuS aber mit zwei Klassen zu tun, was hier vermieden werden sollte, falls dieser Quelltext mal interessieren sollte.
*/

public class RGBScheinwerfer{

    private RPRGB pRGB;
    private String standort;
    private boolean lichtAktiv;
    private int farberot;
    private int farbegruen;
    private int farbeblau;

    /**
    * Erstelle einen neuen RGB-Scheinwerfer unter angabe der drei Pin
    * @param pPinRot Der Pin fuer die rote Farbe.
    * @param pPinGruen Der Pin fuer die gruene Farbe.
    * @param pPinBlau Der Pin fuer die blaue Farbe.
    */
    public RGBScheinwerfer(int pPinRot, int pPinGruen, int pPinBlau){
        pRGB = new RPRGB(pPinRot, pPinGruen, pPinBlau);
    }

    /**
    * Schalte den RGB-Scheinwerfer ein, indem die angegbeene Farbe gesetzt wird.
    * @param rot Farbanteil fuer die Farbe rot.
    * @param gruen Farbanteil fuer die Farbe gruen.
    * @param blau Farbanteil fuer die Farbe blau.
    */
    public void einschalten(int rot, int gruen, int blau){
        mischen(rot, gruen, blau);
    }

    /**
    * Schalte den RGB-Scheinwerfer ein
    */
    public void einschalten(){
        setzeStatus(true);
    }

    /**
    * Stelle eine neue Farbe ein.
    * @param rot Farbanteil fuer die Farbe rot.
    * @param gruen Farbanteil fuer die Farbe gruen.
    * @param blau Farbanteil fuer die Farbe blau.
    */
    public void mischen(int rot, int gruen, int blau){
        int r = (int)((float)rot/100f * 255f);
        int g = (int)((float)gruen/100f * 255f);
        int b = (int)((float)blau/100f * 255f);

        pRGB.setzeFarbe(r,g,b);

        farberot = rot;
        farbegruen = gruen;
        farbeblau = blau;

        setzeStatus(true);
    }

    /**
    * Stelle eine neue Farbe ein.
    * @param rot Farbanteil fuer die Farbe rot.
    * @param gruen Farbanteil fuer die Farbe gruen.
    * @param blau Farbanteil fuer die Farbe blau.
    */
    public void farbeEinstellen(int rot, int gruen, int blau){
        this.mischen(rot, gruen, blau);
    }

    /**
    * Schalte den RGBScheinwerfer mit der Rueckgabe eines anderen Objekts.
    * @param status Erforderlich ist ein Wahrheitswert (true / false). Ist der Parameterwert true, bleibt der RGBScheinwerfer aus. Ist der Parameterwert false, so geht der RGBScheinwerfer an.
    */
    public void schalten(boolean status){
        if (status){
            einschalten(100,100,100);
        } else {
            ausschalten();
        }
    }

    /**
    * Schalte den RGB-Scheinwerfer aus.
    */
    public void ausschalten(){
        setzeStatus(false);
    }

    /**
    * Frage nach dem Standort des RGB-Scheinwerfers.
    * @return Gibt den Standort des RGB-Scheinwerfers als String zurueck.
    */
    public String gibStandort(){
        System.out.println("Standort des RGB-Scheinwerfers ist: " + standort);
        return standort;
    }

    /**
    * Setze den Standort des RGB-Scheinwerfers.
    * @param pStandort Der Standort des RGB-Scheinwerfers als String.
    */
    public void setzeStandort(String pStandort){
        System.out.println("Standort des RGB-Scheinwerfers als >" + pStandort + "< gesetzt");
        standort =  pStandort;
    }

    /**
    * Gib den Licht-Aktiv-Status des RGB-Scheinwerfers.
    * @return Der Status des RGB-Scheinwerfers (an = true / aus = false).
    */
    public boolean gibStatus(){
        System.out.println("Licht-Aktiv-Status des RGB-Scheinwerfers ist: " + lichtAktiv);
        return lichtAktiv;
    }

    /**
    * Setze den  Licht-Aktiv-Status des Scheinwerfers.
    * @param pStatus Der  Licht-Aktiv-Status des RGB-Scheinwerfers als String.
    */
    public void setzeStatus(boolean pStatus){
        System.out.println("Licht-Aktiv-Status des RGB-Scheinwerfers auf >" + pStatus + "< geaendert");
        lichtAktiv =  pStatus;
        if (pStatus) {
            pRGB.an();
        } else {
            pRGB.aus();
        }
    }

    /**
    * Gibt den Anteil der Farbe rot zurueck.
    * @return Anteil der Farbe rot.
    */
    public int gibFarbeRot(){
        return farberot;
    }

    /**
    * Gibt den Anteil der Farbe gruen zurueck.
    * @return Anteil der Farbe gruen.
    */
    public int gibFarbeGruen(){
        return farbegruen;
    }

    /**
    * Gibt den Anteil der Farbe blau zurueck.
    * @return Anteil der Farbe blau.
    */
    public int gibFarbeBlau(){
        return farbeblau;
    }

    /**
    * Setzt den Anteil von Rot und schaltet dabei den RGBScheinwerfer ein.
    *
    * @param farbe Anteil der Farbe rot
    */
    public void setzeR(int farbe){
        this.mischen(farbe, this.farbegruen, this.farbeblau);
    }

    /**
    * Setzt den Anteil von Gruen und schaltet dabei den RGBScheinwerfer ein.
    *
    * @param farbe Anteil der Farbe gruen
    */
    public void setzeG(int farbe){
        this.mischen(this.farberot, farbe, this.farbeblau);
    }

    /**
    * Setzt den Anteil von Blau und schaltet dabei den RGBScheinwerfer ein.
    *
    * @param farbe Anteil der Farbe blau
    */
    public void setzeB(int farbe){
        this.mischen(this.farberot, this.farbegruen, farbe);
    }
}