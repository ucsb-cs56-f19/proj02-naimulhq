package earthquakes.searches;

public class EqSearch{
    
    private int distance;
    private int minmag;
    private double lat;
    private double lon;
    private String location;



    EqSearch(){};
    
    public int getDistance(){
	return distance;
    }

    public int getMinmag(){
	return minmag;
    }

    public double getLon(){
        return lon;
    }

    public double getLat(){
        return lat;
    }

    public String getLocation(){
        return location;

    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public void setLat(double lat){
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
