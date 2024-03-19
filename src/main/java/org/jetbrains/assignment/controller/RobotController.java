package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.dto.MovementDTO;
import org.jetbrains.assignment.dto.PositionDTO;
import org.jetbrains.assignment.service.RobotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RobotController {

    private final RobotService service;

    public RobotController(RobotService service) {
        this.service = service;
    }

    @PostMapping("/locations")
    public ResponseEntity<List<PositionDTO>> locations(@RequestBody List<MovementDTO> movements) {
        List<PositionDTO> positions = service.saveNewLocations(movements);
        return ResponseEntity.ok(positions);
    }


    @PostMapping("/moves")
    public List<MovementDTO> getMoves(@RequestBody List<PositionDTO> locations) {
        List<MovementDTO> moves = service.saveMoves(locations);

        return moves;
    }
}
