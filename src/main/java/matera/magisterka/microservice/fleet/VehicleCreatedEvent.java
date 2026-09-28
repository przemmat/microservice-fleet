package matera.magisterka.microservice.fleet;

import java.io.Serializable;

public record VehicleCreatedEvent(Long vehicleId, String vin, String plateNumber) implements Serializable {}