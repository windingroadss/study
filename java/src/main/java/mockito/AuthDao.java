package mockito;

public class AuthDao {
    public boolean isLogin(String id) {
        if (id.equals("windingroad")) { return true; } else { return false; }
    }
}