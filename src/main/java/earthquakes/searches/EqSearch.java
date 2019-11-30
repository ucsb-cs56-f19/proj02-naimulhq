package earthquakes.searches;

public class EqSearch{
    
    private int distance;
    private int minmag;
    private long lat;
    private long lon;
    private String location;



    EqSearch(){};
    public int getDistance(){
	return distance;
    }

    public int getMinmag(){
	return minmag;
    }

    public long getLon(){
        return lon;
    }

    public long getLat(){
        return lat;
    }

    public String getLocation(){
        return location;

    }

    public void setLon(long lon){
        this.lon = lon;
    }

    public void setLat(long lat){
        this.lat = lat;
    }

    public void setLocation(String location){
        this.location = location;
        
    }
    public void setMinmag(int minmag){
	this.minmag = minmag;
    }

    public void setDistance(int distance){
	this.distance = distance;
    }
}
