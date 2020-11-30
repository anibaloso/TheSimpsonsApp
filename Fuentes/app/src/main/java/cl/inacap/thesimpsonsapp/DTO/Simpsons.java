package cl.inacap.thesimpsonsapp.DTO;

import java.io.Serializable;

public class Simpsons implements Serializable {

    private String quote;
    private String character;
    private String image;
    private String characterDirection;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCharacterDirection() {
        return characterDirection;
    }

    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }
}
