package fi.utu.tech.assignment4;

import java.util.ArrayList;
import java.util.List;

// Käytetään tehtässä 1 muokattua GradingTask-oliota
import fi.utu.tech.common.GradingTask;
// Allokointifunktiot implementoidaan TaskAllocator-luokassa
import fi.utu.tech.common.TaskAllocator;

import fi.utu.tech.common.Submission;
import fi.utu.tech.common.SubmissionGenerator;
import fi.utu.tech.common.SubmissionGenerator.Strategy;


public class App4 {
    public static void main(String[] args) {
        // Kopioi edellisen tehtävän ratkaisu tähän lähtökohdaksi
        // Otetaan funktion aloitusaika talteen suoritusajan laskemista varten
        long startTime = System.currentTimeMillis();

        // Generoidaan kasa esimerkkitehtäväpalautuksia
        List<Submission> ungradedSubmissions = SubmissionGenerator.generateSubmissions(21, 200, Strategy.STATIC);

        // Tulostetaan tiedot esimerkkipalautuksista ennen arviointia
        for (var ug : ungradedSubmissions) {
            System.out.println(ug);
        }

        // Luodaan uusi arviointitehtävä
        List<GradingTask> gradings = TaskAllocator.sloppyAllocator(ungradedSubmissions);

        Thread t1 = new Thread(gradings.get(0));
        Thread t2 = new Thread(gradings.get(1));
        t1.start();
        t2.start();
        try {
            t1.join(); t2.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        List<Submission> gradedSubmissions = new ArrayList<>();

        gradedSubmissions.addAll(gradings.get(0).getGradedSubmissions());
        gradedSubmissions.addAll(gradings.get(1).getGradedSubmissions());


        // Tulostetaan arvioidut palautukset
        System.out.println("------------ CUT HERE ------------");
        for (var gs : gradedSubmissions) {
            System.out.println(gs);
        }

        // Lasketaan funktion suoritusaika
        System.out.printf("Total time for grading: %d ms%n", System.currentTimeMillis() - startTime);
    }
}
