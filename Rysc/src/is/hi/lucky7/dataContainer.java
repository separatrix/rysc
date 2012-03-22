package is.hi.lucky7;

import java.net.InetAddress;

public class dataContainer {
	private String msg = null;
	private InetAddress address = null;
	
	public dataContainer(String msg, InetAddress address)
	{
		this.msg = msg;
		this.address = address;
	}
	
	public InetAddress getAddress()
	{
		return this.address;
	}
	
	public String getMsg()
	{
		return this.msg;
	}
	
	public void setAddress(InetAddress address)
	{
		this.address = address;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}
