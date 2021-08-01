package org.eivindkopperud.dummyspringbackend.users

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User {
    @Id
    val username: String = ""

    @Column
    val displayName: String = ""
}