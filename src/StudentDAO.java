import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDAO {

    public static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "mysql");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public static int save(Student student) {
        int status = 0;
        try {
            Connection connection = StudentDAO.getCon();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Student(studentId, name, email) values (?,?,?)");
            preparedStatement.setInt(1,student.getStudentId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int delete(Student student) {
        int status = 0;
        try {
            Connection connection = StudentDAO.getCon();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from Student where studentId=?");
            preparedStatement.setInt(1, student.getStudentId());
            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int update(Student student) {
        int status = 0;
        try {
            Connection connection = StudentDAO.getCon();
            PreparedStatement preparedStatement = connection.prepareStatement("update Student set name = ?, email=? where studentId=?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getStudentId());
            status = preparedStatement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static List<Student> view() {
        List<Student> list = new ArrayList<Student>();
        try {
            Connection connection = StudentDAO.getCon();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from Student");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("studentId"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                list.add(student);
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static Student getStudentById(int studentId) {
        Student student = new Student();
        try {
            Connection connection = StudentDAO.getCon();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from Student where studentId=?");
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student.setStudentId(resultSet.getInt("studentId"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));

            }
            connection.close();
        }catch (Exception e){System.out.println(e);}
        return student;

    }


}

