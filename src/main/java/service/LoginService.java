package service;

import javafx.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface LoginService {

     Pair<String, String> authenticate(HttpSession session, String username, String password);

}
