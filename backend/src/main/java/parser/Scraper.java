package parser;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Scraper{
    private String addr; //base ip address
    private boolean secure; //SSL?
    private Document deviceList;
    public Scraper(String addr, boolean secure){
        this.addr = addr;
        this.secure = secure;
    }

    public void fetchDevices(){
        try{
            String prefix = secure ? "https://" : "http://";
            String suffix = "/cgi-bin/devices.ha";
            deviceList = Jsoup.connect(prefix + addr + suffix).get();
        } catch(IOException e){
            throw new RuntimeException();
        }
        Elements deviceElements = deviceList.select("tr");
        Iterator<Element> itr = deviceElements.iterator();
        Device temp = null;
        Database db = new Database();
        while(itr.hasNext()){ //iterate through rows and tables.
            Element s = itr.next();
            if(temp == null){
                temp = new Device();
            }
            if(!s.select("td").attr("colspan").isEmpty()){ //the only distinction between devices is a css column line. if it exists, then make a new device obj
                db.insertOrUpdate(temp);
                temp = new Device();
                continue;
            }
            String attribute = s.select("th").text();
            String val = s.select("td").text();
            switch(attribute){
                case "MAC Address":
                    temp.setMac(val);
                    break;
                case "Name":
                    temp.setName(val);
                    break;
                case "Status":
                    temp.setActive(val.equals("on") ? 1 : 0);
                    break;
                case "IPv4 Address / Name":
                    String[] addrName  = val.split("/"); //TODO: device names with slashes in them will break the program
                    temp.setIpv4(addrName[0].trim());
                    temp.setName(addrName[1].trim());
                    break;
            }

        }


    }




}
