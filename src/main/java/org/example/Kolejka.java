package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kolejka {

    // ogólne -----------------------------------------
    private double lambda;
    private double mju; //μ ogólna zdolność obsługi
    private double rho; //ρ ogólny współczynnik wykorzystania systemu
    private int m;
    private int N;
    private double c1;
    private double c2;
    // END ogólne -------------------------------------


    // Które wynikają z ogólnych ----------------------
    private double p_0; // prawdopodobieństwo że kolejka jest pusta
    private List<Double> listaPrawdopodopienstw;

    // END Które wynikają z ogólnych -------------------

    public Kolejka() {}

    // konstruktor bez funkcji celu
    public Kolejka(double lambda, double mju, int m, int N) {
        this.lambda = lambda;
        this.mju = mju;
        this.m = m;
        this.N = N;

        this.rho = obliczRho();
        this.p_0 = obliczP_0();
        this.listaPrawdopodopienstw = obliczPrawdopodobienstwa();
    }

    // Konstruktor do funkcji celu
    public Kolejka(double lambda, double mju, double c1, double c2) {
        this.lambda = lambda;
        this.mju = mju;
        this.c1 = c1;
        this.c2 = c2;
        // TODO: 12.06.2023 do wykminienia
    }

    private double obliczP_0() {
        // TODO: 12.06.2023 do zrobienia
        return 0;
    }

    private List<Double> obliczPrawdopodobienstwa() {
        List<Double> listaPrawdopodobienstwToReturn = new ArrayList<>();
        // dla przedziału: 1 <= k <= m - 1 -----------------------------
        for (int k = 1; k <=  m - 1; k++) {
            double prawdopodobienstwo_k, licznik, mianownik;
            licznik = Math.pow(rho, k) * p_0;
            mianownik = silnia(k);
            prawdopodobienstwo_k = licznik / mianownik;
            // TODO: 12.06.2023 można tutaj zaokrąglić wynik przed dodaniem
            listaPrawdopodobienstwToReturn.add(prawdopodobienstwo_k);
        }

        // dla przedziału: m <= j <= m + N -----------------------------
        for (int j = m; j <=  m + N - 1; j++) {
            double prawdopodobienstwo_j, licznik, mianownik;
            licznik = Math.pow(rho, j) * p_0;
            mianownik = Math.pow(m, j-m) * silnia(m);
            prawdopodobienstwo_j = licznik / mianownik;
            // TODO: 12.06.2023 można tutaj zaokrąglić wynik przed dodaniem
            listaPrawdopodobienstwToReturn.add(prawdopodobienstwo_j);
        }

        return listaPrawdopodobienstwToReturn;

    }

    private double obliczRho() {
        return this.lambda / this.mju;
    }

    private static int silnia(int x) {
        if (x == 0) return 1;
        int factorial = 1;

        for(int i = 2; i <= x; i++) {
            factorial *= i;
        }
        return factorial;
    }


}
