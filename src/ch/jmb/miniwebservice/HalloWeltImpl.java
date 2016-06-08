package ch.jmb.miniwebservice;

import javax.jws.WebService;

@WebService(endpointInterface = "miniwebservice.HalloWelt")
public class HalloWeltImpl
{
	public String hallo(String wer)
	{
		return "hallo .... " + wer;
	}
}
