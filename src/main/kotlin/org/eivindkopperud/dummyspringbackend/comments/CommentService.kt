package org.eivindkopperud.dummyspringbackend.comments

import org.eivindkopperud.dummyspringbackend.AuthService
import org.eivindkopperud.dummyspringbackend.posts.PostRepository
import org.eivindkopperud.dummyspringbackend.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService @Autowired constructor(
        val commentRepository: CommentRepository,
        val postRepository: PostRepository,
        val userRepository: UserRepository,
        val authService: AuthService
) {
    fun retrieve(postId: Long): List<Comment> {
        return commentRepository.findAll().filter { it.post.id == postId }
    }

    fun create(userId: String, postId: Long, content: String, ) {
        val post = postRepository.findById(postId).get()
        val author = userRepository.findById(userId).get()
        val newComment = Comment(content, author, post)

        commentRepository.save(newComment)
    }

    fun update(userId: String, id: Long, content: String) {
        val user = userRepository.findById(userId).get()
        val commentToUpdate = commentRepository.findById(id).get()

        if (authService.isAuthor(user, commentToUpdate)) {
            commentToUpdate.content = content
            commentRepository.save(commentToUpdate)
            return
        }

        commentToUpdate.toggleLike(user)
        commentRepository.save(commentToUpdate)
    }

    fun delete(userId: String, id: Long) {
        val user = userRepository.findById(userId).get()
        val commentToDelete = commentRepository.findById(id).get()

        if (authService.isAuthor(user, commentToDelete)) {
            commentRepository.deleteById(id)
        }
    }
}