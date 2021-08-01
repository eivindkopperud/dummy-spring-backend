package org.eivindkopperud.dummyspringbackend.posts

import org.eivindkopperud.dummyspringbackend.AuthService
import org.eivindkopperud.dummyspringbackend.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService @Autowired constructor(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository,
        private val authService: AuthService
        ) {

    fun list() = postRepository.findAll()

    fun create(userId: String, content: String) {
        val author = userRepository.findById(userId).get()
        val newPost = Post(content, author)

        postRepository.save(newPost)
    }

    fun update(userId: String, postId: Long, content: String) {
        val user = userRepository.findById(userId).get()
        val post = postRepository.findById(postId).get()

        if (authService.isAuthor(user, post)) {
            post.content = content
            postRepository.save(post)
        }
    }

    fun delete(userId: String, postId: Long) {
        val user = userRepository.findById(userId).get()
        val post = postRepository.findById(postId).get()

        if (authService.isAuthor(user, post)) {
            postRepository.deleteById(postId)
        }
    }

    fun toggleLike(userId: String, postId: Long) {
        val user = userRepository.findById(userId).get()
        val post = postRepository.findById(postId).get()

        if (!authService.isAuthor(user, post))
        post.toggleLike(user)
        postRepository.save(post)
    }
}