package tp1;

import tests.Tester;

import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenue au premier labo de INF2010!");
        LetterPlacer letterPlacer = new LetterPlacer();
        /*letterPlacer.placeNext('C');
        letterPlacer.placeNext('a');
        letterPlacer.placeNextln('V');
        letterPlacer.placeNext('a');
        letterPlacer.placeNextln('B');
        letterPlacer.placeNext('i');
        letterPlacer.placeNext('e');
        letterPlacer.placeNext('n');
        letterPlacer.placeNextln('A');
        letterPlacer.placeNext('l');
        letterPlacer.placeNext('l');
        letterPlacer.placeNext('e');
        letterPlacer.placeNext('r');*/
        letterPlacer.placeNext('Z');
        letterPlacer.placeNext('Y');
        File imgFile = letterPlacer.saveImage("image", false);
        Desktop dt = Desktop.getDesktop();
        dt.open(imgFile);
    }
}