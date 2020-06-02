package control;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import org.graphstream.graph.Graph;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.util.Collection;
import org.apache.commons.collections15.Transformer;


public class controller extends JFrame   {

    private JPanel contentPane;
    private JTextField numofvertices;
    private JTextField from;
    private JTextField to;
    private Graph sf;
    DirectedSparseMultigraph<String,String>sff=new DirectedSparseMultigraph<String, String>();
    private JTextField weight;
    private JTextField numofedges;
    private static graph r;
     private static int numofVertices;
    private static int numofEdges;
    private int stpt,endpt,From ,To,Weight,counter=0;
    private JTextField startpoint;
    private JTextField endpoint;
 //   private static Graph<String, String> g = new DirectedSparseMultigraph<String, String>();;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    controller frame = new controller();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public controller() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 725, 774);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("No. of Nodes");
        lblNewLabel.setBounds(42, 13, 81, 32);
        contentPane.add(lblNewLabel);

        numofvertices = new JTextField();
        numofvertices.setBounds(12, 45, 151, 22);
        contentPane.add(numofvertices);
        numofvertices.setColumns(10);


        numofedges = new JTextField();
        numofedges.setBounds(12, 109, 151, 22);
        contentPane.add(numofedges);
        numofedges.setColumns(10);

        JLabel lblErrorEnterAgain = new JLabel("");
        lblErrorEnterAgain.setForeground(Color.RED);
        contentPane.add(lblErrorEnterAgain);

        JLabel lblStartPoint = new JLabel("Start point");
        lblStartPoint.setBounds(42, 144, 81, 16);
        contentPane.add(lblStartPoint);

        startpoint = new JTextField();
        startpoint.setBounds(12, 174, 151, 22);
        contentPane.add(startpoint);
        startpoint.setColumns(10);

        JLabel lblEndPoint = new JLabel("End point");
        lblEndPoint.setBounds(54, 209, 56, 16);
        contentPane.add(lblEndPoint);

