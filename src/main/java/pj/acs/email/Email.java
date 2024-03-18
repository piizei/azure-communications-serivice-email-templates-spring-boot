package pj.acs.email;

import java.util.Map;

public class Email {
    private String template;
    private String to;
    private String subject;
    private String body;
    private String sender;
    private Map<String, String> templateData;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Map<String, String> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(Map<String, String> templateData) {
        this.templateData = templateData;
    }

    public String getTemplate() {
        return template;
    }
}

