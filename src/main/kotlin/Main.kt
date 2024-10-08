package org.gary

import kotlinx.coroutines.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    runBlocking {
        val bricksJob = launch(Dispatchers.Default) {
            perform("laying bricks")
        }

        bricksJob.cancel()  //cancel the bricksJob coroutine from outside
        launch(Dispatchers.IO) {
            val windows = order(Product.WINDOWS)
            throw Exception("Out of money!")
            bricksJob.join()
            withContext(Dispatchers.Default) {
                perform("installing ${windows.description}")
            }
        }

        launch(Dispatchers.IO) {
            val doors = order(Product.DOORS)
            bricksJob.join()
            withContext(Dispatchers.Default) {
                perform("installing ${doors.description}")
            }
        }
    }
    // Using withContext to switch the context of the coroutine
//    runBlocking {
//        val bricksJob = launch(Dispatchers.Default) {
//            perform("laying bricks")
//        }
//
//        bricksJob.cancel()  //cancel the bricksJob coroutine from outside
//        launch(Dispatchers.IO) {
//            val windows = order(Product.WINDOWS)
//            bricksJob.join()
//            withContext(Dispatchers.Default) {
//                perform("installing ${windows.description}")
//            }
//        }
//
//        launch(Dispatchers.IO) {
//            val doors = order(Product.DOORS)
//            bricksJob.join()
//            cancel()    //cancels the install Doors coroutine from inside
//            withContext(Dispatchers.Default) {
//                perform("installing ${doors.description}")
//            }
//        }
//        //cancel()    //cancels the coroutine for the entire scope
//    }

    //Parallel execution of tasks
    // Dispatchers.IO is used to perform blocking IO operations of CPU-heavy tasks(scale
    // up the number of threads to 64 threads by default)
    //
    //
    // Dispatchers.Default is used to perform IO-heavy tasks (thread pool containing
    // the number of threads equal to the number of CPU cores)
//    runBlocking {
//        val windows = async(Dispatchers.IO) { order(Product.WINDOWS) }
//        val doors = async(Dispatchers.IO) { order(Product.DOORS) }
//        launch(Dispatchers.Default) {
//            perform("laying bricks")
//            launch { perform("installing ${windows.await().description}") }
//            launch { perform("installing ${doors.await().description}") }
//        }
//    }

    //concurrent execution of tasks
//    runBlocking {
//        val windows = async { order(Product.WINDOWS) }
//        val doors = async { order(Product.DOORS) }
//        launch {
//            perform("laying bricks")
//            perform("installing ${windows.await().description}")
//            perform("installing ${doors.await().description}")
//        }
//    }

//    runBlocking{
//        runBlocking {
//            println(">>>>>>1<<<<<<<")
//            println(">>>>>>2<<<<<<<")
//        }
//        println(">>>>>>3<<<<<<<")
//        println(">>>>>>4<<<<<<<")
//        println(">>>>>>5<<<<<<<")
//    }   //output: 1, 2, 3, 4, 5

//    runBlocking{
//        launch {
//            println(">>>>>>1<<<<<<<")
//            println(">>>>>>2<<<<<<<")
//        }
//        println(">>>>>>3<<<<<<<")
//        println(">>>>>>4<<<<<<<")
//        println(">>>>>>5<<<<<<<")
//    }   //output: 3, 4, 5, 1, 2
//    runBlocking{
//        launch {
//            println(">>>>>>1<<<<<<<")
//            tagOut()
//            println(">>>>>>2<<<<<<<")
//            tagOut()
//        }
//        println(">>>>>>3<<<<<<<")
//        tagOut()
//        println(">>>>>>4<<<<<<<")
//        tagOut()
//        println(">>>>>>5<<<<<<<")
//    }   //output: 3, 1, 4, 2, 5

}

suspend fun perform(taskName: String) {
    println("STARTING TASK   >>> $taskName")
    repeat(5) {
        Thread.sleep(200)
        yield()
    }
    println("FINISHED TASK   >>> $taskName")
}

suspend fun tagOut() {
    println("TAGGING OUT")
    yield()
}