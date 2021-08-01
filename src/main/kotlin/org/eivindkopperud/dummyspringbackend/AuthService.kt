package org.eivindkopperud.dummyspringbackend

import org.eivindkopperud.dummyspringbackend.posts.Post
import org.eivindkopperud.dummyspringbackend.users.User
import org.eivindkopperud.dummyspringbackend.comments.Comment
import org.springframework.stereotype.Service

@Service
class AuthService {
    fun isAuthor(user: User, comment: Comment): Boolean {
        return user == comment.author
    }

    fun isAuthor(user: User, post: Post): Boolean {
        return user == post.author
    }
}
