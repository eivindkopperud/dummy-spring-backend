package org.eivindkopperud.dummyspringbackend.posts

import org.eivindkopperud.dummyspringbackend.users.User
import javax.persistence.*

@Entity
class Post(
        @Column
        var content: String,

        @ManyToOne
        val author: User,
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