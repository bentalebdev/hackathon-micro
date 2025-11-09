package controller;

import model.ClientModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/gateway/clients")
public class ClientGatewayController {
    private final WebClient.Builder webClientBuilder;
    public ClientGatewayController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    // ======================
    // GET all clients
    // ======================
    @GetMapping
    public Mono<List<ClientModel>> getAllClients() {
        return webClientBuilder.build()
                .get()
                .uri("http://client-service/api/clients")
                .retrieve()
                .bodyToFlux(ClientModel.class)
                .collectList();
    }
    // ======================
    // GET client by id
    // ======================
    @GetMapping("/{id}")
    public Mono<ClientModel> getClientById(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri("http://client-service/api/clients/{id}", id)
                .retrieve()
                .bodyToMono(ClientModel.class);
    }
    // ======================
    // POST create client
    // ======================
    @PostMapping
    public Mono<ClientModel> createClient(@RequestBody ClientModel client) {
        return webClientBuilder.build()
                .post()
                .uri("http://client-service/api/clients")
                .bodyValue(client)
                .retrieve()
                .bodyToMono(ClientModel.class);
    }
    // ======================
    // PUT update client
    // ======================
    @PutMapping("/{id}")
    public Mono<ClientModel> updateClient(@PathVariable Long id, @RequestBody
    ClientModel client) {
        return webClientBuilder.build()
                .put()
                .uri("http://client-service/api/clients/{id}", id)
                .bodyValue(client)
                .retrieve()
                .bodyToMono(ClientModel.class);
    }
    // ======================
    // DELETE client
    // ======================
    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable Long id) {
        return webClientBuilder.build()
                .delete()
                .uri("http://client-service/api/clients/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
