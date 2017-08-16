package isaacearl.oauth.two;


import isaacearl.AbstractUser;

public class User extends AbstractUser {

    private String token;

    private String refreshToken;

    private int expiresIn;

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public User setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public User setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
