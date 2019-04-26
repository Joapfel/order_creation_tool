package Utils;

public enum HTMLFiles {
    KUNDENERFASSUNG("kundenerfassung.html"),
    AUFTRAGSERSTELLUNG("auftragserstellung.html"),
    AUFTRAGSERSTELLUNG_VORSCHAU("auftragserstellung_vorschau.html");

    private String htmlFile;

    private HTMLFiles(String s) {
        this.htmlFile = s;
    }

    public String getHtmlFile(){
        return this.htmlFile;
    }
}

