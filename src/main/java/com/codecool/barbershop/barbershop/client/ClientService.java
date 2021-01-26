package com.codecool.barbershop.barbershop.client;

import com.codecool.barbershop.barbershop.client.request.ClientSearchAutocompleteReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientModel addClient(String firstName, String lastName, String email, String phoneNo) {
        ClientModel newClient = new ClientModel();
        newClient.setFirstName(firstName);
        newClient.setLastName(lastName);
        newClient.setEmail(email);
        newClient.setPhoneNo(phoneNo);
        return clientRepository.save(newClient);
    }


    public ClientModel updateClientById(long clientId, ClientModel client) {
        ClientModel clientToUpdate = clientRepository.getByClientId(clientId);
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setPhoneNo(client.getPhoneNo());
        return clientRepository.save(clientToUpdate);

    }

    public void deleteClient(long clientId) throws Exception {
        ClientModel clientToDelete = getClientById(clientId);
        clientRepository.deleteById(clientToDelete.getClientId());
    }



    public ClientModel getClientById(long clientId) throws Exception {
        Optional<ClientModel> clientModel = clientRepository.findById(clientId);

        return clientModel.orElseThrow(() -> new Exception("Client not found id:" + clientId));
    }


    public List<ClientSearchAutocompleteReq> searchClientWithAutocomplete() {
        List<ClientSearchAutocompleteReq> clientList = new ArrayList<>();
        List<ClientModel> allClients = getAllClients();

        for (ClientModel client : allClients) {
            ClientSearchAutocompleteReq clientSearchAutocompleteReq = new ClientSearchAutocompleteReq();
            clientSearchAutocompleteReq.setId(client.getClientId());
            clientSearchAutocompleteReq.setFirstName(client.getFirstName());
            clientSearchAutocompleteReq.setPhoneNo(client.getPhoneNo());
            clientSearchAutocompleteReq.setFirstNameAndPhone(client.getFirstName()+" "+client.getPhoneNo());

            clientList.add(clientSearchAutocompleteReq);
        }
        return clientList;
    }
    public int getTotalClients() {
        return clientRepository.findAll().size();
    }
}
