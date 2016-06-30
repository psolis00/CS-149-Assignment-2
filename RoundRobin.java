
 import java.util.ArrayList;

/**********************************************
 * Round Robin (preemptive)
 * 
 * Every process is  given a quantum (time interval)
 * The process runs in its quantum
 * When it is over the next process is started
 * 
 * @author Team: Cache City
 * CS149
 * 
 *********************************************/
public class RoundRobin implements InterfaceScheduler{
	
	private ArrayList<Process> processList;
    private ArrayList<Process> roundrobin;
    private ArrayList<String> log;
    private ArrayList<Process> graveYard;
    private int quantum;
    
    public RoundRobin(ArrayList<Process> processList) {
    	this.processList = processList;
    	this.roundrobin = new ArrayList<Process>();
    	this.log = new ArrayList<String>();
    	this.graveYard = new ArrayList<Process>();
    	this.quantum = 0;
    	
    	run();
    }
    
    /**
     * Creates the list for the processes
     * puts them in their quantum 
     */
    public void run() {
	//Run while there is quatum left or roundrobin is not empty
	while(quantum < 100 || !roundrobin.isEmpty()){
	    //move all new aRoundRobinivals into roundrobin
	    while(!processList.isEmpty() && processList.get(0).getArrTime() < quantum){
		roundrobin.add(processList.remove(0));
	    }
	    //If processes are in the RoundRobin
	    if(!roundrobin.isEmpty()){
		//run the first
		log.add(roundrobin.get(0).getName());
		roundrobin.get(0).decRTime();
		roundrobin.get(0).incQTime();
		//If first run, mark it so.
		if(roundrobin.get(0).getStartTime()==-1){
		    roundrobin.get(0).setStartTime(quantum);
		}
		//If last run mark it and move it out.
		if(roundrobin.get(0).getRTime()<0){
		    roundrobin.get(0).setEndTime(quantum);
		    graveYard.add(roundrobin.remove(0));
		}else{
		    roundrobin.add(roundrobin.remove(0));
		}
	    }//If no processes in the RoundRobin
	    else{
		log.add("   ");
	    }
	    quantum++;
	}
    }

    public ArrayList<String> getStringList() {
    	return log;
    }
    
    public ArrayList<Process> getStatistics() {
		return graveYard;
    }

}
