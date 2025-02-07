package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

import static java.lang.Character.isAlphabetic;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }

        if (!username.matches("[a-z]{3,}")){
            status.addError("username should contain only characters a-z");
        }

        if (password.length()<8) {
            status.addError("password should have at least 8 characters");
        }

        if (!passwordCheck(password)){
            status.addError("password not strong enough (include numbers or symbols)");
        }

        if (!password.equals(passwordConfirmation)){
            status.addError("password and password confirmation do not match");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

    private boolean passwordCheck(String password){
        for(char c: password.toCharArray()){
            if (!isAlphabetic(c)){
                return true;
            }
        }
        return false;
    }

}
