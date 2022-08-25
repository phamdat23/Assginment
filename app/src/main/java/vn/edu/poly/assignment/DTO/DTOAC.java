package vn.edu.poly.assignment.DTO;

public class DTOAC {
    private int id;
    private String userName, passWord, rePass;

    public DTOAC() {
    }

    public DTOAC( int id, String userName, String passWord, String rePass) {
        this.id=id;
        this.userName = userName;
        this.passWord = passWord;
        this.rePass = rePass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRePass() {
        return rePass;
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
    }
}
