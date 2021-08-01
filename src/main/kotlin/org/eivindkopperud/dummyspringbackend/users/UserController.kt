package org.eivindkopperud.dummyspringbackend.users

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @GetMapping
    fun list() = userService.list()

    @GetMapping("/{username}/posts")
    fun listPosts(@PathVariable username: String) = userService.listPosts(username)
}