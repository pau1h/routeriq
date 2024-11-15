package parser;
public class Device {
    private String mac, ipv4, name, lastActivity;
    private int active;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int isActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active ;
    }

    public Device(String mac, String name, String lastActivity, int active){ //device class with no ipv4 address
        this.mac = mac;
        this.name = name;
        this.lastActivity = lastActivity;
        this.active = active;
        ipv4 = "";
    }
    public Device(String mac, String ipv4, String name, String lastActivity, int active){ //overloaded device class for devices with ipv4 addresses
        this.mac = mac;
        this.ipv4 = ipv4;
        this.name = name;
        this.lastActivity = lastActivity;
        this.active = active;
    }
    public Device(){
        mac = "";
        ipv4 = "";
        name = "";
        lastActivity = "";
        active = 0;
    }

    @Override
    public String toString() { //prints out device data, json encoded.
        return "{ \"mac\":\"" + mac + "\", "
                + " \"ipv4\": \"" + ipv4 + "\", "
                + " \"name\": \"" + name + "\", "
                + "\"lastActivity:\":\"" + lastActivity + "\", "
                + "\"active:\": \"" + isActive() + "\" }";
    }
}
