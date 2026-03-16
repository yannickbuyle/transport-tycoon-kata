fun calculateDeliveryTime(cargo: String): Int {
    val factoryQueue = cargo.map { it.toString() }.toMutableList()
    val portQueue = mutableListOf<String>()
    var delivered = 0
    val totalCargo = cargo.length

    val truck1 = Transport("truck1", "FACTORY")
    val truck2 = Transport("truck2", "FACTORY")
    val ship = Transport("ship", "PORT")

    var time = 0

    while (delivered < totalCargo) {
        assignCargoToTransport(truck1, factoryQueue)
        assignCargoToTransport(truck2, factoryQueue)
        assignCargoToShip(ship, portQueue)

        time++

        moveTransports(listOf(truck1, truck2, ship))

        delivered += handleArrivals(truck1, portQueue)
        delivered += handleArrivals(truck2, portQueue)
        delivered += handleShipArrival(ship)
    }

    return time
}

data class Transport(
    val id: String,
    var location: String,
    var cargo: String? = null,
    var destination: String? = null,
    var timeToDestination: Int = 0
)


fun assignCargoToTransport(transport: Transport, queue: MutableList<String>) {
    if (transport.location == "FACTORY" && transport.cargo == null && queue.isNotEmpty()) {
        val cargo = queue.removeAt(0)
        transport.cargo = cargo
        if (cargo == "B") {
            transport.destination = "B"
            transport.timeToDestination = 5
        } else {
            transport.destination = "PORT"
            transport.timeToDestination = 1
        }
    }
}

fun assignCargoToShip(ship: Transport, portQueue: MutableList<String>) {
    if (ship.location == "PORT" && ship.cargo == null && portQueue.isNotEmpty()) {
        val cargo = portQueue.removeAt(0)
        ship.cargo = cargo
        ship.destination = "A"
        ship.timeToDestination = 4
    }
}

fun moveTransports(transports: List<Transport>) {
    transports.forEach { transport ->
        if (transport.timeToDestination > 0) {
            transport.timeToDestination--
        }
    }
}

fun handleArrivals(transport: Transport, portQueue: MutableList<String>): Int {
    var delivered = 0

    if (transport.timeToDestination == 0 && transport.destination != null) {
        if (transport.cargo != null) {
            val cargo = transport.cargo!!

            if (transport.destination == "PORT") {
                portQueue.add(cargo)
                transport.location = "PORT"
                transport.cargo = null
                transport.destination = "FACTORY"
                transport.timeToDestination = 1
            } else if (transport.destination == "B") {
                delivered = 1
                transport.location = "B"
                transport.cargo = null
                transport.destination = "FACTORY"
                transport.timeToDestination = 5
            }
        } else if (transport.destination == "FACTORY") {
            transport.location = "FACTORY"
            transport.destination = null
        }
    }

    return delivered
}

fun handleShipArrival(ship: Transport): Int {
    var delivered = 0

    if (ship.timeToDestination == 0 && ship.cargo != null) {
        delivered = 1
        ship.location = "A"
        ship.cargo = null
        ship.destination = "PORT"
        ship.timeToDestination = 4
    } else if (ship.timeToDestination == 0 && ship.destination == "PORT") {
        ship.location = "PORT"
        ship.destination = null
    }

    return delivered
}
