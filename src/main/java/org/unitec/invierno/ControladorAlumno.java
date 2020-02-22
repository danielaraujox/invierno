
package org.unitec.invierno;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ControladorAlumno {
    
    @Autowired AlumnoRepositorio alumnRepo;
    

//Este metodo busca a todos los alumnos guardados 
//Si es que hay
    
    @GetMapping ("/alumno")
    public List<Alumno> buscarTodos () throws Exception{
        return alumnRepo.findAll();
    
    }

    // El metodo para guarda un nuevo alumno
    @PostMapping("/alumno")
    public Estatus guardarAlumno(@RequestBody String json) throws Exception{
    
        //¨Primero el cuerpo que llega lo deserializamos
        ObjectMapper maper = new ObjectMapper();
        
        //lo mapeamos
        //mapear: convertir objeto de una plataforma a otra plataforma 
        Alumno a=maper.readValue(json, Alumno.class);
        
        //Ahora si lo guardamos
        alumnRepo.save(a);
        
        //Bebemos informar a el response al cliente que mas adelante sera //android
        
        Estatus e=new Estatus();
        e.setMensaje("Alumno se guardo bien");
        e.setSuccess(true);
        return e;
    }
    
}
