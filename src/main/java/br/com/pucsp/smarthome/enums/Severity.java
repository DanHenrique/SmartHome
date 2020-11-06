package br.com.pucsp.smarthome.enums;

public enum Severity {
    NONE("Funcionando Normalmente"), LOW("Baixa"), MEDIUM("Média"), HIGH("Alta"), VERY_HIGH("Muito Alta");

    private final String text;

    Severity(String txt) {
        this.text = txt;
    }

    public String getText(){
        return this.text;
    }
}
