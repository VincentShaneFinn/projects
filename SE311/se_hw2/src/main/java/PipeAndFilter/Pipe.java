package PipeAndFilter;
//interface Pipe
public interface Pipe{
    public boolean put(Object obj);
    public Object get() throws InterruptedException;
}

