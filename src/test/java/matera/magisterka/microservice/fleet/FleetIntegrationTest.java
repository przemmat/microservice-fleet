package matera.magisterka.microservice.fleet;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class FleetIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private FleetService fleetService;

    @Autowired
    private FleetRepository fleetRepository;

    @MockitoBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void shouldRegisterAndRetrieveVehicle() {
        FleetVehicle vehicle = fleetService.registerVehicle("1C9TESTVIN0000001", "PO-12345");

        FleetVehicle found = fleetService.getVehicle(vehicle.getId());
        assertThat(found.getVin()).isEqualTo("1C9TESTVIN0000001");
        assertThat(found.getStatus()).isEqualTo(VehicleStatus.AVAILABLE);
    }
}