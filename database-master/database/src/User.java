

public class User {
    
   
    protected String userid;
   
    protected String firstname;
    protected String lastname; 
    protected int age;
	protected int streetnumber;
    protected String street; 
    protected String city;
    protected String state;
    protected int zipcode; 
    public User(String userid, String firstname, String lastname, int age, int streetnumber, String street, String city,
			String state, int zipcode, String password) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.streetnumber = streetnumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.password = password;
	}
    
    public User(String userid, String firstname, String lastname, int age, int streetnumber, String street, String city,
			String state, int zipcode) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.streetnumber = streetnumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.password = "";
	}

	protected String password;
 
 

 
  
    
    public User() {
    }
 
 
	public String getUsername() {
		return userid;
	}

	public void setUsername(String username) {
		this.userid = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    public int getStreetNumber() {
		return streetnumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetnumber = streetNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
}
     