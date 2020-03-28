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
    
    public static Type myValueOf(String arg) {
        if (arg.equals("hit")) {
            return HIT;
        } else if (arg.equals("heal")) {
            return HEAL;
        } else {
            throw new AssertionError();
        }
    }
}
