//package com.hohai.aocheng;
//
//
//import com.github.weisj.darklaf.LafManager;
//import com.github.weisj.darklaf.theme.DarculaTheme;
//
//import com.hohai.aocheng.excel.*;
//import com.hohai.aocheng.pojo.Data;
//import com.hohai.aocheng.pojo.Data2;
//import com.hohai.aocheng.pojo.Plan;
//import com.hohai.aocheng.service.Figservice;
//import com.hohai.aocheng.service.Pyservice;
//import com.hohai.aocheng.utils.MybatisUtils;
//import com.nari.slsd.hd.MessageBus.SingletonMsgBus;
//import com.nari.slsd.hd.common.*;
//import com.nari.slsd.hd.rights.PurvTeamdef;
//import com.nari.slsd.hd.user.UserInfoManager;
//import com.nari.slsd.hd.user.UserManager;
//import lombok.SneakyThrows;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.log4j.Logger;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.Vector;
//
//public class AcPanel3 extends JPanel implements IRunEntry, IStaticMsgCard {
//    @SuppressWarnings("unused")
//    private UUID address;
//    @SuppressWarnings("unused")
//    private String userName = " ";
//    Logger log= Logger.getLogger(AcPanel3.class);
//
//    int erro=1;
//    //界面1参数
//    String turbin="";
//    String sdate1="";
//    String sdate2="";
//    Date date1=new Date();
//    Date date2=new Date();
//    //界面2参数
//    String jizunum="";
//    String capall="";
//    String capdan="";
//    String zhenup="";
//    String zhendown="";
//    String flowysd="";
//    String flowysu="";
//    String ckfysd="";
//    String ckfysu="";
//    String kurongd="";
//    String kurongu="";
//    String maxitem="";
//    String calway="";
//
//
//
//    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH");
//    //表格
//    Vector headVector=new Vector<>();
//    Vector headVector2=new Vector<>();
//    Vector headVector3=new Vector<>();
//    Vector dataVector = new Vector();
//    Vector dataVector2 = new Vector();
//    Vector dataVector3 = new Vector();
//    int judgehead=0;
//    //背景
//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        Image image = null;
//        try {
//            image = ImageIO.read(new File("D:\\fuzaifenpei_resources\\images\\beijin5.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        g.drawImage(image, 0,0,this.getWidth(),this.getHeight(),this);
//    }
//
//
//    public AcPanel3() {
//
//        JPanel jPanel1 = new JPanel();
//        jPanel1.setOpaque(false);
//
//        headVector.add("时间");
//        headVector.add("发电量(kw.h)");
//        headVector.add("出库流量(m³/h)");
//        headVector.add("入库径流预报(m³/h)");
//        headVector.add("入库径流实际(m³/h)");
//
//        headVector2.add("  ");
//        headVector2.add("耗水量");
//        headVector2.add("机组1");
//        headVector2.add("机组2");
//        headVector2.add("机组3");
//        headVector2.add("机组4");
//        headVector2.add("机组5");
//        headVector2.add("机组6");
//
//        headVector3.add("  ");
//        headVector3.add("初始偏差");
//        headVector3.add("机组1");
//        headVector3.add("机组2");
//        headVector3.add("机组3");
//        headVector3.add("机组4");
//        headVector3.add("出力再调整");
//
//
//
//
//
////加载图片
//        ImageIcon bejinicon1=new ImageIcon("D:\\fuzaifenpei_resources\\images\\beijin2.png");
//        bejinicon1.setImage(bejinicon1.getImage().getScaledInstance(1500,55,Image.SCALE_DEFAULT));
//
//        //将图片放入label中
//        JLabel bejinlabel1=new JLabel(bejinicon1);
//
//        //设置label的大小
//        bejinlabel1.setBounds(0,0,1500,55);
//
//        JPanel jp0=new JPanel();
//        jp0.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jp0.setOpaque(false);
//        JPanel jp1=new JPanel();
//        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
//        JPanel jjp1=new JPanel();
//        jjp1.setPreferredSize(new Dimension(1700,120));
//        jjp1.setOpaque(false);
//        jp0.add(bejinlabel1);
//        JPanel jp2=new JPanel();
//        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
//
////        ImageIcon jbic1=new ImageIcon("D:\\fuzaifenpei_resources\\images\\jb1.png");
//
//        JButton jb1=new JButton("数据读取");
//        JButton jb2=new JButton("参数设置");
//
//
//        JButton jb3=new JButton("结果展示");
//        jb1.setPreferredSize(new Dimension(140,40));
//        jb2.setPreferredSize(new Dimension(140,40));
//        jb3.setPreferredSize(new Dimension(140,40));
//        jb1.setBorder(BorderFactory.createRaisedBevelBorder());
//        jb2.setBorder(BorderFactory.createRaisedBevelBorder());
//        jb3.setBorder(BorderFactory.createRaisedBevelBorder());
//
//        jb1.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        jb2.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        jb3.setFont(new Font("微软雅黑", Font.BOLD, 20));
//
//
//
//
//        JComboBox jComboBox1 = new JComboBox();
//        jComboBox1.setPreferredSize(new Dimension(240,40));
//        jComboBox1.setFont(new Font("微软雅黑", Font.BOLD, 20));
//
//        jComboBox1.addItem("二滩水电站");
//        jComboBox1.addItem("锦西水电站");
//        jComboBox1.addItem("锦东水电站");
//        jComboBox1.addItem("官地水电站");
//        jComboBox1.addItem("桐子林水电站");
//
////图片读取展示
//        JLabel imagelable=new JLabel();
//        ImageIcon imageIcon=new ImageIcon("D:\\fuzaifenpei_resources\\images\\img.png");
//        imageIcon.setImage(imageIcon.getImage().getScaledInstance(650,500,Image.SCALE_DEFAULT));
//        imagelable.setIcon(imageIcon);
//
//
//        //表1
//        DefaultTableModel jtmodel= new DefaultTableModel();
//
//        JTable jt = new JTable();
//        jt.setModel(jtmodel);
//        jt.setEnabled(false);
//        // 设置表头
//        jt.getTableHeader().setFont(new Font(null, Font.BOLD, 16));  // 设置表头名称字体样式
//        jt.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        jt.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
//        jt.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
//        // 设置表格内容颜色
//        jt.setForeground(Color.WHITE);                   // 字体颜色
//        jt.setFont(new Font(null, Font.PLAIN, 17));      // 字体样式
//        jt.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
//        jt.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
//        jt.setGridColor(Color.GRAY);                     // 网格颜色
//
//
//
//
//
//
//        JLabel label1=new JLabel("                            ");
//        JLabel label2=new JLabel("       ");
//        JLabel label3=new JLabel("       ");
//
//
//        jjp1.add(jp0,BorderLayout.NORTH);
//        jjp1.add(jp1,BorderLayout.CENTER);
//        jp1.add(jComboBox1);
//        jp1.add(label1);
//        jp1.add(jb1);
//        jp1.add(label2);
//        jp1.add(jb2);
//        jp1.add(label3);
//        jp1.add(jb3);
//
//
////界面1下半区
//        JPanel jPanel2=new JPanel();
//        jPanel2.setPreferredSize(new Dimension(1700,1000));
//        JPanel jp3=new JPanel();
//        JPanel jp4=new JPanel();
//        JPanel jp5=new JPanel();
//        jp2.setOpaque(false);
//        jp3.setOpaque(false);
//        jp4.setOpaque(false);
//        jp5.setOpaque(false);
//        jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//
//        JLabel imagelable2=new JLabel();
//        ImageIcon imageIcon2=new ImageIcon("D:\\fuzaifenpei_resources\\images\\JL1.png");
////        imageIcon2.setImage(imageIcon.getImage().getScaledInstance(800,500,Image.SCALE_DEFAULT));
//        imagelable2.setIcon(imageIcon2);
//
//        JTextField jTextField1=new JTextField(14);
//        JTextField jTextField2=new JTextField(14);
//
//        jTextField1.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        jTextField1.setPreferredSize(new Dimension(140,40));
//        jTextField2.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        jTextField2.setPreferredSize(new Dimension(140,40));
//
//        JLabel label4=new JLabel("开始时间");
//        JLabel label5=new JLabel("————————————");
//        JLabel label6=new JLabel("终止时间");
//
//        JLabel label7=new JLabel("              ");
//        JLabel label8=new JLabel("              ");
//        JLabel label9=new JLabel("              ");
//
//        label4.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        label5.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        label6.setFont(new Font("微软雅黑", Font.BOLD, 20));
//
//        JButton tbutton1 = new DateSelector();
//        tbutton1.setPreferredSize(new Dimension(220,40));
//        tbutton1.setBorder(BorderFactory.createRaisedBevelBorder());
//        tbutton1.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        JButton tbutton2 = new DateSelector();
//        tbutton2.setPreferredSize(new Dimension(220,40));
//        tbutton2.setBorder(BorderFactory.createRaisedBevelBorder());
//        tbutton2.setFont(new Font("微软雅黑", Font.BOLD, 20));
//        JButton jb4=new JButton("读取");
//        jb4.setPreferredSize(new Dimension(140,40));
//        jb4.setBorder(BorderFactory.createRaisedBevelBorder());
//        jb4.setFont(new Font("微软雅黑", Font.BOLD, 20));
//
//        jp4.setPreferredSize(new Dimension(300,80));
//        jp4.add(label4);
//        jp4.add(tbutton1);
//
//        jp5.setPreferredSize(new Dimension(300,80));
//        jp5.add(label6);
//        jp5.add(tbutton2);
//        jPanel2.add(jp3,BorderLayout.NORTH);
//        jp3.add(imagelable2);
//        jp3.add(label7);
//        jp3.add(jp4);
//        jp3.add(label5);
//        jp3.add(jp5);
//        jp3.add(label8);
//        jp3.add(jb4);
//        jPanel2.add(jp3,BorderLayout.NORTH);
//        JScrollPane jsp = new JScrollPane(jt);
//        jsp.setPreferredSize(new Dimension(800,500));
//
//        jp2.add(imagelable,BorderLayout.WEST);
//        jp2.add(jsp,BorderLayout.EAST);
//
//        //界面二下半区
//        JPanel jPanel3=new JPanel();
//        jPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel3.setOpaque(false);
//
//        //label
//        JLabel label21=new JLabel("机组台数     ");
//        JLabel label22=new JLabel("总装机容量    ");
//        JLabel label23=new JLabel("MW");
//        JLabel label24=new JLabel("单机容量");
//        JLabel label25=new JLabel("MW");
//        JLabel label26=new JLabel("震动区下限");
//        JLabel label27=new JLabel("MW");
//        JLabel label28=new JLabel("震动区上限");
//        JLabel label29=new JLabel("MW");
//        JLabel label210=new JLabel("发电流量约束");
//        JLabel label211=new JLabel("——————");
//        JLabel label212=new JLabel("m³/s");
//        JLabel label214=new JLabel("出库流量约束");
//        JLabel label215=new JLabel("——————");
//        JLabel label216=new JLabel("m³/s");
//        JLabel label217=new JLabel("库容约束");
//        JLabel label218=new JLabel("——————");
//        JLabel label219=new JLabel("亿m³/s");
//        JLabel label220=new JLabel("迭代次数");
//        JLabel label221=new JLabel("                                   ");
//        JLabel label222=new JLabel("                                ");
//        JLabel label223=new JLabel("                                                       ");
//        JLabel label224=new JLabel("                                                       ");
//        JLabel label225=new JLabel("                                                              ");
//        JLabel label226=new JLabel("                                                                      ");
//        JLabel label227=new JLabel("计算方法     ");
//        JLabel label228=new JLabel("                                                                                                                                ");
//
//        label21.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label22.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label23.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label24.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label25.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label26.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label27.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label28.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label29.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label210.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label211.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label212.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label214.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label215.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label216.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label217.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label218.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label219.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label220.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label221.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label222.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        label227.setFont(new Font("微软雅黑", Font.BOLD, 24));
//
//        //输入框
//        JTextField tf21=new JTextField(5);
//        tf21.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf21.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf22=new JTextField(5);
//        tf22.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf22.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf23=new JTextField(29);
//        tf23.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf23.setPreferredSize(new Dimension(800,40));
//
//
//
//
//        JTextField tf24=new JTextField(28);
//        tf24.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf24.setPreferredSize(new Dimension(800,40));
//
//        JTextField tf25=new JTextField(28);
//        tf25.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf25.setPreferredSize(new Dimension(800,40));
//
//        tf23.setText("    多个数字以英文逗号“,”隔开");
//        tf23.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton()==MouseEvent.BUTTON1){
//                    tf23.setText("");
//                }
//            }
//        });
//
//        tf24.setText("    多个数字以英文逗号“,”隔开");
//        tf24.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton()==MouseEvent.BUTTON1){
//                    tf24.setText("");
//                }
//            }
//        });
//
//        tf25.setText("    多个数字以英文逗号“,”隔开");
//        tf25.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton()==MouseEvent.BUTTON1){
//                    tf25.setText("");
//                }
//            }
//        });
//        JTextField tf26=new JTextField(5);
//        tf26.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf26.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf27=new JTextField(5);
//        tf27.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf27.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf28=new JTextField(5);
//        tf28.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf28.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf210=new JTextField(5);
//        tf210.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf210.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf211=new JTextField(5);
//        tf211.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf211.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf212=new JTextField(5);
//        tf212.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf212.setPreferredSize(new Dimension(500,40));
//
//        JTextField tf213=new JTextField(5);
//        tf213.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf213.setPreferredSize(new Dimension(500,40));
//
//
//        JComboBox tf214=new JComboBox();
//        tf214.addItem("动态规划计算负荷分配");
//        tf214.addItem("深度强化学习计算偏差量");
//        tf214.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        tf214.setPreferredSize(new Dimension(500,40));
//
//
//        //button
//        JButton jButton21=new JButton("开始计算");
//        jButton21.setPreferredSize(new Dimension(140,40));
//        jButton21.setBorder(BorderFactory.createRaisedBevelBorder());
//        jButton21.setFont(new Font("微软雅黑", Font.BOLD, 20));
//
//        //panel
//        JPanel p0=new JPanel();
//        p0.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p0.setPreferredSize(new Dimension(1450,20));
//
//        JPanel p1=new JPanel();
//        p1.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p1.setPreferredSize(new Dimension(1450,60));
//
//
//        JPanel p2=new JPanel();
//        p2.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p2.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p3=new JPanel();
//        p3.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p3.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p4=new JPanel();
//        p4.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p4.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p5=new JPanel();
//        p5.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p5.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p6=new JPanel();
//        p6.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p6.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p7=new JPanel();
//        p7.setLayout(new FlowLayout(FlowLayout.CENTER));
//        p7.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p8=new JPanel();
//        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
//        p8.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p9=new JPanel();
//        p9.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        p9.setPreferredSize(new Dimension(1450,60));
//
//        JPanel p10=new JPanel();
//        p10.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        p10.setPreferredSize(new Dimension(1450,60));
//
//        p0.setOpaque(false);
//        p1.setOpaque(false);
//        p2.setOpaque(false);
//        p3.setOpaque(false);
//        p4.setOpaque(false);
//        p5.setOpaque(false);
//        p6.setOpaque(false);
//        p7.setOpaque(false);
//        p8.setOpaque(false);
//        p9.setOpaque(false);
//        p10.setOpaque(false);
//
//        Box Box1=Box.createVerticalBox();
//        Box1.setOpaque(false);
//
//        p10.add(label227);
//        p10.add(tf214);
//        p10.add(label228);
//        p1.add(label21);
//        p1.add(tf21);
//        p1.add(label221);
//        p1.add(label22);
//        p1.add(tf22);
//        p1.add(label23);
//
//        p2.add(label24);
//        p2.add(tf23);
//        p2.add(label25);
//
//        p3.add(label26);
//        p3.add(tf24);
//        p3.add(label27);
//
//        p4.add(label28);
//        p4.add(tf25);
//        p4.add(label29);
//
//        p5.add(label210);
//        p5.add(tf26);
//        p5.add(label211);
//        p5.add(tf27);
//        p5.add(label212);
//        p5.add(label223);
//
//
//        p6.add(label214);
//        p6.add(tf28);
//        p6.add(label215);
//        p6.add(tf210);
//        p6.add(label216);
//        p6.add(label224);
//
//        p7.add(label217);
//        p7.add(tf211);
//        p7.add(label218);
//        p7.add(tf212);
//        p7.add(label219);
//        p7.add(label225);
//
//        p8.add(label226);
//        p8.add(label220);
//        p8.add(tf213);
//
//
//
//        p9.add(jButton21);
//        p9.add(label222);
//
//        Box1.add(p0);
//        Box1.add(p10);
//        Box1.add(p1);
//        Box1.add(p2);
//        Box1.add(p3);
//        Box1.add(p4);
//        Box1.add(p5);
//        Box1.add(p6);
//        Box1.add(p7);
//        Box1.add(p8);
//        Box1.add(p9);
//        jPanel3.add(Box1);
//
////界面3
//        JPanel jPanel4=new JPanel();
//        jPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel4.setPreferredSize(new Dimension(1700,1000));
//
//        JPanel jPanel4_=new JPanel();
//        jPanel4_.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel4_.setPreferredSize(new Dimension(1700,110));
//
//        JPanel jPanel4_1=new JPanel();
//        jPanel4_1.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel4_1.setPreferredSize(new Dimension(1700,10));
//
//        JPanel jPanel4_2=new JPanel();
//        jPanel4_2.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel4_2.setPreferredSize(new Dimension(1700,90));
//
//        JPanel jPanel4_3=new JPanel();
//        jPanel4_3.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel4_3.setPreferredSize(new Dimension(1700,10));
//        jPanel4.setOpaque(false);
//        jPanel4_.setOpaque(false);
//        jPanel4_1.setOpaque(false);
//        jPanel4_2.setOpaque(false);
//        jPanel4_3.setOpaque(false);
//
//        //jPanel4_2  上方信息提示1
//        JLabel j4imagelable1=new JLabel();
//        ImageIcon j4imageIcon1=new ImageIcon("D:\\fuzaifenpei_resources\\images\\beijin_jp4_1.png");
//        j4imagelable1.setIcon(j4imageIcon1);
//
//        JLabel j4label11=new JLabel("发电量平均提高   ");
//        JLabel j4label12=new JLabel("%");
//        JLabel j4label13=new JLabel("穿越振动区次数   ");
//        JLabel j4label14=new JLabel("次");
//        JLabel j4label15=new JLabel("                                  ");
//        JLabel j4label16=new JLabel("                                  ");
//
//        j4label11.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label12.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label13.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label14.setFont(new Font("微软雅黑", Font.BOLD, 24));
//
//        JTextField j4tf11=new JTextField(5);
//        j4tf11.setHorizontalAlignment(JTextField.CENTER);
//        j4tf11.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf11.setPreferredSize(new Dimension(500,40));
//        j4tf11.setEditable(false);
//
//        JTextField j4tf12=new JTextField(5);
//        j4tf12.setHorizontalAlignment(JTextField.CENTER);
//        j4tf12.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf12.setPreferredSize(new Dimension(500,40));
//        j4tf12.setEditable(false);
//
//
//        //jPanel4_2  上方信息提示2
//        JLabel j4imagelable2=new JLabel();
//        ImageIcon j4imageIcon2=new ImageIcon("D:\\fuzaifenpei_resources\\images\\beijin_jp4_2.png");
//        j4imagelable2.setIcon(j4imageIcon2);
//
//        Box box2=Box.createHorizontalBox();
//        box2.setOpaque(false);
//
//        JPanel j4jp0=new JPanel();
//        j4jp0.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp0.setPreferredSize(new Dimension(250,80));
//
//        JPanel j4jp1=new JPanel();
//        j4jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp1.setPreferredSize(new Dimension(200,80));
//
//        JPanel j4jp2=new JPanel();
//        j4jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp2.setPreferredSize(new Dimension(200,80));
//
//        JPanel j4jp3=new JPanel();
//        j4jp3.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp3.setPreferredSize(new Dimension(250,80));
//
//        JPanel j4jp4=new JPanel();
//        j4jp4.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp4.setPreferredSize(new Dimension(200,80));
//
//        JPanel j4jp5=new JPanel();
//        j4jp5.setLayout(new FlowLayout(FlowLayout.CENTER));
//        j4jp5.setPreferredSize(new Dimension(200,80));
//
//        j4jp0.setOpaque(false);
//        j4jp1.setOpaque(false);
//        j4jp2.setOpaque(false);
//        j4jp3.setOpaque(false);
//        j4jp4.setOpaque(false);
//        j4jp5.setOpaque(false);
//
//        JLabel j4label21=new JLabel("步长因子");
//        JLabel j4label22=new JLabel("折扣因子");
//        JLabel j4label23=new JLabel("经验记忆池容量");
//        JLabel j4label24=new JLabel("训练频次");
//        JLabel j4label25=new JLabel("训练批次数");
//
//        j4label21.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label22.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label23.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label24.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4label25.setFont(new Font("微软雅黑", Font.BOLD, 24));
//
//        JTextField j4tf21=new JTextField(5);
//        j4tf21.setHorizontalAlignment(JTextField.CENTER);
//        j4tf21.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf21.setPreferredSize(new Dimension(150,35));
//        j4tf21.setEditable(false);
//
//        JTextField j4tf22=new JTextField(5);
//        j4tf22.setHorizontalAlignment(JTextField.CENTER);
//        j4tf22.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf22.setPreferredSize(new Dimension(150,35));
//        j4tf22.setEditable(false);
//
//        JTextField j4tf23=new JTextField(8);
//        j4tf23.setHorizontalAlignment(JTextField.CENTER);
//        j4tf23.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf23.setPreferredSize(new Dimension(200,35));
//        j4tf23.setEditable(false);
//
//        JTextField j4tf24=new JTextField(5);
//        j4tf24.setHorizontalAlignment(JTextField.CENTER);
//        j4tf24.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf24.setPreferredSize(new Dimension(150,35));
//        j4tf24.setEditable(false);
//
//        JTextField j4tf25=new JTextField(5);
//        j4tf25.setHorizontalAlignment(JTextField.CENTER);
//        j4tf25.setFont(new Font("微软雅黑", Font.BOLD, 24));
//        j4tf25.setPreferredSize(new Dimension(150,35));
//        j4tf25.setEditable(false);
//
//        j4tf21.setText("0.01");
//        j4tf22.setText("0.95");
//        j4tf23.setText("20000");
//        j4tf24.setText("5");
//        j4tf25.setText("32");
//
//        j4jp0.add(j4imagelable2);
//
//        j4jp1.add(j4label21,BorderLayout.NORTH);
//        j4jp1.add(j4tf21,BorderLayout.SOUTH);
//
//        j4jp2.add(j4label22,BorderLayout.NORTH);
//        j4jp2.add(j4tf22,BorderLayout.SOUTH);
//
//        j4jp3.add(j4label23,BorderLayout.NORTH);
//        j4jp3.add(j4tf23,BorderLayout.SOUTH);
//
//        j4jp4.add(j4label24,BorderLayout.NORTH);
//        j4jp4.add(j4tf24,BorderLayout.SOUTH);
//
//        j4jp5.add(j4label25,BorderLayout.NORTH);
//        j4jp5.add(j4tf25,BorderLayout.SOUTH);
//
//        box2.add(j4jp0);
//        box2.add(j4jp1);
//        box2.add(j4jp2);
//        box2.add(j4jp3);
//        box2.add(j4jp4);
//        box2.add(j4jp5);
//
//        //右侧表格2
//
//        DefaultTableModel jtmodel2= new DefaultTableModel();
//
//        JTable jt2 = new JTable();
//        jt2.setModel(jtmodel2);
//        jt2.setEnabled(false);
//        // 设置表头
//        jt2.getTableHeader().setFont(new Font(null, Font.BOLD, 16));  // 设置表头名称字体样式
//        jt2.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
//        jt2.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
//        jt2.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
//        // 设置表格内容颜色
//        jt2.setForeground(Color.WHITE);                   // 字体颜色
//        jt2.setFont(new Font(null, Font.PLAIN, 17));      // 字体样式
//        jt2.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
//        jt2.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
//        jt2.setGridColor(Color.GRAY);
//
//
//
//
//
//        //主界面
//        jPanel1.setLayout(new BorderLayout());
////        jPanel1.setPreferredSize(new Dimension(1600,1200));
//        jp1.setOpaque(false);
//        jPanel2.setOpaque(false);
//        jPanel1.add(jjp1,BorderLayout.NORTH);
//
//
////        jPanel1.add(jp2,BorderLayout.CENTER);
//
//
//
//        this.setPreferredSize(new Dimension(1450,800));
//        this.setLayout(new BorderLayout());
//
//        this.add(jPanel1,BorderLayout.NORTH);
//        this.setOpaque(false);
//
//
//
//
//        //界面1
//        jb1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//
//                jPanel1.removeAll();
//                jPanel1.add(jjp1,BorderLayout.NORTH);
//                jPanel1.add(jPanel2,BorderLayout.CENTER);
//                jPanel1.updateUI();
//
//
//                //查询
//                jb4.addActionListener(new ActionListener() {
//                    @SneakyThrows
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        dataVector.clear();
//                        sdate1 = tbutton1.getText();
//                        sdate2 = tbutton2.getText();
//
//
//                        PlanExcelimpl planMapper=new PlaneExcel();
//                        if (sdate1!=null&&sdate2!=null){
//                            date1 = simpleDateFormat.parse(sdate1);
//                            date2 = simpleDateFormat.parse(sdate2);
//                        }
//                        else {
//                            JOptionPane.showMessageDialog(null, "请输入日期", "提示", JOptionPane.INFORMATION_MESSAGE);
//                            return;
//                        }
//
//                        int selectedIndex = jComboBox1.getSelectedIndex();
//                        List<Plan> plans = planMapper.getdatabyiddate(selectedIndex,date1,date2);
//
//                        for (Plan datat : plans) {
//                            Vector row=new Vector();
//                            if (datat.getTime()!=null){
//                                row.add(simpleDateFormat.format(datat.getTime()));
//                            }
//                            row.add(datat.getPower());
//                            row.add(datat.getOut());
//                            row.add(datat.getFin());
//                            row.add(datat.getRin());
//
//                            dataVector.add(row);
//                        }
//                        jtmodel.setDataVector(dataVector,headVector);
//                        jt.setRowHeight(30);
//                        //列宽度
//                        jt.getColumnModel().getColumn(0).setPreferredWidth(60);
//                        jt.getColumnModel().getColumn(1).setPreferredWidth(60);
//                        jt.getColumnModel().getColumn(2).setPreferredWidth(105);
//                        jt.getColumnModel().getColumn(3).setPreferredWidth(105);
//                        jt.getColumnModel().getColumn(4).setPreferredWidth(105);
////                        jt.setPreferredSize(new Dimension(650,500));
//                        jPanel2.add(jp2,BorderLayout.CENTER);
//                        jPanel2.updateUI();
//                    }
//                });
//
//            }
//        });
//
//        //界面2
//        jb2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
////                jb2.setForeground(Color.BLACK);
////                jb2.setBackground(Color.ORANGE);
////                jb1.setBackground(Color.GRAY);
//                jPanel1.removeAll();
//                jPanel1.add(jjp1,BorderLayout.NORTH);
//                jPanel1.add(jPanel3,BorderLayout.CENTER);
//                jPanel1.updateUI();
//
//
//
//
//                //开始计算
//                jButton21.addActionListener(new ActionListener() {
//                    @SneakyThrows
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        turbin=jComboBox1.getSelectedItem().toString();
//                        jizunum=tf21.getText();
//                        capall=tf22.getText();
//                        capdan=tf23.getText();
//                        zhendown=tf25.getText();
//                        zhenup=tf24.getText();
//                        flowysd=tf26.getText();
//                        flowysu=tf27.getText();
//                        ckfysd=tf28.getText();
//                        ckfysu=tf210.getText();
//                        kurongd=tf211.getText();
//                        kurongu=tf212.getText();
//                        maxitem=tf213.getText();
//                        calway=tf214.getSelectedItem().toString();
//
//                        //运行py程序
//                        Pyservice pyservice=new Pyservice();
//                        String runs= "111";
//                        runs= pyservice.run(turbin, sdate1, sdate2, jizunum, capall, capdan, zhenup,
//                                zhendown, flowysd, flowysu, ckfysd, ckfysu, kurongd, kurongu, maxitem,calway);
//                        System.out.println("结束");
//
//                        //加载表格数据
//
//
//                        DataExcelimpl dataMapper=new DataExcel();
//                        List<Data> dataList = dataMapper.getalldata();
//
//                        FigExcelimpl figMapper=new FigExcel();
//                        String imgs = figMapper.getfigbyid("111");
//
//                        Data2Excelimpl data2Mapper=new Data2Excel();
//                        List<Data2> data2List = data2Mapper.getalldata();
//
//
//                        for (Data data : dataList) {
//                            Vector row=new Vector();
//                            row.add(data.getId());
//                            row.add(data.getFitness());
//                            row.add(data.getJizu1());
//                            row.add(data.getJizu2());
//                            row.add(data.getJizu3());
//                            row.add(data.getJizu4());
//                            row.add(data.getJizu5());
//                            row.add(data.getJizu6());
//                            dataVector2.add(row);
//                        }
//
//                        for (Data2 data : data2List) {
//                            Vector row=new Vector();
//                            row.add(data.getId());
//                            row.add(data.getChushi());
//                            row.add(data.getJizu1());
//                            row.add(data.getJizu2());
//                            row.add(data.getJizu3());
//                            row.add(data.getJizu4());
//                            row.add(data.getPagain());
//                            dataVector3.add(row);
//                        }
//
//
//                        Figservice.GenerateImage(imgs,"D:\\fuzaifenpei_resources\\images\\fenpei.jpg");
//
////                        if ("111".equals(runs)){
//////                            JOptionPane.showMessageDialog(null, "计算完成，请到结果展示界面查看", "提示", JOptionPane.INFORMATION_MESSAGE);
////                            erro=0;
////                        }
////                        else {
////
//////                            JOptionPane.showMessageDialog(null,
//////                                    "计算出现异常！请检查输入参数！", "提示", JOptionPane.INFORMATION_MESSAGE);
////                            erro=1;
////                        }
//
//
//                    }
//                });
//
//            }
//        });
//
//
//
//        //界面3
//        jb3.addActionListener(new ActionListener() {
//            @SneakyThrows
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//
//                erro=0;
//                if (erro==0){
//                    //左侧图片
//                    JLabel imagelable3=new JLabel();
//                    ImageIcon imageIcon3=new ImageIcon("D:\\fuzaifenpei_resources\\images\\fenpei.jpg");
//                    imageIcon3.setImage(imageIcon3.getImage().getScaledInstance(650,550,Image.SCALE_DEFAULT));
//                    imagelable3.setIcon(imageIcon3);
//
//                    JScrollPane jsp2 = new JScrollPane(jt2);
//                    jsp2.setPreferredSize(new Dimension(650,550));
//
//                    jPanel4_2.removeAll();
//                    //方法判断
//                    if ("动态规划计算负荷分配".equals(calway)){
//
//                        FigExcelimpl figMapper = new FigExcel();
//                        String t2 = figMapper.getfigbyid("222");
//                        String t1 = figMapper.getfigbyid("333");
//                        j4tf11.setText(t1);
//                        j4tf12.setText(t2);
//                        jtmodel2.setDataVector(dataVector2,headVector2);
//                        jt2.setRowHeight(30);
//                        //列宽度
//                        jt2.getColumnModel().getColumn(0).setPreferredWidth(30);
//                        jt2.getColumnModel().getColumn(1).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(2).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(3).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(4).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(5).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(6).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(7).setPreferredWidth(60);
//                        jPanel4_2.add(j4imagelable1);
//                        jPanel4_2.add(j4label15);
//                        jPanel4_2.add(j4label11);
//                        jPanel4_2.add(j4tf11);
//                        jPanel4_2.add(j4label12);
//                        jPanel4_2.add(j4label16);
//                        jPanel4_2.add(j4label13);
//                        jPanel4_2.add(j4tf12);
//                        jPanel4_2.add(j4label14);
//                    }
//                    else if("深度强化学习计算偏差量".equals(calway)){
//                        jtmodel2.setDataVector(dataVector3,headVector3);
//                        jt2.setRowHeight(30);
//                        //列宽度
//                        jt2.getColumnModel().getColumn(0).setPreferredWidth(30);
//                        jt2.getColumnModel().getColumn(1).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(2).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(3).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(4).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(5).setPreferredWidth(60);
//                        jt2.getColumnModel().getColumn(6).setPreferredWidth(60);
//                        jPanel4_2.add(box2);
//                    }
//                    jPanel4.removeAll();
//                    jPanel4_.removeAll();
//
//                    jPanel4_.add(jPanel4_1,BorderLayout.NORTH);
//                    jPanel4_.add(jPanel4_2,BorderLayout.CENTER);
//                    jPanel4_.add(jPanel4_3,BorderLayout.SOUTH);
//                    jPanel4.add(jPanel4_,BorderLayout.NORTH);
//                    jPanel4.add(imagelable3,BorderLayout.WEST);
//                    jPanel4.add(jsp2,BorderLayout.EAST);
//                    jPanel1.removeAll();
//                    jPanel1.add(jjp1,BorderLayout.NORTH);
//                    jPanel1.add(jPanel4,BorderLayout.CENTER);
//                    jPanel1.updateUI();
//                }
//                else {
//                    JOptionPane.showMessageDialog(null,
//                            "未得到有效计算结果，请先进行计算！", "提示", JOptionPane.INFORMATION_MESSAGE);
//                }
//
//
//            }
//        });
//
//
//
//
//
//
//
//    }
//
//    public static void main(String[] args) {
////        try {
////            UIManager.setLookAndFeel(LittleLuckLookAndFeel.class.getName());
////        } catch (UnsupportedLookAndFeelException e) {
////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
//
//        try
//        {
////            UIManager.put("RootPane.setupButtonVisible",false);
////            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            // Specify explicit theme.
//            LafManager.setTheme(new DarculaTheme());
//            LafManager.install();
//        }
//        catch(Exception e)
//        {
//            //TODO exception
//        }
//
//        AcPanel3 panel1=new AcPanel3();
//        panel1.setOpaque(false);
//
//
//
//        JFrame frame= new JFrame("水电站机组组合与负荷分配优化运行");
//
////        ImageIcon icon = new ImageIcon("D:\\fuzaifenpei_resources\\images\\beijin1.png");
////
////        JLabel beijinlabel=new JLabel(icon);
////
////        frame.getLayeredPane().add(beijinlabel,new Integer(Integer.MIN_VALUE));
//
////        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
//
//        JPanel panelTop=(JPanel)frame.getContentPane();
//        panelTop.setOpaque(false);
//
//
//        frame.getContentPane().add(panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        // 设置窗口的其他参数，如窗口大小
//        frame.setSize(screenSize);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
//
//
//
//
//
//    @Override
//    public Object invoke(Container container, FuncActionItem funcActionItem) {
//        userName = UserManager.LoginUser.getUsername();
//        UserInfoManager manager = UserManager.getUserInfoManager();
//        PurvTeamdef teamdef = manager.getCurTeam();
//        String teamName = teamdef.getName();
//        /*
//         * if(userName != null &&userName.length() > 0){ doc.setUserName(userName); } if
//         * (teamName != null && teamName.length() > 0) { doc.setFunitName(teamName); }
//         */
//        plugBus();
//        return this;
//
//    }
//
//    @Override
//    public String getTitle() {
//        return null;
//    }
//
//    @Override
//    public IStaticMsgCard getPanelMessageCard() {
//        return null;
//    }
//
//    @Override
//    public UUID getAddr() {
//        return null;
//    }
//
//    @Override
//    public void setAddr(UUID uuid) {
//
//    }
//
//    @Override
//    public int getGroup() {
//        return 0;
//    }
//
//    @Override
//    public int getUsage() {
//        return 0;
//    }
//
//    @Override
//    public Object receiveMessage(IStaticMsgCard iStaticMsgCard, MessageBody messageBody) {
//        return null;
//    }
//    private void plugBus() {
//        IStaticMsgBus iBus = SingletonMsgBus.getMessageBus();
//        this.address = iBus.Register(this);
//    }
//}
//
//
//
