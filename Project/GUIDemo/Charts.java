/**
 * @Author Ertuğrul Yılmaz / Muhammed Rahmetullah Kartal
 * @Date 20.03.2019 / 03.04.2019
 * @Description: Creating graphs.
 */


package Project.GUIDemo;

import Project.barCharts.barChart;
import Project.mainObjects.File_f;
import Project.mainObjects.Section;
import Project.mainObjects.Subsection;
import Project.pieCharts.pieChart;
import Project.radarCharts.radarChart;
import org.knowm.xchart.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Charts {
    static ArrayList<String> votes = new ArrayList<String>(Arrays.asList("Vote1","Vote2","Vote3","Vote4","Vote5"));
    static ArrayList<String> sectionNames = new ArrayList<String>(Arrays.asList("Flipped","Course","Instructor",
            "Lab","Teaching Ass.","Overall Evalution","Learning Outcomes"));


    private static ArrayList<Double> createOneSubSectionVote(Subsection subsection){
        ArrayList<Double> yAxis = new ArrayList<>();

        int numberOfOne = (int)subsection.one;
        int numberOfTwo = (int)subsection.two;
        int numberOfThree = (int)subsection.three;
        int numberOfFour = (int)subsection.four;
        int numberofFive = (int)subsection.five;

        yAxis.add((double) numberOfOne);
        yAxis.add((double) numberOfTwo);
        yAxis.add((double) numberOfThree);
        yAxis.add((double) numberOfFour);
        yAxis.add((double) numberofFive);

        return yAxis;
    }

    private static CategoryChart createOneSectionVoteBC(Subsection subsection){
        String nameOfChart = subsection.text + " - Votes";
        barChart chart = new barChart(500,500,nameOfChart,"Votes","Number of Votes");

        ArrayList<String> xAxis = votes;
        ArrayList<Double> yAxis = createOneSubSectionVote(subsection);

        chart.addElements("hey",xAxis,yAxis);
        return chart.getChart();
    }

    public static ArrayList<CategoryChart> createAllSectionsVoteBCS(File_f file){
        ArrayList<CategoryChart> barCharts = new ArrayList<>();
        Section[] x = file.Sections;
        ArrayList<Subsection> subsections = x[2].subsections;
        for (int i =0; i<file.Sections.length; i++){
            for (int j=0; j<file.Sections[i].subsections.size(); j++){
                barCharts.add(createOneSectionVoteBC(file.Sections[i].subsections.get(j)));
            }
        }
        return barCharts;
    }






    private static PieChart createOneSectionVotePC(Subsection subsection){
        String nameOfChart = subsection.text + " - Votes";
        pieChart chart = new pieChart(500,500,nameOfChart);

        ArrayList<String> xAxis = votes;
        ArrayList<Double> yAxis = createOneSubSectionVote(subsection);

        chart.addElements(xAxis,yAxis);
        return chart.getChart();
    }

    public static ArrayList<PieChart> createAllSectionsVotePCS(File_f file){
        ArrayList<PieChart> pieCharts = new ArrayList<>();
        Section[] x = file.Sections;
        ArrayList<Subsection> subsections = x[2].subsections;
        for (int i =0; i<file.Sections.length; i++){
            for (int j=0; j<file.Sections[i].subsections.size(); j++){
                pieCharts.add(createOneSectionVotePC(file.Sections[i].subsections.get(j)));
            }
        }
        return pieCharts;
    }


    public static RadarChart createAllSubsectionAveragesRC(File_f file){
        radarChart chart = new radarChart(500,500,"Averages of Subsections");
        ArrayList<String> parameters = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        String ssNumber;
        for (int i=0; i<file.Sections.length; i++){
            for (int j=0; j<file.Sections[i].subsections.size(); j++){
//                parameters.add(file.Sections[i].subsections.get(j).text);
                ssNumber= "SS"+j;
                parameters.add(ssNumber);
                values.add(file.Sections[i].subsections.get(j).average/5);
            }
        }
        chart.setLabelNames(parameters);
        chart.addElements("Subsection Averages", values);
        return chart.getChart();
    }

    public static RadarChart createAllSubsectionAveragesUniAveragesRC(File_f file){
        radarChart chart = new radarChart(500,500,"Averages and University Averages of Subsections");
        ArrayList<String> parameters = new ArrayList<>();
        ArrayList<Double> values1 = new ArrayList<>();
        ArrayList<Double> values2 = new ArrayList<>();
        String ssNumber;
        int n=0;
        for (int i=0; i<file.Sections.length; i++){
            for (int j=0; j<file.Sections[i].subsections.size(); j++){
//                parameters.add(file.Sections[i].subsections.get(j).text);
                n++;
                ssNumber= "SS"+n;
                parameters.add(ssNumber);
                values1.add(file.Sections[i].subsections.get(j).universityAverage/5);
                values2.add(file.Sections[i].subsections.get(j).average/5);
            }
        }
        chart.setLabelNames(parameters);
        chart.addElements("University Averages", values1);

        chart.addElements("Averages", values2);
        return chart.getChart();
    }

    public static CategoryChart createAllSubsectionAveragesUniAveragesBC(File_f file){
        barChart chart = new barChart(500,500,"Averages and University Averages of Subsections","Subsections","Averages");
        ArrayList<String> parameters = new ArrayList<>();
        ArrayList<Double> values1 = new ArrayList<>();
        ArrayList<Double> values2 = new ArrayList<>();
        String ssNumber;
        int n=0;
        for (int i=0; i<file.Sections.length; i++){
            for (int j=0; j<file.Sections[i].subsections.size(); j++){
//                parameters.add(file.Sections[i].subsections.get(j).text);
                n=n+1;
                ssNumber= ""+n;
                parameters.add(ssNumber);
                values1.add(file.Sections[i].subsections.get(j).universityAverage);
                values2.add(file.Sections[i].subsections.get(j).average);
            }
        }
        chart.addElements("University Averages", parameters,values1);

        chart.addElements("Averages", parameters,values2);
        return chart.getChart();
    }





    public static RadarChart createSectionOrtalamalariRadarChart(File_f file) {
        // Create Chart
        radarChart chart = new radarChart(500,500,"Averages of Sections");

        // Series

        double[] sectionAverages = new double[7];
        double sectionAverage=0;

        for(int i =0; i<7;i++){
            int counter=0;
            sectionAverage=0;
            double total=0;
            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
                counter++;
                total = total + file.Sections[i].subsections.get(j).average;
                sectionAverage = total/counter;
            }
            sectionAverages[i]=sectionAverage;
        }


        ArrayList<Double> valuesCourse = new ArrayList<>();
        valuesCourse.add((double) sectionAverages[0]/5);
        valuesCourse.add((double) sectionAverages[1]/5);
        valuesCourse.add((double) sectionAverages[2]/5);
        valuesCourse.add((double) sectionAverages[3]/5);
        valuesCourse.add((double) sectionAverages[4]/5);
        valuesCourse.add((double) sectionAverages[5]/5);
        valuesCourse.add((double) sectionAverages[6]/5);



        chart.setLabelNames(sectionNames);
        chart.addElements("Values Course", valuesCourse);


        return chart.getChart();
    }

    public static void printSectionOrtalamalariRadarChart(File_f file){
        RadarChart chart = createSectionOrtalamalariRadarChart(file);
        new SwingWrapper<RadarChart>(chart).displayChart();
    }
