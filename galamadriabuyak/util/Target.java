package galamadriabuyak.util;

public enum Target {
    PLAYER("player"),
    ENEMY("enemy"),
    BASIC_ATTACK("basicAttack");
    
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