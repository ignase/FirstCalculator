package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora {
    public static void main(String[] args) {
        MarcoCalculadora mimarco = new MarcoCalculadora();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mimarco.setVisible(true);
    }
}

class MarcoCalculadora extends JFrame{

    public MarcoCalculadora(){
        setTitle("Calculadora");
        setBounds(500, 300, 450, 300);
        PanelCalculadora milamina = new PanelCalculadora();
        add(milamina);


    }
}

class PanelCalculadora extends JPanel{

    private JPanel numeracion;
    private JButton pantalla;
    private boolean comienzo;
    private String ultimaOperacion="";
    private double resultado;
    private double ultimoValor;

    public PanelCalculadora(){
        comienzo=true;
        setLayout(new BorderLayout());
        pantalla = new JButton("0"); //Mejor usar un JTextArea
        pantalla.setEnabled(false);
        add(pantalla, BorderLayout.NORTH);
        numeracion = new JPanel();
        numeracion.setLayout(new GridLayout(4, 4));

        InsertarNumero insertar = new InsertarNumero();
        Operacion realizarOperacion = new Operacion();

        ponerBoton("7", insertar);
        ponerBoton("8", insertar);
        ponerBoton("9", insertar);
        ponerBotonOperaciones("x", realizarOperacion);
        ponerBoton("4", insertar);
        ponerBoton("5", insertar);
        ponerBoton("6", insertar);
        ponerBotonOperaciones("-", realizarOperacion);
        ponerBoton("1", insertar);
        ponerBoton("2", insertar);
        ponerBoton("3", insertar);
        ponerBotonOperaciones("+", realizarOperacion);
        ponerBoton("0", insertar);
        ponerBoton(",", insertar);
        ponerBotonOperaciones("=", realizarOperacion);
        ponerBotonOperaciones("/", realizarOperacion);

        add(numeracion, BorderLayout.CENTER);
    }

    private void ponerBoton(String textoBoton, InsertarNumero oyente){

        JButton boton = new JButton(textoBoton);
        boton.addActionListener(oyente);
        numeracion.add(boton);
    }
    private void ponerBotonOperaciones(String textoBoton, Operacion oyente){

        JButton boton = new JButton(textoBoton);
        boton.addActionListener(oyente);
        numeracion.add(boton);
    }

    private class InsertarNumero implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String entrada = e.getActionCommand();

            if (comienzo){
                pantalla.setText(entrada);
                comienzo=false;
            }else{
                pantalla.setText(pantalla.getText()+entrada);
            }
            ultimoValor=Double.parseDouble(pantalla.getText());
        }
    }

    private class Operacion implements ActionListener{

        private String ultimaOperacion;
        private int contador;

        @Override
        public void actionPerformed(ActionEvent e) {

            String operacionPulsada = e.getActionCommand();
            if (operacionPulsada.equals("+")) {

                resultado+=ultimoValor;

                pantalla.setText("" + resultado);

                ultimaOperacion="+";

            }else if(operacionPulsada.equals("-")) {

                double valor=Double.parseDouble(pantalla.getText());

                if(contador==0) resultado=valor;

                else { resultado-=ultimoValor;}

                pantalla.setText("" + resultado);

                ultimaOperacion="-";

                contador++;


            }else if(operacionPulsada.equals("x")) {

                double valor=Double.parseDouble(pantalla.getText());

                if(contador==0) resultado=valor*1;

                else { resultado*=ultimoValor;}

                pantalla.setText("" + resultado);

                ultimaOperacion="x";

                contador++;
            }

            else if(operacionPulsada.equals("/")) {

                double valor=Double.parseDouble(pantalla.getText());

                if(contador==0) resultado=valor;

                else { resultado/=ultimoValor;}

                pantalla.setText("" + resultado);

                ultimaOperacion="/";

                contador++;
            }
            else {

                if(ultimaOperacion.equals("+")) resultado+=ultimoValor;
                if(ultimaOperacion.equals("-")) resultado-=ultimoValor; contador=0;
                if(ultimaOperacion.equals("x")) resultado*=ultimoValor; contador=0;
                if(ultimaOperacion.equals("/")) resultado/=ultimoValor; contador=0;

                pantalla.setText("" + resultado);

                ultimoValor=0;
            }
            comienzo=true;
        }
    }
}
