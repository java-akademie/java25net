package ch.jmb.miniwebservice;

import javax.jws.WebService;

@WebService
public interface HalloWelt
{
	String hallo(String wer);
}
