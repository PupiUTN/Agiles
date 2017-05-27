/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.persistencia;

import java.util.List;
import app.modelo.entidades.Localidad;
import app.modelo.entidades.Provincia;

/**
 *
 * @author jorge
 */
public class LocalidadDAO extends DAO<Localidad> {

    public LocalidadDAO() throws Exception {
        super(Localidad.class);
    }

    public void cargarDatos() throws Exception {
        if (count() == 0) {
            ProvinciaDAO provinciaDao = new ProvinciaDAO();
            List<Provincia> provincias = provinciaDao.findAll();

            Localidad cbaCapital = new Localidad();
            cbaCapital.setNombre("Cordoba");
            cbaCapital.setProvincia(provincias.get(0));
            create(cbaCapital);
            
            Localidad capitalFederal = new Localidad();
            capitalFederal.setNombre("Capital Federal");
            capitalFederal.setProvincia(provincias.get(1));
            create(capitalFederal);
        }
    }

}
