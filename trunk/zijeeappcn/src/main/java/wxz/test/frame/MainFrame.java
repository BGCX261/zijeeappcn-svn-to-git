package wxz.test.frame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 4163607795054116755L;
    //    private JPanel pnlToolBars;
    private static final int width = 800;
    private static final int height = 600;
    private static final int width_pnl = 785;
    private static final int height_pnl_btn =30;
    private static final int height_pnl_data =450;
    private void init() {

    }

    public MainFrame() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu();
        menu.setText("File");

        JMenuItem file = new JMenuItem();
        JMenuItem help = new JMenuItem();

        file.setText("aa");
        help.setText("bb");

        menu.add(file);
        menu.add(help);

        menuBar.add(menu);
        setJMenuBar(menuBar);

        init();
        Container cont = getContentPane();
        cont.setLayout(null);
        
        JPanel pnlBtn = new JPanel();
//        pnlBtn.setBackground(Color.darkGray);
        pnlBtn.setBounds(0,0,width_pnl,height_pnl_btn);
//        pnlBtn.setBorder (BorderFactory.createLineBorder (Color.red, 3));
//        pnlBtn.setBorder (BorderFactory.createMatteBorder (1,5,1,1,Color.yellow));
        pnlBtn.setBorder (BorderFactory.createRaisedBevelBorder());
        
        JPanel pnlData = new JPanel();
//        pnlData.setBackground(Color.LIGHT_GRAY);
        pnlData.setBounds(0,40,width_pnl,height_pnl_data);
//        pnlData.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        pnlData.setBorder (BorderFactory.createTitledBorder ("标题"));
        
        cont.add(pnlBtn);
        cont.add(pnlData);
        
        setTitle("testg");
        setSize(width, height);
        // 居中
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        
         MainFrame frame = new MainFrame();
        // JPanel panel = new JPanel();
        // JTextArea textArea = new JTextArea();
        //
        // panel.setLayout(new GridLayout());
        // textArea.setText("test");
        // //当TextArea里的内容过长时生成滚动条
        // panel.add(new JScrollPane(textArea));
        // frame.add(panel);

        // frame.setSize(800,600);
        // frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         
        frame.setVisible(true);
    }
}
