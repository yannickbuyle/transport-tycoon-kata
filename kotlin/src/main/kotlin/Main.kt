fun main(args: Array<String>) {
    val cargo = args.firstOrNull() ?: error("Usage: provide cargo string, e.g. AABABBAB")
    val result = calculateDeliveryTime(cargo)
    println(result)
}
