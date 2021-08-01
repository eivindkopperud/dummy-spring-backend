package org.eivindkopperud.dummyspringbackend

import org.eivindkopperud.dummyspringbackend.users.User
import org.eivindkopperud.dummyspringbackend.users.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(val userService: UserService) {

    @PostMapping("/users")
    fun createUser(@RequestBody user: User) = userService.create(user)
}