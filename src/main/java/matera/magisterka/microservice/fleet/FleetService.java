package matera.magisterka.microservice.fleet;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;
    private final RabbitTemplate rabbitTemplate;
    @Transactional
    public FleetVehicle registerVehicle(String vin, String plateNumber) {
        FleetVehicle vehicle = fleetRepository.save(
                FleetVehicle.builder().vin(vin).plateNumber(plateNumber).status(VehicleStatus.AVAILABLE).build()
        );

        // Naturalna komunikacja między mikroserwisami
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                "fleet.vehicle.created",
                new VehicleCreatedEvent(vehicle.getId(), vehicle.getVin(), vehicle.getPlateNumber())
        );

        return vehicle;
    }

    @Transactional(readOnly = true)
    public FleetVehicle getVehicle(Long id) {
        return fleetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found: " + id));
    }
}