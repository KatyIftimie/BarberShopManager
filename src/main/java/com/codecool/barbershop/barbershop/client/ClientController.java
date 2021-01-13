package com.codecool.barbershop.barbershop.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @Transactional
    @GetMapping("/clients")
    public List<ClientModel> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/total-clients")
    public int totalClients(){
        return clientService.getTotalClients();
    }

    @GetMapping("/profile/{clientId}")
    public ClientModel clientProfile(@PathVariable("clientId") long clientId){

        return clientService.getClient(clientId).orElse(null);
    }


    @PostMapping("/add-client")
    public ResponseEntity<String> addClient(@RequestBody ClientRequest request) {
        ClientModel client = clientService.addClient(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhoneNo());
        return new ResponseEntity<>(Long.toString(client.getClientId()), HttpStatus.CREATED);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<ClientModel> updateClientById(@PathVariable("clientId") long clientId,
                                                        @RequestBody ClientModel clientToUpdate) {
        ClientModel client = clientService.updateClientById(clientId, clientToUpdate);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{clientId}")
    public void deleteClientByID(@PathVariable("clientId") String clientId) {
        List<ClientModel> clients= clientService.getAllClients();
        for(ClientModel client : clients) {
            if(client.getClientId() == Long.valueOf(clientId)) {
                clientService.deleteClient(Long.valueOf(clientId));
            }
        }
    }

}
