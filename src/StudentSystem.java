//CMJD104-COURSEWORK
//By Lasini Pallewaththa
import java.util.*;
class StudentSystem{
    //By Lasini Pallewaththa 
    //CMJD104 

    //----------main method------------------------------ 
    public static void main(String[] args) {
        //4 main arrays 
        String[] stIdArray=new String[0];
        String[] stNameArray=new String[0];
        int[] prfMarksArray=new int[0];
        int[] dbmsMarksArray=new int[0];
        //4 additional arrays 
        int[] totalArray=new int[0];
        double[] averageArray=new double[0];
        int[] rankArray=new int[0];

        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //----------Introduction interface------------------- 
    public static void intro(String stIdArray[],String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double averageArray[],int
            rankArray[]){
        Scanner input=new Scanner(System.in);
        boolean validOption = false;
        while (!validOption) {
            try {
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("|              WELCOME TO THE GDSE MARKS MANAGEMENT SYSTEM              |");
                System.out.println("-------------------------------------------------------------------------");
                System.out.println();
                System.out.println("[1] Add New Student"+"\t\t\t"+"[2] Add New Student With Marks");
                System.out.println("[3] Add Marks"+"\t\t\t\t"+"[4] Update Student Details");
                System.out.println("[5] Update Marks"+"\t\t\t"+"[6] Delete Student");
                System.out.println("[7] Print Student Details"+"\t\t"+"[8] Print Student Ranks");
                System.out.println("[9] Best in PRF"+"\t\t\t\t"+"[10] Best in DBMS");
                System.out.println();
                System.out.print("Enter an option to continue > ");
                int option = input.nextInt();

                switch(option){
                    case 1:AddNewStudent(stIdArray,stNameArray,prfMarksArray,dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 2:AddNewStudentWithMarks(stIdArray,stNameArray,prfMarksArray,dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 3:AddMarks(stIdArray,stNameArray,prfMarksArray,dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 4:UpdateStudentDetails(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 5:UpdateMarks(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 6:DeleteStudent(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 7:PrintStudentDetails(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 8:PrintStudentRanks(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 9:BestInPrf(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    case 10:BestInDbms(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);break;
                    default:
                        System.out.println("Invalid entry. Please enter a valid entry...");
                        System.out.println();
                        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
                }

                validOption = true;
            }catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a valid entry...");
                System.out.println();
                input.next();
            }
        }
        input.close();
    }


    //----------other methods---------------------------- 
    //clear console method 
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    //checking string duplicates 
    public static boolean checkforDuplicate(String a[],String stId){
        for(int i=0;i<a.length;i++){
            if(a[i].equals(stId)){
                return true;
            }
        }
        return false;
    }

    //search for st id 
    public static int searchString(String stIdArray[],String stId){
        for(int i=0;i<stIdArray.length;i++){
            if (stId.equalsIgnoreCase(stIdArray[i])){
                return i;
            }
        }
        return -1;
    }

    //Total marks method 
    public static int TotalMarks(int returnedi,int prfMarksArray[],int dbmsMarksArray[]){
        int total=prfMarksArray[returnedi]+dbmsMarksArray[returnedi];
        return total;
    }

    //Avg method 
    public static double AverageMarks(int returnedi,int prfMarksArray[],int dbmsMarksArray[]){
        int total=prfMarksArray[returnedi]+dbmsMarksArray[returnedi];
        double avg=(double)total/2;
        return avg;
    }

    //Rank method-get an index and return rank. 
    public static int Rank(double averageArray[],int rankArray[],int returnedi){
        //int rankVariable=-1; 
        int count=0;
        // Initialize rankArray with -1 for all students 
        for (int i=0; i<rankArray.length; i++) {
            rankArray[i] = -1;
        }
        //count present averages 
        for(int i=0;i<averageArray.length;i++){
            if(averageArray[i]!=-1){
                count++;
            }
        }
        //temp avg array 
        double tempAverageArray[]=new double[count];
        int targetIndex=0;
        for(int i=0;i<averageArray.length;i++){
            if(averageArray[i]!=-1){
                tempAverageArray[targetIndex]=averageArray[i];
                targetIndex++;
            }
        }
        //System.out.println(Arrays.toString(tempAverageArray)); 

        //sorting temp avg array 
        for(int i=tempAverageArray.length-1;i>=0;i--){
            for(int j=0;j<i;j++){
                if(tempAverageArray[j]<tempAverageArray[j+1]){
                    double t=tempAverageArray[j];
                    tempAverageArray[j]=tempAverageArray[j+1];
                    tempAverageArray[j+1]=t;
                }
            }
        }
        //System.out.println(Arrays.toString(tempAverageArray)); 

        //to rank array 
        for (int i = 0; i < averageArray.length; i++) {
            int rank = findIndex(tempAverageArray, averageArray[i]) + 1;//-1 ranks are incremented to 0 
            rankArray[i] = rank;
            if(rank==0){
                rankArray[i]=-1;
            }
        }
        //System.out.println(Arrays.toString(rankArray)); 
        return returnedi;
    }

    // Find the index of an element in an array 
    public static int findIndex(double tempAverageArray[], double value) {
        for (int i=0;i<tempAverageArray.length;i++) {
            if (tempAverageArray[i]==value) {
                return i;
            }
        }
        return -1;
    }


    //----------takes inputs------------------------------ 
    // ADD NEW STUDENT 
    public static void AddNewStudent(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                            ADD NEW STUDENT                            |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N") && choice.equalsIgnoreCase("Y")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //check for st id duplicates 
            boolean isDuplicate=checkforDuplicate(stIdArray,stId);
            if(isDuplicate){
                System.out.println("This Student ID already exists...");
                System.out.println();
            }else{
                //creating st id array 
                String temp1[]=new String[stIdArray.length+1];
                for(int i=0;i<stIdArray.length;i++){
                    temp1[i]=stIdArray[i];
                }
                temp1[temp1.length-1]=stId;
                stIdArray=temp1;

                System.out.print("Enter Student Name:");
                String stName =input.next();

                //creating st name array 
                String temp2[]=new String[stNameArray.length+1];
                for(int i=0;i<stNameArray.length;i++){
                    temp2[i]=stNameArray[i];
                }
                temp2[temp2.length-1]=stName;
                stNameArray=temp2;

                //creating PRF marks 
                int temp3[]=new int[prfMarksArray.length+1];
                for(int i=0;i<prfMarksArray.length;i++){
                    temp3[i]=prfMarksArray[i];
                }
                temp3[temp3.length-1]=-1;
                prfMarksArray=temp3;

                //creating DBMS marks 
                int temp4[]=new int[dbmsMarksArray.length+1];
                for(int i=0;i<dbmsMarksArray.length;i++){
                    temp4[i]=dbmsMarksArray[i];
                }
                temp4[temp4.length-1]=-1;
                dbmsMarksArray=temp4;

                //creating total array 
                int temp5[]=new int[totalArray.length+1];
                for(int i=0;i<totalArray.length;i++){
                    temp5[i]=totalArray[i];
                }
                temp5[temp5.length-1]=-1;
                totalArray=temp5;

                //creating avg array 
                double temp6[]=new double[averageArray.length+1];
                for(int i=0;i<averageArray.length;i++){
                    temp6[i]=averageArray[i];
                }
                temp6[temp6.length-1]= -1;
                averageArray=temp6;

                //creating ranks array 
                int temp7[]=new int[rankArray.length+1];
                for(int i=0;i<rankArray.length;i++){
                    temp7[i]=rankArray[i];
                }
                temp7[temp7.length-1]= -1;
                rankArray=temp7;

                System.out.println();
                System.out.println("Student ID and Name added successfully...");
                System.out.println();

                choice="P";
                while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                    System.out.print("Add another Student ID and Name? (Y/N): ");
                    choice = input.next();
                    if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                        System.out.println("Invalid entry. Please enter Y/N...");
                    }
                    System.out.println();
                }

            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);

    }

    //ADD NEW STUDENT WITH MARKS 
    public static void AddNewStudentWithMarks(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                       ADD NEW STUDENT WITH MARKS                       |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equals("n")&&!choice.equals("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //check for st id duplicates 
            boolean isDuplicate=checkforDuplicate(stIdArray,stId);
            if(isDuplicate){
                System.out.println("This Student ID already exists...");
                System.out.println();
            }else{
                //creating st id array 
                String temp1[]=new String[stIdArray.length+1];
                for(int i=0;i<stIdArray.length;i++){
                    temp1[i]=stIdArray[i];
                }
                temp1[temp1.length-1]=stId;
                stIdArray=temp1;

                System.out.print("Enter Student Name:");
                String stName =input.next();

                //creating st name array 
                String temp2[]=new String[stNameArray.length+1];
                for(int i=0;i<stNameArray.length;i++){
                    temp2[i]=stNameArray[i];
                }
                temp2[temp2.length-1]=stName;
                stNameArray=temp2;

                System.out.println();

                //PRF marks 
                boolean validOption1 = false;
                while(!validOption1) {
                    try {
                        int prfmarks;
                        System.out.print("Programming Fundamentals Marks   :");
                        prfmarks =input.nextInt();
                        if(!(prfmarks>=0 && prfmarks<=100)){
                            System.out.println("Invalid marks. Please enter valid marks...");
                            System.out.println();
                            validOption1=false;
                        }else{
                            int temp3[]=new int[prfMarksArray.length+1];
                            for(int i=0;i<prfMarksArray.length;i++){
                                temp3[i]=prfMarksArray[i];
                            }
                            temp3[temp3.length-1]=prfmarks;
                            prfMarksArray=temp3;
                            validOption1=true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry. Please enter a valid entry...");
                        validOption1=false;
                        System.out.println();
                        input.next();
                    }
                }

                //DBMS marks 
                boolean validOption2 = false;
                while(!validOption2) {
                    try {
                        int dbmsmarks;
                        System.out.print("Database Management System Marks :");
                        dbmsmarks =input.nextInt();
                        if(!(dbmsmarks>=0 && dbmsmarks<=100)){
                            System.out.println("Invalid marks. Please enter valid marks...");
                            System.out.println();
                            validOption2=false;
                        }else{
                            int temp4[]=new int[dbmsMarksArray.length+1];
                            for(int i=0;i<dbmsMarksArray.length;i++){
                                temp4[i]=dbmsMarksArray[i];
                            }
                            temp4[temp4.length-1]=dbmsmarks;
                            dbmsMarksArray=temp4;
                            validOption2=true;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry. Please enter a valid entry...");
                        validOption2=false;
                        System.out.println();
                        input.next();
                    }
                }

                int returnedi=searchString(stIdArray, stId);

                //creating total array 
                int temp5[]=new int[totalArray.length+1];
                for(int i=0;i<totalArray.length;i++){
                    temp5[i]=totalArray[i];
                }
                temp5[temp5.length-1]=-1;
                totalArray=temp5;

                //updating total array 
                int totalMarksVariable=TotalMarks(returnedi,prfMarksArray,dbmsMarksArray);
                totalArray[returnedi]=totalMarksVariable;

                //creating avg array 
                double temp6[]=new double[averageArray.length+1];
                for(int i=0;i<averageArray.length;i++){
                    temp6[i]=averageArray[i];
                }
                temp6[temp6.length-1]= -1;
                averageArray=temp6;

                //updating avg array 
                double averageMarksVariable=AverageMarks(returnedi,prfMarksArray,dbmsMarksArray);
                averageArray[returnedi]=averageMarksVariable;

                //creating ranks array 
                int temp7[]=new int[rankArray.length+1];
                for(int i=0;i<rankArray.length;i++){
                    temp7[i]=rankArray[i];
                }
                temp7[temp7.length-1]= -1;
                rankArray=temp7;

                //ranks will be calculated at the print details method 

                System.out.println();
                System.out.println("Student with marks has been added successfully...");
                System.out.println();

                choice="P";
                while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                    System.out.print("Add another student with marks? (Y/N): ");
                    choice = input.next();
                    if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                        System.out.println("Invalid entry. Please enter Y/N...");
                    }
                    System.out.println();
                }
            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //ADD MARKS 
    public static void AddMarks(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double averageArray[],int
            rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                               ADD MARKS                               |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //search for st id 
            int returnedi=searchString(stIdArray, stId);
            if(returnedi==-1){
                System.out.println("Invalid Student ID...");
            }else{
                System.out.println("Student Name:"+stNameArray[returnedi]);
                System.out.println();
                if(prfMarksArray[returnedi]==-1 && dbmsMarksArray[returnedi]==-1){
                    //PRF marks 
                    boolean validOption1 = false;
                    while(!validOption1) {
                        try {
                            int prfmarks;
                            System.out.print("Programming Fundamentals Marks   :");
                            prfmarks =input.nextInt();
                            if(!(prfmarks>=0 && prfmarks<=100)){
                                System.out.println("Invalid marks. Please enter valid marks...");
                                System.out.println();
                                validOption1=false;
                            }else{
                                prfMarksArray[returnedi] = prfmarks;
                                validOption1=true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Please enter a valid entry...");
                            validOption1=false;
                            System.out.println();
                            input.next();
                        }
                    }

                    //DBMS marks 
                    boolean validOption2 = false;
                    while(!validOption2) {
                        try {
                            int dbmsmarks;
                            System.out.print("Database Management System Marks :");
                            dbmsmarks =input.nextInt();
                            if(!(dbmsmarks>=0 && dbmsmarks<=100)){
                                System.out.println("Invalid marks. Please enter valid marks...");
                                System.out.println();
                                validOption2=false;
                            }else{
                                dbmsMarksArray[returnedi] = dbmsmarks;
                                validOption2=true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Please enter a valid entry...");
                            validOption2=false;
                            System.out.println();
                            input.next();
                        }
                    }

                    System.out.println();
                    System.out.println("Marks have been added successfully...");

                    //creating total array 
                    int temp5[]=new int[totalArray.length];
                    for(int i=0;i<totalArray.length;i++){
                        temp5[i]=totalArray[i];
                    }
                    //temp5[temp5.length-1]=-1; 
                    totalArray=temp5;

                    //updating total array 
                    int totalMarksVariable=TotalMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    totalArray[returnedi]=totalMarksVariable;

                    //creating avg array 
                    double temp6[]=new double[averageArray.length];
                    for(int i=0;i<averageArray.length;i++){
                        temp6[i]=averageArray[i];
                    }
                    //temp6[temp6.length-1]= -1; 
                    averageArray=temp6;

                    //updating avg array 
                    double averageMarksVariable=AverageMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    averageArray[returnedi]=averageMarksVariable;

                    //creating ranks array 
                    int temp7[]=new int[rankArray.length];
                    for(int i=0;i<rankArray.length;i++){
                        temp7[i]=rankArray[i];
                    }
                    //temp7[temp7.length-1]= -1; 
                    rankArray=temp7;

                    //ranks will be calculated at the print details method 

                }else{
                    System.out.println("Marks for this student have already been assigned.");
                    System.out.println("If you want to update the marks, please use [4] Update Marks option...");
                    System.out.println();
                }
            }
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Add another student marks? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //UPDATE STUDENT DETAILS 
    public static void UpdateStudentDetails(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                        UPDATE STUDENT  DETAILS                        |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //search for st id 
            int returnedi=searchString(stIdArray, stId);
            if(returnedi==-1){
                System.out.println("Invalid Student ID...");
            }else{
                System.out.println("Student Name:"+stNameArray[returnedi]);
                System.out.println();
                System.out.print("Enter New Student Name: ");
                String newName=input.next();
                stNameArray[returnedi]=newName;
                System.out.println();
                System.out.println("Student details has been updated successfully...");
            }
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Update another student details? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //UPDATE MARKS 
    public static void UpdateMarks(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                             UPDATE MARKS                              |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //search for st id 
            int returnedi=searchString(stIdArray, stId);
            if(returnedi==-1){
                System.out.println("Invalid Student ID...");
            }else{
                System.out.println("Student Name:"+stNameArray[returnedi]);
                System.out.println();
                if(prfMarksArray[returnedi]==-1){
                    System.out.println("This stuent's marks are yet to be added...");
                }else{
                    System.out.println("Programming Fundamentals Marks: "+prfMarksArray[returnedi]);
                    System.out.println("Database Management Marks: "+dbmsMarksArray[returnedi]);
                    System.out.println();

                    //PRF marks 
                    boolean validOption1 = false;
                    while(!validOption1) {
                        try {
                            int prfmarks;
                            System.out.print("Enter New Programming Fundamentals Marks   :");
                            prfmarks =input.nextInt();
                            if(!(prfmarks>=0 && prfmarks<=100)){
                                System.out.println("Invalid marks. Please enter valid marks...");
                                System.out.println();
                                validOption1=false;
                            }else{
                                prfMarksArray[returnedi] = prfmarks;
                                validOption1=true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Please enter a valid entry...");
                            validOption1=false;
                            System.out.println();
                            input.next();
                        }
                    }

                    //DBMS marks 
                    boolean validOption2 = false;
                    while(!validOption2) {
                        try {
                            int dbmsmarks;
                            System.out.print("Enter New Database Management System Marks :");
                            dbmsmarks =input.nextInt();
                            if(!(dbmsmarks>=0 && dbmsmarks<=100)){
                                System.out.println("Invalid marks. Please enter valid marks...");
                                System.out.println();
                                validOption2=false;
                            }else{
                                dbmsMarksArray[returnedi] = dbmsmarks;
                                validOption2=true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid entry. Please enter a valid entry...");
                            validOption2=false;
                            System.out.println();
                            input.next();
                        }
                    }

                    //creating total array 
                    int temp5[]=new int[totalArray.length];
                    for(int i=0;i<totalArray.length;i++){
                        temp5[i]=totalArray[i];
                    }
                    //temp5[temp5.length-1]=-1; 
                    totalArray=temp5;

                    //updating total array 
                    int totalMarksVariable=TotalMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    totalArray[returnedi]=totalMarksVariable;

                    //creating avg array 
                    double temp6[]=new double[averageArray.length];
                    for(int i=0;i<averageArray.length;i++){
                        temp6[i]=averageArray[i];
                    }
                    //temp6[temp6.length-1]= -1; 
                    averageArray=temp6;

                    //updating avg array 
                    double averageMarksVariable=AverageMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    averageArray[returnedi]=averageMarksVariable;

                    //creating ranks array 
                    int temp7[]=new int[rankArray.length];
                    for(int i=0;i<rankArray.length;i++){
                        temp7[i]=rankArray[i];
                    }
                    //temp7[temp7.length-1]= -1; 
                    rankArray=temp7;

                    System.out.println();
                    System.out.println("Marks have been updated successfully...");
                }
            }
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Update another student's marks? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //DELETE STUDENT 
    public static void DeleteStudent(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){
        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                            DELETE STUDENTS                            |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //search for st id 
            int returnedi=searchString(stIdArray, stId);
            if(returnedi==-1){
                System.out.println("Invalid Student ID...");
            }else{
                System.out.println("Student Name:"+stNameArray[returnedi]);
                System.out.println();

                //for st id Array 
                String temp1[]=new String[stIdArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp1[i]=stIdArray[i];
                }
                for(int i=returnedi;i<stIdArray.length-1;i++){
                    temp1[i]=stIdArray[i+1];
                }
                stIdArray=temp1;

                //for st name array 
                String temp2[]=new String[stNameArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp2[i]=stNameArray[i];
                }
                for(int i=returnedi;i<stNameArray.length-1;i++){
                    temp2[i]=stNameArray[i+1];
                }
                stNameArray=temp2;

                //for prf marks array 
                int temp3[]=new int[prfMarksArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp3[i]=prfMarksArray[i];
                }
                for(int i=returnedi;i<prfMarksArray.length-1;i++){
                    temp3[i]=prfMarksArray[i+1];
                }
                prfMarksArray=temp3;

                //for dbms marks array 
                int temp4[]=new int[dbmsMarksArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp4[i]=dbmsMarksArray[i];
                }
                for(int i=returnedi;i<dbmsMarksArray.length-1;i++){
                    temp4[i]=dbmsMarksArray[i+1];
                }
                dbmsMarksArray=temp4;

                //for total array 
                int temp5[]=new int[totalArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp5[i]=totalArray[i];
                }
                for(int i=returnedi;i<totalArray.length-1;i++){
                    temp5[i]=totalArray[i+1];
                }
                totalArray=temp5;

                //for avg array 
                double temp7[]=new double[averageArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp7[i]=averageArray[i];
                }
                for(int i=returnedi;i<averageArray.length-1;i++){
                    temp7[i]=averageArray[i+1];
                }
                averageArray=temp7;

                //for ranks array 
                int temp6[]=new int[rankArray.length-1];
                for(int i=0; i<returnedi;i++){
                    temp6[i]=rankArray[i];
                }
                for(int i=returnedi;i<rankArray.length-1;i++){
                    temp6[i]=rankArray[i+1];
                }
                rankArray=temp6;

                System.out.println("This student has been deleted successfully...");
            }
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Delete another student? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }


    //----------takes no inputs--------------------------- 
    //PRINT STUDENT DETAILS 
    public static void PrintStudentDetails(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){

        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                         PRINT STUDENT DETAILS                         |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="Y";
        while (!choice.equalsIgnoreCase("N")) {
            System.out.print("Enter Student ID:");
            String stId =input.next();

            //search for st id 
            int returnedi=searchString(stIdArray, stId);
            if(returnedi==-1){
                System.out.println("Invalid Student ID...");
            }else{
                System.out.println("Student Name:"+stNameArray[returnedi]);
                System.out.println();
                if(prfMarksArray[returnedi]==-1 && dbmsMarksArray[returnedi]==-1){
                    System.out.println("Marks yet to be added...");
                }else{
                    System.out.println("+-----------------------------------------+---------------+");
                    System.out.print("| Programming Fundamentals Marks          |");
                    System.out.printf(" %-13d |%n",prfMarksArray[returnedi]);

                    System.out.print("| Database Management Systems Marks       |");
                    System.out.printf(" %-13d |%n",dbmsMarksArray[returnedi]);

                    int totalMarksVariable=TotalMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    System.out.print("| Total Marks                             |");
                    System.out.printf(" %-13d |%n",totalMarksVariable);

                    double averageMarksVariable=AverageMarks(returnedi,prfMarksArray,dbmsMarksArray);
                    System.out.print("| Average Marks                           |");
                    System.out.printf(" %-13.2f |%n",averageMarksVariable);

                    int rankArrayIndex=Rank(averageArray,rankArray,returnedi);
                    int rankVariable=rankArray[rankArrayIndex];
                    String rankInLetters="";
                    if(rankVariable==1||rankVariable==2||rankVariable==3){
                        switch (rankVariable) {
                            case 1:rankInLetters="(First)";break;
                            case 2:rankInLetters="(Second)";break;
                            case 3:rankInLetters="(Third)";break;
                        }
                    }
                    System.out.print("| Rank                                    |");
                    System.out.printf(" %-13s |%n",rankVariable+" "+rankInLetters);
                    System.out.println("+-----------------------------------------+---------------+");
                }
            }
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Do you want to print another student details? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //PRINT STUDENT RANKS 
    public static void PrintStudentRanks(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){

        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                          PRINT STUDENT RANKS                          |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="N";
        while (!choice.equalsIgnoreCase("Y")) {
            //table like structure 
            System.out.println("+------+--------+-----------------+-------------+--------------+");
            System.out.println("| Rank |  ID    | Name            | Total Marks | Avg. Marks   |");
            System.out.println("+------+--------+-----------------+-------------+--------------+");

            int extrai=0;
            Rank(averageArray,rankArray,extrai);
            for(int i=1;i<=rankArray.length;i++){
                for(int j=0;j<rankArray.length;j++){
                    if(i==rankArray[j]){
                        System.out.printf("| %-4d | %-6s | %-15s | %-11d | %-12.2f |%n", rankArray[j], stIdArray[j], stNameArray[j], totalArray[j], averageArray[j]);
                    }
                }
            }
            System.out.println("+------+--------+-----------------+-------------+--------------+");

            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Do you want to go back to the main menu? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        clearConsole();
        // System.out.println("-----Updated arrays are-----"); 
        // System.out.println(Arrays.toString(stIdArray)); 
        // System.out.println(Arrays.toString(stNameArray)); 
        // System.out.println(Arrays.toString(prfMarksArray)); 
        // System.out.println(Arrays.toString(dbmsMarksArray)); 
        // System.out.println(Arrays.toString(totalArray)); 
        // System.out.println(Arrays.toString(averageArray)); 
        // System.out.println(Arrays.toString(rankArray)); 
        // System.out.println("----------------------------"); 
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //BEST IN PRF 
    public static void BestInPrf(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double averageArray[],int
            rankArray[]){

        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                   BEST IN PROGRAMMING FUNDAMENTALS                    |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="N";
        while (!choice.equalsIgnoreCase("Y")) {
            //table like structure 
            System.out.println("+--------+-----------------+--------------+--------------+");
            System.out.println("|  ID    | Name            | PRF Marks    | DBMS Marks   |");
            System.out.println("+--------+-----------------+----------- --+--------------+");

            // Sort indices based on pfMarksArray in descending order 
            Integer[] indices = new Integer[stIdArray.length];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
            Arrays.sort(indices, (a, b) -> Integer.compare(prfMarksArray[b], prfMarksArray[a]));
            for (int i : indices) {
                if (prfMarksArray[i] != -1) {
                    System.out.printf("| %-6s | %-15s | %-12d | %-12d |%n", stIdArray[i], stNameArray[i], prfMarksArray[i],dbmsMarksArray[i]);
                }
            }
            System.out.println("+--------+-----------------+--------------+--------------+");

            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Do you want to go back to the main menu? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }

    //BEST IN DBMS 
    public static void BestInDbms(String stIdArray[], String stNameArray[],int prfMarksArray[],int dbmsMarksArray[],int totalArray[],double
            averageArray[],int rankArray[]){

        Scanner input=new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|                  BEST IN DATABASE MANAGEMENT SYSTEM                   |");
        System.out.println("-------------------------------------------------------------------------");

        String choice="N";
        while (!choice.equalsIgnoreCase("Y")) {
            //table like structure 
            System.out.println("+--------+-----------------+--------------+--------------+");
            System.out.println("|  ID    | Name            | DBMS Marks   | PRF Marks    |");
            System.out.println("+--------+-----------------+----------- --+--------------+");

            // Sort indices based on pfMarksArray in descending order 
            Integer[] indices = new Integer[stIdArray.length];
            for (int i = 0; i < indices.length; i++) {
                indices[i] = i;
            }
            Arrays.sort(indices, (a, b) -> Integer.compare(dbmsMarksArray[b], dbmsMarksArray[a]));
            for (int i : indices) {
                if (dbmsMarksArray[i] != -1) {
                    System.out.printf("| %-6s | %-15s | %-12d | %-12d |%n", stIdArray[i], stNameArray[i], dbmsMarksArray[i],prfMarksArray[i]);
                }
            }
            System.out.println("+--------+-----------------+--------------+--------------+");
            System.out.println();
            choice="P";
            while(!choice.equalsIgnoreCase("N") && !choice.equalsIgnoreCase("Y")){
                System.out.print("Do you want to go back to the main menu? (Y/N): ");
                choice = input.next();
                if(!choice.equalsIgnoreCase("N")&& !choice.equalsIgnoreCase("Y")){
                    System.out.println("Invalid entry. Please enter Y/N...");
                }
                System.out.println();
            }
        }
        clearConsole();
        intro(stIdArray, stNameArray, prfMarksArray, dbmsMarksArray,totalArray,averageArray,rankArray);
    }
} 