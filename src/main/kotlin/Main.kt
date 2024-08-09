package org.gary

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val windows = order(Product.WINDOWS)
    val doors = order(Product.DOORS)
    perform("laying bricks")
    perform("installing ${windows.description}")
    perform("installing ${doors.description}")
}

fun perform(taskName: String) {
    println("STARTING TASK   >>> $taskName")
    Thread.sleep(1_000)
    println("FINISHED TASK   >>> $taskName")
}