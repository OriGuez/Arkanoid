package Managment;

public class Counter {
    private int count;
    public Counter(int n)
    {
        this.count=n;
    }
    public void increase(int b)
    {
        this.count+=b;
    }
    public void decrease(int b)
    {
        this.count-=b;
    }
    public int getValue()
    {
        return this.count;
    }

    @Override
    public String toString()
    {
        return (""+this.count);
    }
}
