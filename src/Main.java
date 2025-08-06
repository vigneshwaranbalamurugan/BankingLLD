
import models.*;
import repository.*;
import services.*;
import abstractions.*;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Admin admin = Admin.getInstance();1
        UserRepository userRepo = new UserRepository();
        AccountRepository accRepo = new AccountRepository();
        TransactionRepository txRepo = new TransactionRepository();

        IAuthService authService = new AuthService(admin, userRepo);
        IBankService bankService = new BankService(userRepo, accRepo,txRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Admin Login\n2. User Login\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Admin Username: ");
                    String auser = sc.nextLine();
                    System.out.print("Admin Password: ");
                    String apass = sc.nextLine();
                    if (authService.loginAsAdmin(auser, apass)) {
                        System.out.println("Admin logged in.");
                        boolean isuexists=false;
                        String uname="";
                        while(!isuexists) {
                            System.out.print("Enter new user's username: ");
                            uname = sc.nextLine();
                            if(userRepo.exists(uname))
                            {
                                System.out.println("Username already exists..!");
                            }else{
                                isuexists=true;
                            }
                        }
                        System.out.print("Password: ");
                        String upass = sc.nextLine();
                        System.out.print("Full Name: ");
                        String fname = sc.nextLine();
                        String accNum = UUID.randomUUID().toString();
                        Account acc = new Account(accNum, 0.0);
                        User user = new User(uname, upass, fname, acc);
                        bankService.registerUser(user);
                        System.out.println("User registered successfully.");
                    } else {
                        System.out.println("Invalid Admin Credentials.");
                    }
                }

                case 2 -> {
                    System.out.print("Username: ");
                    String uname = sc.nextLine();
                    System.out.print("Password: ");
                    String upass = sc.nextLine();
                    if (authService.loginAsUser(uname, upass)) {
                        System.out.println("User logged in.");
                        while (true) {
                            System.out.println("1. Deposit\n2. Withdraw\n3. Balance\n4. Transactions\n5. Transfer\n6. Logout");
                            int op = sc.nextInt();

                            switch (op) {
                                case 1 -> {
                                    System.out.print("Amount: ");
                                    double amt = sc.nextDouble();
                                    bankService.deposit(uname, amt);
                                }
                                case 2 -> {
                                    System.out.print("Amount: ");
                                    double amt = sc.nextDouble();
                                    bankService.withdraw(uname, amt);
                                }
                                case 3 -> bankService.showBalance(uname);
                                case 4 -> bankService.showTransactionSummary(uname);
                                case 5 -> {
                                    System.out.print("Enter recipient's username: ");
                                    sc.nextLine();
                                    String receiver = sc.nextLine();
                                    System.out.print("Enter amount to transfer: ");
                                    double amt = sc.nextDouble();
                                    bankService.transfer(uname, receiver, amt);
                                }
                                case 6 -> {
                                    System.out.println("Logged out.");
                                    break;
                                }

                                default -> System.out.println("Invalid option.");
                            }
                            if (op == 6) break;
                        }
                    } else {
                        System.out.println("Invalid User Credentials.");
                    }
                }

                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid input.");
            }
        }
    }
}
