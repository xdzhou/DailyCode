package com.sky.designpattern.chainOfResponsibility;

public class Test {

    public static void main(String[] args) {
        GeneralManage generalManage = new GeneralManage();
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setSuccessHandle(generalManage);
        ProjectManager projectManager = new ProjectManager();
        projectManager.setSuccessHandle(departmentManager);

        projectManager.handleRequest("Loic", 300);
        projectManager.handleRequest("Loic", 800);
        projectManager.handleRequest("Loic", 1300);
        projectManager.handleRequest("Loic", 1800);
    }

}
