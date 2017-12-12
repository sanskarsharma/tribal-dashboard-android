package sanskar.dev.com.tribaldashboardcg.Models;

/**
 * Created by Sanskar on 10-Dec-17.
 */

public class UserModel {

    private String userid;
    private String password;
    private String department;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public UserModel(String userid, String password, String department, String role, String name) {
        this.userid = userid;
        this.password = password;
        this.department = department;
        this.role = role;
        this.name=name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
