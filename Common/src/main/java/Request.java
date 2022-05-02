import java.io.Serializable;

public class Request implements Serializable {
    private String name;
    private int id= -1;
    private Object object=null;
    public Request(String name){
        this.name=name;
    }
    public Request(String name, int id, Object object){
        this.id=id;
        this.name=name;
        this.object=object;
    }
    public Request(String name, Object object){
        this.name=name;
        this.object=object;
    }
    public Request(String name, int id){
        this.id=id;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public Object getObject(){
        return object;
    }

    public int getId(){
        return id;
    }

}
