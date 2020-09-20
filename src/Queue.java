import java.util.ArrayList;

public class Queue {
    private ArrayList<Duck> queue = new ArrayList<>();

    public Duck get(int index){
        return queue.get(index);
    }

    public void add(Duck duck){
        queue.add(duck);
    }

    public Duck remove(){
        Duck duck = queue.get(0);
        queue.remove(0);
        return duck;
    }

    public int size(){
        return queue.size();
    }

    public String toString(){
        String s = "";
        if(queue.size() == 0){
            return "[]";
        }else{
            s += "[" + queue.get(0).toString();
            for(int i = 1; i < queue.size(); i++){
                s += "," + queue.get(i).toString();
            }
            s += "]\n";
        }


        return s;
    }
}
