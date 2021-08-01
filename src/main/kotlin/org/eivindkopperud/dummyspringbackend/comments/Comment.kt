package org.eivindkopperud.dummyspringbackend.comments


import org.eivindkopperud.dummyspringbackend.posts.Post
import org.eivindkopperud.dummyspringbackend.users.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
class Comment(
        @Column
        var content: String,

        @ManyToOne
        val author: User,

        @ManyToOne
        @OnDelete(action = OnDeleteAction.CASCADE)
        val post: Post,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToMany
    val likes: MutableList<User> = mutableListOf()

    fun toggleLike(user: User) {
        if (likes.contains(user)) likes.remove(user) else likes.add(user)
    }
}