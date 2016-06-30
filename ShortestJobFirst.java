import java.util.ArrayList;

/**Shortest Job First Algorithm
 * The shortest job in the list has priority
 * Created by Team Cache City on 6/29/16.
 */

public class ShortestJobFirst implements InterfaceScheduler{
	private ArrayList<Process> pList;
	private ArrayList<Process> queue;
	private ArrayList<String> sList;
	private Process shortest;
	private int quantTime;
	private ArrayList<Process> stats;
	
	/*
	 * Constructor for SJF algorithm
	 * @param p list of processes to go through
	 */
	
	public ShortestJobFirst(ArrayList<Process> p){
		pList = p;
		quantTime = 0;
		queue = new ArrayList<Process>();
		sList = new ArrayList<String>();
		stats = new ArrayList<Process>();
		
		run();
	}
	
	/*
	 * Begins the SJF algorithm
	 */
	public void run(){
		while(quantTime < 100){
			for(Process p : pList){
				if(p.getArrTime() < quantTime)
				{
					queue.add(p);
				}
			}
			pList.removeAll(queue);
			
			if(queue.isEmpty())
			{
				sList.add("");
				quantTime++;
			}
			else
			{
				shortest = queue.get(0);
				for(Process q : queue)
				{
					if(q.getArrTime() < shortest.getArrTime())
					{
						shortest = q;
					}
				}
				
				shortest.setStartTime(quantTime);
				
				while(shortest.getRTime() > 0)
				{
					sList.add(shortest.getName());
					shortest.decRTime();
					shortest.incQTime();
					quantTime++;
				}
				shortest.setEndTime(quantTime -1);
				stats.add(shortest);
				queue.remove(shortest);
			}
		}
	}

	public ArrayList<String> getStringList()
	{
		return sList;
	}
	
	
	public ArrayList<Process> getStatistics()
	{
		return stats;
	}
	
}