        endpoint = new JTextField();
        endpoint.setBounds(12, 238, 151, 22);
        contentPane.add(endpoint);
        endpoint.setColumns(10);
        JButton btnaddnodes = new JButton("add");
        btnaddnodes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(numofvertices.getText()!=null&&numofedges.getText()!=null&&startpoint.getText()!=null&&endpoint.getText()!=null&&checkifint(startpoint.getText())&&checkifint(endpoint.getText())&&checkifint(numofedges.getText())&&checkifint(numofvertices.getText())) {
                    numofEdges=Integer.parseInt(numofedges.getText());numofVertices=Integer.parseInt(numofvertices.getText());
                    stpt=Integer.parseInt(startpoint.getText());
                    endpt=Integer.parseInt(endpoint.getText());
                    numofedges.setText("");numofvertices.setText("");
                    startpoint.setText("");endpoint.setText("");
                    lblErrorEnterAgain.setText("");
                    for (int i=0;i<numofVertices;i++)
                        sff.addVertex(String.valueOf(i+1));
                    display();
                     //   graph.addNode(String.valueOf(i+1));
                    /*for(Node n:graph)
                        n.addAttribute("ui.label",n.getId());
                 */   r=new graph(numofVertices,numofEdges,stpt-1,endpt-1);}
                else {
                    lblErrorEnterAgain.setBounds(145,269, 102, 16);
                    lblErrorEnterAgain.setText("Error! Enter again");				}
            }
        });
        btnaddnodes.setBounds(42, 271, 81, 22);
        contentPane.add(btnaddnodes);

        JLabel lblFrom = new JLabel("From");
        lblFrom.setBounds(54, 306, 56, 16);
        contentPane.add(lblFrom);

        from = new JTextField();
        from.setBounds(12, 335, 151, 22);
        contentPane.add(from);
        from.setColumns(10);

        JLabel lblTo = new JLabel("To");
        lblTo.setBounds(67, 370, 32, 16);
        contentPane.add(lblTo);

        to = new JTextField();
        to.setBounds(12, 399, 151, 22);
        contentPane.add(to);
        to.setColumns(10);

        JLabel lblWei = new JLabel("weight");
        lblWei.setBounds(67, 434, 56, 16);
        contentPane.add(lblWei);

        weight = new JTextField();
        weight.setBounds(12, 463, 151, 22);
        contentPane.add(weight);
        weight.setColumns(10);

        JButton btnAdd_1 = new JButton("add");
        btnAdd_1.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           if(weight.getText()!=null&&to.getText()!=null&&from.getText()!=null&&checkifint(from.getText())&&checkifint(to.getText())) {
                                               Weight=Integer.parseInt(weight.getText());
                                               To=Integer.parseInt(to.getText());
                                               From=Integer.parseInt(from.getText());
                                             /*  graph.addEdge(from.getText()+""+to.getText(),from.getText(),to.getText(),true);
                                               graph.getEdge(from.getText()+""+to.getText()).addAttribute("ui.label",weight.getText());
                                            */sff.addEdge("w"+String.valueOf(counter)+":"+weight.getText(),from.getText(),to.getText());
                                            display();
                                             lblErrorEnterAgain.setText("");
                                               if(counter<numofEdges) {
                                                   r.setEdge(From-1, To-1, Weight);counter++;
                                               }
                                               weight.setText("");to.setText("");from.setText("");
                                           }
                                           else {lblErrorEnterAgain.setBounds(110,530,100,24);
                                               lblErrorEnterAgain.setText("Error! Enter again");				}
                                       }
                                   }
        );
        btnAdd_1.setBounds(42, 498, 81, 22);
        contentPane.add(btnAdd_1);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(12, 607, 583, 107);
        contentPane.add(textArea);

        JLabel lblNumOfEd = new JLabel("Num of edges");
        lblNumOfEd.setBounds(42, 80, 81, 16);
        contentPane.add(lblNumOfEd);

        JButton btnCalculateoveralltf = new JButton("CalculateOverAllTF");
        btnCalculateoveralltf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(r!=null) {

                    textArea.setText("Overall Transfer Function= "+String.valueOf(r.CalculateoverallTF()));}
                else {
                    lblErrorEnterAgain.setBounds(110,580,100,24);
                    lblErrorEnterAgain.setText("Error! Enter again");
                }
            }
        });
        btnCalculateoveralltf.setBounds(12, 559, 151, 22);
        contentPane.add(btnCalculateoveralltf);

        JButton btnNew = new JButton("new graph");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter=0;
                r=null;
             cleargraph();
         //  graph.clear();
                textArea.setText("");
            }
        });
        btnNew.setBounds(607, 630, 97, 25);
        contentPane.add(btnNew);
    /*    Viewer v=graph.display();
        v.addDefaultView(false);
        View view=v.getDefaultView();
        contentPane.add((Component)view);
        ((Component)view).   setBounds(300,100,930,430);
*/
    }

    private void cleargraph() {
        Collection<String> l= sff.getEdges();
        for(int i=0;i<l.size();i++){
            sff.removeEdge(l.iterator().next());
            i--;
        }
        for (int i=0;i<numofVertices;i++){
            sff.removeVertex(String.valueOf(i+1));
        }
        display();
    }

    private boolean checkifint(String label){
        String regex = "\\d+";
        if (label.matches(regex))
            return true;
        return false;
    }
    private void display(){
        VisualizationImageServer<String,String>vs=new VisualizationImageServer<String,String>(new  CircleLayout<String,String>(sff),new Dimension(930,430));
        Transformer<String, String> transformer = new Transformer<String, String>()
        {
            @Override
            public String transform(String s)
            {
                return s;
            }
        };
        vs.getRenderContext().setVertexLabelTransformer(transformer);
        vs.getRenderContext().setEdgeLabelTransformer(transformer);
        vs.setLocation(300,100);
        contentPane.add(vs);
        contentPane.setVisible(true);
    }
}
