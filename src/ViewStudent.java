import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewStudent extends JFrame {
    static  ViewStudent frame;

    public ViewStudent(){
        List<Student> list=StudentDAO.view();
        int size = list.size();
        String data[][]=new String[size][3];
        int row=0;
        for (Student student:list){
            data[row][0]=String.valueOf(student.getStudentId());
            data[row][1]=student.getName();
            data[row][2]=student.getEmail();
            row++;

        }
        String columnNames[]={"StudentId","Name","Email"};
        JTable jTable = new JTable(data,columnNames);
        JScrollPane scrollPane=new JScrollPane(jTable);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,800,400);



    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    frame=new ViewStudent();
                    frame.setVisible(true);
                    frame.setTitle("Students Table");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}
