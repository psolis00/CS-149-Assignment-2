import java.util.ArrayList;

/**Shortest Remaining Time Algorithm
 * Processes with short run time is prioritized
 * Created by Team Cache City on 6/29/16.
 */

public class ShortestRemainingTime implements InterfaceScheduler{
    private ArrayList<Process> pList;
    private ArrayList<Process> rData;
    private ArrayList<Process> stats;
    private ArrayList<String> sList;
    private int quantTime;
    
    /*
     * Constructor for SRT
     * @param p list of processes to go through
     */
    public ShortestRemainingTime(ArrayList<Process> p)
    {
        pList = p;
        rData = new ArrayList<Process>();
        stats = new ArrayList<Process>();
        sList = new ArrayList<String>();
        quantTime = 0;
        
        run();
    }
    
    /*
     * Begins the SRT algorithm
     */
    public void run()
    {
        while(quantTime < 100 || !rData.isEmpty())
        {
            if(quantTime < 100)
            {
                runtimeProcesses();
            }
            if(rData.isEmpty())
            {
                sList.add("");
            }
            else
            {
                Process add = findSRT();
                if(add != null)
                {
                    sList.add(add.getName());
                    add.decRTime();
                    add.incQTime();
                    add.setStartTime(quantTime);
                }
                else
                {
                    rData.clear();
                }
            }
            removeProcess();
            quantTime++;
        }
    }
    
    /*
     * Adds processes to rData
     */
    public void runtimeProcesses()
    {
        for(Process p: pList)
        {
            if(p.getArrTime() <= quantTime)
            {
                rData.add(p);
            }
        }
        pList.removeAll(rData);
    }
    
    /*
     * Finds the shortest remaining time
     * @return shortest process
     */
    public Process findSRT()
    {
        Process shortest = rData.get(0);
        for(Process p: pList)
        {
            if(quantTime < 100 && (p.getRTime() < shortest.getRTime()))
            {
                shortest = p;
            }
            if(quantTime >= 100 && (p.getRTime() < shortest.getRTime()) && (p.getStartTime() > -1))
            {
                shortest = p;
            }
        }
        if(quantTime < 100)
        {
            return shortest;
        }
        if(quantTime >= 100 && shortest.getStartTime() > -1)
        {
            return shortest;
        }
        return null;
    }
    
    /*
     * removes completed processes 
     */
    public void removeProcess()
    {
        for(Process p: rData)
        {
            if(p.getRTime() <= 0)
            {
                p.setEndTime(quantTime);
                stats.add(p);
            }
        }
        rData.removeAll(stats);
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
