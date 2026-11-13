package use.tool.ksp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * KSP 1.5.1 .sfs Parent-Aktualisierer
 *
 * Java 1.7 kompatibel
 */
public class StructureEditor {

   
    /**
     * Aktualisiert innerhalb aller PART{...}-Blöcke
     * die parent = ... Werte.
     *
     * @param objFileIn Eingabedatei
     * @param iParentFirst Wert für den ersten PART-Block
     * @param iParentStartOthers Startwert für weitere PART-Blöcke
     * @param sSuffix Dateisuffix z.B. "_STEP01"
     * @throws Exception
     */
    public static void updateParentValues(
            File objFileIn,
            int iParentFirst,
            int iParentStartOthers,
            String sSuffix
    ) throws Exception {

        List<String> listaLine = Files.readAllLines(
                objFileIn.toPath(),
                Charset.forName("UTF-8")
        );

        List<String> listaLineOut = new ArrayList<String>();

        boolean bInsidePart = false;
        int iPartCount = 0;
        int iCurrentParent = iParentStartOthers;

        /*    RegEx:    	
	    Zwei kleine Tipps für deine Methode
	    • Sonderzeichen absichern: Da sToFind als beliebiger Text von außen übergeben wird, könnte darin zufällig ein Regex-Steuerzeichen wie ein Punkt (.) oder ein Fragezeichen (?) stecken. Mit Pattern.compile(Pattern.quote(sToFind)) stellst du sicher, dass die Methode immer nach dem exakten Text sucht und nicht abstürzt.
	    • Dollarzeichen beim Ersetzen: Falls im Ersatztext (sReplacement) später einmal ein $-Zeichen vorkommen sollte, wirft replaceAll eine Fehlermeldung, weil es darin eine Regex-Gruppe vermutet. Mit Matcher.quoteReplacement(sReplacement) umgehst du dieses Problem zuverlässig.
	     */
		//alt bleibt erhalten: 
        Pattern objPatternParent =
                Pattern.compile("^\\s*parent\\s*=\\s*-?\\d+\\s*$");

        for (String sLine : listaLine) {

            String sLineNew = sLine;

            // Beginn eines PART-Blocks
            if (sLine.trim().equals("PART")) {
                bInsidePart = true;
            }

            // parent = ... ersetzen
            if (bInsidePart) {

                Matcher objMatcher = objPatternParent.matcher(sLine);

                if (objMatcher.matches()) {

                    int iNewValue;

                    // Erster PART
                    if (iPartCount == 0) {
                        iNewValue = iParentFirst;
                    } else {
                        iNewValue = iCurrentParent;
                        iCurrentParent++;
                    }

                    sLineNew = replaceParentLine(sLine, iNewValue);

                    iPartCount++;
                }
            }

            listaLineOut.add(sLineNew);

            // Ende eines PART-Blocks
            // Achtung:
            // sehr einfach gehalten.
            // Funktioniert für normale PART-Strukturen.
            if (bInsidePart && sLine.trim().equals("}")) {
                bInsidePart = false;
            }
        }

        File objFileOut = createOutputFile(objFileIn, sSuffix);

        writeLines(objFileOut, listaLineOut);

        System.out.println("Datei gespeichert:");
        System.out.println(objFileOut.getAbsolutePath());
    }

    /**
     * Ersetzt den parent-Wert einer Zeile.
     *
     * Beispiel:
     * parent = 5
     * ->
     * parent = 999
     */
    public static String replaceParentLine(
            String sLine,
            int iNewParentValue
    ) {

        return sLine.replaceAll(
                "(^\\s*parent\\s*=\\s*)-?\\d+(\\s*$)",
                "$1" + iNewParentValue + "$2"
        );
    }

    /**
     * Erzeugt Ausgabedatei mit Suffix.
     *
     * Beispiel:
     * persistent.sfs
     * ->
     * persistent_STEP01.sfs
     */
    public static File createOutputFile(
            File objFileIn,
            String sSuffix
    ) {

        String sName = objFileIn.getName();

        int iPos = sName.lastIndexOf('.');

        String sNameOnly;
        String sExtension;

        if (iPos >= 0) {
            sNameOnly = sName.substring(0, iPos);
            sExtension = sName.substring(iPos);
        } else {
            sNameOnly = sName;
            sExtension = "";
        }

        String sNewName =
                sNameOnly + sSuffix + sExtension;

        return new File(
                objFileIn.getParentFile(),
                sNewName
        );
    }

    /**
     * Schreibt Zeilen in Datei.
     */
    public static void writeLines(
            File objFileOut,
            List<String> listaLine
    ) throws IOException {

        BufferedWriter objWriter = Files.newBufferedWriter(
                objFileOut.toPath(),
                Charset.forName("UTF-8")
        );

        try {

            for (String sLine : listaLine) {
                objWriter.write(sLine);
                objWriter.newLine();
            }

        } finally {
            objWriter.close();
        }
    }
}
