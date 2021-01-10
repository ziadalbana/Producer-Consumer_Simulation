
package Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;


public class Graph {
    /*Hashtable<Integer,Node> graph=new Hashtable<>();
    private int order;
    public class Node{
        Queue Q;
        Edge Machine;
        public Node(Queue q){
            this.Q=q;
            Machine=new Edge();
        }
    }
    public class Edge{
        ArrayList<Machine> m;
        public Edge(){
            this.m=new ArrayList<>();
        }
    }
    public void AddQueue(int order,Queue q){
        Node node=new Node(q);
        graph.put(order, node);
    }
    public void UpdataQueueZero(String colour){
       graph.get(0).Q.add(colour);
    }
    public void updateMachine(int order,Machine m){
        this.order=order;
        graph.get(order).Machine.m.add(m);
    }
    public void updateMachineconnection(String MachineName){
        for(int i=0;i<graph.get(order).Machine.m.size();i++){
            if(MachineName.equals(graph.get(order).Machine.m.get(i).name)){
                graph.get(order).Machine.m.get(i).connectedQueue=new LinkedList<>();
            }
        }
    }*/
}
