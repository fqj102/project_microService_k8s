# coding:utf-8
from message.api import MessageService
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import smtplib
from email.mime.text import MIMEText
from email.header import Header

sander = 'imoocd@163.com'
authCode = 'aA111111'


class MessageServiceHandler:

    def sendMobileMessage(self, Mobile, message):
        print('send mobile message')
        return True

    def sendEmailMessage(self, email, message):
        print('send Email message')
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj['From'] = sander
        messageObj['To'] = email
        messageObj['Subject'] = Header('测试邮件', 'utf-8')
        try:
            smtpdObj = smtplib.SMTP('smtp.163.com')
            smtpdObj.login(sander, authCode)
            smtpdObj.send(sander, [email], messageObj.as_string())
            return True
        except smtplib.SMTPException, ex:
            print('send mail failed')
            print(ex)
            return False
        print('send mail success')


if __name__ == "__main__":
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket("localhost", "9090")
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print("py start")
    server.serve()
    print("py exit")
