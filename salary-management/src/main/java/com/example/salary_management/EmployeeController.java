package com.example.salarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Xem tất cả nhân viên
    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeeList";  // Trả về view employeeList.html
    }

    // Thêm nhân viên - form
    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";  // Tên của trang HTML chứa form thêm nhân viên
    }
    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee, Model model) {
        // Kiểm tra xem tên nhân viên đã tồn tại chưa
        if (employeeService.checkIfEmployeeExists(employee.getName())) {
            model.addAttribute("errorMessage", "Error while creating User: Unable to create. A User with name " + employee.getName() + " already exists.");
            return "addEmployee";  // Trả lại trang thêm nhân viên với thông báo lỗi
        }
        employeeService.addEmployee(employee);
        return "redirect:/employees";  // Quay lại danh sách nhân viên sau khi thêm
    }

}
    // Chỉnh sửa nhân viên
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "editEmployee";  // Trả về view editEmployee.html
    }

    @PostMapping("/edit")
    public String updateEmployee(@ModelAttribute Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";  // Chuyển hướng lại danh sách nhân viên
    }

    // Xóa nhân viên
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";  // Chuyển hướng lại danh sách nhân viên
    }

    // Tìm kiếm nhân viên
    @GetMapping("/search")
    public String searchEmployees(@RequestParam("name") String name, Model model) {
        model.addAttribute("employees", employeeService.searchEmployeesByName(name));
        return "employeeList";  // Trả về view employeeList.html
    }
}
