package hello;

public class numbermodel {

    private String method;
    private int n;
    private int min;
    private int max;
    private boolean replacement;

    public numbermodel(String method, int n, int min, int max, boolean replacement) {
        this.method = method;
        this.n = n;
        this.min = min;
        this.max = max;
        this.replacement = replacement;
    }

    public void setMethod(String method){
        this.method = method;
    }
    public String getMethod(){
        return method;
    }

    public void setn(int n){

        this.n = n;

    }

    public int getn(){
        return n;
    }


    public void setMin(int min){

        this.min = min;
    }

    public int getMin(){
        return min;
    }

    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    public void setReplacement(boolean replacement){
        this.replacement = replacement;
    }

    public boolean getReplacement(){
        return replacement;
    }

}
