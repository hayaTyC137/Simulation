import Action.PopulateSpawnWorldAction;
import Action.PopulationMakeMove;
import GameMap.Coordinates;
import Simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        Coordinates coordinates = new Coordinates(1,1);
        Simulation simulation = new Simulation(10,10);
        simulation.addInitAction(new PopulateSpawnWorldAction());
        simulation.addTurnAction(new PopulationMakeMove());
        simulation.startSimulation();
    }
}
