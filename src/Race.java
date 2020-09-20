import java.util.ArrayList;
import java.util.Random;

public class Race {
    private ArrayList<Queue> queues;
    private ArrayList<Queue> nextQueues;
    private int iteration;

    public Race(){
        queues = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            queues.add(new Queue());
        }
        nextQueues = new ArrayList<>();
        for(int i = 0; i < queues.size()-1; i++){
            nextQueues.add(new Queue());
        }
        iteration = 1;
        //fill queues
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
        for (Queue queue : queues) {
            for (int j = 0; j < queue.size(); j++) {
                if (queue.get(j).getNumber() == cheatNumber) {
                    for (int l = 0; l < j; l++) {
                        queue.remove();
                    }
                    Duck duck = queue.remove();
                    nextQueues.get(0).add(duck);
                }
            }
        }
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
            s += q.toString();
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
