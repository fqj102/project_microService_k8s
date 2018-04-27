namespace java com.eddie.micro.message
namespace py message.api

service MessageService{

    bool sendMobileMessage(1:string Mobile,2:string message);

    bool sendEmailMessage(1:string email,2:string message);

}