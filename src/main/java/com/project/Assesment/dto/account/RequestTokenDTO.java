package com.project.Assesment.dto.account;

public class RequestTokenDTO {
    private Long accountId;
    private String username;
    private String password;
    private String subject;
    private String secretKey;
    private String audience;

    public RequestTokenDTO() {}

    public RequestTokenDTO(Long accountId, String username, String password, String subject, String secretKey, String audience) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.secretKey = secretKey;
        this.audience = audience;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "RequestTokenDTO{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", subject='" + subject + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", audience='" + audience + '\'' +
                '}';
    }
}
