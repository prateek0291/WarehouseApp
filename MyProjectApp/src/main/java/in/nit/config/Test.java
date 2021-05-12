package in.nit.config;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Test implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setMaxInactiveInterval((int)TimeUnit.MINUTES.toSeconds(20));
	}
}
