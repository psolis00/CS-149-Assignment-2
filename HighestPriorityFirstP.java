import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**Highest Priority First (preemptive)
 * high priority runs first
 * Created by Teacm Cache City on 6/27/16.
 * CS 149
 */
public class HighestPriorityFirstP implements InterfaceScheduler {

    private static final int QUANT_MAX = 100;
    private ArrayList<Process> pList;
    private ArrayList<String> sList;
    private ArrayList<Process> stat;

    /**
     * Consttuctor to start Highest priority first (non premative type)
     * @param pList
     */
    public HighestPriorityFirstP(ArrayList<Process> pList){
        this.pList = pList;
        sList = new ArrayList<String>();
        stat = new ArrayList<Process>();

        run();
    }

    /**
     * Runs the algorithm
     * @return
     */
    private void run() {
        Comparator<Process> comp = new Comparator<Process>(){
            public int compare(Process o1, Process o2) {
                int pDiff = o1.getPriority() - o2.getPriority();

                if (pDiff < 0) {
                    return -1;
                }
                else if (pDiff > 0) {
                    return 1;
                }
                else {
                    return new Float(o1.getArrTime()).compareTo(new Float(o2.getArrTime()));
                }

            }

        };

        ArrayList<Process> priorityPList = new ArrayList<>();
        priorityPList.add(pList.remove(0));
        int quant = 0;
        while(quant <= QUANT_MAX){
            Process p = priorityPList.get(0);
            while(p.getArrTime() > quant){
                sList.add("");

                quant++;
            }

            while(p.getRTime()>0){
                sList.add(p.getName());
                p.decRTime();
                p.incQTime();
                p.setEndTime(quant);
                if(p.getStartTime() == -1){
                    p.setStartTime(quant);
                }
                quant++;

                for(Process tempo : pList){
                    if(tempo.getArrTime()<quant){
                        priorityPList.add(tempo);
                    }
                }

                pList.removeAll(priorityPList);
                for(Process tempo : priorityPList){
                    if(tempo != p){
                        tempo.incWaitQuantTimeAmount();
                    }
                }
                Collections.sort(priorityPList,comp);
                p = priorityPList.get(0);
            }

            stat.add(priorityPList.get(0));
            priorityPList.remove(0);

            for(Process temp2 : pList){
                if(temp2.getArrTime() < quant){
                    priorityPList.add(temp2);
                }
            }

            if(priorityPList.isEmpty()){
                priorityPList.add(pList.remove(0));
            }

            pList.removeAll(priorityPList);

            Collections.sort(priorityPList, comp);
        }
    }
    public ArrayList<String> getStringList() {
        return sList;
    }


    public ArrayList<Process> getStatistics() {
        return stat;
    }
}
