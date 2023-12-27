package com.example.todo.domain.todos.model

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "TODOS")
class Todos(

    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "day")
    var days: Date,

    @Column(name = "nickname")
    var nickname: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}