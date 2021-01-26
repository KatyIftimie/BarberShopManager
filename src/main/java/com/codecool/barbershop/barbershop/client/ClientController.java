package com.codecool.barbershop.barbershop.client;

import com.codecool.barbershop.barbershop.client.request.ClientRequest;
import com.codecool.barbershop.barbershop.client.request.ClientSearchAutocompleteReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private  ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }


    @GetMapping("{clientId}")
    public ClientModel clientProfile(@PathVariable("clientId") long clientId) throws Exception {
        return clientService.getClientById(clientId);
    }

    @PostMapping()
    public ResponseEntity<String> addClient(@RequestBody ClientRequest request) {
        ClientModel client = clientService.addClient(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNo());
        return new ResponseEntity<>(Long.toString(client.getClientId()), HttpStatus.CREATED);
    }

    @PutMapping("{clientId}")
    public ResponseEntity<ClientModel> updateClientById(@PathVariable("clientId") long clientId,
                                                        @RequestBody ClientModel clientToUpdate) {
        ClientModel client = clientService.updateClientById(clientId, clientToUpdate);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("{clientId}")
    public void deleteClientByID(@PathVariable("clientId") Long clientId) throws Exception {
        clientService.deleteClient(clientId);
    }

    @GetMapping("search-client")
    public List<ClientSearchAutocompleteReq> searchClientWithAutocomplete(){
        return clientService.searchClientWithAutocomplete();
    }
    @GetMapping("total-clients")
    public int totalClients(){
        return clientService.getTotalClients();
    }


}
