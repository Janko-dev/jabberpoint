package Controls;

import Communication.Command;
import Communication.Observable;
import Communication.Observer;
import Communication.OpenXMLCommand;
import Domain.Services.DomainServices;
import Infrastructure.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuController extends MenuBar implements Observable {

    private ArrayList<Observer> observers;
    private DomainServices services;
    private JFrame parent;

    public MenuController(DomainServices services, JFrame parent){
        this.services = services;
        this.parent = parent;
        observers = new ArrayList<>();
        Menu fileMenu = new Menu("file");
        addOpenMenuItem(fileMenu);

        add(fileMenu);
    }

    private void addOpenMenuItem(Menu menu){
        MenuItem menuItem = mkMenuItem("open");
        menuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (chooser.showOpenDialog(parent) == JFileChooser.OPEN_DIALOG) {
                File file = chooser.getSelectedFile();
//                System.out.println(FileUtils.getExtension(file));
                switch (FileUtils.getExtension(file)){
                    case "xml":
                        notifyObservers(new OpenXMLCommand(services, file.toString()));
                        break;
                    default: break;
                }
            }
        });
        menu.add(menuItem);
    }



    private MenuItem mkMenuItem(String name) {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Command command) {
        for (Observer ob : observers){
            ob.update(command);
        }
    }
}
