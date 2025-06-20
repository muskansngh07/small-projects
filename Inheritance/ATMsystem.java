package Inheritance;
import java.util.*;
class bank
{
    double balance;
    bank(double balance)
    {
        this.balance=balance;
    }
    void withdrawal(double amount)
    {
        if(amount<=balance)
        {
            balance-=amount;
            System.out.println("Withrawal successful!\n");
        }
        else{
            System.out.println("Invalid Input! Enter aamount again.\n");
        }
    }
    void deposit(double amount)
    {
        if(amount>0)
        {
            balance+=amount;
            System.out.println("Deposited successfully!\n");
        }
        else{
            System.out.println("Invalid Input!Enter amount again.\n");
        }
    }
    double checkbalance()
    {
        return balance;
    }
}
class atmachine
{
    Scanner sc=new Scanner(System.in);
    bank obj=new bank(100000.0);
    void system()
    {
    while(true)
    {
        System.out.println("WELCOME TO THE ATM");
        System.out.println("1. Initial Balance");
        System.out.println("2. Withdrawal");
        System.out.println("3. Deposit");
        System.out.println("4. Check Balance");
        System.out.println("Enter your option: ");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println("Your initial balance is: "+obj.checkbalance());
                break;
            case 2:
                System.out.print("Enter the amount you want to withdraw: ");
                double amount=sc.nextDouble();
                obj.withdrawal(amount);
                break;
            case 3:
                System.out.println("Enter the amount to be deposited: ");
                double amount1=sc.nextDouble();
                obj.deposit(amount1);
                break;
            case 4:
                System.out.println("Your current balance is: "+obj.checkbalance());
                System.out.println("THANK YOU FOR USING THE ATM!");
                return;
            default: System.out.println("Invalid choice! Please enter again.");
        }
    }
  }
}
class ATMsystem
{
    public static void main(String args[])
    {
        atmachine x=new atmachine();
        x.system();
    }
}