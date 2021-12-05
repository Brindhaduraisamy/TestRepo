

public class Follow {

	protected String userid;
    protected String followerid; 
   
	public Follow(String userid, String followerid) {
		super();
		this.userid = userid;
		this.followerid = followerid;
	}
    
    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFollowerid() {
		return followerid;
	}

	public void setFollowerid(String followerid) {
		this.followerid = followerid;
	}
    
}