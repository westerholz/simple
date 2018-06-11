package controller;


import persistence.User;
public class LoginValidation {

	public  static Boolean validatePassword(String password, User user) {
		try {
		return PasswordHash.validatePassword(password, user.getCredentials().getHashedPassword());
		}catch (Exception e) {
			return false;
		}
	}
	
}
