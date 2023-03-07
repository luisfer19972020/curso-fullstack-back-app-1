package com.curso.fullstack.back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.fullstack.back.models.entity.Cliente;
import com.curso.fullstack.back.models.services.IClienteService;
import com.curso.fullstack.back.models.services.IResponsesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IResponsesService responsesService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {
        final Cliente cliente = this.clienteService.findById(id);
        return cliente == null
                ? responsesService.getNotFoundError("Cliente no encontrado")
                : responsesService.getStatusOk(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@RequestBody Cliente clienteRequest) {
        try {
            return responsesService.getStatusCreated(this.clienteService.save(clienteRequest));
        } catch (Exception e) {
            return responsesService
                    .getInternalError("Error al actualizar el cliente debido a : ".concat(e.getMessage()));
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente clienteUpdated) {
        try {
            Cliente cliente = clienteService.findById(id);
            if (cliente == null) {
                return responsesService.getNotFoundError("Cliente no encontrado");
            } else {
                cliente.setNombre(clienteUpdated.getNombre());
                cliente.setApellido(clienteUpdated.getApellido());
                cliente.setEmail(clienteUpdated.getEmail());
                return responsesService.getStatusCreated(this.clienteService.save(cliente));
            }
        } catch (Exception e) {
            return responsesService
                    .getInternalError("Error al actualizar el cliente debido a : ".concat(e.getMessage()));
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        try {
            clienteService.delete(id);
            return responsesService.getStatusOk("User deleted");
        } catch (Exception e) {
            return responsesService.getInternalError("Error al elimnar el cliente debido a : ".concat(e.getMessage()));
        }
    }

}
