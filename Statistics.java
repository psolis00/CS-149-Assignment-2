
import java.util.ArrayList;


/**********************************************
 *  This Calculates the statistics from the given lists of process of 
 *  scheduling algorithm schemes
 * 
 * @author Team: Cache City
 * CS149
 * 
 *********************************************/

public class Statistics {
	
	/**
     * @param list all the processes.
     * @return a formatted string of the calculated statistics.
     */
	
	 public static String CalculateStatistics(ArrayList<Process> list) {
			return "Average Turnaround: " + AverageTaround(list) +
			    	"\nAverage Waiting: " + AverageWait(list) +
			    	"\nAverage Response: " + AverageResponse(list);
		    }

		    /**
		     * Calculates the average turn around time of the given lists.
		     * From submission to completion.
		     * @param list of processes
		     * @return a float of the average turn around time.
		     */
		    private static float AverageTaround(ArrayList<Process> list) {
		    	float turnTime = 0.0f;
		    	for(Process p: list)
		    	{	turnTime += p.getTurnTime();	}
		    	return (turnTime / list.size());
		    }

		    /**
		     * Calculates the average waiting time of the given lists.
		     * From submission to been active
		     * @param list of processes
		     * @return a float of the average waiting time.
		     */
		    private static float AverageWait(ArrayList<Process> list) {
		    	float waiting = 0.0f;
		    	for(Process p: list)
		    	{	waiting += p.getWaitTime();	}
		    	return (waiting / list.size());
		    }

		    /**
		     * Calculates the average response time of the given lists.
		     * From first active until finished
		     * @param list of processes
		     * @return a float of the average response time.
		     */
		    private static float AverageResponse(ArrayList<Process> list) {
		    	float response = 0.0f;
		    	for(Process p: list){
		    		response += p.getResponseTime();
		    	}
		    	return (response / list.size());
		    }
		    
		    /**
		     * Sums the number of processes that finished running within 100 quanta.
		     * @param a is a list or process
		     * @return a float representing the total number of processes that finished running
		     */
		    public static float CalculaleThroughput(ArrayList<Process> a) {
			float count = 0;
			for(Process p: a){
			    if(p.getETime()<100) count++;
			}
			return count;
		    }

		    /**
		     * Calculates the average throughput over the list of lists of processes. 
		     * @param listOfLists the list of lists of processes
		     * @return a float representing the average
		     */
		    public static float CalculateAverageThroughput(
			    ArrayList<ArrayList<Process>> listOfLists) {
			float count = 0;
			for(ArrayList<Process> a: listOfLists)
		    	{
			    count = CalculaleThroughput(a);
		    	}
			return count/listOfLists.size();
		    }

}
