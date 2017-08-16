package isaacearl;


import isaacearl.contract.UserContract;

public abstract class AbstractUser implements UserContract {

    protected String id;

    protected String nickName;

    protected String name;

    protected String email;

    protected String avatar;


    @Override
    public String getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }



    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

