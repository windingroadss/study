package mockito;

public class AuthService {
    private AuthDao dao;

    // some code...
    public boolean isLogin(String id) {
        boolean isLogin = dao.isLogin(id);
        if (isLogin) {
            // some code...
            System.out.println("Login success");
        }
        return isLogin;
    }
}
