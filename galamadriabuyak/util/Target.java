package galamadriabuyak.util;

public enum Target {
    Player("player"),
    Enemy("enemy"),
    BasicAttack("basicAttack");
    
    private String name;
    
    Target(String name) {
        if (name == null) {
            throw new AssertionError();
        }
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}