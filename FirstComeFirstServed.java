import java.util.ArrayList;

/**First Come First serve Algorithm
 * runs 100 processes and runs it
 * Created by Team Cache City on 6/29/16.
 */
public class FirstComeFirstServed implements InterfaceScheduler{
    private static final int QUANT_MAX= 100;
    private ArrayList<Process> pList;
    private ArrayList<String> sList;
    private ArrayList<Process> stats;

    /**
     * constructor to start the algorithm
     * @param pList list of process to go through
     */
    public FirstComeFirstServed(ArrayList<Process> pList){
        this.pList = pList;
        sList = new ArrayList<String>();
        stats = new ArrayList<Process>();

        run();
    }
    /**
     * run method for first come first served
     */
    private void run(){
        int quant = 0;

        while(!pList.isEmpty() && quant <= QUANT_MAX){
            Process p = pList.remove(0);
            while(p.getArrTime() > quant){
                sList.add("");
                quant++;
            }
            p.setStartTime(quant);
            while(p.getRTime() > 0){
                sList.add(p.getName());
                p.decRTime();
                p.incQTime();
                p.setEndTime(quant);
                quant++;
            }
            stats.add(p);
        }
    }
    public ArrayList<String> getStringList(){
        return sList;
    }

    public ArrayList<Process> getStatistics(){
        return stats;
    }
}
