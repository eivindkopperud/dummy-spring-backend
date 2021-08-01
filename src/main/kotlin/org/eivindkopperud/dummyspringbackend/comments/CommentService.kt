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

    fun update(userId: String, commentId: Long, content: String) {
        val user = userRepository.findById(userId).get()
        val comment = commentRepository.findById(commentId).get()

        if (authService.isAuthor(user, comment)) {
            comment.content = content
            commentRepository.save(comment)
        }
    }

    fun delete(userId: String, commentId: Long) {
        val user = userRepository.findById(userId).get()
        val comment = commentRepository.findById(commentId).get()

        if (authService.isAuthor(user, comment)) {
            commentRepository.deleteById(commentId)
        }
    }

    fun toggleLike(userId: String, commentId: Long) {
        val user = userRepository.findById(userId).get()
        val comment = commentRepository.findById(commentId).get()

        if (!authService.isAuthor(user, comment)) {
            comment.toggleLike(user)
            commentRepository.save(comment)
        }
    }
}