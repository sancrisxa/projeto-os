package com.sancrisxa.os.resources;

import com.sancrisxa.os.dtos.OSDTO;
import com.sancrisxa.os.service.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/os")
public class OsResource {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
        OSDTO oSdto = new OSDTO(this.osService.findById(id));

        return ResponseEntity.ok().body(oSdto);
    }
}
