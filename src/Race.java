import java.util.ArrayList;
import java.util.Random;

public class Race {
    private ArrayList<Queue> queues;
    private ArrayList<Queue> nextQueues;
    private int iteration;

    public Race(){
        //we fill our list with ten queues
        queues = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            queues.add(new Queue());
        }
        //the next list only needs to have 9 queues
        nextQueues = new ArrayList<>();
        for(int i = 0; i < queues.size()-1; i++){
            nextQueues.add(new Queue());
        }
        iteration = 1;
        //during the first iteration we just fill the queues
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                queues.get(i).add(new Duck(j + 1 + i * 10));
            }
        }
    }

    public void go(){
        System.out.println("Iteration: 1");
        System.out.println(this);

        while(iteration < 10){
            iteration++;
            iteration();
            System.out.println("Iteration: " + iteration);
            System.out.println(this);
        }
    }

    public void iteration(){
        //10 - iteration + 1 = 10 - 2 + 1 = 9. So iteration 2 will run 81 times
        for(int i = 0; i < (10 - iteration + 1) * (10 - iteration + 1); i++){
            removeAndPlace();
        }
        queues = nextQueues;
        nextQueues = new ArrayList<>();
        for(int i = 0; i < 10 - iteration; i++){
            nextQueues.add(new Queue());
        }
    }

    private void removeAndPlace() {
        Random random = new Random();
        boolean removeDuck = false;
        Duck duck = null;
        while (!removeDuck){
            //Remove a duck from previous queues (this starts second iteration)
            //(since previous iteration had ten lists. we need to choose a
            //duck from the ten lists ( thus the + 2)).
            int remove = random.nextInt(10 - iteration + 2);
            //we check that the random list we've "chosen" isn't empty
            if(queues.get(remove).size() != 0){
                duck = queues.get(remove).remove();
                removeDuck = true;
            }
        }

        boolean placed = false;
        //we loop until we've placed the duck in a list (that isn't already "full")
        while (!placed){
            int nextQueue = random.nextInt(10 - iteration + 1);
            //Check that it isn't full
            if (10 - iteration + 1 > nextQueues.get(nextQueue).size()){
                nextQueues.get(nextQueue).add(duck);
                placed = true;
            }
        }
    }

    public void go(int cheatNumber){
        System.out.println("Iteration: 1");
        System.out.println(this);

        while(iteration < 10){
            iteration++;
            iteration(cheatNumber);
            System.out.println("Iteration: " + iteration);
            System.out.println(this);
        }
    }

    public void iteration(int cheatNumber){
        //we search for our cheatNumber and we remove that as the first one
        for (Queue queue : queues) {
            for (int j = 0; j < queue.size(); j++) {
                if (queue.get(j).getNumber() == cheatNumber) {
                    for (int l = 0; l < j; l++) {
                        queue.remove(); //since we remove index 0, we need to remove all the numbers before our cheat number
                    }
                    Duck duck = queue.remove();
                    nextQueues.get(0).add(duck);
                }
            }
        }
        //we remove from random queues and add to random queues in the next list
        for(int i = 0; i < (10 - iteration + 1) * (10 - iteration + 1) - 1; i++){
            removeAndPlace();
        }
        queues = nextQueues;
        nextQueues = new ArrayList<>();
        for(int i = 0; i < 10 - iteration; i++){
            nextQueues.add(new Queue());
        }
    }

    public String toString(){
        String s = "";
        for(Queue q : queues) {
            s += q.toString() + "\n";
        }
        return s;
    }

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration(){
        return iteration;
    }
}