//
//
//
//
//
//
//
//
    public static RadarChart createScoringOfLearningOutComesRadarChart(File_f file) {
        radarChart chart = new radarChart(500,500,"Scoring of Learning Outcomes");

        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Score 1");
        parameters.add("Score 2");
        parameters.add("Score 3");
        parameters.add("Score 4");
        parameters.add("Score 5");

        int numberOfOne=0;
        int numberOfTwo=0;
        int numberOfThree=0;
        int numberOfFour=0;
        int numberofFive=0;


        for(int j = 0; j<file.Sections[6].subsections.size(); j++) {
            numberOfOne += (int) file.Sections[6].subsections.get(j).one;
            numberOfTwo += (int) file.Sections[6].subsections.get(j).two;
            numberOfThree += (int) file.Sections[6].subsections.get(j).three;
            numberOfFour += (int) file.Sections[6].subsections.get(j).four;
            numberofFive += (int) file.Sections[6].subsections.get(j).five;
        }

        int totalScoringForRange = numberOfOne+numberOfTwo+numberOfThree+numberOfFour+numberofFive;


        ArrayList<Double> valuesCourse = new ArrayList<>();
        valuesCourse.add((double)numberOfOne/totalScoringForRange);
        valuesCourse.add((double)numberOfTwo/totalScoringForRange);
        valuesCourse.add((double)numberOfThree/totalScoringForRange);
        valuesCourse.add((double)numberOfFour/totalScoringForRange);
        valuesCourse.add((double)numberofFive/totalScoringForRange);



        chart.setLabelNames(parameters);
        chart.addElements("Scoring of Course", valuesCourse);

        return chart.getChart();

    }

    public static void printScoringOfLearningOutComesRadarChart(File_f file){
        RadarChart chart = createScoringOfLearningOutComesRadarChart(file);
        new SwingWrapper<RadarChart>(chart).displayChart();
    }



