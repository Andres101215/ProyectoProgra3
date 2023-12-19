package Logic;

public class Word {
    private int id;
    private String word;
    private String definition;

    private String translate;

    public Word(int id, String word, String definition, String translate) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.translate = translate;
    }

    public Word() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

}
