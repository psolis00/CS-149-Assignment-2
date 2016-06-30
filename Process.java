import java.util.Random;
/**Process objects to handle instructions
 * Created by Team Cache City on 6/25/16.
 * Team Cache City:
 */
public class Process {
    private static final int QUANTUM_MAX =5;
    private static final int HIGH_PRIORITY = 1;
    private float arrivalTime;
    private float runTime;
    private float quantTime;
    private int priority;
    private int quant_W_Amount;
    private String name;
    private int startTime;
    private int endTime;

    /**
     * Default Constuctor for proccesses not used
     */
    public Process(){
        name = "";

        arrivalTime = 200;
        runTime = 100;
        priority = -1;
        quant_W_Amount = 0;
        startTime = -1;
        endTime = -1;
        quantTime = 0.0f;

    }

    /**
     * default constructer for proccesess
     * @param seed the number of proccesses to run
     *
     */
    public Process(int seed){
        name = seed + "";


        Random random = new Random();
        arrivalTime = random.nextFloat() * 99.0f;
        runTime = random.nextFloat() * 10.0f;
        priority = random.nextInt(4) +1;
        quant_W_Amount = 0;
        startTime = -1;
        endTime = -1;
        quantTime = 0.0f;

    }

    /**
     * arrival time of process
     * @return arrival of the process
     */
    public float getArrTime(){
        return arrivalTime;
    }

    /**
     * sets arrival time
     */
    public void setArrTime(float arrivalTime){
        this.arrivalTime = arrivalTime;
    }

    /**
     * runtime of proccess
     * @returns runtime
     */
    public float getRTime(){
        return runTime;
    }
    /**
     * sets run time
     */
    public void setRTime(float runTime){
        this.runTime = runTime;
    }
    /**
     * process priorities
     * @return priority number
     */
    public int getPriority(){
        return priority;
    }
    
    /**
     * Gets the quanta time the alg ended
     * @return the endTime
     */
    public int getETime() {
        return endTime;
    }

    /**
     * sets priority
     */
    public void setPriority(int priority){
        this.priority= priority;
    }
    /**
     *
     * @return start time
     */
    public int getStartTime(){
        return startTime;
    }
    /**
     * sets start time
     */
    public void setStartTime(int startTime){
        if(this.startTime< 0){
            this.startTime = startTime;
        }
    }
    /**
     *@return process name
     */
    public String getName(){
        return name;
    }
    /**
     * names process
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Gets the quan_W_Amount
     * @return quant amount
     */
    public int getQuantWait(){
        return quant_W_Amount;
    }
    /**
     * Gets response time
     */
    public float getResponseTime(){
        return endTime - startTime;
    }

    /**
     * gets turn around time
     * @return turn around time
     */
    public float getTurnTime(){
        return endTime - arrivalTime;
    }
    
    /**
     * Gets the waitingTime.
     * @return the waitingTime.
     */
    public float getWaitTime() {
        return endTime - arrivalTime - quantTime;
    }
    /**
     *
     * @return quant amount
     */
    public float getQTime(){
        return quantTime;
    }

    /**
     * incrementing quanta
     */
    public void incQTime(){
        quantTime++;
    }


    /**
     * Decrements wait time for processes
     * helps when proess is stuck
     */
    public void decWaitQuantTimeAmount(){
        quant_W_Amount--;
    }

    /**
     * Increments the quant amount time when it reaches 5 (extra credit)
     * Uses it to increase priority when CPU is idle
     */
    public void incWaitQuantTimeAmount(){
        quant_W_Amount--;

        if(quant_W_Amount == QUANTUM_MAX){
            quant_W_Amount = 0;

        incPriority();
        }

    }

    /**
     * increases priority by one
     */
    public void incPriority(){
        if(priority > HIGH_PRIORITY){
            priority--;
        }
    }
    /**
     * creates clone copy of process object
     * uses for running the porgram for 5 times
     */
    public Process clone() {
        Process ProcessClone = new Process();

        ProcessClone.setArrTime(arrivalTime);
        ProcessClone.setRTime(runTime);
        ProcessClone.setName(name);
        ProcessClone.setPriority(priority);
        return ProcessClone;
    }

    
    /**
     * decrementing quanta
     */
    public void decRTime(){
    	runTime--;
    }
    
    /**
     * sets end time
     */
    public void setEndTime(int endTime){
    	this.endTime = endTime;
    }


}
