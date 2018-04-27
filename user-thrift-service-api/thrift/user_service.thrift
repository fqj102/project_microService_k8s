namespace java com.eddie.micro.user

struct UserInfo{
    1:i32 id,
    2:string userName,
    3:string passWord,
    4:string realName,
    5:string mobile,
    6:string email
}

service UserService{

    UserInfo getUserById(1:i32 id);

    UserInfo getUserByUserName(1:string userName);

    void registerUser(1:UserInfo userInfo);

}