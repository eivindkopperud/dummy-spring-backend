package org.eivindkopperud.dummyspringbackend.users

import org.eivindkopperud.dummyspringbackend.posts.Post
import org.eivindkopperud.dummyspringbackend.posts.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
        val userRepository: UserRepository,
        val postRepository: PostRepository
) {
    fun list() = userRepository.findAll()

    fun listPosts(userId: String): List<Post>{
        return postRepository.findAll().filter { it.author.username == userId }
    }

    fun create(user: User) {
        userRepository.save(user)
    }
}
