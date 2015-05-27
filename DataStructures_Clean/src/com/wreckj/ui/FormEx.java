package com.wreckj.ui;

import javax.swing.*;

/**
 * Created by jagsir on 27/04/15.
 */
public class FormEx {

    private JTextPane textPane1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JProgressBar progressBar1;

    public void writeInTextArea(String message){
        textArea1.append(message);
        textArea1.setRows(200);

    }
}
