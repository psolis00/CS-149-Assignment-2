import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*********************************************
 * The tester Runs the different scheduling algorithms
 * for processes and calculates statistics for
 * the scheduler
 * @author Team: Cache City .....:Nnamdi Eneh, Yehia Qtaish, Patrick Solis
 * CS149
 **********************************************/
public class ProcessTester {
	
	/**
     * Creates random processes to test the algorithms. 
     * @param None.
     */	
	public static void main(String[] args) {
		
		 ArrayList<Process> process1 = new ArrayList <Process>();
	     ArrayList<Process> process2 = new ArrayList <Process>();
	     ArrayList<Process> process3 = new ArrayList <Process>();
	     ArrayList<Process> process4 = new ArrayList <Process>();
	     ArrayList<Process> process5 = new ArrayList <Process>();
	        
	     int numberOfRuns = 30;
	        for(int i=0; i<numberOfRuns*5; i++)
	        {
	            Process n = new Process(i);
	            if (i < numberOfRuns )
	            {   process1.add(n); }
	            else if (i < numberOfRuns * 2)
	            {   process2.add(n); }
	            else if (i < numberOfRuns *3)
	            {   process3.add(n); }
	            else if (i < numberOfRuns *4)
	            {   process4.add(n); }
	            else
	            {   process5.add(n); }
	        }
	        
	        SortLists(process1, process2, process3, process4, process5);
	        
	        System.out.println("Pre scheduled process lists:");
	        System.out.println("_List1________________________________________________________________________");
	        System.out.println(printProcessList(process1));
	        System.out.println("_List2________________________________________________________________________");
	        System.out.println(printProcessList(process2));;
	        System.out.println("_List3________________________________________________________________________");
	        System.out.println(printProcessList(process3));;
	        System.out.println("_List4________________________________________________________________________");
	        System.out.println(printProcessList(process4));;
	        System.out.println("_List5________________________________________________________________________");
	        System.out.println(printProcessList(process5));;
	        System.out.println("______________________________________________________________________________");
	        System.out.println();
	        
	       
	        int count = 1;
	        float avgThroughput = 0;

	        // First Come First Serve
	        System.out.println("First Come First Served(FCFS)");        
	        ArrayList<ArrayList<Process>> FCFSclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: FCFSclone)
	        {
	            System.out.println("List"+count++);
	            FirstComeFirstServed fcfs = new FirstComeFirstServed(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(fcfs.getStringList()));;
	            System.out.println(Statistics.CalculateStatistics(fcfs.getStatistics()));
	            System.out.println(printProcessList(fcfs.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(fcfs.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(fcfs.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        avgThroughput=0;
	        System.out.println("\n\n");
	        
	        count = 1;
	        //Shortest Job First
	        System.out.println("Shortest Job First(SJF)");
	        ArrayList<ArrayList<Process>> SJFclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: SJFclone)
	        {
	            System.out.println("List"+count++);
	            ShortestJobFirst sjf = new ShortestJobFirst(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(sjf.getStringList()));
	            System.out.println(Statistics.CalculateStatistics(sjf.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(sjf.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(sjf.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        avgThroughput=0;
	        System.out.println("\n\n");
	        
	        count = 1;
	        // Shortest Remaining Time
	        System.out.println("Shortest Remaining Time(SRT)");
	        ArrayList<ArrayList<Process>> SRTclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: SRTclone)
	        {
	            System.out.println("List"+count++);
	            ShortestRemainingTime srt = new ShortestRemainingTime(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(srt.getStringList()));
	            System.out.println(Statistics.CalculateStatistics(srt.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(srt.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(srt.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        avgThroughput=0;
	        System.out.println("\n\n");
	        count = 1;
	        
	        //Round Robin
	        System.out.println("Round Robin(RR)");
	        ArrayList<ArrayList<Process>> RRclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: RRclone)
	        {
	            System.out.println("List"+count++);
	            RoundRobin rr = new RoundRobin(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(rr.getStringList()));
	            System.out.println(Statistics.CalculateStatistics(rr.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(rr.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(rr.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        avgThroughput=0;
	        System.out.println("\n\n");
	        
	        count = 1;
	        //Highest Priority First (Non-preemptive)
	        System.out.println("Highest Priority First(HPF)");
	        ArrayList<ArrayList<Process>> HPFclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: HPFclone)
	        {
	            System.out.println("List"+count++);
	            HighestPriorityFirst hpf = new HighestPriorityFirst(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(hpf.getStringList()));
	            System.out.println(Statistics.CalculateStatistics(hpf.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(hpf.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(hpf.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        avgThroughput=0;
	        System.out.println("\n\n");
	        
	        count = 1;
	        //Highest Priority First (Preemptive)
	        System.out.println("Highest Priority First Preemptive(HPFP)");
	        ArrayList<ArrayList<Process>> HPFPclone = deepCopy(process1, process2, process3, process4, process5);
	        for (ArrayList<Process> list: HPFPclone)
	        {
	            System.out.println("List"+count++);
	            HighestPriorityFirstP hpfp = new HighestPriorityFirstP(list);
	            System.out.println(printTimeline());
	            System.out.println(printStringList(hpfp.getStringList()));
	            System.out.println(Statistics.CalculateStatistics(hpfp.getStatistics()));
	            avgThroughput+=Statistics.CalculaleThroughput(hpfp.getStatistics());
	            System.out.println("Throughput: " + Statistics.CalculaleThroughput(hpfp.getStatistics()));
	        }
	        System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
	        System.out.println("\n\n");
	        
	    }

	    private static void SortLists(ArrayList<Process> list1,
		    ArrayList<Process> list2, ArrayList<Process> list3,
		    ArrayList<Process> list4, ArrayList<Process> list5) {
	        Comparator<Process> comparator = new Comparator<Process>() {
	            public int compare(Process process1, Process process2) {
	                return new Float(process1.getArrTime()).compareTo(new Float( process2.getArrTime()));
	            }
	        };
	        Collections.sort(list1, comparator);
	        Collections.sort(list2, comparator);
	        Collections.sort(list3, comparator);
	        Collections.sort(list4, comparator);
	        Collections.sort(list5, comparator);
	    }

	    public static String printProcessList(List<Process> processList) {
	        String s = "";
	        for(Process process : processList) {
	            s+="[Name: " + String.format("%3s", process.getName()) 
	        	    + " --> Arrival Time: " + String.format("%10f", process.getArrTime()) 
	        	    + ", Run Time: " + String.format("%9f", process.getRTime()) 
	        	    + ", Priority: " + process.getPriority() 
	        	    + ", Final runtime: " + String.format("%9f", process.getRTime())
	        	    + ", Time started: " + String.format("%9d", process.getStartTime())
	        	    + ", Turn Around time: " + String.format("%9f", process.getTurnTime())
	        	    + ", Waiting time: " + String.format("%9f", process.getWaitTime())
	        	    + ", Responce time: " + String.format("%9f", process.getResponseTime())
	        	    + "]\n";
	        }
	        return s;
	    }
	   
	    public static String printStringList(List<String> stringList) {
	        String previousString = stringList.get(0);

	        String output = "[";
	        for(String string : stringList) {
	            if(string.equals(previousString)) {
	                output += String.format("%3s", string) + "|";
	            }
	            else {
	                output = output.substring(0, output.length() - 1); // remove last |
	                output += "]" + String.format("%3s", string) + "|";
	                previousString = string;
	            }
	        }
	        output = output.substring(0, output.length() - 1); // remove last |
	        output += "]";
	        return output;
	    }
	 
	    public static String printTimeline() {

	        String output = "[";
	        for(int i = 0; i<100; i++) {
	            output += String.format("%3d",i) + "|";
	        }
	        output = output.substring(0, output.length() - 1); // remove last |
	        output += "]";
	        return output;
	    }
	    

	    public static ArrayList<ArrayList<Process>> deepCopy( ArrayList<Process> list1, ArrayList<Process> list2,
	        ArrayList<Process> list3, ArrayList<Process> list4, ArrayList<Process> list5) {
	        ArrayList<Process> list1clone = new ArrayList<Process>();
	        ArrayList<Process> list2clone = new ArrayList<Process>();
	        ArrayList<Process> list3clone = new ArrayList<Process>();
	        ArrayList<Process> list4clone = new ArrayList<Process>();
	        ArrayList<Process> list5clone = new ArrayList<Process>();
	        ArrayList<ArrayList<Process>> clone = new ArrayList<ArrayList<Process>>();
	        
	        for(Process p: list1)
	        {   list1clone.add(p.clone());   }
	        clone.add(list1clone);

	        for(Process p: list2)
	        {   list2clone.add(p.clone());   }
	        clone.add(list2clone);
	        
	        for(Process p: list3)
	        {   list3clone.add(p.clone());   }
	        clone.add(list3clone);
	        
	        for(Process p: list4)
	        {   list4clone.add(p.clone());   }
	        clone.add(list4clone);
	        
	        for(Process p: list5)
	        {   list5clone.add(p.clone());   }
	        clone.add(list5clone);
	        
	        return clone;
	    }
	}

