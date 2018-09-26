import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ConnectSQL conn = new ConnectSQL();
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            System.out.println("=====MENU=====");
            System.out.println("1. Get all data of Student table. \n" +
                    "2. Get Student by ID. \n" +
                    "3. Insert Student. \n" +
                    "4. Update Student. \n" +
                    "5. Delete Student. \n" +
                    "6. Exit \n");
            System.out.print("Input a number: ");
            int number = 0;
            boolean b = false;
            while (!b) {
                String i = sc.nextLine();
                try {
                    number = Integer.parseInt(i);
                    b = true;
                } catch (Exception ex) {
                    System.out.print("Data input is invalid!" +
                            "Please input again: ");
                }
            }
            boolean isCorrect = true;
            switch (number) {
                case 1:
                    conn.getData();
                    break;
                case 2:
                    conn.getStudentByID();
                    break;
                case 3:
                    conn.insertData();
                    break;
                case 4:
                    conn.updateData();
                    break;
                case 5:
                    conn.deleteData();
                    break;
                case 6:
                    System.out.println("=====BYE BYE=====");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Input number between 1~5!");
                    isCorrect = false;
            }
            if (!isCorrect) {
                s = "y";
            } else {
                System.out.print("Press 'y' if you want continue!!");
                s = sc.nextLine();
            }
        } while (s.equals("y"));
    }
}
