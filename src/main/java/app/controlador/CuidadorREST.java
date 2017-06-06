/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controlador;

import app.modelo.entidades.Cuidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import app.persistencia.CuidadorDAO;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cuidadores")
public class CuidadorREST {

    private final CuidadorDAO cuidadorDAO;

    @Autowired
    public CuidadorREST(CuidadorDAO cuidadorDAO) {
        this.cuidadorDAO = cuidadorDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Cuidador> findAll() throws Exception {
//        CuidadorDAO cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.findAll();
    }

    @RequestMapping(value = "localidades/{id}", method = RequestMethod.GET)
    public List<Cuidador> findPorLocalidad(@PathVariable("id") Long id) throws Exception {
//        CuidadorDAO cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.findPorLocalidad(id.intValue());

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Cuidador find(@PathVariable("id") Long id) throws Exception {
//        CuidadorDAO cuidadorDAO = new CuidadorDAO();
        return cuidadorDAO.find(id);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
//        CuidadorDAO cuidadorDAO = new CuidadorDAO();
        cuidadorDAO.removeID(id);
        System.out.println("Eliminar " + id);
        //return cuidadorDAO.find(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Cuidador entity) throws Exception {
        System.out.println(entity);
        if (StringUtils.isEmpty(entity.getNombre())) return new ResponseEntity<>("El nombre es requerido",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        if (StringUtils.isEmpty(entity.getEmail())) return new ResponseEntity<>("El email es requerido",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        if (entity.getTelefono() == 0l) return new ResponseEntity<>("El telefono es requerido",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        if (entity.getListaImagenes()!= null) if (entity.getListaImagenes().size() > 4) return new ResponseEntity<>("El maximo de imagenes es 4",HttpStatus.UNSUPPORTED_MEDIA_TYPE);

//        CuidadorDAO cuidadorDAO = new CuidadorDAO();
        cuidadorDAO.create(entity);
        return new ResponseEntity<>(entity,HttpStatus.OK);
    }

}
