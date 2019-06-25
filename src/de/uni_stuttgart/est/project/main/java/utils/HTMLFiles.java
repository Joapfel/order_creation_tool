package utils;

/**
 * 
 * @author johannes
 *
 */
public enum HTMLFiles {
    KUNDENERFASSUNG("kundenerfassung.html"),
    AUFTRAGSERSTELLUNG("auftragserstellung.html"),
    AUFTRAGSERSTELLUNG_VORSCHAU("auftragserstellung_vorschau.html"),
    ANMELDUNG_FORM("anmeldung.html"),
	HR_INTERFACE_VIEW("hr_interface_view.html");

    private String htmlFile;

    private HTMLFiles(String s) {
        this.htmlFile = s;
    }

    public String getHtmlFile(){
        return this.htmlFile;
    }
}

