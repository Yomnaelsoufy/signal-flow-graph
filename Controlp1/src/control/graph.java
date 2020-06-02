package control;

import java.util.ArrayList;
import java.util.List;
class graph {
    private int numofvertices;
    private int numofedges;
    private boolean[] isvisted;
    private int startpt;
    private int delta=1;
    private List<Integer>Deltas = new ArrayList<>();
    private List<Long>ValofPaths=new ArrayList<>();
    private List<List<Long>>ValofnonTouch=new ArrayList<>();
    private List<Integer>Valofloops=new ArrayList<>();
    private List<ArrayList<Integer>>nontouch=new ArrayList<>();
    private ArrayList<ArrayList<edge>>loops=new ArrayList<>();
    private ArrayList<edge>path=new ArrayList<>();
    private int endpt;
    private List<ArrayList<edge>> Paths=new ArrayList<ArrayList<edge>>();
   private List<edge>adjlist[];
   public graph(){}
  public graph(int numofvertices,int numofedges,int startpt,int endpt){
     setNumofVertices(numofvertices);
      isvisted=new boolean[numofvertices];
      this.numofedges=numofedges;
      this.startpt=startpt;
      this.endpt=endpt;
  }
 public void setNumofVertices(int numofvertices){
this.numofvertices=numofvertices;
adjlist=new List[numofvertices];
for (int i=0;i<numofvertices;i++){
    adjlist[i]=new ArrayList<>();
}
 }
    public void DFS(int s) {
        isvisted[s] = true;
        if (s == endpt) {
            Paths.add((ArrayList<edge>) path.clone());
            Long x= Long.valueOf(1);
for (edge e:path){
     x*=e.getWeight();
}
ValofPaths.add(x);
isvisted[s] = false;
            return;
        }
        for (edge e : adjlist[s]) {
            if (!isvisted[e.getTo()]) {
                path.add(e);
                DFS(e.getTo());
                path.remove(e);
            } else {
                ArrayList<edge> temp = (ArrayList<edge>) path.clone();
                for (int i = 0; i < path.size(); i++) {
                    if (path.get(i).getFrom() != e.getTo()) {
                        temp.remove(path.get(i));
                    } else break;
                }
                temp.add(e);
                loops.add(temp);
                int sum=1;
                for (edge ed:temp){
                    sum*=ed.getWeight();
                }
                Valofloops.add(sum);
            }
        }
        isvisted[s] = false;
    }
    public void setEdge(int from,int to,int w){
     adjlist[from].add(new edge(from,to,w));
 }
 void Nontouch(){
      removesimilar(loops);
      ArrayList<ArrayList<Integer>> tempfrom= new ArrayList<>();ArrayList<ArrayList<Integer>> tempto= new ArrayList<>();
      List<List<Integer>>L1=new ArrayList<>(); List<List<Integer>>L2=new ArrayList<>();
      for (int i=0;i<loops.size();i++){List <Integer>l1=new ArrayList<>();List <Integer>l2=new ArrayList<>();
         for (int  j=0;j<loops.get(i).size();j++){
             l1.add(loops.get(i).get(j).getFrom());l2.add(loops.get(i).get(j).getTo());
         }    L1.add(new ArrayList<>(l1));    L2.add(new ArrayList<>(l2));
          tempfrom.add(new ArrayList<>(l1));tempto.add(new ArrayList<>(l2));
      }
      for (int i=0;i<loops.size()-1;i++)
          nontouch.add(new ArrayList<>());
      for (int i=0;i<loops.size();i++){
          for (int j=i+1;j<loops.size();j++){
              tempfrom.get(i).retainAll(tempfrom.get(j));    tempto.get(i).retainAll(tempto.get(j));
          if (tempfrom.get(i).size()==0&&tempto.get(i).size()==0){
               nontouch.get(i).add(j);
          }tempfrom.add(i,new ArrayList<>(L1.get(i)));tempto.add(i,new ArrayList<>(L2.get(i)));
              tempfrom.remove(i+1);tempto.remove(i+1);
          }
      }

 }

    private void removesimilar(ArrayList<ArrayList<edge>> loops) {

      for (int i=0;i<loops.size()-1;i++){
          if (i<0)i=0;

      List<edge>l1=new ArrayList<>(loops.get(i));
      for (int j=i+1;j<loops.size();j++){
      List<edge>l2=new ArrayList<>(loops.get(j));
      if (l2.size()==l1.size())
      { l2.retainAll(l1);
      if (l2.size()==l1.size()){
          loops.remove(j);Valofloops.remove(j);i--;
      }
      }}
  }
    }

    void Calculatenontouch(){Long v;
          for (int i=0;i<nontouch.size();i++){
              v= Long.valueOf(Valofloops.get(i));
    for (int j=0;j<nontouch.get(i).size();j++)
    { if (ValofnonTouch.size()==j){
        ValofnonTouch.add(new ArrayList<>());
    }
        if (j!=0)
        ValofnonTouch.get(0).add((long) (Valofloops.get(i)*Valofloops.get(nontouch.get(i).get(j))));
        v*=Valofloops.get(nontouch.get(i).get(j));
        ValofnonTouch.get(j).add(v);
    }
}
 }
 void calculateDeltas(){
      int s=0;
      for (int i=0;i<Valofloops.size();i++){
          s+=Valofloops.get(i);
      }
      delta-=s;
      for (int i=0;i<ValofnonTouch.size();i++){int x=0;
          for (int j=0;j<ValofnonTouch.get(i).size();j++){
              x+=ValofnonTouch.get(i).get(j);
          }
          delta+=Math.pow(-1,i)*x;
      }

     List<List<Integer>>L1=new ArrayList<>(); List<List<Integer>>L2=new ArrayList<>();
     for (int i=0;i<loops.size();i++){List <Integer>l1=new ArrayList<>();List <Integer>l2=new ArrayList<>();
         for (int  j=0;j<loops.get(i).size();j++){
             l1.add(loops.get(i).get(j).getFrom());l2.add(loops.get(i).get(j).getTo());
         }    L1.add(new ArrayList<>(l1));    L2.add(new ArrayList<>(l2));
     }
     ArrayList<ArrayList<Integer>> tempfrom= new ArrayList<>();ArrayList<ArrayList<Integer>> tempto= new ArrayList<>();
     for (int i=0;i<Paths.size();i++){List <Integer>l1=new ArrayList<>();List <Integer>l2=new ArrayList<>();
         for (int  j=0;j<Paths.get(i).size();j++){
             l1.add(Paths.get(i).get(j).getFrom());l2.add(Paths.get(i).get(j).getTo());
         }
         tempfrom.add(new ArrayList<>(l1));tempto.add(new ArrayList<>(l2));
     }
      for (int i=0;i<tempfrom.size();i++){int x=1;
              List<Integer>l1=new ArrayList<>(tempfrom.get(i));    List<Integer>l2=new ArrayList<>(tempto.get(i));
              for(int k=0;k<L1.size();k++){
                L1.get(k).retainAll(l1);
                L2.get(k).retainAll(l2);
                if (L1.get(k).size()==0&&L2.get(k).size()==0){
                   x-=Valofloops.get(k);
                }
          }Deltas.add(x);
      }
 }
  double CalculateoverallTF(){
      DFS(startpt);
       Nontouch();
      Calculatenontouch();
      calculateDeltas();
      double tf=0;
      for (int i=0;i<ValofPaths.size();i++){
          tf+=Deltas.get(i)*ValofPaths.get(i);
      }
      return tf/delta;
 }
}
