package com.eddie.micro.user.DTO;

public class Teacher extends User{

    private String intro;

    private int stars;

    public Teacher(String userName, String passWord) {
        super(userName, passWord);
    }

    public Teacher(String userName, String passWord, String realName, String mobile, String email, String intro, int stars) {
        super(userName, passWord, realName, mobile, email);
        this.intro = intro;
        this.stars = stars;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
