package com.codecool.barbershop.barbershop.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
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

    public void deleteClient(long clientId) {
        clientRepository.deleteClientModelByClientId(clientId);
    }

    public ClientModel updateClientById(long clientId,ClientModel client) {
        ClientModel clientToUpdate= clientRepository.getByClientId(clientId);
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setPhoneNo(client.getPhoneNo());
        return clientRepository.save(clientToUpdate);

    }

}
