package com.example.proggramingtechnologytests;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label currentFile;
    @FXML
    private Button filePicker;
    @FXML
    private Button buttonSend;
    @FXML
    private TextField emailText;
    BufferedImage bufferedImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void sendMessage(ActionEvent actionEvent) throws IOException {
        //ImageIO.write(bufferedImage,"png",new File("result.png"));
        ImageIO.write(bufferedImage,"png",new File("result"));

        String host = "smtp.gmail.com";
        final String senderAddress = "kursprojecttask5fantokin@gmail.com";
        final String senderPassword = "qqpytrfmzcjpaycn";
        final String toAddress = emailText.getText();
        if (toAddress.isEmpty()){
            return;
        }
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", senderAddress);
        props.put("mail.smtp.password", senderPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage mine = new MimeMessage(session);
        try {
            mine.setFrom(new InternetAddress(senderAddress));
            mine.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAddress));
            mine.setSubject("Форматирование");
            mine.setText("Исправлено");
            mine.setContent(new File("result.png"),".png");

            //send mail
            Transport transport = session.getTransport("smtp");
            transport.connect(host, senderAddress, senderPassword);
            transport.sendMessage(mine, mine.getAllRecipients());
            transport.close();
            //sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
    public void fileChose(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Jpeg", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        currentFile.setText("Выбранный файл: " + selectedFile.getName());
        bufferedImage = ImageIO.read(selectedFile);
    }
}