//
//
//
//
//
//    public static RadarChart createMultipleAveragesRadarChart(File_f file) {
//        // Create Chart
//        radarChart chart = new radarChart(500,500,"Multiple Averages");
//
//        // Series
//        ArrayList<String> parameters = new ArrayList<>();
//        parameters.add("Flipped");
//        parameters.add("Course");
//        parameters.add("Instructor");
//        parameters.add("Lab");
//        parameters.add("Teaching Ass.");
//        parameters.add("Overall Evalution");
//        parameters.add("Learning Outcomes");
//
//        double[] sectionAverages = new double[7];
//        double sectionAverage=0;
//
//        for(int i =0; i<7;i++){
//            int counter=0;
//            sectionAverage=0;
//            double total=0;
//            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
//                counter++;
//                total = total + file.Sections[i].subsections.get(j).average;
//                sectionAverage = total/counter;
//            }
//            sectionAverages[i]=sectionAverage;
//        }
//
//        double[] sectionAveragesUniversity = new double[7];
//        double sectionAverageUniversity=0;
//
//        for(int i =0; i<7;i++){
//            int counter=0;
//            sectionAverageUniversity=0;
//            double total=0;
//            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
//                counter++;
//                total = total + file.Sections[i].subsections.get(j).universityAverage;
//                sectionAverageUniversity = total/counter;
//            }
//            sectionAveragesUniversity[i]=sectionAverageUniversity;
//        }
//
//
//
//
//        ArrayList<Double> valuesCourse = new ArrayList<>();
//        valuesCourse.add((double) sectionAverages[0]/5);
//        valuesCourse.add((double) sectionAverages[1]/5);
//        valuesCourse.add((double) sectionAverages[2]/5);
//        valuesCourse.add((double) sectionAverages[3]/5);
//        valuesCourse.add((double) sectionAverages[4]/5);
//        valuesCourse.add((double) sectionAverages[5]/5);
//        valuesCourse.add((double) sectionAverages[6]/5);
//
//        ArrayList<Double> valuesUniversity = new ArrayList<>();
//        valuesUniversity.add((double) sectionAveragesUniversity[0]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[1]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[2]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[3]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[4]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[5]/5);
//        valuesUniversity.add((double) sectionAveragesUniversity[6]/5);
//
//        chart.setLabelNames(parameters);
//        chart.addElements("Values Course", valuesCourse);
//        chart.addElements("Values University", valuesUniversity);
//
//        return chart.getChart();
//    }
//
//    public static void printMultipleAveragesRadarChart(File_f file){
//        RadarChart chart = createMultipleAveragesRadarChart(file);
//        new SwingWrapper<RadarChart>(chart).displayChart();
//    }
//
//
//
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // ##############################################################BarCharts##############################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//
//
//
//    public static CategoryChart createScoringOfLearningOutComesBarChart(File_f file){
//
//        barChart chart = new barChart(600, 500, "Number of Scores", "Score Points", "Numbers");
//        ArrayList<String> x = new ArrayList<>();
//        x.add("1");
//        x.add("2");
//        x.add("3");
//        x.add("4");
//        x.add("5");
//
//        int numberOfOne=0;
//        int numberOfTwo=0;
//        int numberOfThree=0;
//        int numberOfFour=0;
//        int numberofFive=0;
//
//
//        for(int j = 0; j<file.Sections[6].subsections.size(); j++) {
//            numberOfOne += (int) file.Sections[6].subsections.get(j).one;
//            numberOfTwo += (int) file.Sections[6].subsections.get(j).two;
//            numberOfThree += (int) file.Sections[6].subsections.get(j).three;
//            numberOfFour += (int) file.Sections[6].subsections.get(j).four;
//            numberofFive += (int) file.Sections[6].subsections.get(j).five;
//        }
//
//
//
//        ArrayList<Double> y = new ArrayList<>();
//        y.add((double) numberOfOne);
//        y.add((double) numberOfTwo);
//        y.add((double) numberOfThree);
//        y.add((double) numberOfFour);
//        y.add((double) numberofFive);
//
//
//
//        chart.addElements("Number for Learning Outcome Scores",x,y);
//
//
//        return chart.getChart();
//
//    }
//
//    public static void printScoringOfLearningOutComesBarChart(File_f file){
//        CategoryChart chart = createScoringOfLearningOutComesBarChart(file);
//        new SwingWrapper<CategoryChart>(chart).displayChart();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public static CategoryChart createMultipleAveragesBarChart(File_f file){
//
//        barChart chart = new barChart(970, 500, "Section Ortalamalari", "Sections", "Averages");
//        ArrayList<String> x = new ArrayList<>();
//        x.add("Flipped");
//        x.add("Course");
//        x.add("Instructor");
//        x.add("Lab");
//        x.add("Teching Ass.");
//        x.add("Overall Evalution");
//        x.add("Learning OutComes");
//
//        double[] sectionAverages = new double[7];
//        double sectionAverage=0;
//
//        for(int i =0; i<7;i++){
//            int counter=0;
//            sectionAverage=0;
//            double total=0;
//            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
//                counter++;
//                total = total + file.Sections[i].subsections.get(j).average;
//                sectionAverage = total/counter;
//            }
//            sectionAverages[i]=sectionAverage;
//        }
//
//        double[] sectionAveragesUniversity = new double[7];
//        double sectionAverageUniversity=0;
//
//        for(int i =0; i<7;i++){
//            int counter=0;
//            sectionAverageUniversity=0;
//            double total=0;
//            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
//                counter++;
//                total = total + file.Sections[i].subsections.get(j).universityAverage;
//                sectionAverageUniversity = total/counter;
//            }
//            sectionAveragesUniversity[i]=sectionAverageUniversity;
//        }
//
//        ArrayList<Double> y = new ArrayList<>();
//        y.add((double) sectionAverages[0]);
//        y.add((double) sectionAverages[1]);
//        y.add((double) sectionAverages[2]);
//        y.add((double) sectionAverages[3]);
//        y.add((double) sectionAverages[4]);
//        y.add((double) sectionAverages[5]);
//        y.add((double) sectionAverages[6]);
//
//        ArrayList<Double> t = new ArrayList<>();
//        t.add((double) sectionAveragesUniversity[0]);
//        t.add((double) sectionAveragesUniversity[1]);
//        t.add((double) sectionAveragesUniversity[2]);
//        t.add((double) sectionAveragesUniversity[3]);
//        t.add((double) sectionAveragesUniversity[4]);
//        t.add((double) sectionAveragesUniversity[5]);
//        t.add((double) sectionAveragesUniversity[6]);
//
//        chart.addElements("Course",x,y);
//        chart.addElements("University",x,t);
//
//
//        return chart.getChart();
//
//    }
//
//    public static void printMultipleAveragesBarChart(File_f file){
//        CategoryChart chart = createMultipleAveragesBarChart(file);
//        new SwingWrapper<CategoryChart>(chart).displayChart();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//


    public static CategoryChart createScoringForAllSectionsBarChart(File_f file){

        barChart chart = new barChart(600, 500, "Number of Scores", "Score Points", "Numbers");
        ArrayList<String> x = new ArrayList<>();
        x.add("1");
        x.add("2");
        x.add("3");
        x.add("4");
        x.add("5");


        int numberOfOne=0;
        int numberOfTwo=0;
        int numberOfThree=0;
        int numberOfFour=0;
        int numberofFive=0;

        for(int i =0; i<7;i++){


            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
                numberOfOne += (int)file.Sections[i].subsections.get(j).one;
                numberOfTwo += (int)file.Sections[i].subsections.get(j).two;
                numberOfThree += (int)file.Sections[i].subsections.get(j).three;
                numberOfFour += (int)file.Sections[i].subsections.get(j).four;
                numberofFive += (int)file.Sections[i].subsections.get(j).five;


            }

        }

        ArrayList<Double> y = new ArrayList<>();
        y.add((double) numberOfOne);
        y.add((double) numberOfTwo);
        y.add((double) numberOfThree);
        y.add((double) numberOfFour);
        y.add((double) numberofFive);


        chart.addElements("Quantities",x,y);


        return chart.getChart();

    }

    public static void printScoringForAllSectionsBarChart(File_f file){
        CategoryChart chart = createScoringForAllSectionsBarChart(file);
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }









    public static CategoryChart createSectionOrtalamalariBarChart(File_f file){

        barChart chart = new barChart(970, 500, "Section Ortalamalari", "Sections", "Averages");
        ArrayList<String> x = new ArrayList<>();
        x.add("Flipped");
        x.add("Course");
        x.add("Instructor");
        x.add("Lab");
        x.add("Teching Ass.");
        x.add("Overall Evalution");
        x.add("Learning OutComes");

        double[] sectionAverages = new double[7];
        double sectionAverage=0;

        for(int i =0; i<7;i++){
            int counter=0;
            sectionAverage=0;
            double total=0;
            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
                counter++;
                total = total + file.Sections[i].subsections.get(j).average;
                sectionAverage = total/counter;
            }
            sectionAverages[i]=sectionAverage;
        }

        ArrayList<Double> y = new ArrayList<>();
        y.add((double) sectionAverages[0]);
        y.add((double) sectionAverages[1]);
        y.add((double) sectionAverages[2]);
        y.add((double) sectionAverages[3]);
        y.add((double) sectionAverages[4]);
        y.add((double) sectionAverages[5]);
        y.add((double) sectionAverages[6]);


        chart.addElements("Average",x,y);


        return chart.getChart();

    }

    public static void printSectionOrtalamalariBarChart(File_f file){
        CategoryChart chart = createSectionOrtalamalariBarChart(file);
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // #############################################################PieChart################################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//    // #####################################################################################################################################
//
//    public static PieChart createScoringForAllSectionsPieChart(File_f file){
//
//        int numberOfOne=0;
//        int numberOfTwo=0;
//        int numberOfThree=0;
//        int numberOfFour=0;
//        int numberofFive=0;
//
//        for(int i =0; i<7;i++){
//
//
//            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
//                numberOfOne += (int)file.Sections[i].subsections.get(j).one;
//                numberOfTwo += (int)file.Sections[i].subsections.get(j).two;
//                numberOfThree += (int)file.Sections[i].subsections.get(j).three;
//                numberOfFour += (int)file.Sections[i].subsections.get(j).four;
//                numberofFive += (int)file.Sections[i].subsections.get(j).five;
//
//
//            }
//
//        }
//
//        pieChart chart1 = new pieChart(500,450,"Scroring Averages");
//        chart1.addSingleElement("1",numberOfOne);
//        chart1.addSingleElement("2", numberOfTwo);
//        chart1.addSingleElement("3",numberOfThree);
//        chart1.addSingleElement("4", numberOfFour);
//        chart1.addSingleElement("5", numberofFive);
//
//        return chart1.getChart();
//    }
//
//    public static void printScroingForAllSectionsPieChart(File_f file){
//        PieChart chart = createScoringForAllSectionsPieChart(file);
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//
//            @Override
//            public void run() {
//
//                // Create and set up the window.
//                JFrame frame = new JFrame("Charts");
//                frame.setLayout(new BorderLayout());
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                // chart
//                JPanel chartPanel = new XChartPanel<PieChart>(chart);
//                frame.add(chartPanel, BorderLayout.CENTER);
//
//
//                frame.pack();
//                frame.setVisible(true);
//            }
//        });
//    }
//
    public static PieChart createSectionOrtalamariPieChart(File_f file){

        double[] sectionAverages = new double[7];
        double sectionAverage=0;

        for(int i =0; i<7;i++){
            int counter=0;
            sectionAverage=0;
            double total=0;
            for(int j = 0; j<file.Sections[i].subsections.size(); j++){
                counter++;
                total = total + file.Sections[i].subsections.get(j).average;
                sectionAverage = total/counter;
            }
            sectionAverages[i]=sectionAverage;
        }

        pieChart chart1 = new pieChart(500,450,"Section Averages");
        chart1.addSingleElement("Flipped Classroom",sectionAverages[0]);
        chart1.addSingleElement("Course", sectionAverages[1]);
        chart1.addSingleElement("Instructor",sectionAverages[2]);
        chart1.addSingleElement("Labs", sectionAverages[3]);
        chart1.addSingleElement("Teaching Assistant", sectionAverages[4]);
        chart1.addSingleElement("Overall Evaluation", sectionAverages[5]);
        chart1.addSingleElement("Course Learning Outcomes", sectionAverages[6]);
        return chart1.getChart();
    }

    public static void printSectionOrtalamalariPieChart(File_f file){
        PieChart chart = createSectionOrtalamariPieChart(file);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Create and set up the window.
                JFrame frame = new JFrame("Charts");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // chart
                JPanel chartPanel = new XChartPanel<PieChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);


                frame.pack();
                frame.setVisible(true);
            }
        });
    }




    public static PieChart createCevaplamaOraniPieChart(File_f file){
        pieChart chart1 = new pieChart(400,250,"Cevaplama Oranı");
        chart1.addSingleElement("Cevaplayan Öğrenci",file.getCevapAdedi());
        chart1.addSingleElement("Cevaplamayan Öğrenci", file.getÖğrenciSayısı());
        return chart1.getChart();
    }

    public static void printCevaplamaOraniPieChart(File_f file){
        PieChart chart = createCevaplamaOraniPieChart(file);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                // Create and set up the window.
                JFrame frame = new JFrame("Charts");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // chart
                JPanel chartPanel = new XChartPanel<PieChart>(chart);
                frame.add(chartPanel, BorderLayout.CENTER);


                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
