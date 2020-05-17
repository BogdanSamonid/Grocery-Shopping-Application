package model;

public class Customer{

    private String username;
    private String password;

    public Customer() {}
    public Customer(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;

        Customer customer=(Customer) o;

        if(!username.equals(customer.username)) return false;
        if(!password.equals(customer.password)) return false;

        return true;
    }

    @Override
    public int hashCode (){
        int result=username.hashCode();
        result=31*result+password.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return "Customer -> " + username + password;
    }
}
