/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muestreo;

/**
 *
 * @author Usuario 1
 */
public class Kernel {
    private final double[][] diferenciaPixelesGx = {{0.0, 0.0, 0.0}, {0.0, 1.0, -1.0}, {0.0, 0.0, 0.0}};
    private final double[][] diferenciaPixelesGy = {{0.0, -1.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private final double[][][] diferenciaPixeles = {this.diferenciaPixelesGx,this.diferenciaPixelesGy};
    // mascara de diferencia de pixeless separados
    private final double[][] diferenciaPixelesSeparadosGx = {{0.0, 0.0, 0.0}, {1.0, 0.0, -1.0}, {0.0, 0.0, 0.0}};
    private final double[][] diferenciaPixelesSeparadosGy = {{0.0, -1.0, 0.0}, {0.0, 0.0, 0.0}, {0.0, 1.0, 0.0}};
    private final double[][][] diferenciaPixelesSeparados = {this.diferenciaPixelesSeparadosGx, this.diferenciaPixelesSeparadosGy};
    // mascara de operador prewitt
    private final double[][] prewittGx = {{1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}, {1.0, 0.0, -1.0}};
    private final double[][] prewittGy = {{-1.0, -1.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 1.0, 1.0}};
    private final double[][][] prewitt = {this.prewittGx, this.prewittGy};
    // mascara de operador Sobel
    private final double[][] sobelGx = {{1.0, 0.0, -1.0}, {2.0, 0.0, -2.0}, {1.0, 0.0, -1.0}};
    private final double[][] sobelGy = {{-1.0, -2.0, -1.0}, {0.0, 0.0, 0.0}, {1.0, 2.0, 1.0}};
    private final double[][][] sobel = {this.sobelGx, this.sobelGy};
    // mascara dde operador Roberts
    private final double[][] robertsGx = {{0.0, 0.0, -1.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private final double[][] robertsGy = {{-1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 0.0}};
    private final double[][][] roberts = {this.robertsGx, this.robertsGy};
    // kirch
    private final double[][] kirsch1 = {{-3, -3, 5}, {-3, 0, 5}, {-3, -3, 5}};
    private final double[][] kirsch2 = {{-3, 5, 5}, {-3, 0, 5}, {-3, -3, -3}};
    private final double[][] kirsch3 = {{5, 5, 5}, {-3, 0, -3}, {-3, -3, -3}};
    private final double[][] kirsch4 = {{5, 5, -3}, {5, 0, -3}, {-3, -3, -3}};
    private final double[][] kirsch5 = {{5, -3, -3}, {5, 0, -3}, {5, -3, -3}};
    private final double[][] kirsch6 = {{-3, -3, -3}, {5, 0, -3}, {5, 5, -3}};
    private final double[][] kirsch7 = {{-3, -3, -3}, {-3, 0, -3}, {5, 5, 5}};
    private final double[][] kirsch8 = {{-3, -3, -3}, {-3, 0, 5}, {-3, 5, 5}};
    private final double[][][] kirsch = {kirsch1, kirsch2, kirsch3, kirsch4, kirsch5, kirsch6, kirsch7, kirsch8};
    // terminamos kirch
    // creamos mascara de Laplace
    // cesarimm
    private final double[][] laplaceAux = {{0.0, 1.0, 0.0}, {1.0, -4.0, 1.0}, {0.0, 1.0, 0.0}};
    private final double[][][] laplace = {this.laplaceAux};
    private final double[][][][] mascaras = {this.diferenciaPixeles, this.diferenciaPixelesSeparados, this.kirsch, this.laplace, this.prewitt, this.roberts, this.sobel};
    
    public double[][][] getDiferenciaPixeles() {
        return diferenciaPixeles;
    }

    public double[][][] getDiferenciaPixelesSeparados() {
        return diferenciaPixelesSeparados;
    }

    public double[][][] getPrewitt() {
        return prewitt;
    }

    public double[][][] getSobel() {
        return sobel;
    }

    public double[][][] getRoberts() {
        return roberts;
    }

    public double[][][] getKirsch() {
        return kirsch;
    }

    public double[][][] getLaplace() {
        return laplace;
    }

    public double[][][][] getMascaras() {
        return mascaras;
    }

    
}
