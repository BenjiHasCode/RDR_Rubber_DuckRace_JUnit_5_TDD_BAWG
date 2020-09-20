public class Duck {
    private int number;

    public Duck(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public String toString(){
       // return String.valueOf(number);
        return String.format("%3d", number);
    }
}
