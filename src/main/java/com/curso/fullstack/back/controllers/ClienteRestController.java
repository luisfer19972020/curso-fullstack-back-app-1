package com.curso.fullstack.back.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.curso.fullstack.back.models.entity.Cliente;
import com.curso.fullstack.back.models.entity.Region;
import com.curso.fullstack.back.models.services.IClienteService;
import com.curso.fullstack.back.models.services.IResponsesService;
import com.curso.fullstack.back.models.services.IUploadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IResponsesService responsesService;

    @Autowired
    private IUploadService uploadService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/clientes/page/{page}")
    public ResponseEntity<Page<Cliente>> getAllPaginated(@PathVariable(name = "page") Integer page) {
        return ResponseEntity.ok(clienteService.findAll(PageRequest.of(page, 4)));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {
        final Cliente cliente = this.clienteService.findById(id);
        return cliente == null
                ? responsesService.getNotFoundError("Cliente no encontrado")
                : responsesService.getStatusOk(cliente);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente clienteRequest, BindingResult result) {
        if (result.hasErrors()) {
            return responsesService.getBadRequest(
                    result.getFieldErrors().stream().map(e -> e.getField().concat(": ").concat(e.getDefaultMessage()))
                            .collect(Collectors.toList()));
        }
        try {
            return responsesService.getStatusCreated(this.clienteService.save(clienteRequest));
        } catch (Exception e) {
            return responsesService
                    .getInternalError("Error al actualizar el cliente debido a : ".concat(e.getMessage()));
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente clienteUpdated,
            BindingResult result) {
        if (result.hasErrors()) {
            return responsesService.getBadRequest(
                    result.getFieldErrors().stream().map(e -> e.getField().concat(": ").concat(e.getDefaultMessage()))
                            .collect(Collectors.toList()));
        }
        try {
            Cliente cliente = clienteService.findById(id);
            if (cliente == null) {
                return responsesService.getNotFoundError("Cliente no encontrado");
            } else {
                cliente.setNombre(clienteUpdated.getNombre());
                cliente.setApellido(clienteUpdated.getApellido());
                cliente.setEmail(clienteUpdated.getEmail());
                cliente.setRegion(clienteUpdated.getRegion());
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
            Cliente cliente = clienteService.findById(id);
            this.uploadService.delete(cliente.getFoto());
            clienteService.delete(id);
            return responsesService.getStatusOk(Map.of("message", "User deleted"));
        } catch (Exception e) {
            return responsesService.getInternalError("Error al elimnar el cliente debido a : ".concat(e.getMessage()));
        }
    }

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Cliente cliente = this.clienteService.findById(id);
        if (cliente == null) {
            return responsesService.getNotFoundError("Cliente no encontrado");
        }
        if (!archivo.isEmpty()) {
            String nuevoArchivo;
            try {
                nuevoArchivo = this.uploadService.copy(archivo);
            } catch (IOException e) {
                return responsesService
                        .getInternalError("Error cargar la imagen debido a : ".concat(e.getMessage()));
            }
            this.uploadService.delete(cliente.getFoto());
            cliente.setFoto(nuevoArchivo);
            clienteService.save(cliente);
        }
        return responsesService.getStatusCreated(cliente);
    }

    @GetMapping("/prueba/{busqueda}/{page}")
    public ResponseEntity<Page<Cliente>> getPrueba(@PathVariable(name = "busqueda") String busqueda,
            @PathVariable(name = "page") Integer page) {
        return ResponseEntity.ok(clienteService.findAllPrueba(PageRequest.of(page, 10), busqueda));
    }

    @GetMapping("/clientes/regiones")
    public ResponseEntity<List<Region>> getAllRegions() {
        return ResponseEntity.ok(clienteService.getAllRegions());
    }

}
