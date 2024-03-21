# azure-communications-serivice-email-templates-spring-boot
Using email templates in azure communications service with spring boot

* Configure Azure Communications Service connection-string into application.properties

The system expects tempaltes to be on the resources folder.

Example request to invoke it (POST http://localhost:8080/email/send):
```json
{
  "template": "template1.html",
  "to": "recpieint@emailaddress.com",
  "subject": "test email",
  "sender": "DoNotReply@my-emaildomain.net",
  "templateData": {
    "headertext": "<p>This is marketing email header example</p>",
    "buttonlink": "http://portal.azure.com",
    "buttontext": "Azure Portal",
    "bodytext": "<p> Clicking the button you can get into Azure portal</p>"
  }
}
```
