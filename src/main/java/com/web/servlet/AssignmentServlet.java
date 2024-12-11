package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Dormitory;
import com.pojo.PageBean;
import com.pojo.Room;
import com.pojo.Student;
import com.service.DormitoryService;
import com.service.RoomService;
import com.service.StudentService;
import com.service.impl.DormitoryServiceImpl;
import com.service.impl.RoomServiceImpl;
import com.service.impl.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/assignmentServlet/*")
public class AssignmentServlet extends BaseServlet {
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private RoomService roomService = new RoomServiceImpl();
    private StudentService studentService = new StudentServiceImpl();

    public void autoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        // 解析请求参数为 Student 对象
        Student newStudent = JSON.parseObject(params, Student.class);

        // 第一次事务：添加新学生
        addStudentTransaction(newStudent);

        // 第二次事务：更新新学生信息（依赖第一次事务提交的数据）
        updateStudentTransaction(newStudent);

        // 返回成功信息
        response.getWriter().write("success");
    }

    private void addStudentTransaction(Student newStudent) {
        // 添加新学生
        studentService.add(newStudent);
        System.out.println(newStudent);

    }

    private void updateStudentTransaction(Student newStudent) {
        // 获取所有学生信息（包括第一次事务提交的新学生）
        List<Student> students = studentService.selectAll();
        List<Dormitory> dormitories = dormitoryService.selectAll();
        List<Room> rooms = roomService.selectAll();
        // 统计每个房间的学生人数
        HashMap<String, Integer> roomCount = new HashMap<>();
        for (Student student : students) {
            roomCount.put(student.getRoomNumber(), roomCount.getOrDefault(student.getRoomNumber(), 0) + 1);
        }

        // 将人数超过4的房间设置为无效
        for (Student student : students) {
            if (roomCount.get(student.getRoomNumber()) > 4) {
                student.setGender(null);
            }
        }

        // 提交第一次事务完成的逻辑

        int max = 0;
        // 为新学生分配宿舍和房间
        for (Student student : students) {
            if (student.getGender().equals(newStudent.getGender()) && student.getMajor().equals(newStudent.getMajor()) && roomCount.get(student.getRoomNumber())<=4) {

                newStudent.setDormitoryName(student.getDormitoryName());
                newStudent.setRoomNumber(student.getRoomNumber());
                break;
            }
//            if(max < student.getStudentId())
//            {
//                max = student.getStudentId();
//            }
        }
        for (Student student : students) {
            if(max < student.getStudentId())
            {
                max = student.getStudentId();
            }
        }
        for(Dormitory dormitory : dormitories) {
            if(newStudent.getDormitoryName().equals(dormitory.getDormitoryName())) {
                newStudent.setDormitoryId(dormitory.getDormitoryId());
                break;
            }
        }
        for(Room room : rooms) {
            if(newStudent.getDormitoryName().equals(room.getDormitoryName()) && newStudent.getRoomNumber().equals(room.getRoomNumber())) {
                newStudent.setRoomId(room.getRoomId());
            }
        }
        newStudent.setStudentId(max);
        System.out.println(newStudent);
        // 更新新学生信息
        studentService.update(newStudent);


        // 提交第二次事务完成的逻辑
    }
}
