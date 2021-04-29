package com.soongjamm.startboot.godOfJava;

public class SalaryManager {
    public static void main(String[] args) {
        SalaryManager salaryManager = new SalaryManager();
        double salary = salaryManager.getMonthlySalary(30000000);
        System.out.println("salary : " + salary);
    }

    public double getMonthlySalary(int yearlySalary) {
        double monthSalary = yearlySalary / 12.0;
        double tax = calculateTax(monthSalary);
        double nationalPension = calculateNationalPension(monthSalary);
        double healthInsurance = calculateHealthInsurance(monthSalary);
        double sum = tax + nationalPension + healthInsurance;

        monthSalary -= sum;
        return monthSalary;
    }

    public double calculateTax(double monthSalary) {
        double tax = monthSalary * 0.125;
        System.out.println("tax : " + tax);
        return tax;
    }

    public double calculateNationalPension(double monthSalary) {
        double nationalPension = monthSalary * 0.081;
        System.out.println("national pension : " + nationalPension);
        return nationalPension;
    }

    public double calculateHealthInsurance(double monthSalary) {
        double healthInsurance = monthSalary * 0.135;
        System.out.println("health insurance : " + healthInsurance);
        return healthInsurance;
    }


}
