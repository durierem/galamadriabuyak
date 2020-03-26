package galamadriabuyak.util;

public enum Type {
    HEAL("heal"),
    HIT("hit");
    
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