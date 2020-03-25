package galamadriabuyak.util;

public enum Type {
    Heal("heal"),
    Hit("hit");
    
    private String name;
    
    Type(String name) {
        if (name == null) {
            throw new AssertionError();
        }
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}