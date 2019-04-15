package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent ;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.*;

/**
 * Created by Mohamed Essam on 3/16/2019.
 */

public class DB_Connection {
    private Connection connection;
    private Statement  statement;
    private ResultSet  resultSet;

    public DB_Connection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/clinic","root","");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkData(String query) throws SQLException {
        try {
           resultSet = statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return resultSet.isBeforeFirst();
    }

    public boolean insertData(String InserQuery, String SelectQuery){
        try {
            resultSet = statement.executeQuery(SelectQuery);

            if (!resultSet.next())
            statement.executeUpdate(InserQuery);
            else return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void getData(String query){
        try {
            resultSet = statement.executeQuery(query);
            System.out.println("records");
            while (resultSet.next()){
                String fname = resultSet.getString("fname");
                System.out.println(fname);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteDate(String query){
        try {
             statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String query){
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<patients> getpatient(String query){
        ObservableList<patients>patientss = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String fname   = resultSet.getString("fname");
                String lname   = resultSet.getString("lname");
                String id      =  resultSet.getString("id");
                String mobile  = resultSet.getString("mobile");
                String address = resultSet.getString("address");
                String date  = resultSet.getString("date");
                String time = resultSet.getString("time");
                patientss.add(new patients(fname+" "+lname,id,mobile,address,date,time));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return patientss;
    }

    public void deletePatients(TableView tableView){
        patients patients = (sample.patients) tableView.getSelectionModel().getSelectedItem();
        ObservableList<patients>patientsSelected, allPatients;
        allPatients = tableView.getItems();
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            patientsSelected = tableView.getSelectionModel().getSelectedItems();
            System.out.println(patients.getId());
            deleteDate("delete from patients where id = '"+patients.getId()+"'");
            patientsSelected.forEach(allPatients::remove);
        }
    }

    public  void updatePatient(TableView tableView,TableColumn.CellEditEvent<patients,String>updatePatientCell){
        patients patients = (sample.patients) tableView.getSelectionModel().getSelectedItem();
        //patients.setName(updatePatientCell.getNewValue());

      }
    }