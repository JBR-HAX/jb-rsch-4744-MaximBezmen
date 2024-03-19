package org.jetbrains.assignment.service;

import org.jetbrains.assignment.dto.MovementDTO;
import org.jetbrains.assignment.dto.PositionDTO;
import org.jetbrains.assignment.entity.Robot;
import org.jetbrains.assignment.repository.RobotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotService {

    private final RobotRepository repository;

    public RobotService(RobotRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<PositionDTO> saveNewLocations(final List<MovementDTO> movements) {
        List<PositionDTO> positionDTOS = new ArrayList<>();
        PositionDTO currentPositionDTO = new PositionDTO();
        positionDTOS.add(currentPositionDTO);

        for (MovementDTO movement : movements) {
            PositionDTO newPositionDTO = new PositionDTO();

            switch (movement.getDirection().toUpperCase()) {
                case "EAST":
                    newPositionDTO.setX(currentPositionDTO.getX() + movement.getSteps());
                    newPositionDTO.setY(currentPositionDTO.getY());
                    break;
                case "WEST":
                    newPositionDTO.setX(currentPositionDTO.getX() - movement.getSteps());
                    newPositionDTO.setY(currentPositionDTO.getY());
                    break;
                case "NORTH":
                    newPositionDTO.setY(currentPositionDTO.getY() + movement.getSteps());
                    newPositionDTO.setX(currentPositionDTO.getX());
                    break;
                case "SOUTH":
                    newPositionDTO.setY(currentPositionDTO.getY() - movement.getSteps());
                    newPositionDTO.setX(currentPositionDTO.getX());
                    break;
            }

            positionDTOS.add(newPositionDTO);
            currentPositionDTO = newPositionDTO;
        }

        Robot robot = new Robot();
        robot.setMovements(movements);
        robot.setPositions(positionDTOS);

        repository.save(robot);
        return positionDTOS;
    }


    @Transactional
    public List<MovementDTO> saveMoves(final List<PositionDTO> locations) {
        List<MovementDTO> moves = new ArrayList<>();

        PositionDTO currentLocation = locations.get(0);

        for (int i = 1; i < locations.size(); i++) {
            PositionDTO nextLocation = locations.get(i);

            if (currentLocation.getX() != nextLocation.getX()) {
                MovementDTO move = new MovementDTO();
                move.setDirection(currentLocation.getX() < nextLocation.getX() ? "EAST" : "WEST");
                move.setSteps(Math.abs(nextLocation.getX() - currentLocation.getX()));
                moves.add(move);
            }

            if (currentLocation.getY() != nextLocation.getY()) {
                MovementDTO move = new MovementDTO();
                move.setDirection(currentLocation.getY() < nextLocation.getY() ? "NORTH" : "SOUTH");
                move.setSteps(Math.abs(nextLocation.getY() - currentLocation.getY()));
                moves.add(move);
            }

            currentLocation = nextLocation;
        }

        Robot robot = new Robot();
        robot.setMovements(moves);
        robot.setPositions(locations);

        repository.save(robot);
        return moves;
    }
}
