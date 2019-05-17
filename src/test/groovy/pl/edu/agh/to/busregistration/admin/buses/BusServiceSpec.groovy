package pl.edu.agh.to.busregistration.admin.buses

import spock.lang.Specification

class BusServiceSpec extends Specification {

    BusRepository busRepository = Mock()
    def busService = new BusService(busRepository)

    def "should return all buses from repository when searching all buses"() {
        given: "buses stored in database"
        def bus1 = new Bus()
        bus1.id = 1
        def bus2 = new Bus()
        bus2.id = 2

        and: "repository returns two buses from database"
        busRepository.findAll() >> [bus1, bus2]

        when: "searching all buses"
        def buses = busService.getAllBuses()

        then: "collection with two buses is returned"
        buses.size() == 2
        buses.get(0).id == 1
        buses.get(1).id == 2
    }

    def "should persist bus in database when saving bus"() {
        given: "bus to store"
        def bus = new Bus()
        bus.id = 1

        when: "saving bus"
        busService.saveBus(bus)

        then: "bus is saved in database"
        1 * busRepository.save(_)
    }

    def "should throw exception when searching bus and bus id is invalid"() {
        given: "lower than zero bus id"
        def busId = -5

        when: "searching bus with given id"
        busService.findById(busId)

        then: "illegal argument exception is thrown"
        IllegalArgumentException e = thrown()
        e.message == "Bus id must be number greater than zero"
    }

    def "should return bus stored in database when searching bus"() {
        given: "valid bus id"
        def busId = 3

        and: "bus stored in database"
        def bus = new Bus()
        bus.id = busId

        and: "bus repository returns optional with bus from database"
        busRepository.findById(_) >> Optional.of(bus)

        when: "searching bus with given id"
        def storedBus = busService.findById(busId)

        then: "optional containing stored bus is returned"
        storedBus.isPresent()
        storedBus.get().id == busId
    }

    def "should remove bus from database when removing bus"() {
        when: "removing bus"
        busService.deleteBus(1)

        then: "bus is removed from database"
        1 * busRepository.deleteById(_)
    }
}
