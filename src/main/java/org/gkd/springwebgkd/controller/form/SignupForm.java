package org.gkd.springwebgkd.controller.form;

import org.gkd.springwebgkd.bean.jpa.WebgkdUsersecEntity;
import org.hibernate.validator.constraints.*;

public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE )
	private String email;

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;
    
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String npk;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNpk() {
		return npk;
	}

	public void setNpk(String npk) {
		this.npk = npk;
	}

	public WebgkdUsersecEntity createAccount() {
        return new WebgkdUsersecEntity(getEmail(), getPassword(), "1", npk);
	}
}
