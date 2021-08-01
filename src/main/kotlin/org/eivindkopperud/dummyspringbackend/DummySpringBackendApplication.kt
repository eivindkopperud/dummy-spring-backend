package org.eivindkopperud.dummyspringbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DummySpringBackendApplication

fun main(args: Array<String>) {
	runApplication<DummySpringBackendApplication>(*args)
}
