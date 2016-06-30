import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** Highest Priority First Algorithm(non-preemptive)
 *  The Highest priority goes runs until it is complete
 */

public class HighestPriorityFirst implements InterfaceScheduler{
	private static final int QUANTUM_MAX = 100;
	private ArrayList<Process> pList;
	private ArrayList<Process> stats;
	private ArrayList<String> sList;
	
	
	/* Constructor for HPF
	 * @param p list to go through algorithm
	 */
	public HighestPriorityFirst(ArrayList<Process> p)
	{
		pList = p;
		stats = new ArrayList<Process>();
		sList = new ArrayList<String>();
		
		run();
		}
	
	/*
	 *Runs HPF algorithm 
	 *Compares priority to determine 
	 *which process runs first
	 */
	public void run()
	{
		Comparator<Process> comparator = new Comparator<Process>()
		{
			public int compare(Process a, Process b)
			{
				int priority = a.getPriority() - b.getPriority();
				if(priority < 0){ return -1;}
				else if(priority == 0){ return 0;}
				else if(priority > 0){ return 1;}
				else{ return new Float (a.getArrTime()).compareTo(new Float(b.getArrTime()));}
			}
		};
		
		ArrayList<Process> priorityList = new ArrayList<Process>();
		priorityList.add(pList.remove(0));
		int quantTime = 0;
		
		while(quantTime <= QUANTUM_MAX)
		{
			Process p = priorityList.remove(0);
			
			while(p.getArrTime() > quantTime)
			{
				sList.add("");
				
				quantTime++;
			}
			
			p.setArrTime(quantTime);
			while(p.getRTime() > 0)
			{
				sList.add(p.getName());
				
				p.decRTime();
				p.incQTime();
				quantTime++;
				
				for(Process q: priorityList)
				{
					if(q != p)
					{
						q.incQTime();
					}
				}
			}
			p.setEndTime(quantTime-1);
			stats.add(p);
			
			for(Process r : pList)
			{
				if(r.getArrTime() < quantTime)
				{
					priorityList.add(r);
				}
			}
			if(priorityList.isEmpty())
			{
				priorityList.add(pList.remove(0));
			}
			
			pList.removeAll(priorityList);
			
			Collections.sort(priorityList, comparator);
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
