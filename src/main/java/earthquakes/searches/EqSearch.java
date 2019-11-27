package earthquakes.searches;

public class EqSearch{
    
    private int distance;
    private int minmag;

    EqSearch(){};
    public int getDistance(){
	return distance;
    }

    public int getMinmag(){
	return minmag;
    }

    public void setMinmag(int minmag){
	this.minmag = minmag;
    }

    public void setDistance(int distance){
	this.distance = distance;
    }
}
