package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ParkingLot implements Serializable {
    private ArrayList<Client> clientsPL;
    private ArrayList<Vehicle> vehiclesPL;
  

    public ParkingLot(){
        clientsPL = new ArrayList<>();
        vehiclesPL = new ArrayList<>();
    }
    
    
    
    /**
    Add a client to the parking lot <br>
    <b> pre: </b>Needs verify if the clients is already in the parking lot<br>
    <b> post: </b>The client will be created and added to the parking lot<br>
    @param name A string who have the new client�s name
    @param id A string who have the new client�s ID
    @param cellNumber A string who have the new client�s cellNumber
    @return true or false
    */
    public boolean addClient(String name, String id, String cellNumber) {
    	String temp=ClientVeryfier(name,id, cellNumber);
    	//A�adir excepci�n para verificar si el nombre est� repetido o si el id est� repetido
    	if(temp.contains("name")){
    		return false;
    	}
    	else if(temp.contains("id")) {
    		return false;
    	}
    	else {
    		Client aux=new Client(name, id, cellNumber);
    		clientsPL.add(aux);
    		return true;
    	}
    }
    
    
    /**
    Disable a specific client in the parking lot <br>
    <b> pre: </b>Needs verify if the client already exists and if he does not own a car in the parking lot<br>
    <b> post: </b>The specific client will be disabled<br>
    @param id A string who have the client�s id
    @return true or false
    */
    public boolean disableClient(String id) {
    	Client aux=searchByID(id);
    	if(aux!=null) {
    		aux.setState(false);
    		return true;
    	}
    	return false;
    }
    
    
    /**
    Update a specific client's name <br>
    <b> pre: </b>Needs verify if the client already exists and if his new name is not used by other client<br>
    <b> post: </b>The specific client will be updated his name<br>
    @param newName A string who have the client�s newName
    @param pastName A string who have the client�s pastName
    @return true or false
    */
    public boolean updateClientName(String newName, String pastName) {
    	if((searchByName(newName)==null)&&(searchByName(pastName)!=null)) {
    		searchByName(pastName).setName(newName);
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    
    /**
    Verify if the new client has already exist<br>
    <b> pre: </b>Needs ask to the user the necessary parameters to create a client<br>
    <b> post: </b>The user verify if the client�s name or ID is duplicated<br>
    @param name A string who have the new client�s name
    @param id A string who have the new client�s ID
    @param cellNumber A string who have the new client�s cellNumber
    @return temp A string
    */
    public String ClientVeryfier(String name, String id, String cellNumber) {
    	String temp="";
	    	if(clientsPL!=null) {
	    		Client aux=new Client(name, id, cellNumber);
		    	for(int i=0;i<clientsPL.size();i++) {		    		
		    		if (aux.getName().equalsIgnoreCase(clientsPL.get(i).getName())) {
		    			temp+="name";
		    		}
		    		if(aux.getId().equalsIgnoreCase(clientsPL.get(i).getId())) {
		    			temp+="id";
		    		}
		    	}
	    	}
	    	 return temp;
    	}
    
    
    /**
    Search a specific client by It�s ID <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param id A string who have the specific client�s id
    @return Object client or null
    */
    public Client searchByID(String id) {
    	if(clientsPL!=null) {
    		for(int i=0;i<clientsPL.size();i++) {
    			if(clientsPL.get(i).getId().equalsIgnoreCase(id)) {
    				return clientsPL.get(i);
    			}
    		}
    		return null;
    	}
		return null;
    	
    }
    
    
    /**
    Search a specific client by It�s name <br>
    <b> pre: </b>Needs the specific client were created<br>
    <b> post: </b>The specific client will be founded<br>
    @param name A string who have the specific client�s name
    @return Object client or null
    */
    public Client searchByName(String name) {
    	if(clientsPL!=null) {
    		for(int i=0;i<clientsPL.size();i++) {
    			if(clientsPL.get(i).getName().equalsIgnoreCase(name)) {
    				return clientsPL.get(i);
    			}
    		}
    		return null;
    	}
    	return null;
    }
   
}
