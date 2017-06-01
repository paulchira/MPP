package Client.gui;

import Client.ams.ControllerObserverAMS;
import Model.Bilet;
import Model.Zbor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.annotation.Resource;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Chira Paul on 3/18/2017.
 */
public class ControllerZbor implements Initializable {
    @FXML
    private TextField txt_destinatia;
    @FXML
    private TextField txt_data;
    @FXML
    private TextField txt_nume;
    @FXML
    private TextField txt_prenume;
    @FXML
    private TextField txt_adresa;
    @FXML
    private TextField txt_nrLoc;
    @FXML
    private TableView<Zbor> table;

    @FXML
    private TableView<Zbor> table2;

    private ControllerObserverAMS ctr;
    public ObservableList<Zbor> model;
    private int idAngajat;

    public void setIdAngajat(int idAngajat) {
        this.idAngajat = idAngajat;
    }
    public void setControllerObserverAMS(ControllerObserverAMS ctr) {
        this.model = FXCollections.observableArrayList(ctr.receiveAllZbor());
        table.setItems(model);
        this.ctr = ctr;
        onSelected();
        updateTable();
    }

    public void showErrorMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void onSelected() {
        //clearFields();
        table.getSelectionModel().selectedItemProperty().addListener(x -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
                Zbor z = (Zbor) selectionModel.getSelectedItem();
                if (z != null) {
                    txt_destinatia.setText(String.valueOf(z.getDest()));
                    String data = String.valueOf(z.getData());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    try {
                        //Date d1 = df.parse(data);
                        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                        txt_data.setText(date.format(z.getData()));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });
    }

    public void searchZbor() {
        try {
            String destinatia = txt_destinatia.getText();
            String data = txt_data.getText();
            this.model = FXCollections.observableArrayList(ctr.receiveSearchZbor(destinatia, data));
            table2.setItems(model);
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    public void buyTicket() {
        try {
            String nume = txt_nume.getText();
            String prenume = txt_prenume.getText();
            String adresa = txt_adresa.getText();
            int nrLoc = Integer.valueOf(txt_nrLoc.getText());
            Zbor z = new Zbor();
            if (table2.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = table2.getSelectionModel();
                z = (Zbor) selectionModel.getSelectedItem();
            } else if (table.getSelectionModel().getSelectedItem() != null) {
                TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
                z = (Zbor) selectionModel.getSelectedItem();
            } else {
                showErrorMessage("nu e selectat nimic");
                return;
            }
            if (ctr.foundClient(nume, prenume, adresa) == true) {
                int idClient = ctr.getIdClient(nume, prenume, adresa);
                Bilet b = new Bilet(100, idAngajat, idClient, z.getId(), nrLoc);
                int nr = z.getNrLoc() - nrLoc;
                if (nr == 0) {
                    z.setNrLoc(0);
                    ctr.update(z, z.getId());
                    //ctr.delete(z.getId());
                    //this.model = FXCollections.observableArrayList(ctr.receiveAllZbor());
                    //table.setItems(model);
                } else if (nr < 0) {
                    showErrorMessage("STOC PREA MIC");
                } else {
                    ctr.insertBilet(b);
                    z.setNrLoc(nr);
                    ctr.update(z, z.getId());
                    //this.model = FXCollections.observableArrayList( ctr.receiveAllZbor());
                    //table.setItems(model);
                }
            } else {
                ctr.insertClient(nume, prenume, adresa);
                int idClient = ctr.getIdClient(nume, prenume, adresa);
                Bilet b = new Bilet(100, idAngajat, idClient, z.getId(), nrLoc);
                int nr = z.getNrLoc() - nrLoc;
                if (nr == 0) {
                    z.setNrLoc(0);
                    ctr.update(z, z.getId());
                    //ctr.delete(z.getNrLoc());
                    //this.model = FXCollections.observableArrayList(ctr.receiveAllZbor());
                    //table.setItems(model);
                } else if (nr < 0) {
                    showErrorMessage("STOC PREA MIC");
                } else {
                    ctr.insertBilet(b);
                    z.setNrLoc(nr);
                    ctr.update(z, z.getNrLoc());
                    //this.model = FXCollections.observableArrayList(ctr.receiveAllZbor());
                    //table.setItems(model);
                }
            }
        } catch (Exception ex) {
            showErrorMessage(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateTable() {
        ctr.sold.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (ctr.sold.get() == true) {
                    //model.setAll(ctr.receiveAllZbor());
                    ExecutorService executor = Executors.newFixedThreadPool(10000);
                    executor.execute(() -> {
                        try {
                            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                            model.setAll(ctr.receiveAllZbor());
                            ctr.sold.setValue(false);
                        } catch (Exception e) {
                            System.err.println("Error thread model " + e);
                        }
                    });
                    executor.shutdown();

                }

            }
        });
    }
}
