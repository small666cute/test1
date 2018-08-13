package com.json;

import com.alibaba.fastjson.*;

import java.util.List;

public class TestFastjson {
    //json字符串-简单对象型
    private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String JSON_ARRAY_STR = "[{" +
            "\"studentName\":\"lily\"," +
            "\"studentAge\":12}," +
            "{\"studentName\":\"lucy\"," +
            "\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String COMPLEX_JSON_STR = "{" +
            "\"teacherName\":\"crystall\"," +
            "\"teacherAge\":27," +
            "\"course\":{" +
            "\"courseName\":\"english\"," +
            "\"code\":1270}," +
            "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    public static void strToJsonobject() {
        //JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR); //因为JSONObject继承了JSON，所以这样也是可以的

        System.out.println(jsonObject.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));

    }
    public static void testComplexJSONStrToJavaBean(){

        Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
        //Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});//因为JSONObject继承了JSON，所以这样也是可以的
        String teacherName = teacher.getTeacherName();
        Integer teacherAge = teacher.getTeacherAge();
        Course course = teacher.getCourse();
        List<Student> students = teacher.getStudents();
        System.out.println(teacherName+teacherAge+course.getCourseName()+course.getCode());
        for(Student st:students){
            System.out.println(st.getStudentName()+st.getStudentAge());
        }
    }
    public static void main(String[] args) {
        //strToJsonobject();
        testComplexJSONStrToJavaBean();
    }
}
class Student {

    private String studentName;
    private Integer studentAge;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }
}
class Course {

    private String courseName;
    private Integer code;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
class Teacher {

    private String teacherName;
    private Integer teacherAge;
    private Course course;
    private List<Student> students;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
